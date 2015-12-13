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

import java.util.Random;

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

        if (isGameOver(board)) {
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

        if (!player1Turn && !isGameOver(board)) {
            doAIMove();
        }
    }

    private boolean isGameOver(int[][] boardToCheck) {
        //Checking columns
        for (int i = 0; i < 4; i++) {
            int total = 0;
            for (int j = 0; j < 4; j++) {
                if (boardToCheck[i][j] == 1) {
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
                if (boardToCheck[j][i] == 1) {
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
                if ((i == j) && boardToCheck[j][i] == 1) {
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
                if ((i + j == 3) && boardToCheck[i][j] == 1) {
                    total++;
                }
            }
        }
        return total == 4;
    }

    public void doAIMove() {
        Random random = new Random();
        int i, j;
        int[][] boardToSend;
        boolean toContinue;
        int count = 0;
        double mistakeIndex = 0.15;

        do {
            boardToSend = copyArray(board, 4);
            toContinue = false;
            count++;
            i = random.nextInt(4);
            j = random.nextInt(4);
            if (boardToSend[i][j] == 0) {
                boardToSend[i][j] = 1;
                if (!isGameOver(boardToSend)) {
                    break;
                }
            } else {
                toContinue = true;
            }
        } while (toContinue || count < 16);

        if (count >= 16 || Math.random() <= mistakeIndex) {
            outer:
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    if (board[x][y] == 0) {
                        board[x][y] = 1;
                        i = x;
                        j = y;
                        break outer;
                    }
                }
            }
        } else {
            board[i][j] = 1;
        }

        switch (i) {
            case 0:
                switch (j) {
                    case 0:
                        updateCell((Button) findViewById(R.id.button00));
                        break;
                    case 1:
                        updateCell((Button) findViewById(R.id.button01));
                        break;
                    case 2:
                        updateCell((Button) findViewById(R.id.button02));
                        break;
                    case 3:
                        updateCell((Button) findViewById(R.id.button03));
                        break;
                }
                break;
            case 1:
                switch (j) {
                    case 0:
                        updateCell((Button) findViewById(R.id.button10));
                        break;
                    case 1:
                        updateCell((Button) findViewById(R.id.button11));
                        break;
                    case 2:
                        updateCell((Button) findViewById(R.id.button12));
                        break;
                    case 3:
                        updateCell((Button) findViewById(R.id.button13));
                        break;
                }
                break;
            case 2:
                switch (j) {
                    case 0:
                        updateCell((Button) findViewById(R.id.button20));
                        break;
                    case 1:
                        updateCell((Button) findViewById(R.id.button21));
                        break;
                    case 2:
                        updateCell((Button) findViewById(R.id.button22));
                        break;
                    case 3:
                        updateCell((Button) findViewById(R.id.button23));
                        break;
                }
                break;
            case 3:
                switch (j) {
                    case 0:
                        updateCell((Button) findViewById(R.id.button30));
                        break;
                    case 1:
                        updateCell((Button) findViewById(R.id.button31));
                        break;
                    case 2:
                        updateCell((Button) findViewById(R.id.button32));
                        break;
                    case 3:
                        updateCell((Button) findViewById(R.id.button33));
                        break;
                }
                break;
        }

    }

    public int[][] copyArray(int[][] toCopy, int size) {
        int[][] copied = new int[size][size];

        for (int i = 0; i < size; i++) {
            //noinspection ManualArrayCopy
            for (int j = 0; j < size; j++) {
                copied[i][j] = toCopy[i][j];
            }
        }

        return copied;
    }
}
