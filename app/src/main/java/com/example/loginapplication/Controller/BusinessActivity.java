package com.example.loginapplication.Controller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.loginapplication.Model.BackEnd.MyService;
import com.example.loginapplication.Model.BackEnd.UpdateSingleton;
import com.example.loginapplication.R;


public class BusinessActivity extends Activity {

    private Button bAddBusinessButton;
    private Button bAddBusinessActButton;
    private Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        //runService();

        bAddBusinessButton = (Button) findViewById(R.id.add_business_button);
        bAddBusinessActButton = (Button) findViewById(R.id.add_business_act_button);
        signOut = (Button) findViewById(R.id.sign_out);

        bAddBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getApplicationContext(),
                            AddBusinessActivity.class);
                    startActivity(i);
                    finish();
                    return;
                } catch (Exception e) {
                    Log.d("EXCEPTION", e.toString());
                }
            }
        });
        bAddBusinessActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        AddActionActivity.class);
                startActivity(i);
                finish();
                return;
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

                Intent i = new Intent(
                        getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
                return;

            }
        });


        //Intent intent = new Intent("com.example.loginapplication.UPDATE");
        //sendBroadcast(intent);


    }

    private void runService() {
        UpdateSingleton updateSingleton = UpdateSingleton.getInstance();
        if(updateSingleton.isUpdatet() == false)
        {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
        }
/*
        ComponentName componentName = new ComponentName
                (
                        "com.example.mailo.myacadmyproject",
                        "com.example.mailo.myacadmyproject.model.backend.MyService"
                );

        Intent intent_2 = new Intent();
        intent_2.setComponent(componentName);
        startService(new Intent(intent_2));
*/
        //IllegalArgumentException --->  Service Intent must be explicit
        //   startService(new Intent("com.oshri.academy.SERVICE_UPDATE"));
    }
}
