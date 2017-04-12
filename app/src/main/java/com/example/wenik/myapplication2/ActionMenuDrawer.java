package com.example.wenik.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ActionMenuDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private SharedPreferences sp;
    private TextView t1;
    private TextView t2;
    private MySecondDBHandler db;

    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_menu_drawer);
        db = new MySecondDBHandler(this, null, null, 1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //both buttons are invisible at the creation on the activity
        findViewById(R.id.button2).setVisibility(View.INVISIBLE);
        findViewById(R.id.button3).setVisibility(View.INVISIBLE);

        //getting the textViews of greeting and alertsData of the user
        t1 = (TextView) findViewById(R.id.hello);
        t2 = (TextView) findViewById(R.id.alertsData);

        //shared preference
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        String value = sp.getString("isLogged", "");
        String valuePhone = sp.getString("userPhone", "");

        if(value.compareTo("no")==0)
        {
            String hello= "ברוך הבא לאפליקציה, \n לרישום לחץ כאן";
            t1.setText(hello);
            t2.setText("");
            findViewById(R.id.button2).setVisibility(View.VISIBLE);
        }
        else
        {
            Toast.makeText(ActionMenuDrawer.this, "haha", Toast.LENGTH_LONG).show();
            try {
                String name = db.getUserFirstName(valuePhone.substring(1));
                String hello = "שלום, " + name;
                t1.setText(hello);
                int alertsCount = db.getAlertsCount(valuePhone.substring(1));
               String youHaveAlerts = "יש לך " + alertsCount + " התראות פעילות: \n";
                t2.setText(youHaveAlerts);
            }
            catch (SQLException e)
            {
                Toast.makeText(ActionMenuDrawer.this, e.toString(), Toast.LENGTH_LONG).show();
            }
            findViewById(R.id.button3).setVisibility(View.VISIBLE);
        }

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActionMenuDrawer.this, Register.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("isLogged", "no");
                    editor.putString("userPhone", "");
                    editor.apply();
                    Intent intent = new Intent(ActionMenuDrawer.this, ActionMenuDrawer.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(ActionMenuDrawer.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }//end onCreate


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            Intent intent = new Intent(ActionMenuDrawer.this, Login.class);
            startActivity(intent);

        } else if (id == R.id.nav_addChild) {
            Intent intent = new Intent(ActionMenuDrawer.this, AddChild.class);
            startActivity(intent);

        } else if (id == R.id.nav_addContact) {
            Intent intent = new Intent(ActionMenuDrawer.this, AddContact.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
