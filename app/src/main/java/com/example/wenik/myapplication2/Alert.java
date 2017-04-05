package com.example.wenik.myapplication2;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by wenik on 01-Apr-17.
 */

public class Alert {

    private String user;
    private String child;
    private String alert;
    private String validTil;

    public Alert(String user, String child, String alert, String validTil) {
        this.user = user;
        this.child = child;
        this.alert = alert;
        this.validTil = validTil;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public void setValidTil(String validTil) {
        this.validTil = validTil;
    }

    public String getUser() {
        return user;
    }

    public String getChild() {
        return child;
    }


    public String getAlert() {
        return alert;
    }

    public String getValidTil() {
        return validTil;
    }
}
