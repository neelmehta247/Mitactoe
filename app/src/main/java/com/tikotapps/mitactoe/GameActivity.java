package com.tikotapps.mitactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by neel on 28/11/15 at 3:26 PM.
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private int[][] board;
    private boolean player1Turn, player1StartedPrevGame, aiNeeded;
    private String player1, player2;
    private int player1Score, player2Score, size;
    private double mistakeIndex;
    private HashMap<String, Button> buttonHashMap = new HashMap<>();
    private HashMap<Integer, String> buttonKeysHashMap = new HashMap<>();

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
        if (getIntent().getExtras().getString("player1").equals("")) {
            player1 = "Player 1";
        } else {
            player1 = getIntent().getExtras().getString("player1");
        }
        if (getIntent().getExtras().get("difficulty") == Difficulty.NONE) {
            if (getIntent().getExtras().getString("player2").equals("")) {
                player2 = "Player 2";
            } else {
                player2 = getIntent().getExtras().getString("player2");
            }
            aiNeeded = false;
        } else {
            player2 = "Computer";
            aiNeeded = true;
            if (getIntent().getExtras().get("difficulty") == Difficulty.EASY) {
                mistakeIndex = 0.35;
            } else if (getIntent().getExtras().get("difficulty") == Difficulty.MEDIUM) {
                mistakeIndex = 0.175;
            } else {
                mistakeIndex = 0;
            }
        }
        player1StartedPrevGame = false;
        updateTextViews();
        startGame();
    }

    private void startGame() {
        resetButtons();
        board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = 0;
            }
        }
        player1Turn = !player1StartedPrevGame;
        player1StartedPrevGame = player1Turn;

        if (!player1Turn && aiNeeded) {
            doAIMove();
        }

        if (player1Turn) {
            ((TextView) findViewById(R.id.turn)).setText(player1 + "\'s Turn");
        } else {
            ((TextView) findViewById(R.id.turn)).setText(player2 + "\'s Turn");
        }
    }

    private void resetButtons() {
        TextDrawable blankBox = TextDrawable.builder().beginConfig()
                .withBorder(4)
                .endConfig()
                .buildRect("", Color.WHITE);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttonHashMap.get(String.valueOf(i) + String.valueOf(j)).setBackground(blankBox);
            }
        }
    }

    private void initComponents() {
        initateHashMaps();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttonHashMap.get(String.valueOf(i) + String.valueOf(j)).setOnClickListener(this);
            }
        }

        player1Score = 0;
        player2Score = 0;

        updateTextViews();
    }

    @Override
    public void onClick(View view) {
        String button = buttonKeysHashMap.get(view.getId());
        int i = Integer.parseInt(button.substring(0, 1));
        int j = Integer.parseInt(button.substring(1));

        if (board[i][j] == 0) {
            board[i][j] = 1;
            updateBoard(buttonHashMap.get(button));
        }
    }

    private void updateBoard(Button buttonClicked) {
        player1Turn = !player1Turn;

        TextDrawable drawable = TextDrawable.builder().beginConfig()
                .withBorder(4)
                .endConfig()
                .buildRect("X", Color.BLACK);

        buttonClicked.setBackground(drawable);

        if (isGameOver(board)) {
            String winString;
            if (player1Turn) {
                winString = player1 + " Wins!";
                player1Score++;
            } else {
                winString = player2 + " Wins!";
                player2Score++;
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
            updateTextViews();
        }

        if (player1Turn) {
            ((TextView) findViewById(R.id.turn)).setText(player1 + "\'s Turn");
        } else {
            ((TextView) findViewById(R.id.turn)).setText(player2 + "\'s Turn");
        }

        if (!player1Turn && !isGameOver(board) && aiNeeded) {
            doAIMove();
        }
    }

    private void updateTextViews() {
        ((TextView) findViewById(R.id.playerOneScore)).setText(player1 + ": " + player1Score);
        ((TextView) findViewById(R.id.playerTwoScore)).setText(player2 + ": " + player2Score);
    }

    private boolean isGameOver(int[][] boardToCheck) {
        //Checking columns
        for (int i = 0; i < size; i++) {
            int total = 0;
            for (int j = 0; j < size; j++) {
                if (boardToCheck[i][j] == 1) {
                    total++;
                }
            }
            if (total == size) {
                return true;
            }
        }

        //Checking rows
        for (int i = 0; i < size; i++) {
            int total = 0;
            for (int j = 0; j < size; j++) {
                if (boardToCheck[j][i] == 1) {
                    total++;
                }
            }
            if (total == size) {
                return true;
            }
        }

        //Checking one diagonal
        int total = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i == j) && boardToCheck[j][i] == 1) {
                    total++;
                }
            }
        }
        if (total == size) {
            return true;
        }

        //Checking the other diagonal
        total = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i + j == 3) && boardToCheck[i][j] == 1) {
                    total++;
                }
            }
        }
        return total == size;
    }

    private void doAIMove() {
        Random random = new Random();
        int i, j;
        int[][] boardToSend;
        boolean toContinue;
        int count = 0;

        do {
            boardToSend = copyArray(board, size);
            toContinue = false;
            count++;
            i = random.nextInt(size);
            j = random.nextInt(size);
            if (boardToSend[i][j] == 0) {
                boardToSend[i][j] = 1;
                if (!isGameOver(boardToSend)) {
                    break;
                }
            } else {
                toContinue = true;
            }
        } while (toContinue || count < (size * size));

        if (count >= (size * size) || Math.random() <= mistakeIndex) {
            do {
                i = random.nextInt(size);
                j = random.nextInt(size);
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    break;
                }
            } while (true);
        } else {
            board[i][j] = 1;
        }

        updateBoard(buttonHashMap.get(String.valueOf(i) + String.valueOf(j)));
    }

    private int[][] copyArray(int[][] toCopy, int size) {
        int[][] copied = new int[size][size];

        for (int i = 0; i < size; i++) {
            //noinspection ManualArrayCopy
            for (int j = 0; j < size; j++) {
                copied[i][j] = toCopy[i][j];
            }
        }

        return copied;
    }

    private void initateHashMaps() {
        switch (size) {
            case 6:
                buttonKeysHashMap.put(R.id.button05, "05");
                buttonKeysHashMap.put(R.id.button15, "15");
                buttonKeysHashMap.put(R.id.button25, "25");
                buttonKeysHashMap.put(R.id.button35, "35");
                buttonKeysHashMap.put(R.id.button45, "45");
                buttonKeysHashMap.put(R.id.button50, "50");
                buttonKeysHashMap.put(R.id.button51, "51");
                buttonKeysHashMap.put(R.id.button52, "52");
                buttonKeysHashMap.put(R.id.button53, "53");
                buttonKeysHashMap.put(R.id.button54, "54");
                buttonKeysHashMap.put(R.id.button55, "55");
                buttonHashMap.put("05", (Button) findViewById(R.id.button05));
                buttonHashMap.put("15", (Button) findViewById(R.id.button15));
                buttonHashMap.put("25", (Button) findViewById(R.id.button25));
                buttonHashMap.put("35", (Button) findViewById(R.id.button35));
                buttonHashMap.put("45", (Button) findViewById(R.id.button45));
                buttonHashMap.put("50", (Button) findViewById(R.id.button50));
                buttonHashMap.put("51", (Button) findViewById(R.id.button51));
                buttonHashMap.put("52", (Button) findViewById(R.id.button52));
                buttonHashMap.put("53", (Button) findViewById(R.id.button53));
                buttonHashMap.put("54", (Button) findViewById(R.id.button54));
                buttonHashMap.put("55", (Button) findViewById(R.id.button55));
            case 5:
                buttonKeysHashMap.put(R.id.button04, "04");
                buttonKeysHashMap.put(R.id.button14, "14");
                buttonKeysHashMap.put(R.id.button24, "24");
                buttonKeysHashMap.put(R.id.button34, "34");
                buttonKeysHashMap.put(R.id.button40, "40");
                buttonKeysHashMap.put(R.id.button41, "41");
                buttonKeysHashMap.put(R.id.button42, "42");
                buttonKeysHashMap.put(R.id.button43, "43");
                buttonKeysHashMap.put(R.id.button44, "44");
                buttonHashMap.put("04", (Button) findViewById(R.id.button04));
                buttonHashMap.put("14", (Button) findViewById(R.id.button14));
                buttonHashMap.put("24", (Button) findViewById(R.id.button24));
                buttonHashMap.put("34", (Button) findViewById(R.id.button34));
                buttonHashMap.put("40", (Button) findViewById(R.id.button40));
                buttonHashMap.put("41", (Button) findViewById(R.id.button41));
                buttonHashMap.put("42", (Button) findViewById(R.id.button42));
                buttonHashMap.put("43", (Button) findViewById(R.id.button43));
                buttonHashMap.put("44", (Button) findViewById(R.id.button44));
            case 4:
                buttonKeysHashMap.put(R.id.button03, "03");
                buttonKeysHashMap.put(R.id.button13, "13");
                buttonKeysHashMap.put(R.id.button23, "23");
                buttonKeysHashMap.put(R.id.button30, "30");
                buttonKeysHashMap.put(R.id.button31, "31");
                buttonKeysHashMap.put(R.id.button32, "32");
                buttonKeysHashMap.put(R.id.button33, "33");
                buttonHashMap.put("03", (Button) findViewById(R.id.button03));
                buttonHashMap.put("13", (Button) findViewById(R.id.button13));
                buttonHashMap.put("23", (Button) findViewById(R.id.button23));
                buttonHashMap.put("30", (Button) findViewById(R.id.button30));
                buttonHashMap.put("31", (Button) findViewById(R.id.button31));
                buttonHashMap.put("32", (Button) findViewById(R.id.button32));
                buttonHashMap.put("33", (Button) findViewById(R.id.button33));
            case 3:
                buttonKeysHashMap.put(R.id.button00, "00");
                buttonKeysHashMap.put(R.id.button00, "01");
                buttonKeysHashMap.put(R.id.button00, "02");
                buttonKeysHashMap.put(R.id.button00, "10");
                buttonKeysHashMap.put(R.id.button00, "11");
                buttonKeysHashMap.put(R.id.button00, "12");
                buttonKeysHashMap.put(R.id.button00, "20");
                buttonKeysHashMap.put(R.id.button00, "21");
                buttonKeysHashMap.put(R.id.button00, "22");
                buttonHashMap.put("00", (Button) findViewById(R.id.button00));
                buttonHashMap.put("01", (Button) findViewById(R.id.button01));
                buttonHashMap.put("02", (Button) findViewById(R.id.button02));
                buttonHashMap.put("10", (Button) findViewById(R.id.button10));
                buttonHashMap.put("11", (Button) findViewById(R.id.button11));
                buttonHashMap.put("12", (Button) findViewById(R.id.button12));
                buttonHashMap.put("20", (Button) findViewById(R.id.button20));
                buttonHashMap.put("21", (Button) findViewById(R.id.button21));
                buttonHashMap.put("22", (Button) findViewById(R.id.button22));
        }
    }
}
