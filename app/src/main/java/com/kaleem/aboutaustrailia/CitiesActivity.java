package com.kaleem.aboutaustrailia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class CitiesActivity extends AppCompatActivity {
    String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        Intent i = getIntent();
        username = i.getStringExtra("username");
        password = i.getStringExtra("pass");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        try{
            if(username.equalsIgnoreCase("admin") & password.equalsIgnoreCase("admin")){
                inflater.inflate(R.menu.menu1, menu);
            }
            else {
                inflater.inflate(R.menu.menu2, menu);
            }
        }
        catch (Exception r){
            inflater.inflate(R.menu.menu2, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.all_users:
                Intent i = new Intent(CitiesActivity.this, ActivityAllUsers.class);
                i.putExtra("username",username);
                i.putExtra("pass",password);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void btnHomeClick(View view) {
        Intent i = new Intent(CitiesActivity.this, MainActivity.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }
    public void btnCitiesClick(View view) {
        Intent i = new Intent(CitiesActivity.this, CitiesActivity.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }

    public void sydneyClicked(View view) {
        Intent i = new Intent(CitiesActivity.this, ActivitySydneyEvents.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }

    public void melbourneClicked(View view) {
        Intent i = new Intent(CitiesActivity.this, ActivityMelbourneEvents.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }

    public void btnExit(View view) {
        finish();
    }

    public void btnEvent(View view) {
        Intent i = new Intent(CitiesActivity.this, ActivityEvents.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }
}
