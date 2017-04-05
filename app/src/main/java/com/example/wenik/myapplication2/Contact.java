package com.example.wenik.myapplication2;

/**
 * Created by wenik on 05-Apr-17.
 */

public class Contact {

    private String userPhone;
    private String contactPhone;
    private String contactFname;
    private String contactLname;


    public Contact(String userPhone, String contactPhone, String contactFname, String contactLname) {
        this.userPhone = userPhone;
        this.contactPhone = contactPhone;
        this.contactFname = contactFname;
        this.contactLname = contactLname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getContactFname() {
        return contactFname;
    }

    public String getContactLname() {
        return contactLname;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public void setContactFname(String contactFname) {
        this.contactFname = contactFname;
    }

    public void setContactLname(String contactLname) {
        this.contactLname = contactLname;
    }
}
