package com.example.wenik.myapplication2;

/**
 * Created by wenik on 01-Apr-17.
 */

public class Child {

    private String userPhone;
    private String name;

    public Child(String user, String name) {
        this.userPhone = user;
        this.name = name;
    }

    public String getUser() {
        return userPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getUserPhone() {
        return userPhone;
    }
}
