package com.example.android.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; //0 = yellow; 1 = red
    boolean isGameActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{0,3,6},{0,4,8},{1,4,7},{2,5,8},{2,4,6},{3,4,5},{6,7,8}};

    public void playTurn(View view) {

        ImageView counter = (ImageView) view;

        int counterNumber = Integer.parseInt(counter.getTag().toString());

        if (gameState[counterNumber] == 2 && isGameActive) {

            gameState[counterNumber] = activePlayer;

            if(activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
            } else {
                counter.setImageResource(R.drawable.red);
            }

            counter.setTranslationY(-1000f);
            counter.animate().translationYBy(1000f).rotation(360).setDuration(500);

            if(activePlayer == 0) {
                activePlayer = 1;
            } else {
                activePlayer = 0;
            }

            for(int[] winningState: winningPositions) {
                if (gameState[winningState[0]] == gameState[winningState[1]]
                        && gameState[winningState[0]] == gameState[winningState[2]]
                        && gameState[winningState[0]] != 2) {

                    //Someone has won
                    isGameActive = false;
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
                    TextView winText = (TextView) findViewById(R.id.winMessage);
                    if(gameState[winningState[0]] == 0) {
                        winText.setText("Yellow has won!");
                    } else {
                        winText.setText("Red has won!");
                    }
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        isGameActive = true;
        activePlayer = 0;
        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
            gameState[i] = 2;
        }
        linearLayout.setVisibility(View.INVISIBLE);
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
