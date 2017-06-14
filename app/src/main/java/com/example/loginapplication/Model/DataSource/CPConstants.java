package com.example.loginapplication.Model.DataSource;

import android.content.UriMatcher;
import android.net.Uri;
/**
 * Created by Arye on 07/06/2017.
 */

public class CPConstants {

    public static final String PROVIDER_NAME = "com.example.loginapplication.Model.DataSource.BusinessAndActionProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/" ;
    public static final String AccountUrl = URL + DBConstants.ACCOUNT_TABLE;
    public static final String BusinessActionUrl = URL + DBConstants.BUSINESS_ACTION_TABLE;
    public static final String BusinessUrl = URL + DBConstants.BUSINESS_TABLE;
    public static final Uri CONTENT_URI_ACCOUNT = Uri.parse(AccountUrl);
    public static final Uri CONTENT_URI_BUSI_ACTION = Uri.parse(BusinessActionUrl);
    public static final Uri CONTENT_URI_BUSINESS = Uri.parse(BusinessUrl);

    public static final  int ALL_ACCOUNT = 0;
    public static final int SINGLE_ACCOUNT = 1;
    public static final  int ALL_BUSINESS = 2;
    public static final int SINGLE_BUSINESS= 3;
    public static final  int ALL_BUSINESS_ACTION = 4;
    public static final int SINGLE_BUSINESS_ACTION = 5;

    public static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "account",  ALL_ACCOUNT);
        uriMatcher.addURI(PROVIDER_NAME, "account/#", SINGLE_ACCOUNT);
        uriMatcher.addURI(PROVIDER_NAME, "business",  ALL_BUSINESS);
        uriMatcher.addURI(PROVIDER_NAME, "business/#", SINGLE_BUSINESS);
        uriMatcher.addURI(PROVIDER_NAME, "businessAction",  ALL_BUSINESS_ACTION);
        uriMatcher.addURI(PROVIDER_NAME, "businessAction/#", SINGLE_BUSINESS_ACTION);
    }

}



