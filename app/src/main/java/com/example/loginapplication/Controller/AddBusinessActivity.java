package com.example.loginapplication.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
        try {
            setContentView(R.layout.activity_add_business);
            busi_Id = (EditText) findViewById(R.id.busi_id);
            busiEmail = (EditText) findViewById(R.id.busi_email);
            busiWebSite = (EditText) findViewById(R.id.busi_website);
            busiState = (EditText) findViewById(R.id.busi_state);
            busiCity = (EditText) findViewById(R.id.busi_city);
            busiAddress = (EditText) findViewById(R.id.busi_address);
            busiPhone = (EditText) findViewById(R.id.busi_phone);
            addBusinessButton = (Button) findViewById(R.id.btnAddBusiness);

            busi_Id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (checkIfBusinessExisted(busi_Id.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "The business already exist", Toast.LENGTH_LONG).show();
                    }
                }
            });

            addBusinessButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!busi_Id.getText().toString().isEmpty()
                            && !busiEmail.getText().toString().isEmpty()
                            && !busiWebSite.getText().toString().isEmpty()
                            && !busiState.getText().toString().isEmpty()
                            && !busiCity.getText().toString().isEmpty()
                            && !busiAddress.getText().toString().isEmpty()
                            && !busiPhone.getText().toString().isEmpty()
                            && !checkIfBusinessExisted(busi_Id.getText().toString()))
                    {
                        addNewBusiness(busi_Id.getText().toString(),
                                busiEmail.getText().toString(), busiWebSite.getText().toString(),
                                busiState.getText().toString(), busiCity.getText().toString(),
                                busiAddress.getText().toString(), busiPhone.getText().toString());
                    } else {
                        String msg = (checkIfBusinessExisted(busi_Id.getText().toString())) ? "The business already exist" : "Please complete all the details";
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Please complete all the details", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception e) {
            Log.d("EXCEPTION", e.toString());
        }

    }

    private void addNewBusiness(final String id,
                                final String email, final String webSite, final String state,
                                final String city, final String address, final String phone) {
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

    private boolean checkIfBusinessExisted(String id) {
        String[] arg = new String[]{id};

        Cursor mCursor = getContentResolver().query(
                CPConstants.CONTENT_URI_BUSINESS,
                null,
                DBConstants._BUSI_ID + "=?", arg,
                null);
        return (mCursor.getCount() != 0) ? true : false;
    }
}
