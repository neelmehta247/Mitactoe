package com.tikotapps.mitactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ViewFlipper;

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
        findViewById(R.id.buttonSubmit).setOnClickListener(this);
        findViewById(R.id.buttonEasy).setOnClickListener(this);
        findViewById(R.id.buttonMedium).setOnClickListener(this);
        findViewById(R.id.buttonHard).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewFlipperPageNumber = 0;
        Intent gameIntent = new Intent(this, GameActivity.class);
        switch (view.getId()) {
            case R.id.buttonOnePlayer:
                viewFlipperPageNumber = 1;
                findViewById(R.id.playerName).requestFocusFromTouch();
                break;
            case R.id.buttonTwoPlayer:
                viewFlipperPageNumber = 2;
                findViewById(R.id.playerOneName).requestFocusFromTouch();
                break;
            case R.id.buttonInstructions:
                break;
            case R.id.buttonSubmit:
                gameIntent.putExtra("player1", ((EditText) findViewById(R.id.playerOneName)).getText().toString().trim());
                gameIntent.putExtra("player2", ((EditText) findViewById(R.id.playerTwoName)).getText().toString().trim());
                gameIntent.putExtra("difficulty", Difficulty.NONE);
                startActivity(gameIntent);
                break;
            case R.id.buttonEasy:
                gameIntent.putExtra("player1", ((EditText) findViewById(R.id.playerName)).getText().toString().trim());
                gameIntent.putExtra("difficulty", Difficulty.NONE);
                startActivity(gameIntent);
                break;
            case R.id.buttonMedium:
                gameIntent.putExtra("player1", ((EditText) findViewById(R.id.playerName)).getText().toString().trim());
                gameIntent.putExtra("difficulty", Difficulty.MEDIUM);
                startActivity(gameIntent);
                break;
            case R.id.buttonHard:
                gameIntent.putExtra("player1", ((EditText) findViewById(R.id.playerName)).getText().toString().trim());
                gameIntent.putExtra("difficulty", Difficulty.HARD);
                startActivity(gameIntent);
                break;
        }
        ((ViewFlipper) findViewById(R.id.mainViewFlipper)).setDisplayedChild(viewFlipperPageNumber);
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
