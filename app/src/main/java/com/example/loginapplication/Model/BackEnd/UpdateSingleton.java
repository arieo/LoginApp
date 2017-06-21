package com.example.loginapplication.Model.BackEnd;

/**
 * Created by Arye on 21/06/2017.
 */

public class UpdateSingleton {

    private static UpdateSingleton us = new UpdateSingleton();
    private UpdateSingleton(){}

    public static UpdateSingleton getInstance(){
        return us;
    }


    private boolean updateFlag = false;

    public void setUpdate() {
        updateFlag = true;
    }

    public boolean isUpdatet() {
        if (updateFlag) {
            updateFlag = false;
            return true;
        }
        return false;
    }

}

