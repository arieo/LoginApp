package com.example.loginapplication.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginapplication.Model.DataSource.CPConstants;
import com.example.loginapplication.Model.DataSource.DBConstants;
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

        addBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!busi_Id.toString().isEmpty()
                        && !busiEmail.toString().isEmpty()
                        && !busiWebSite.toString().isEmpty()
                        && !busiState.toString().isEmpty()
                        && !busiCity.toString().isEmpty()
                        && !busiAddress.toString().isEmpty()
                        && !busiPhone.toString().isEmpty())
                {
                    addNewBusiness(busi_Id.toString(),
                            busiEmail.toString(),busiWebSite.toString(),
                            busiState.toString(),busiCity.toString(),
                            busiAddress.toString(), busiPhone.toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please complete all the details", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private  void addNewBusiness(final String id,
                                 final String email, final String webSite, final String state ,
                                 final String city, final String address, final String phone)
    {
        ContentValues values = new ContentValues();
        values.put(DBConstants._BUSI_ID, id);
        values.put(DBConstants.BUSI_EMAIL, email);
        values.put(DBConstants.BUSI_WEBSITE, webSite);
        values.put(DBConstants.BUSI_STATE, state);
        values.put(DBConstants.BUSI_CITY, city);
        values.put(DBConstants.BUSI_ADDRESS, address);
        values.put(DBConstants.BUSI_PHONE, phone);

        Uri uri = getContentResolver().insert(CPConstants.CONTENT_URI_BUSINESS, values);

        Toast.makeText(getApplicationContext(), "Business successfully added", Toast.LENGTH_LONG).show();
    }
}
