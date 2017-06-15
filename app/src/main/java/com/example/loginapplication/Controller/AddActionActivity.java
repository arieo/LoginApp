package com.example.loginapplication.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

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
        addActionButton = (Button) findViewById(R.id.add_business_act_button);

    }
}