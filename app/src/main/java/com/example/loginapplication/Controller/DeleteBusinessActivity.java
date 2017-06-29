package com.example.loginapplication.Controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
 * Created by Arye on 29/06/2017.
 */

public class DeleteBusinessActivity extends Activity {
    private Button deleteButton;
    private EditText busiID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_busi);
        deleteButton = (Button) findViewById(R.id.delete_business_button);
        busiID =(EditText) findViewById(R.id.busi_id_delete);


        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            String msg;
            @Override
            public void onClick(View v) {

                if(busiID.getText().toString().isEmpty())
                {
                    msg = "Please enter business id";
                }
                else
                {
                    if(checkIfBusinessExisted(busiID.getText().toString()))
                    {
                        if(deleteBusiness(busiID.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(), "Business with id: " + busiID.getText().toString()+ "delete with all his action!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            msg = "Can not delete the busibess id: " + busiID.getText().toString();
                        }

                    }
                    else{
                        msg = "Please enter correct business ID";
                    }

                }
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
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

    private boolean deleteActionByForeignKey(String id)
    {
        String[] arg = new String[]{id};

        int count = getContentResolver().delete(
                CPConstants.CONTENT_URI_BUSI_ACTION,
                DBConstants.BUSINESS_ID + "=?", arg);
        return  (count != -1) ? true : false;
    }

    private boolean deleteBusiness(String id)
    {
        if(deleteActionByForeignKey(id)) {
            String[] arg = new String[]{id};

            int count = getContentResolver().delete(
                    CPConstants.CONTENT_URI_BUSINESS,
                    DBConstants._BUSI_ID + "=?", arg);
            return (count != 0) ? true : false;
        }
        else {
            return false;
        }
    }

}
