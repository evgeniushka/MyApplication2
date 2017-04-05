package com.example.wenik.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MySecondDBHandler db;
    private String userPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MySecondDBHandler(this, null, null, 1);
        String loggedphone = db.returnFirstLoggedInUser();

        userPhone = getIntent().getStringExtra("EXTRA_SESSION_ID");

        if(loggedphone==null)
        {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        }
        else
        {

        }


    }
}
