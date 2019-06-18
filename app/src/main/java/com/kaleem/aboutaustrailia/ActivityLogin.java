package com.kaleem.aboutaustrailia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    EditText lUser, lPass;
    TextView newAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lUser = (EditText) findViewById(R.id.loginUsername);
        lPass = (EditText) findViewById(R.id.loginPassword);
        newAccount = (TextView) findViewById(R.id.newAccount_login);


    }

    public void btnLogin(View view) {
        final String username = lUser.getText().toString();
        final String password = lPass.getText().toString();

        if(!username.isEmpty() && !password.isEmpty()){
            DbHandler db = new DbHandler(ActivityLogin.this);
            boolean che = db.login(new SettingsDTO(username,password));

            if(che){
                createSession(true);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(ActivityLogin.this, MainActivity.class);
                        i.putExtra("username",username);
                        i.putExtra("pass",password);
                        startActivity(i);
                        finish();
                    }
                },1000);

            }
            else {
                Toast.makeText(getApplicationContext(),"Incorrect username or password.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnGoToRegistration(View view){
        Intent intent = new Intent(ActivityLogin.this,RegistrationActivity.class);
        startActivity(intent);
    }

    public void createSession(boolean value){
        SharedPreferences preferences = getSharedPreferences("session",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("is_login",value);
        editor.commit();
    }
    public boolean getSession(){
        SharedPreferences preferences = getSharedPreferences("session",MODE_PRIVATE);
        return preferences.getBoolean("is_login",false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getSession()){
            Intent i = new Intent(ActivityLogin.this, MainActivity.class);
            startActivity(i);
        }
    }
}
