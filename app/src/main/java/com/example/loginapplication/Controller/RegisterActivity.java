package com.example.loginapplication.Controller;

/**
 * Created by Arye on 09/06/2017.
 */


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginapplication.Model.BackEnd.SaveSharedPreference;
import com.example.loginapplication.Model.DataSource.CPConstants;
import com.example.loginapplication.Model.DataSource.DBConstants;
import com.example.loginapplication.R;

public class RegisterActivity extends Activity{

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputFullName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

        //check If User Already Register
        inputEmail.setOnFocusChangeListener( new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkIfUserAlreadyRegister(inputEmail.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "This email already in the system", Toast.LENGTH_LONG).show();
                }
            }


        });

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = inputFullName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty())
                {
                    registerUser(name, email, password);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // Move to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    // Save User (Tag, Name, Email, Password)
    private void registerUser(final String name,
                              final String email,
                              final String password)
    {



        // ERROR ERROR ERROR
            if ( checkIfUserAlreadyRegister(email))
            {
                Toast.makeText(getApplicationContext(), "User already exists, please login", Toast.LENGTH_LONG).show();
            }
            else
            {
                ContentValues values = new ContentValues();
                values.put(DBConstants.NAME, name);
                values.put(DBConstants.EMAIL, email);
                values.put(DBConstants.PASSWORD, password);
                Uri uri = getContentResolver().insert(CPConstants.CONTENT_URI_ACCOUNT, values);

                Toast.makeText(getApplicationContext(), "User successfully registered", Toast.LENGTH_LONG).show();

                // Move to Business page
                Intent intent = new Intent(
                        RegisterActivity.this,
                        BusinessActivity.class);
                startActivity(intent);
                finish();
            }
    }
    private boolean checkIfUserAlreadyRegister(String email){

        String[] arg = new String[]{ email};

        Cursor mCursor = getContentResolver().query(
                CPConstants.CONTENT_URI_ACCOUNT,
                null,
                DBConstants.EMAIL + "=?", arg,
                null);
        return ( mCursor.getCount()!= 0) ? true : false;
    }
}
