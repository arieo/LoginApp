package com.example.loginapplication.Model.DataSource.BE;

/**
 * Created by Yakov Shechter on 30/05/2017.
 */

public class BusinessAction {

    private double actPrice;
    private String actStart;
    private String actEnd;
    private String actState;
    private String actType;
    private ActDescription actDescription;
    private int businessID;

    public ActDescription getActDescription() {
        return actDescription;
    }

    public void setActDescription(ActDescription actDescription) {
        this.actDescription = actDescription;
    }

    public int getBusinessID() {
        return businessID;
    }

    public void setBusinessID(int businessID) {
        this.businessID = businessID;
    }



    public void setActPrice(double actPrice) {
        this.actPrice = actPrice;
    }

    public double getActPrice() {
        return actPrice;
    }

    public String getActStart() {
        return actStart;
    }

    public void setActStart(String actStart) {
        this.actStart = actStart;
    }

    public String getActEnd() {
        return actEnd;
    }

    public void setActEnd(String actEnd) {
        this.actEnd = actEnd;
    }

    public String getActState() {
        return actState;
    }

    public void setActState(String actState) {
        this.actState = actState;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

}
