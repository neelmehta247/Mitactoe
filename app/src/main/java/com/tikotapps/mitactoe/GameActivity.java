package com.tikotapps.mitactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;

/**
 * Created by neel on 28/11/15 at 3:26 PM.
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
        resetButtons();
    }

    private void resetButtons() {
        TextDrawable blankBox = TextDrawable.builder().beginConfig()
                .withBorder(4)
                .endConfig()
                .buildRect("", Color.WHITE);

        ((ImageView) findViewById(R.id.button11)).setImageDrawable(blankBox);
        ((ImageView) findViewById(R.id.button12)).setImageDrawable(blankBox);
        ((ImageView) findViewById(R.id.button13)).setImageDrawable(blankBox);
        ((ImageView) findViewById(R.id.button21)).setImageDrawable(blankBox);
        ((ImageView) findViewById(R.id.button22)).setImageDrawable(blankBox);
        ((ImageView) findViewById(R.id.button23)).setImageDrawable(blankBox);
        ((ImageView) findViewById(R.id.button31)).setImageDrawable(blankBox);
        ((ImageView) findViewById(R.id.button32)).setImageDrawable(blankBox);
        ((ImageView) findViewById(R.id.button33)).setImageDrawable(blankBox);
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
        (Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT)).show();
    }
}
