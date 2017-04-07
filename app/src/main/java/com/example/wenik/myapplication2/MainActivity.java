package com.example.wenik.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private MySecondDBHandler db;
    private String userPhone;
    private SharedPreferences sp;
    private TextView t1;
    private TextView t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("LogInfo", Context.MODE_PRIVATE);
        String value = sp.getString("isLogged", "");
        if(value=="")
        {

        }


        SharedPreferences.Editor editor = sp.edit();
        editor.putString("isLogged", "no");
        editor.putString("userPhone", "");
        editor.commit();

        //userPhone = getIntent().getStringExtra("EXTRA_SESSION_ID");

        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);


    }
}
