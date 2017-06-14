package com.example.loginapplication.Model.BackEnd;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;


import com.example.loginapplication.Model.DataSource.CPConstants;
import com.example.loginapplication.Model.DataSource.DBConstants;

import java.util.HashMap;

import static com.example.loginapplication.Model.DataSource.CPConstants.uriMatcher;
import static com.example.loginapplication.Model.DataSource.DBConstants.ACCOUNT_TABLE;
import static com.example.loginapplication.Model.DataSource.DBConstants.BUSINESS_ACTION_TABLE;
import static com.example.loginapplication.Model.DataSource.DBConstants.BUSINESS_TABLE;

/**
 * Created by Arye on 07/06/2017.
 */

public class BusinessAndActionProvider extends ContentProvider {


    DataBaseHelper dataBaseHelper = null;
    protected SQLiteDatabase db;
    private static HashMap<String, String> PROJECTION;


    @Override
    public boolean onCreate() {
        //-- Create DataBase Helper: (create database: DATABASE_NAME)
        Context context = getContext();
        dataBaseHelper = new DataBaseHelper(context);

        //-- Open DataBase Connection
        db = dataBaseHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch (uriMatcher.match(uri)) {
            case CPConstants.ALL_ACCOUNT:
                qb.setTables(ACCOUNT_TABLE);
                break;
/*
            case CPConstants.SINGLE_ACCOUNT:
                qb.setTables(ACCOUNT_TABLE);
                qb.appendWhere(DBConstants.NAME + "=" + selectionArgs[0] + " AND " +DBConstants.EMAIL + "="+  selectionArgs[2]);
                break;
*/
            case CPConstants.ALL_BUSINESS:
                qb.setTables(BUSINESS_TABLE);
                qb.setProjectionMap(PROJECTION);
                break;

            case CPConstants.SINGLE_BUSINESS:
                qb.setTables(BUSINESS_TABLE);
                qb.appendWhere(DBConstants._BUSI_ID + "=" + uri.getPathSegments().get(1));
                break;

            case CPConstants.ALL_BUSINESS_ACTION:
                qb.setTables(BUSINESS_ACTION_TABLE);
                qb.setProjectionMap(PROJECTION);
                break;

            case CPConstants.SINGLE_BUSINESS_ACTION:
                qb.setTables(BUSINESS_ACTION_TABLE);
                qb.appendWhere(DBConstants.BUSINESS_ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }

        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, null);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        long rowID = 0;
        Uri contentURI = null;

        switch (uriMatcher.match(uri)) {
            case CPConstants.ALL_ACCOUNT:
                rowID = db.insert(ACCOUNT_TABLE, "", values);
                contentURI = CPConstants.CONTENT_URI_ACCOUNT;
                break;



            case CPConstants.ALL_BUSINESS:
                rowID = db.insert(	BUSINESS_TABLE, "", values);
                contentURI = CPConstants.CONTENT_URI_BUSINESS;
                break;


            case CPConstants.ALL_BUSINESS_ACTION:
                rowID = db.insert(	BUSINESS_ACTION_TABLE, "", values);
                contentURI = CPConstants.CONTENT_URI_BUSI_ACTION;
                break;


            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }


        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(contentURI , rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        int count = 0;
        String id = uri.getPathSegments().get(1);

        switch (uriMatcher.match(uri)) {
            case CPConstants.ALL_ACCOUNT:
                count = db.delete(ACCOUNT_TABLE, selection, selectionArgs);
                break;

            case CPConstants.SINGLE_ACCOUNT:
                count = db.delete( ACCOUNT_TABLE, DBConstants._ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? "  AND (" + selection + ')' : ""), selectionArgs);
                break;

            case CPConstants.ALL_BUSINESS:
                count = db.delete(BUSINESS_TABLE, selection, selectionArgs);
                break;

            case CPConstants.SINGLE_BUSINESS:
                count = db.delete( BUSINESS_TABLE, DBConstants._BUSI_ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? "  AND (" + selection + ')' : ""), selectionArgs);
                break;

            case CPConstants.ALL_BUSINESS_ACTION:
                count = db.delete(BUSINESS_ACTION_TABLE, selection, selectionArgs);
                break;

            case CPConstants.SINGLE_BUSINESS_ACTION:
                count = db.delete( BUSINESS_ACTION_TABLE, DBConstants.BUSINESS_ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? "  AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("In Delete: Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all accounts records
             */
            case CPConstants.ALL_ACCOUNT:
                return "vnd.android.cursor.dir/vnd.example.account";
            /**
             * Get a particular account
             */
            case CPConstants.SINGLE_ACCOUNT:
                return "vnd.android.cursor.item/vnd.example.account";

            /**
             * Get all business records
             */
            case CPConstants.ALL_BUSINESS:
                return "vnd.android.cursor.dir/vnd.example.business";
            /**
             * Get a particular business
             */
            case CPConstants.SINGLE_BUSINESS:
                return "vnd.android.cursor.item/vnd.example.business";

            /**
             * Get all business action records
             */
            case CPConstants.ALL_BUSINESS_ACTION:
                return "vnd.android.cursor.dir/vnd.example.businessAction";
            /**
             * Get a particular business action
             */
            case CPConstants.SINGLE_BUSINESS_ACTION:
                return "vnd.android.cursor.item/vnd.example.businessAction";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        int count = 0;
        String id = uri.getPathSegments().get(1);

        switch (uriMatcher.match(uri)) {
            case CPConstants.ALL_ACCOUNT:
                count = db.update(ACCOUNT_TABLE,values , selection, selectionArgs);
                break;

            case CPConstants.SINGLE_ACCOUNT:
                count = db.update( ACCOUNT_TABLE, values, DBConstants._ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? "  AND (" + selection + ')' : ""), selectionArgs);
                break;

            case CPConstants.ALL_BUSINESS:
                count = db.update(BUSINESS_TABLE, values, selection, selectionArgs);
                break;

            case CPConstants.SINGLE_BUSINESS:
                count = db.update( BUSINESS_TABLE, values, DBConstants._BUSI_ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? "  AND (" + selection + ')' : ""), selectionArgs);
                break;

            case CPConstants.ALL_BUSINESS_ACTION:
                count = db.update(BUSINESS_ACTION_TABLE, values, selection, selectionArgs);
                break;

            case CPConstants.SINGLE_BUSINESS_ACTION:
                count = db.update( BUSINESS_ACTION_TABLE, values, DBConstants.BUSINESS_ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? "  AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("In Update: Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }


}
