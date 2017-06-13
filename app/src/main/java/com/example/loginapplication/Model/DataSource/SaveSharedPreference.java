package com.example.loginapplication.Model.DataSource;

import android.content.Context;
        import android.content.SharedPreferences;
        import android.preference.PreferenceManager;

/**
 * Created by Yakov Shechter on 30/05/2017.
 */

public class SaveSharedPreference
{
    static final String PREF_USER_NAME= "username";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        //Editor editor = getSharedPreferences(ctx).edit();
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
}

