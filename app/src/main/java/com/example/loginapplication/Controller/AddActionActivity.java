package com.example.loginapplication.Controller;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.loginapplication.Model.DataSource.CPConstants;
import com.example.loginapplication.Model.DataSource.DBConstants;
import com.example.loginapplication.R;

import java.util.Locale;

/**
 * Created by Arye on 15/06/2017.
 */

public class AddActionActivity extends Activity implements View.OnClickListener{


    private static final String TAG = AddBusinessActivity.class.getSimpleName();
    private Button addActionButton;
    private EditText businessId;
    private EditText actionType;
    private EditText actionPrice;
    private EditText actionState;
    private EditText actionDescription;
    private EditText fromDateEtxt;
    private EditText toDateEtxt;
    private Spinner spinner;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;



    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),
                LoginActivity.class);
        startActivity(i);
        finish();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_busi_action);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();

        addActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!actionPrice.getText().toString().isEmpty()
                        && !businessId.getText().toString().isEmpty()
                        && !fromDateEtxt.getText().toString().isEmpty()
                        && !toDateEtxt.getText().toString().isEmpty()
                        && !actionType.getText().toString().isEmpty()
                        && !actionState.getText().toString().isEmpty()
                        && !actionDescription.getText().toString().isEmpty()
                        && checkIfBusinessExisted(businessId.getText().toString()))
                {
                    addNewBusiness(businessId.getText().toString(),
                            fromDateEtxt.getText().toString(),toDateEtxt.getText().toString(),
                            actionType.getText().toString(),actionState.getText().toString(),
                            actionDescription.getText().toString(), actionPrice.getText().toString());
                }
                else
                {
                   String msg = (!checkIfBusinessExisted(businessId.getText().toString())) ? "Please enter Correct business ID" :"Please complete all the details";
                    Toast.makeText(getApplicationContext(), msg , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private  void addNewBusiness(final String businessId,
                                 final String fromDateEtxt, final String toDateEtxt, final String actionType ,
                                 final String actionState, final String actionDescription, final String actionPrice) {
        ContentValues values = new ContentValues();
        values.put(DBConstants.BUSINESS_ID, businessId);
        values.put(DBConstants.ACT_START, fromDateEtxt);
        values.put(DBConstants.ACT_END, toDateEtxt);
        values.put(DBConstants.ACT_STATE, actionType);
        values.put(DBConstants.ACT_STATE, actionState);
        values.put(DBConstants.ACT_TYPE, actionType);
        values.put(DBConstants.ACT_DESCRIPTION, actionDescription);
        values.put(DBConstants.ACT_PRICE, actionPrice);

        Uri uri = getContentResolver().insert(CPConstants.CONTENT_URI_BUSI_ACTION, values);

        Toast.makeText(getApplicationContext(), "Action successfully added", Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(),
                BusinessActivity.class);
        startActivity(i);
        finish();
        return;
    }

    private boolean checkIfBusinessExisted(String id)
    {
        String[] arg = new String[]{id};

        Cursor mCursor = getContentResolver().query(
                CPConstants.CONTENT_URI_BUSINESS,
                null,
                DBConstants._BUSI_ID + "=?", arg,
                null);
       return  (mCursor.getCount() != 0) ? true : false;
    }


    // Testing



    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.busi_act_start);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.busi_act_ends);
        toDateEtxt.setInputType(InputType.TYPE_NULL);

        spinner = (Spinner) findViewById(R.id.busi_act_enum);
        setSpinnerMenu();

        businessId = (EditText) findViewById(R.id.busi_act_id);
        actionType = (EditText) findViewById(R.id.busi_act_type);
        actionPrice = (EditText) findViewById(R.id.busi_act_price);
        actionState = (EditText) findViewById(R.id.busi_act_state);
        actionDescription = (EditText) findViewById(R.id.busi_act_description);
        addActionButton = (Button) findViewById(R.id.btnAddBusinessAction);
        //Check if business existed
        businessId.setOnFocusChangeListener( new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!checkIfBusinessExisted(businessId.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Please enter Correct business ID", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setSpinnerMenu() {
        String[] array_spinner=new String[4];
        array_spinner[0]="Vacation Package At Hotel";
        array_spinner[1]="Travel Agency";
        array_spinner[2]="Entertainment Shows";
        array_spinner[3]="Airline Company";

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        spinner.setAdapter(adapter);

    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        java.util.Calendar newCalendar = java.util.Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                java.util.Calendar newDate = java.util.Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(java.util.Calendar.YEAR), newCalendar.get(java.util.Calendar.MONTH), newCalendar.get(java.util.Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                java.util.Calendar newDate = java.util.Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(java.util.Calendar.YEAR), newCalendar.get(java.util.Calendar.MONTH), newCalendar.get(java.util.Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if(view == toDateEtxt) {
            toDatePickerDialog.show();
        }
    }


}