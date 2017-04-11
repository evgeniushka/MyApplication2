package com.example.wenik.myapplication2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddChild extends AppCompatActivity {

    private MySecondDBHandler db;
    private SharedPreferences sp;

    String userPhone ;
    EditText childName;
    CheckBox day1 ;
    CheckBox day2 ;
    CheckBox day3 ;
    CheckBox day4 ;
    CheckBox day5 ;
    CheckBox day6 ;
    TimePicker timeP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        db = new MySecondDBHandler(this, null, null, 4);
        //get the user's phone from previous activity

        final TextView tl = (TextView) findViewById(R.id.InptChildName);
        final TextView t2 = (TextView) findViewById(R.id.ChildName);
        final ImageButton b1 = (ImageButton) findViewById(R.id.BtnNotifi);
        final TextView t3 = (TextView) findViewById(R.id.AddNotifi);
        final TableLayout tb1 = (TableLayout) findViewById(R.id.DaysTable);
        final TextView t4 = (TextView) findViewById(R.id.textView5);
        final ImageButton b2 = (ImageButton) findViewById(R.id.BtnDelChild);
        final ImageButton b3 = (ImageButton) findViewById(R.id.BtnDelNotifi);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        String value = sp.getString("isLogged", "");
        userPhone = sp.getString("userPhone", "");

        timeP= (TimePicker) findViewById(R.id.alertTime);

        day1= (CheckBox) findViewById(R.id.ChBDay1);
        day2= (CheckBox) findViewById(R.id.ChBDay2);
        day3= (CheckBox) findViewById(R.id.ChBDay3);
        day4= (CheckBox) findViewById(R.id.ChBDay4);
        day5= (CheckBox) findViewById(R.id.ChBDay5);
        day6= (CheckBox) findViewById(R.id.ChBDay6);

        tl.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        t4.setVisibility(View.INVISIBLE);
        tb1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);


        findViewById(R.id.BtnChild).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tl.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.BtnNotifi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tb1.setVisibility(View.VISIBLE);
                t4.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.BtnDelChild).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tl.setVisibility(View.INVISIBLE);
                t2.setVisibility(View.INVISIBLE);
                b1.setVisibility(View.INVISIBLE);
                t3.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                tb1.setVisibility(View.INVISIBLE);
                t4.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.BtnDelNotifi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b3.setVisibility(View.INVISIBLE);
                tb1.setVisibility(View.INVISIBLE);
                t4.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.BtnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                childName = (EditText)  findViewById(R.id.InptChildName);

                addChild();

                try
                {
                String StCh= db.childrenToString();
                Toast.makeText(AddChild.this, StCh , Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(AddChild.this, "can't print children" , Toast.LENGTH_LONG).show();
                }

                try
                {
                    addAlert();
                }
                catch (Exception e)
                {
                    Toast.makeText(AddChild.this, "can't add alerts" , Toast.LENGTH_LONG).show();
                }
                try
                {
                    String st2= db.alertsToString();
                    Toast.makeText(AddChild.this, "toast alerts to string \n"+st2 , Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(AddChild.this, "can't print alerts" , Toast.LENGTH_LONG).show();
                }

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,21);
                calendar.set(Calendar.MINUTE,41);

                Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY,pendingIntent);

            }
        });
    }

    public void addChild()
    {
        try
        {
            Child child = new Child(userPhone,childName.getText().toString());
            db.addChild(child);
        }
        catch (Exception e)
        {
            Toast.makeText(AddChild.this,"bad", Toast.LENGTH_LONG).show();
        }
    }

    public void addAlert()
    {
        String alertData="";
        String h="";
        String m="";

        if(timeP.getHour()<10)
        {
            h="0"+Integer.toString(timeP.getHour());
        }
        else
            h=Integer.toString(timeP.getHour());
        if(timeP.getMinute()<10)
        {
            m="0"+Integer.toString(timeP.getMinute());
        }
        else
            m=Integer.toString(timeP.getMinute());

        if(day1.isChecked())
        {
            alertData+="1";
            alertData+=h+m+" ";
            try
            {
                Alert alert = new Alert(userPhone,childName.getText().toString(),alertData,"10/10/2018");
                db.addAlert(alert);
            }
            catch (SQLException e)
            {

            }
        }
        if(day2.isChecked())
        {
            alertData="2";
            alertData+=h+m+" ";
            try
            {
                Alert alert = new Alert(userPhone,childName.getText().toString(),alertData,"10/10/2018");
                db.addAlert(alert);
            }
            catch (SQLException e)
            {

            }
        }
        if(day3.isChecked())
        {
            alertData="3";
            alertData+=h+m+" ";
            try
            {
                Alert alert = new Alert(userPhone,childName.getText().toString(),alertData,"10/10/2018");
                db.addAlert(alert);
            }
            catch (SQLException e)
            {

            }
        }
        if(day4.isChecked())
        {
            alertData="4";
            alertData+=h+m+" ";
            try
            {
                Alert alert = new Alert(userPhone,childName.getText().toString(),alertData,"10/10/2018");
                db.addAlert(alert);
            }
            catch (SQLException e)
            {

            }
        }
        if(day5.isChecked())
        {
            alertData="5";
            alertData+=h+m+" ";
            try
            {
                Alert alert = new Alert(userPhone,childName.getText().toString(),alertData,"10/10/2018");
                db.addAlert(alert);
            }
            catch (SQLException e)
            {

            }
        }
        if(day6.isChecked())
        {
            alertData="6";
            alertData+=h+m+" ";
            try
            {
                Alert alert = new Alert(userPhone,childName.getText().toString(),alertData,"10/10/2018");
                db.addAlert(alert);
            }
            catch (SQLException e)
            {

            }
        }

    }
}



