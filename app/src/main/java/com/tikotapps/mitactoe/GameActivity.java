package com.tikotapps.mitactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
        board = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
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

        findViewById(R.id.button00).setBackground(blankBox);
        findViewById(R.id.button01).setBackground(blankBox);
        findViewById(R.id.button02).setBackground(blankBox);
        findViewById(R.id.button03).setBackground(blankBox);
        findViewById(R.id.button10).setBackground(blankBox);
        findViewById(R.id.button11).setBackground(blankBox);
        findViewById(R.id.button12).setBackground(blankBox);
        findViewById(R.id.button13).setBackground(blankBox);
        findViewById(R.id.button20).setBackground(blankBox);
        findViewById(R.id.button21).setBackground(blankBox);
        findViewById(R.id.button22).setBackground(blankBox);
        findViewById(R.id.button23).setBackground(blankBox);
        findViewById(R.id.button30).setBackground(blankBox);
        findViewById(R.id.button31).setBackground(blankBox);
        findViewById(R.id.button32).setBackground(blankBox);
        findViewById(R.id.button33).setBackground(blankBox);
    }

    private void initComponents() {
        findViewById(R.id.button00).setOnClickListener(this);
        findViewById(R.id.button01).setOnClickListener(this);
        findViewById(R.id.button02).setOnClickListener(this);
        findViewById(R.id.button03).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);
        findViewById(R.id.button11).setOnClickListener(this);
        findViewById(R.id.button12).setOnClickListener(this);
        findViewById(R.id.button13).setOnClickListener(this);
        findViewById(R.id.button20).setOnClickListener(this);
        findViewById(R.id.button21).setOnClickListener(this);
        findViewById(R.id.button22).setOnClickListener(this);
        findViewById(R.id.button23).setOnClickListener(this);
        findViewById(R.id.button30).setOnClickListener(this);
        findViewById(R.id.button31).setOnClickListener(this);
        findViewById(R.id.button32).setOnClickListener(this);
        findViewById(R.id.button33).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button00:
                if (board[0][0] == 0) {
                    board[0][0] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button01:
                if (board[0][1] == 0) {
                    board[0][1] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button02:
                if (board[0][2] == 0) {
                    board[0][2] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button03:
                if (board[0][3] == 0) {
                    board[0][3] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button10:
                if (board[1][0] == 0) {
                    board[1][0] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button11:
                if (board[1][1] == 0) {
                    board[1][1] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button12:
                if (board[1][2] == 0) {
                    board[1][2] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button13:
                if (board[1][3] == 0) {
                    board[1][3] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button20:
                if (board[2][0] == 0) {
                    board[2][0] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button21:
                if (board[2][1] == 0) {
                    board[2][1] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button22:
                if (board[2][2] == 0) {
                    board[2][2] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button23:
                if (board[2][3] == 0) {
                    board[2][3] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button30:
                if (board[3][0] == 0) {
                    board[3][0] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button31:
                if (board[3][1] == 0) {
                    board[3][1] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button32:
                if (board[3][2] == 0) {
                    board[3][2] = 1;
                    updateCell((Button) view);
                }
                break;
            case R.id.button33:
                if (board[3][3] == 0) {
                    board[3][3] = 1;
                    updateCell((Button) view);
                }
                break;
        }
    }

    private void updateCell(Button view) {
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

        view.setBackground(drawable);

        if (isGameOver()) {
            String winString;
            if (player1Turn) {
                winString = "Player 1 Wins!";
            } else {
                winString = "Player 2 Wins!";
            }
            AlertDialog dialog = new AlertDialog.Builder(this).setTitle(winString)
                    .setMessage("Do you want to play a new game?")
                    .setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            startGame();
                        }
                    })
                    .setNegativeButton("No!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                            Intent intent = new Intent(getApplicationContext(), LaunchActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    })
                    .setCancelable(false)
                    .create();
            dialog.show();
        }
    }

    private boolean isGameOver() {
        //Checking columns
        for (int i = 0; i < 4; i++) {
            int total = 0;
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 1) {
                    total++;
                }
            }
            if (total == 4) {
                return true;
            }
        }

        //Checking rows
        for (int i = 0; i < 4; i++) {
            int total = 0;
            for (int j = 0; j < 4; j++) {
                if (board[j][i] == 1) {
                    total++;
                }
            }
            if (total == 4) {
                return true;
            }
        }

        //Checking one diagonal
        int total = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i == j) && board[j][i] == 1) {
                    total++;
                }
            }
        }
        if (total == 4) {
            return true;
        }

        //Checking the other diagonal
        total = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i + j == 3) && board[i][j] == 1) {
                    total++;
                }
            }
        }
        return total == 4;
    }
}
