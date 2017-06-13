package com.example.loginapplication.Model.DataSource.BE;

/**
 * Created by יענקי שכטר on 30/05/2017.
 */

public class Business {

    private int busiId;
    private String busiPhone;
    private String busiName;
    private String busiEmail;
    private String busiWebSite;
    private String busiState;
    private String busiCity;
    private String busiAddress;


    public void setBusiAddress(String busiAddress) {
        this.busiAddress = busiAddress;
    }

    public String getBusiAddress() {
        return busiAddress;
    }

    public int getBusiId() {
        return busiId;
    }

    public void setBusiId(int busiId) {
        this.busiId = busiId;
    }

    public String getBusiPhone() {
        return busiPhone;
    }

    public void setBusiPhone(String busiPhone) {
        this.busiPhone = busiPhone;
    }

    public String getBusiName() {
        return busiName;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }

    public String getBusiEmail() {
        return busiEmail;
    }

    public void setBusiEmail(String busiEmail) {
        this.busiEmail = busiEmail;
    }

    public String getBusiWebSite() {
        return busiWebSite;
    }

    public void setBusiWebSite(String busiWebSite) {
        this.busiWebSite = busiWebSite;
    }

    public String getBusiState() {
        return busiState;
    }

    public void setBusiState(String busiState) {
        this.busiState = busiState;
    }

    public String getBusiCity() {
        return busiCity;
    }

    public void setBusiCity(String busiCity) {
        this.busiCity = busiCity;
    }

    public String getBusiStreet() {
        return busiStreet;
    }

    public void setBusiStreet(String busiStreet) {
        this.busiStreet = busiStreet;
    }

    private String busiStreet;

}
