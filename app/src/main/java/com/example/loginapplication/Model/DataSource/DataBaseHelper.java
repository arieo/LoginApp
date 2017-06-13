package com.example.loginapplication.Model.DataSource;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.loginapplication.Model.DataSource.DBConstants.DATABASE_NAME;
import static com.example.loginapplication.Model.DataSource.DBConstants.DATABASE_VERSION;
import static com.example.loginapplication.Model.DataSource.DBConstants.ACCOUNT_TABLE;
import static com.example.loginapplication.Model.DataSource.DBConstants.BUSINESS_TABLE;
import static com.example.loginapplication.Model.DataSource.DBConstants.BUSINESS_ACTION_TABLE;
import static com.example.loginapplication.Model.DataSource.DBConstants.CREATE_BUSINESS_TABLE;
import static com.example.loginapplication.Model.DataSource.DBConstants.CREATE_BUSINESS_ACTION_TABLE;
import static com.example.loginapplication.Model.DataSource.DBConstants.CREATE_ACCOUNT_TABLE;
/**
 * Created by Yakov Shehter on 05/06/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNT_TABLE);
        db.execSQL(CREATE_BUSINESS_TABLE);
        db.execSQL(CREATE_BUSINESS_ACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  ACCOUNT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " +  BUSINESS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " +  BUSINESS_ACTION_TABLE);
        onCreate(db);
    }

}



