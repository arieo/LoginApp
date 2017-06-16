package com.example.loginapplication.Controller;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
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

public class AddActionActivity extends Activity {


    private static final String TAG = AddBusinessActivity.class.getSimpleName();
    private Button addActionButton;
    private EditText businessId;
    private EditText actionStart;
    private EditText actionEnd;
    private EditText actionType;
    private EditText actionPrice;
    private EditText actionState;
    private EditText actionDescription;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_busi_action);
        businessId = (EditText) findViewById(R.id.busi_act_id);
        actionStart = (EditText) findViewById(R.id.busi_act_start);
        actionEnd = (EditText) findViewById(R.id.busi_act_end);
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


        addActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!actionPrice.getText().toString().isEmpty()
                        && !businessId.getText().toString().isEmpty()
                        && !actionStart.getText().toString().isEmpty()
                        && !actionEnd.getText().toString().isEmpty()
                        && !actionType.getText().toString().isEmpty()
                        && !actionState.getText().toString().isEmpty()
                        && !actionDescription.getText().toString().isEmpty()
                        && checkIfBusinessExisted(businessId.getText().toString()))
                {
                    addNewBusiness(businessId.getText().toString(),
                            actionStart.getText().toString(),actionEnd.getText().toString(),
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
                                 final String actionStart, final String actionEnd, final String actionType ,
                                 final String actionState, final String actionDescription, final String actionPrice) {
        ContentValues values = new ContentValues();
        values.put(DBConstants.BUSINESS_ID, businessId);
        values.put(DBConstants.ACT_START, actionStart);
        values.put(DBConstants.ACT_STATE, actionType);
        values.put(DBConstants.ACT_END, actionEnd);
        values.put(DBConstants.ACT_STATE, actionState);
        values.put(DBConstants.ACT_DESCRIPTION, actionDescription);
        values.put(DBConstants.ACT_PRICE, actionPrice);

        Uri uri = getContentResolver().insert(CPConstants.CONTENT_URI_BUSI_ACTION, values);

        Toast.makeText(getApplicationContext(), "Action successfully added", Toast.LENGTH_LONG).show();
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
}