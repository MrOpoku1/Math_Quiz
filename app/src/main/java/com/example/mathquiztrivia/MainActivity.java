package com.example.mathquiztrivia;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button falseButton, menuButton,trueButton;
   TextView result, Return,countdown;
   TextView Statement;
   ImageView picture;
   Drawable q1q1,q1q2, q1q3,q1q4,q1q5;
   CountDownTimer timer;
    int questionNum=0;
    int numCorrect=0;
    Question []questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdown = findViewById(R.id.Countdown);
        result=(TextView)findViewById(R.id.Result);
        Return=(TextView)findViewById(R.id.Return);
        Statement=(TextView)findViewById(R.id.Statement);
        trueButton=(Button)findViewById(R.id.True);
        falseButton=(Button)findViewById(R.id.False);
        picture =(ImageView)findViewById(R.id.picture);
        menuButton=(Button)findViewById(R.id.menu);
        result.setVisibility(View.INVISIBLE);
        Return.setVisibility(View.INVISIBLE);

        q1q1=getDrawable(R.drawable.q1q1);
        q1q2=getDrawable(R.drawable.q1q2);
        q1q3=getDrawable(R.drawable.q1q3);
        q1q4=getDrawable(R.drawable.q1q4);
        q1q5=getDrawable(R.drawable.q1q5);



        Question[] questions = new Question[]{
                new Question (getString(R.string.Q1Q1_false), false, q1q1),
                new Question (getString(R.string.Q1Q2_true),true, q1q2),
                new Question (getString(R.string.Q1Q3_true), true,q1q3),
                new Question (getString(R.string.Q1Q4_false), false,q1q4),
                new Question(getString(R.string.Q1Q5_false), false,q1q5)
        };
            Statement.setText(questions[questionNum].getStatement());
            Question.setImage(questions[questionNum].getImage(),picture);




        startTime();
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

               if(questions[questionNum].getAnswer()){
                   Toast.makeText(v.getContext(), "Correct!", Toast.LENGTH_SHORT).show();
                   numCorrect++;
               } else{
                   Toast.makeText(v.getContext(), "Incorrect...", Toast.LENGTH_SHORT).show();

               }
               questionNum++;
                if(questionNum==questions.length){
                    String a = numCorrect + "/" + questions.length;
                    result.setText(a);
                    trueButton.setVisibility(View.INVISIBLE);
                    falseButton.setVisibility(View.INVISIBLE);
                    picture.setVisibility(View.INVISIBLE);
                    Return.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                    Statement.setVisibility(View.INVISIBLE);

                    return;
                }
                Statement.setText(questions[questionNum].getStatement());
               Question.setImage(questions[questionNum].getImage(),picture);

            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(!(questions[questionNum].getAnswer())){
                    Toast.makeText(v.getContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    numCorrect++;
                } else {
                    Toast.makeText(v.getContext(), "Incorrect...", Toast.LENGTH_SHORT).show();

                }
               questionNum++;
                if(questionNum==questions.length){
                    String a = numCorrect + "/" + questions.length;
                    result.setText(a);
                    trueButton.setVisibility(View.INVISIBLE);
                    falseButton.setVisibility(View.INVISIBLE);
                    picture.setVisibility(View.INVISIBLE);
                    Return.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                    Statement.setVisibility(View.INVISIBLE);
                    return;
                }
                Statement.setText(questions[questionNum].getStatement());
                Question.setImage(questions[questionNum].getImage(),picture);

            }

        });



        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent menu = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(menu);
            }
        });











    }

    private void startTime() {
        timer = new CountDownTimer(  30000,  1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = (millisUntilFinished / 1000) % 60;
                String timeFormatted = String.format(Locale.getDefault(), "%d", seconds);
                countdown.setText(timeFormatted);
            }

            @Override
            public void onFinish() {
                countdown.setText("0");
                Toast.makeText(MainActivity.this, "Time's up", Toast.LENGTH_SHORT).show();
                questionNum++;
                //Statement.setText(questions[questionNum].getStatement());
                Question.setImage(questions[questionNum].getImage(),picture);

            }
        }.start();
    }

}//six sevennnn