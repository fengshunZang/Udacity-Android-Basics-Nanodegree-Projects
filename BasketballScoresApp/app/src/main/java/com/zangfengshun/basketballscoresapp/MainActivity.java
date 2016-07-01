package com.zangfengshun.basketballscoresapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scoreA = 0;
    int scoreB = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addOnePointA(View v) {
        scoreA += 1;
        displayScoreA(scoreA);
    }

    public void addTwoPointsA(View v) {
        scoreA += 2;
        displayScoreA(scoreA);
    }

    public void addThreePointsA(View v) {
        scoreA += 3;
        displayScoreA(scoreA);
    }

    public void addOnePointB(View v) {
        scoreB += 1;
        displayScoreB(scoreB);
    }

    public void addTwoPointsB(View v) {
        scoreB += 2;
        displayScoreB(scoreB);
    }

    public void addThreePointsB(View v) {
        scoreB += 3;
        displayScoreB(scoreB);
    }

    public void resetScore(View v) {
        scoreA = 0;
        scoreB = 0;
        displayScoreA(scoreA);
        displayScoreB(scoreB);
    }

    private void displayScoreA (int score) {
        TextView scoreA_text_view = (TextView)findViewById(R.id.scoreA_text_view);
        scoreA_text_view.setText("" + score);
    }

    private void displayScoreB (int score) {
        TextView scoreB_text_view = (TextView)findViewById(R.id.scoreB_text_view);
        scoreB_text_view.setText("" + score);
    }
}
