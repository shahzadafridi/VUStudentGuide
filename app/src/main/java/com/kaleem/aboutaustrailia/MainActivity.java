package com.kaleem.aboutaustrailia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String username, password;
    TextView tvUSername;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent i = getIntent();
//        username = i.getStringExtra("username");
//        password = i.getStringExtra("pass");
//        tvUSername = (TextView) findViewById(R.id.usernameTextView);
//        tvUSername.setText(username);
//
//        pass = (EditText) findViewById(R.id.changePassword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        try {
            if (username.equalsIgnoreCase("admin") & password.equalsIgnoreCase("admin")) {
                inflater.inflate(R.menu.menu1, menu);
            } else {
                inflater.inflate(R.menu.menu2, menu);
            }
        } catch (Exception r) {
            inflater.inflate(R.menu.menu2, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.all_users:
                Intent i = new Intent(MainActivity.this, ActivityAllUsers.class);
                i.putExtra("username", username);
                i.putExtra("pass", password);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btnHomeClick(View view) {
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        i.putExtra("username", username);
        i.putExtra("pass", password);
        startActivity(i);
        finish();
    }

    public void btnCitiesClick(View view) {
        Intent i = new Intent(MainActivity.this, CitiesActivity.class);
        i.putExtra("username", username);
        i.putExtra("pass", password);
        startActivity(i);
        finish();
    }

    public void btnExit(View view) {
        SharedPreferences preferences = getSharedPreferences("session", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("is_login", false);
        editor.commit();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },1000);

    }

    public void btnEvent(View view) {
        Intent i = new Intent(MainActivity.this, ActivityEvents.class);
        i.putExtra("username", username);
        i.putExtra("pass", password);
        startActivity(i);
        finish();
    }

    public void btnChangePass(View view) {
        String password = pass.getText().toString();

        if (!password.isEmpty()) {
            DbHandler db = new DbHandler(MainActivity.this);
            db.updateSettings(new SettingsDTO(username, password));
        } else {
            Toast.makeText(getApplicationContext(), "Put new password.", Toast.LENGTH_SHORT).show();
        }
        pass.setText("");
    }
}
