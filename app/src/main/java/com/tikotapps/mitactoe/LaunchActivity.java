package com.tikotapps.mitactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
                startActivity(new Intent(this, InstructionsActivity.class));
                break;
        }
    }
}
