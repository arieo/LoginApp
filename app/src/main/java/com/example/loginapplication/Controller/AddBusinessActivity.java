package com.example.loginapplication.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginapplication.R;

/**
 * Created by Arye on 15/06/2017.
 */

public class AddBusinessActivity extends Activity {


    private static final String TAG = AddBusinessActivity.class.getSimpleName();
    private Button addBusinessButton;
    private EditText busi_Id;
    private EditText busiEmail;
    private EditText busiWebSite;
    private EditText busiState;
    private EditText busiCity;
    private EditText busiAddress;
    private EditText busiPhone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_busi_action);
        busi_Id = (EditText) findViewById(R.id.busi_id);
        busiEmail = (EditText) findViewById(R.id.busi_email);
        busiWebSite = (EditText) findViewById(R.id.busi_website);
        busiState = (EditText) findViewById(R.id.busi_state);
        busiCity = (EditText) findViewById(R.id.busi_city);
        busiAddress = (EditText) findViewById(R.id.busi_address);
        busiPhone = (EditText) findViewById(R.id.busi_phone);
        addBusinessButton = (Button) findViewById(R.id.add_business_button);
    }
}
