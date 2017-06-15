package com.example.loginapplication.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginapplication.R;


public class BusinessActivity extends Activity {

    private Button bAddBusinessButton;
    private Button bAddBusinessActButton;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_business);

        bAddBusinessButton = (Button) findViewById(R.id.add_business_button);
        bAddBusinessActButton = (Button) findViewById(R.id.add_business_act_button);

        bAddBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
                return;
            }
        });
        bAddBusinessActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
    }
}
