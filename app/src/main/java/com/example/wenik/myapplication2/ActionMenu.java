package com.example.wenik.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActionMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_menu);


        findViewById(R.id.BtnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActionMenu.this, Register.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.BtnAddChild).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActionMenu.this, AddChild.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.BtnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActionMenu.this, Login.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.BtnGoToAddContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActionMenu.this, AddContact.class);
                startActivity(intent);
            }
        });
    }
}
