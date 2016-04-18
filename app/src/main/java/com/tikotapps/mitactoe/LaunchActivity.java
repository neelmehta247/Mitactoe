package com.tikotapps.mitactoe;

import android.content.Intent;
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
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        findViewById(R.id.buttonOnePlayer).setOnClickListener(this);
        findViewById(R.id.buttonTwoPlayer).setOnClickListener(this);
        findViewById(R.id.buttonInstructions).setOnClickListener(this);
        findViewById(R.id.buttonBluetoothGame).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOnePlayer:
                startActivity(new Intent(this, OnePlayerActivity.class));
                break;
            case R.id.buttonTwoPlayer:
                startActivity(new Intent(this, TwoPlayerActivity.class));
                break;
            case R.id.buttonInstructions:
                break;
            case R.id.buttonBluetoothGame:
                startActivity(new Intent(this, BluetoothGameActivity.class));
                break;
        }
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
}
