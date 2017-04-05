package com.example.wenik.myapplication2;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private MySecondDBHandler db;
    private EditText ETPhone = null;
    private EditText ETFname = null;
    private EditText ETLname = null;
    private EditText ETPass = null;
    private EditText ETEmail = null;
    private Boolean logged=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new MySecondDBHandler(this, null, null, 1);

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(InputCheck()) {
                    Toast.makeText(Register.this, "input check went successfully", Toast.LENGTH_LONG).show();
                    try
                    {
                        Toast.makeText(Register.this, "0"+ETPhone.getText().toString(), Toast.LENGTH_LONG).show();
                        AddUserOnClick();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(Register.this, "can't add user", Toast.LENGTH_LONG).show();
                    }
                    try
                    {
                        Toast.makeText(Register.this, db.databaseToString(), Toast.LENGTH_LONG).show();
                    }
                    catch (SQLException e)
                    {
                        Toast.makeText(Register.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                    try
                    {
                        Intent intent = new Intent(Register.this, AddChild.class);
                        intent.putExtra("EXTRA_SESSION_ID","0"+ETPhone.getText().toString());
                        startActivity(intent);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(Register.this, "bad", Toast.LENGTH_LONG).show();
                    }
                }
                else
                    Toast.makeText(Register.this, "input check didn't go successfully", Toast.LENGTH_LONG).show();

            }
        });}

    public boolean InputCheck()
    {
        ETPhone = (EditText) findViewById(R.id.InptPhone);
        ETFname = (EditText) findViewById(R.id.InptFname);
        ETLname = (EditText) findViewById(R.id.InptLname);
        ETPass = (EditText) findViewById(R.id.InptPassword);
        ETEmail = (EditText) findViewById(R.id.InptEmail);
        boolean flag=true;

        try{
            if (((EditText) findViewById(R.id.InptPhone)).getText().length() == 0 || ETPhone.length() > 10 || ETPhone.length() > 10)
            {
                ETPhone.setError("יש לרשום מספר בן 10 ספרות");
               // return false;
                flag=false;
            }
            else
            {
                for (int i = 0; i <= ((EditText) findViewById(R.id.InptPhone)).getText().length(); i++)
                {
                    if (!((EditText) findViewById(R.id.InptPhone)).getText().toString().matches("[0-9]+"))
                    {
                        ETPhone.setError("נא להזין ספרות בשדה זה");
                       // return false;
                        flag=false;
                    }
                }
            }

            if (((EditText) findViewById(R.id.InptFname)).getText().length() == 0)
            {
                ETFname.setError("נא למלא שדה זה");
                //return false;
                flag=false;

            }
            else
            {
                for (int i = 0; i <= ((EditText) findViewById(R.id.InptFname)).getText().length(); i++)
                {
                    if (((EditText) findViewById(R.id.InptFname)).getText().toString().matches("[0-9]+"))
                    {
                        ETFname.setError("נא להזין אותיות בשדה זה");
                        //return false;
                        flag=false;
                    }
                }
            }
            if (((EditText) findViewById(R.id.InptLname)).getText().length() == 0)
            {
                ETLname.setError("נא למלא שדה זה");
                //return false;
                flag=false;

            }
            else
            {
                for (int i = 0; i <= ((EditText) findViewById(R.id.InptLname)).getText().length(); i++)
                {
                    if (((EditText) findViewById(R.id.InptLname)).getText().toString().matches("[0-9]+"))
                    {
                        ETLname.setError("נא להזין אותיות בשדה זה");
                        //return false;
                        flag=false;

                    }
                }
            }
            if (((EditText) findViewById(R.id.InptEmail)).getText().length() == 0) {
                ETEmail.setError("נא למלא שדה זה");
                //return false;
                flag=false;

            }
            if (((EditText) findViewById(R.id.InptPassword)).getText().length() == 0) {
                ETPass.setError("נא למלא שדה זה");
                //return false;
                flag=false;
            }

        }
        catch (Exception e)
        {
            Toast.makeText(Register.this,"בעיה בבדיקות קלט",Toast.LENGTH_LONG).show();
        }
        if(!flag)
            return false;
        return true;
    }

    public void  AddUserOnClick()
    {
        try {
            User user = new User("0"+ETPhone.getText().toString(), ETPass.getText().toString(), ETFname.getText().toString(), ETLname.getText().toString(), ETEmail.getText().toString(), logged);
            db.addUser(user);
        }
        catch (SQLException e)
        {
            Toast.makeText(Register.this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}

