package com.tikotapps.mitactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initComponents();
    }

    private void initComponents() {
        (findViewById(R.id.button11)).setOnClickListener(this);
        (findViewById(R.id.button12)).setOnClickListener(this);
        (findViewById(R.id.button13)).setOnClickListener(this);
        (findViewById(R.id.button21)).setOnClickListener(this);
        (findViewById(R.id.button22)).setOnClickListener(this);
        (findViewById(R.id.button23)).setOnClickListener(this);
        (findViewById(R.id.button31)).setOnClickListener(this);
        (findViewById(R.id.button32)).setOnClickListener(this);
        (findViewById(R.id.button33)).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch, menu);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button11:
                break;
            case R.id.button12:
                break;
            case R.id.button13:
                break;
            case R.id.button21:
                break;
            case R.id.button22:
                break;
            case R.id.button23:
                break;
            case R.id.button31:
                break;
            case R.id.button32:
                break;
            case R.id.button33:
                break;
        }
    }
}
