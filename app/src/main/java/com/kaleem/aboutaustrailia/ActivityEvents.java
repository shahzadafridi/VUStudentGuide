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

public class ActivityEvents extends AppCompatActivity {
    String username, password;
    ImageView im;
    TextView desc, timing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
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
                Intent i = new Intent(ActivityEvents.this, ActivityAllUsers.class);
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
        Intent i = new Intent(ActivityEvents.this, MainActivity.class);
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
        Intent i = new Intent(ActivityEvents.this, CitiesActivity.class);
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

    public void btnVivid(View view) {
        desc.setText("Vivid Sydney is an annual festival of light, music and ideas, held in Sydney. It includes outdoor immersive light installations and projections, performances by local and international musicians, and an ideas exchange forum featuring public talks and debates with leading creative thinkers.");
        timing.setText("Begins:  24 May 2019\nEnds:   15 June 2019");
        im.setImageResource(R.drawable.vivid);
    }

    public void btnNewEve(View view) {
        desc.setText("Sydney New Year's Eve is an annual multi-tiered event held every New Year's Eve in Sydney, Australia. Centering on the Sydney Harbour Bridge and surrounding Port Jackson, its main events are two pyrotechnic displays the 9pm Family Fireworks and the Midnight Fireworks, both of which are televised nationally with the more popular Midnight Fireworks televised globally.");
        timing.setText("Begins:  31 Dec 2018\nEnds:   01 Jan 2019");
        im.setImageResource(R.drawable.newyear);
    }

    public void btnExit(View view) {
        finish();
    }

    public void btnEvent(View view) {
        Intent i = new Intent(ActivityEvents.this, ActivityEvents.class);
        i.putExtra("username",username);
        i.putExtra("pass",password);
        startActivity(i);
        finish();
    }
}
