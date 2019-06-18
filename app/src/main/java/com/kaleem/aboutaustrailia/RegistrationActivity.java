package com.kaleem.aboutaustrailia;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

public class RegistrationActivity extends AppCompatActivity {

    EditText sUser, sPass;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        sUser = (EditText) findViewById(R.id.signupUsername);
        sPass = (EditText) findViewById(R.id.signupPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void btnSignup(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String username = sUser.getText().toString();
        String password = sPass.getText().toString();

        if(!username.isEmpty() && !password.isEmpty()){
            DbHandler db = new DbHandler(RegistrationActivity.this);
            db.addSettings(new SettingsDTO(username,password));
            sUser.setText("");
            sPass.setText("");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(RegistrationActivity.this,ActivityLogin.class);
                    startActivity(intent);
                }
            },3000);
        }

    }
}
