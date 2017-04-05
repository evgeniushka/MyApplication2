package com.example.wenik.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SuccessfulRegistration extends AppCompatActivity {


    final MyDBHandler db = new MyDBHandler(this, null, null, 1);
 //   final MySecondDBHandler db2 = new MySecondDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_registration);

        String dbst=db.databaseToString();
        TextView tv= (TextView) findViewById(R.id.textView);

        tv.setText(dbst);

        findViewById(R.id.goTo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userPhone = getIntent().getStringExtra("EXTRA_SESSION_ID");

                Intent intent = new Intent(SuccessfulRegistration.this, AddChild.class);
                intent.putExtra("EXTRA_SESSION_ID",userPhone);
                startActivity(intent);
            }
        });
    }
}
