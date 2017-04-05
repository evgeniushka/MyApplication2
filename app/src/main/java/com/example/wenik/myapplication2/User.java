/**
 * Created by wenik on 27-Mar-17.
 */

package com.example.wenik.myapplication2;

public class User {

    private String phone;
    private String password;
    private String fname;
    private String lname;
    private String email;
    private Boolean logged;

    public User(String phone, String password, String fname, String lname, String email, Boolean logged) {
        this.phone = "0"+phone;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.logged = logged;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getLogged() {
        return logged;
    }


}
