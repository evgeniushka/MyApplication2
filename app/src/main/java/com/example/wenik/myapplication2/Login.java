package com.example.wenik.myapplication2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    final MyDBHandler db = new MyDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.SignUpLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.BtnLoginNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting the phone and password entered
                String phone= ((EditText) findViewById(R.id.InptPhone)).getText().toString();
                String pass= ((EditText) findViewById(R.id.InptPassword)).getText().toString();


                try {
                    if (db.PassIsRight(phone, pass).compareTo(pass)==0)
                        Toast.makeText(Login.this, "התחברת בהצלחה", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Login.this, "שם משתמש או סיסמה שגויים", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(Login.this, "Try again later", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
