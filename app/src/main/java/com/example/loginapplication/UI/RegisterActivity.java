package com.example.loginapplication.UI;

/**
 * Created by Arye on 09/06/2017.
 */


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginapplication.Model.DataSource.CPConstants;
import com.example.loginapplication.Model.DataSource.DBConstants;
import com.example.loginapplication.Model.DataSource.DataBaseHelper;
import com.example.loginapplication.R;

import java.util.HashMap;
import java.util.Map;

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

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);



        // SQLite database handler


        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = inputFullName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    registerUser(name, email, password);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final String name, final String email,
                              final String password) {

        String[] arg = new String[]{ email};
        Cursor mCursor = getContentResolver().query(CPConstants.CONTENT_URI_ACCOUNT  ,null, DBConstants.EMAIL + "=?", arg  , null );
            if (mCursor.getCount()!= 0)
            {
                Toast.makeText(getApplicationContext(), "The user already exists, please login", Toast.LENGTH_LONG).show();
            }
            else
            {
                ContentValues values = new ContentValues();
                values.put(DBConstants.NAME, name);
                values.put(DBConstants.EMAIL, email);
                values.put(DBConstants.PASSWORD, password);
                Uri uri = getContentResolver().insert(CPConstants.CONTENT_URI_ACCOUNT, values);

                Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();
            }


        Intent intent = new Intent(
                RegisterActivity.this,
                LoginActivity.class);
        startActivity(intent);
        finish();
    }



}
