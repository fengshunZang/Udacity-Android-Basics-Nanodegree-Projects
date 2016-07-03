package com.zangfengshun.quizapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int correctAnswer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayMessage(View v){
        RadioButton quiz1CorrectAnswer = (RadioButton)findViewById(R.id.quiz1item2);
        RadioButton quiz2CorrectAnswer = (RadioButton)findViewById(R.id.quiz2item1);
        RadioButton quiz3CorrectAnswer = (RadioButton)findViewById(R.id.quiz3item1);
        RadioButton quiz4CorrectAnswer = (RadioButton)findViewById(R.id.quiz4item3);
        RadioButton quiz5CorrectAnswer = (RadioButton)findViewById(R.id.quiz5item3);

        if (quiz1CorrectAnswer.isChecked()) {
            correctAnswer++;
        }
        if (quiz2CorrectAnswer.isChecked()) {
            correctAnswer++;
        }
        if (quiz3CorrectAnswer.isChecked()) {
            correctAnswer++;
        }
        if (quiz4CorrectAnswer.isChecked()) {
            correctAnswer++;
        }
        if (quiz5CorrectAnswer.isChecked()) {
            correctAnswer++;
        }

        String message;
        if (correctAnswer == 5) {
            message = "Congratulation!!! You've got all the correct answers!";
        } else {
            message = "You found " + correctAnswer + " correct answer out of 5 quizzes. Please rethink and try again.";
        }

        correctAnswer = 0;
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    public void showCorrectAnswer(View v) {
        RadioButton quiz1CorrectAnswer = (RadioButton)findViewById(R.id.quiz1item2);
        RadioButton quiz2CorrectAnswer = (RadioButton)findViewById(R.id.quiz2item1);
        RadioButton quiz3CorrectAnswer = (RadioButton)findViewById(R.id.quiz3item1);
        RadioButton quiz4CorrectAnswer = (RadioButton)findViewById(R.id.quiz4item3);
        RadioButton quiz5CorrectAnswer = (RadioButton)findViewById(R.id.quiz5item3);
        quiz1CorrectAnswer.setTextColor(Color.parseColor("#FF6D00"));
        quiz2CorrectAnswer.setTextColor(Color.parseColor("#FF6D00"));
        quiz3CorrectAnswer.setTextColor(Color.parseColor("#FF6D00"));
        quiz4CorrectAnswer.setTextColor(Color.parseColor("#FF6D00"));
        quiz5CorrectAnswer.setTextColor(Color.parseColor("#FF6D00"));
    }
}
