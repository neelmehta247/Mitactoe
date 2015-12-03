package com.tikotapps.mitactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;

/**
 * Created by neel on 28/11/15 at 3:26 PM.
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private int[][] board;
    private boolean player1Turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
        startGame();
    }

    private void startGame() {
        resetButtons();
        board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
        player1Turn = true;
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
                if (board[0][0] == 0) {
                    updateCell((ImageView) view);
                }
                break;
            case R.id.button12:
                if (board[0][1] == 0) {
                    updateCell((ImageView) view);
                }
                break;
            case R.id.button13:
                if (board[0][2] == 0) {
                    updateCell((ImageView) view);
                }
                break;
            case R.id.button21:
                if (board[1][0] == 0) {
                    updateCell((ImageView) view);
                }
                break;
            case R.id.button22:
                if (board[1][1] == 0) {
                    updateCell((ImageView) view);
                }
                break;
            case R.id.button23:
                if (board[1][2] == 0) {
                    updateCell((ImageView) view);
                }
                break;
            case R.id.button31:
                if (board[2][0] == 0) {
                    updateCell((ImageView) view);
                }
                break;
            case R.id.button32:
                if (board[2][1] == 0) {
                    updateCell((ImageView) view);
                }
                break;
            case R.id.button33:
                if (board[2][2] == 0) {
                    updateCell((ImageView) view);
                }
                break;
        }
    }

    private void updateCell(ImageView view) {
        int color;

        if (player1Turn) {
            color = Color.RED;
        } else {
            color = Color.BLUE;
        }
        player1Turn = !player1Turn;

        TextDrawable drawable = TextDrawable.builder().beginConfig()
                .withBorder(4)
                .endConfig()
                .buildRect("X", color);

        view.setImageDrawable(drawable);
    }
}
