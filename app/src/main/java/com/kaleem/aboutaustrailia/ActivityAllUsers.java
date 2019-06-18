package com.kaleem.aboutaustrailia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ActivityAllUsers extends AppCompatActivity {
    String username, password;
    TextView tvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        tvUsers = (TextView) findViewById(R.id.tvUsers);

        Intent i = getIntent();
        username = i.getStringExtra("username");
        password = i.getStringExtra("pass");

        DbHandler db = new DbHandler(ActivityAllUsers.this);
        List<SettingsDTO> arr = db.getAllUsers();

        for (int k=0; k<arr.size(); k++){
            SettingsDTO o = arr.get(k);
            tvUsers.append(o._name+"\n");
        }
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
                Intent i = new Intent(ActivityAllUsers.this, ActivityAllUsers.class);
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
        Intent i = new Intent(ActivityAllUsers.this, MainActivity.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }

    public void btnCitiesClick(View view) {
        Intent i = new Intent(ActivityAllUsers.this, CitiesActivity.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }

    public void btnExit(View view) {
        finish();
    }

    public void btnEvent(View view) {
        Intent i = new Intent(ActivityAllUsers.this, ActivityEvents.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }
}
