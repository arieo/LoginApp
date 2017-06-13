package com.example.loginapplication.Model.DataSource.BE;

/**
 * Created by יענקי שכטר on 30/05/2017.
 */

public class Account {

    private int id;
    private String name;
    private String email;
    private int password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }


}
