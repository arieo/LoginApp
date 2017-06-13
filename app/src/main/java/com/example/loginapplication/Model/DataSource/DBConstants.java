package com.example.loginapplication.Model.DataSource;

/**
 * Created by Arye on 06/06/2017.
 */

public class DBConstants {

    public static final String DATABASE_NAME = "firstAppDB.db";
    public static final int DATABASE_VERSION = 1;

    //Account
    public static final String ACCOUNT_TABLE = "account";
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";


    public  static final String CREATE_ACCOUNT_TABLE =
            " CREATE TABLE " + ACCOUNT_TABLE +
                    " ( " +
                    ""  + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                    "," + NAME + " TEXT NOT NULL " +
                    "," + EMAIL + " TEXT NOT NULL " +
                    "," + PASSWORD + " TEXT " +
                    "," + "CONSTRAINT email_unique UNIQUE" + "(" + EMAIL + ")" +
                    " ) ";


    //Business
    public static final String BUSINESS_TABLE = "business";
    public static final String _BUSI_ID = "_busiId";
    public static final String BUSI_PHONE = "busiPhone";
    public static final String BUSI_EMAIL = "busiEmail";
    public static final String BUSI_WEBSITE = "busiWebSite";
    public static final String BUSI_STATE = "busiState";
    public static final String BUSI_CITY = "busiCity";
    public static final String BUSI_ADDRESS = "busiAddress";


    public  static final String CREATE_BUSINESS_TABLE =
            " CREATE TABLE " + BUSINESS_TABLE +
                    " ( " +
                    ""  + _BUSI_ID + " INTEGER NOT NULL PRIMARY KEY " +
                    "," + BUSI_PHONE + " TEXT NOT NULL " +
                    "," + BUSI_EMAIL + " TEXT NOT NULL" +
                    "," + BUSI_WEBSITE + " TEXT NOT NULL " +
                    "," + BUSI_STATE + " TEXT NOT NULL " +
                    "," + BUSI_CITY + " TEXT NOT NULL " +
                    "," + BUSI_ADDRESS + " TEXT NOT NULL " +
                    "," + "CONSTRAINT email_unique UNIQUE" + "(" + BUSI_EMAIL + ")" +
                    " ) ";


    // BusinessAction
    public static final String BUSINESS_ACTION_TABLE = "BusinessAction";
    public static final String ACT_PRICE = "actPrice";
    public static final String ACT_START = "actStart";
    public static final String ACT_END = "actEnd";
    public static final String ACT_STATE = "actState";
    public static final String ACT_TYPE = "actType";
    public static final String ACT_DESCRIPTION = "actDescription";
    public static final String BUSINESS_ID = "businessID";

    public  static final String CREATE_BUSINESS_ACTION_TABLE =
            " CREATE TABLE " + BUSINESS_ACTION_TABLE +
                    " ( " +
                    ""  + BUSINESS_ID + " INTEGER  NOT NULL" +
                    "," + ACT_START + " TEXT NOT NULL " +
                    "," + ACT_END + " TEXT NOT NULL " +
                    "," + ACT_TYPE + " TEXT NOT NULL " +
                    "," + ACT_PRICE + " TEXT NOT NULL " +
                    "," + ACT_STATE + " TEXT NOT NULL " +
                    "," + ACT_DESCRIPTION + " TEXT NOT NULL " +
                    "," + "FOREIGN KEY " + " ( " + BUSINESS_ID  +
                    ")" + " REFERENCES "  + BUSINESS_ACTION_TABLE +  " ( " + _BUSI_ID + " ) " +
                    " ) ";
}
