package com.kaleem.aboutaustrailia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityMelbourneEvents extends AppCompatActivity {
    String username, password;
    ImageView im;
    TextView desc, timing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_melbourne_events);
        Intent i = getIntent();
        username = i.getStringExtra("username");
        password = i.getStringExtra("pass");
        im = (ImageView) findViewById(R.id.imSydneyDT);
        desc = (TextView) findViewById(R.id.tvSydneyDT);
        timing = (TextView) findViewById(R.id.tvSydneyT);
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
                Intent i = new Intent(ActivityMelbourneEvents.this, ActivityAllUsers.class);
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
        Intent i = new Intent(ActivityMelbourneEvents.this, MainActivity.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }

    public void btnMoomba(View view) {
        desc.setText("Moomba is held annually in Melbourne, Australia. Run by the City of Melbourne, it is Australia's largest free community festival, and a Melbournian tradition dating back half a century. The event is celebrated over four days, incorporating the Labour Day long weekend, from Friday to the second Monday in March.");
        timing.setText("Begins:  08 Mar 2019\nEnds:   11 Mar 2019");
        im.setImageResource(R.drawable.moomba);
    }

    public void btnCitiesClick(View view) {
        Intent i = new Intent(ActivityMelbourneEvents.this, CitiesActivity.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }

    public void btnMelbCup(View view) {
        desc.setText("The Melbourne Cup is Australia's most prestigious annual Thoroughbred horse race. It is a 3,200 metre race for three-year-olds and over, conducted by the Victoria Racing Club on the Flemington Racecourse in Melbourne, Victoria as part of the Melbourne Spring Racing Carnival. It is the richest \"two-mile\" handicap in the world, and one of the richest turf races.");
        timing.setText("Begins:  06 Nov 2018");
        im.setImageResource(R.drawable.melcup);
    }

    public void btnExit(View view) {
        finish();
    }

    public void btnEvent(View view) {
        Intent i = new Intent(ActivityMelbourneEvents.this, ActivityEvents.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }
}
