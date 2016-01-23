package com.tikotapps.mitactoe;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by neel on 23/01/16 at 3:40 PM.
 */
public class OnePlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent gameIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneplayer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.buttonEasy).setOnClickListener(this);
        findViewById(R.id.buttonMedium).setOnClickListener(this);
        findViewById(R.id.buttonHard).setOnClickListener(this);

        gameIntent = new Intent(this, GameActivity.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonEasy:
                gameIntent.putExtra("player1", ((EditText) findViewById(R.id.playerName)).getText().toString().trim());
                gameIntent.putExtra("difficulty", Difficulty.EASY);
                makeDialog();
                break;
            case R.id.buttonMedium:
                gameIntent.putExtra("player1", ((EditText) findViewById(R.id.playerName)).getText().toString().trim());
                gameIntent.putExtra("difficulty", Difficulty.MEDIUM);
                makeDialog();
                break;
            case R.id.buttonHard:
                gameIntent.putExtra("player1", ((EditText) findViewById(R.id.playerName)).getText().toString().trim());
                gameIntent.putExtra("difficulty", Difficulty.HARD);
                makeDialog();
                break;
            case R.id.button3:
                gameIntent.putExtra("size", 3);
                startActivity(gameIntent);
                break;
            case R.id.button4:
                gameIntent.putExtra("size", 4);
                startActivity(gameIntent);
                break;
            case R.id.button5:
                gameIntent.putExtra("size", 5);
                startActivity(gameIntent);
                break;
            case R.id.button6:
                gameIntent.putExtra("size", 6);
                startActivity(gameIntent);
                break;
        }
    }

    private void makeDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View boardSelectorView = inflater.inflate(R.layout.boardselectordialog, null);

        AlertDialog boardSelector = new AlertDialog.Builder(this)
                .setTitle("What size board would you like to play on?")
                .setView(boardSelectorView)
                .create();

        boardSelectorView.findViewById(R.id.button3).setOnClickListener(this);
        boardSelectorView.findViewById(R.id.button4).setOnClickListener(this);
        boardSelectorView.findViewById(R.id.button5).setOnClickListener(this);
        boardSelectorView.findViewById(R.id.button6).setOnClickListener(this);

        boardSelector.show();
    }
}
