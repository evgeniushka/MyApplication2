package com.example.wenik.myapplication2;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    private EditText phone = null;
    private EditText fname = null;
    private EditText lname = null;
    private String phoneSt;
    private String fnameSt;
    private String lnameSt;
    private MySecondDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        db = new MySecondDBHandler(this, null, null, 1);

        phone= (EditText) findViewById(R.id.TxtPhoneContact);
        fname= (EditText) findViewById(R.id.TxtFnameContact);
        lname= (EditText) findViewById(R.id.TxtLnameContact);

        findViewById(R.id.BtnAddContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneSt= phone.getText().toString();
                fnameSt= fname.getText().toString();
                lnameSt= lname.getText().toString();

                try{
            addContact();
            Toast.makeText(AddContact.this,db.contactsToString(), Toast.LENGTH_LONG).show();
        }
        catch (SQLException e)
        {
            Toast.makeText(AddContact.this,e.toString(), Toast.LENGTH_LONG).show();
        }

            }
        });
    }

    public void addContact()
    {
       Contact contact = new Contact(phoneSt,fnameSt,lnameSt,"0546881105");
        db.addContact(contact);
    }
}
