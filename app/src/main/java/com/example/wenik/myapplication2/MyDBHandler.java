package com.example.wenik.myapplication2; /**
 * Created by wenik on 27-Mar-17.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION=2;
    private static final String DATABSE_NAME = "DBMyApp2.db";

    //tables
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_CHILDREN = "Children";
    private static final String TABLE_ALERTS = "Alerts";
 //   private static final String TABLE_CONTACTS = "Contacts";

    //Users table columns
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_PASS = "password";
    private static final String COLUMN_FNAME= "fname";
    private static final String COLUMN_LNAME = "lname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_LOGGED = "logged";
/*
    //Children table columns
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USER = "user";

    //Alerts table columns
    private static final String COLUMN_CHILD = "child";
    private static final String COLUMN_ALERTARRAY= "alertArray";
/*
    //Contacts table columns
    private static final String COLUMN_CONTACT_PHONE= "conactPhone";
    private static final String COLUMN_CONTACT_FNAME= "conactFname";
    private static final String COLUMN_CONTACT_LNAME= "conactLname";
*/
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABSE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db = getWritableDatabase();

        //create Users table
        String createUsers= "create table "+ TABLE_USERS+ " ( "+
                COLUMN_PHONE+" string primary key, " +
                COLUMN_PASS+ " string , " +
                COLUMN_FNAME+ " string , " +
                COLUMN_LNAME+ " string , " +
                COLUMN_EMAIL+ " string , " +
                COLUMN_LOGGED+ " boolean  );";
                db.execSQL(createUsers);
/*
        //Create Children table
        String createChildren= "create table "+ TABLE_CHILDREN+ " ( "+
                COLUMN_USER+" string primary key, " +
                COLUMN_NAME+ " string primary key);";
        db.execSQL(createChildren);

        //Create Alerts table
        String createAlerts= "create table Alerts ( "+
                COLUMN_USER+" string primary key, " +
                COLUMN_CHILD+ " string primary key, "+
                COLUMN_ALERTARRAY+ "string) ;";
        db.execSQL(createAlerts);
/*
        //Create Contacts table
        String createContacts= "create table "+ TABLE_CONTACTS+ " ( "+
                COLUMN_USER+" string primary key, " +
                COLUMN_CONTACT_PHONE+ " string primary key, "+
                COLUMN_CONTACT_FNAME+ " string, " +
                COLUMN_CONTACT_LNAME+ " string) ;";
        db.execSQL(createContacts);*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USERS+";");
    //    db.execSQL("drop table if exists " + TABLE_CHILDREN+";");
      //  db.execSQL("drop table if exists " + "Alerts;");
  //      db.execSQL("drop table if exists " + TABLE_CONTACTS+";");
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
/*
    public void addChild(Child child)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(COLUMN_USER,child.getUser());
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

            values.put("user",alert.getUser());
            values.put("child",alert.getChild());
            values.put("alertArray",alert.getAlerts());
            db.insert("Alerts",null,values);
            db.close();

         //   return values.toString();
        }
        catch (Exception e)
        {
        }
   //     return "not working";
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
            if(c.getString(c.getColumnIndex("user"))!=null)
            {
                dbString+= c.getString(c.getColumnIndex("user"))+",";
                dbString+= c.getString(c.getColumnIndex("name"))+"\n";
            }

            c.moveToNext();
        }
        dbString+="\n";
        c.close();
        db.close();
        return dbString;

    }

    public String ats()
    {
        String dbString1="";
        SQLiteDatabase db1 = getWritableDatabase();
        String query1 = "select * from Alerts;";

        //Cursor
        Cursor c1=db1.rawQuery(query1,null);

        c1.moveToFirst();

        while(!c1.isAfterLast() && c1.getString(c1.getColumnIndex("user"))!=null)
        {
            dbString1+= c1.getString(c1.getColumnIndex("user"))+",";
            dbString1+= c1.getString(c1.getColumnIndex("child"))+",";
            dbString1+= c1.getString(c1.getColumnIndex("alertsArray"))+"\n";
        }
        c1.moveToNext();

       /*
        while(!c1.isAfterLast())
        {
            if(c1.getString(c1.getColumnIndex("user"))!=null)
            {
                dbString1+= c1.getString(0)+",";
                dbString1+= c1.getString(1)+",";
                dbString1+= c1.getString(2)+"\n";
            }
            c1.moveToNext();
        }
        dbString1+="\n";
        c1.close();
        db1.close();
        return dbString1;
    }
*/
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
/*
    //not working right now
    public void DeleteAllUsers()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE * FROM " + TABLE_USERS+ ";");
    }
*/

    //check if the password is right
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

/*
    public String arrayToString(Alert alert)
    {
        String[][] alerts=alert.getAlertArray();
        String alertsString="";


        for(int i=0;i<alerts.length;i++)
        {
            for (int j=0;j<alerts[i].length;j++)
            {
                alertsString+=alerts[i][j]+" ";
            }
            alertsString+="\n";
        }
        return alertsString;
    }
*/
}
