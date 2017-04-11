package com.example.wenik.myapplication2;

/**
 * Created by wenik on 03-Apr-17.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MySecondDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "AppDB1.db";

    //tables
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_CHILDREN = "Children";
    private static final String TABLE_ALERTS = "Alerts";
    private static final String TABLE_CONTACTS = "Contacts";

    //Users table columns
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_PASS = "password";
    private static final String COLUMN_FNAME = "fname";
    private static final String COLUMN_LNAME = "lname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_LOGGED = "logged";

    //Children table columns
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERPHONE = "userPhone";

    //Alerts table columns
    private static final String COLUMN_CHILDNAME = "childName";
    private static final String COLUMN_ALERT = "alert";
    private static final String COLUMN_VALIDTIL = "validTil";

    //Contacts table columns
   // private static final String COLUMN_USERPHONE = "childName";
    private static final String COLUMN_CONTACTPHONE = "contactPhone";
    private static final String COLUMN_CONTACTFNAME= "contactFname";
    private static final String COLUMN_CONTACTLNAME= "contactLname";

    public MySecondDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //create Users table
        String createUsers = "create table " + TABLE_USERS + " ( " +
                COLUMN_PHONE + " string primary key, " +
                COLUMN_PASS + " string , " +
                COLUMN_FNAME + " string , " +
                COLUMN_LNAME + " string , " +
                COLUMN_EMAIL + " string , " +
                COLUMN_LOGGED + " boolean  );";
        db.execSQL(createUsers);

        //Create Children table
        String createChildren = "create table " + TABLE_CHILDREN + " ( " +
                COLUMN_USERPHONE + " string , " +
                COLUMN_NAME + " string , " +
                "primary key ("+ COLUMN_USERPHONE +", "+ COLUMN_NAME + ") ) ;";
        db.execSQL(createChildren);


        //Create Alerts table
        String createAlerts = "create table " + TABLE_ALERTS + " ( " +
                COLUMN_USERPHONE + " string , " +
                COLUMN_CHILDNAME + " string , " +
                COLUMN_ALERT + " string ," +
                COLUMN_VALIDTIL + " string ," +
                "primary key ("+COLUMN_USERPHONE +", "+ COLUMN_CHILDNAME +", "+ COLUMN_ALERT + ") ) ;";
        db.execSQL(createAlerts);

        //Create Contacts table
        String createContacts = "create table " + TABLE_CONTACTS + " ( " +
                COLUMN_USERPHONE + " string , " +
                COLUMN_CONTACTPHONE + " string , " +
                COLUMN_CONTACTFNAME + " string ," +
                COLUMN_CONTACTLNAME + " string ," +
                "primary key ( "+COLUMN_USERPHONE +", "+ COLUMN_CONTACTPHONE+ ") ) ;";
        db.execSQL(createContacts);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USERS+";");
        db.execSQL("drop table if exists " + TABLE_CHILDREN+";");
        db.execSQL("drop table if exists " + TABLE_ALERTS+";");
        db.execSQL("drop table if exists " + TABLE_CONTACTS+";");
        onCreate(db);
    }



    //Add User
    public void addUser(User user)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(COLUMN_PHONE,user.getPhone());
            values.put(COLUMN_PASS,user.getPassword());
            values.put(COLUMN_FNAME,user.getFname());
            values.put(COLUMN_LNAME,user.getLname());
            values.put(COLUMN_EMAIL,user.getEmail());
            values.put(COLUMN_LOGGED,user.getLogged());
            db.insert(TABLE_USERS,null,values);
            db.close();
        }
        catch (Exception e)
        {
        }
    }

    public void addChild(Child child)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(COLUMN_USERPHONE,child.getUser());
            values.put(COLUMN_NAME,child.getName());
            db.insert(TABLE_CHILDREN,null,values);
            db.close();
        }
        catch (Exception e)
        {
        }
    }

    public void addAlert(Alert alert)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(COLUMN_USERPHONE,alert.getUser());
            values.put(COLUMN_CHILDNAME,alert.getChild());
            values.put(COLUMN_ALERT,alert.getAlert());
            values.put(COLUMN_VALIDTIL,alert.getValidTil());
            db.insert(TABLE_ALERTS,null,values);
            db.close();
        }
        catch (Exception e)
        {
        }
    }

    public void addContact(Contact contact)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USERPHONE,contact.getUserPhone());
        values.put(COLUMN_CONTACTPHONE,contact.getContactPhone());
        values.put(COLUMN_CONTACTFNAME,contact.getContactFname());
        values.put(COLUMN_CONTACTLNAME,contact.getContactLname());
        db.insert(TABLE_CONTACTS,null,values);
        db.close();
    }

    public String contactsToString()
    {
        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "select * from Contacts;";

        //Cursor
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex(COLUMN_USERPHONE))!=null && c.getString(c.getColumnIndex(COLUMN_CONTACTPHONE))!=null)
            {
                dbString+= c.getString(c.getColumnIndex(COLUMN_USERPHONE))+",";
                dbString+= c.getString(c.getColumnIndex(COLUMN_CONTACTPHONE))+",";
                dbString+= c.getString(c.getColumnIndex(COLUMN_CONTACTFNAME))+",";
                dbString+= c.getString(c.getColumnIndex(COLUMN_CONTACTLNAME))+"\n";
            }

            c.moveToNext();
        }
        dbString+="\n";
        c.close();
        db.close();
        return dbString;
    }

    public String childrenToString()
    {
        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "select * from Children;";

        //Cursor
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex(COLUMN_USERPHONE))!=null && c.getString(c.getColumnIndex(COLUMN_NAME))!=null)
            {
                dbString+= c.getString(c.getColumnIndex(COLUMN_USERPHONE))+",";
                dbString+= c.getString(c.getColumnIndex(COLUMN_NAME))+"\n";
            }

            c.moveToNext();
        }
        dbString+="\n";
        c.close();
        db.close();
        return dbString;

    }


    public String alertsToString()
    {
        String dbString1="";
        SQLiteDatabase db1 = getWritableDatabase();
        String query1 = "select * from Alerts;";

        //Cursor
        Cursor c1=db1.rawQuery(query1,null);
        c1.moveToFirst();
        while(!c1.isAfterLast())
        {
            if(c1.getString(c1.getColumnIndex(COLUMN_USERPHONE))!=null && c1.getString(c1.getColumnIndex(COLUMN_CHILDNAME))!=null && c1.getString(c1.getColumnIndex(COLUMN_ALERT))!=null)
            {
                dbString1+= c1.getString(c1.getColumnIndex(COLUMN_USERPHONE))+",";
                dbString1+= c1.getString(c1.getColumnIndex(COLUMN_CHILDNAME))+",";
                dbString1+= c1.getString(c1.getColumnIndex(COLUMN_ALERT))+",";
                dbString1+= c1.getString(c1.getColumnIndex(COLUMN_VALIDTIL))+"\n";
            }
            c1.moveToNext();
        }
        dbString1+="\n";
        c1.close();
        db1.close();
        return dbString1;
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * from "+ TABLE_USERS ;

        //Cursor
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("phone"))!=null)
            {
                dbString+= c.getString(c.getColumnIndex("phone"))+",";
                dbString+= c.getString(c.getColumnIndex("password"))+",";
                dbString+= c.getString(c.getColumnIndex("fname"))+",";
                dbString+= c.getString(c.getColumnIndex("lname"))+",";
                dbString+= c.getString(c.getColumnIndex("email"))+",";
                dbString+= c.getString(c.getColumnIndex("logged"))+"\n";
            }

            c.moveToNext();
        }
        dbString+="\n";
        c.close();
        db.close();
        return dbString;
    }

    public String PassIsRight(String phone, String pass)
    {
        String up="";
        SQLiteDatabase db = getWritableDatabase();
        String userInfo = "Select * from Users where phone = "+phone;
        Cursor c=db.rawQuery(userInfo,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("phone"))!=null)
            {
                up+= c.getString(c.getColumnIndex("password"));
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return up;

    }

    public String returnFirstLoggedInUser()
    {
        String st="";
        SQLiteDatabase db = getWritableDatabase();
        String loggedUser = "Select * from Users where logged=1;";
        Cursor c=db.rawQuery(loggedUser,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("phone"))!=null)
            {
                st+= c.getString(c.getColumnIndex("phone"));
            }
        }
        c.close();
        db.close();
        return st;
    }

    public String getUserFirstName(String ph)
    {
        String st="";
        SQLiteDatabase db = getWritableDatabase();
        String user = "Select * from Users where phone = "+ph;
        Cursor c=db.rawQuery(user,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("phone"))!=null)
            {
                st+= c.getString(c.getColumnIndex("fname"));
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return st;
    }
}