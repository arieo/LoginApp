package com.example.loginapplication.Model.BackEnd;

import android.app.Application;
import android.content.Intent;

import com.example.loginapplication.Model.BackEnd.MyService;

/**
 * Created by Arye on 28/06/2017.
 */

public class StartService extends Application {

    public StartService(){
        //Intent mIntent = new Intent(this.getBaseContext());

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

}
