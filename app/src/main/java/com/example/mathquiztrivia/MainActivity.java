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

    Button falseButton, menuButton, trueButton, share;
    TextView result, Return, countdown;
    TextView Statement;
    ImageView picture, circle;
    Drawable q1q1, q1q2, q1q3, q1q4, q1q5;
    CountDownTimer timer;
    int questionNum = 0;
    int numCorrect = 0;

    Question[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdown = findViewById(R.id.Countdown);
        share=(Button)findViewById(R.id.shareButton);
        result = (TextView) findViewById(R.id.Result);
        Return = (TextView) findViewById(R.id.Return);
        Statement = (TextView) findViewById(R.id.Statement);
        trueButton = (Button) findViewById(R.id.True);
        falseButton = (Button) findViewById(R.id.False);
        picture = (ImageView) findViewById(R.id.picture);
        menuButton = (Button) findViewById(R.id.menu);
        result.setVisibility(View.INVISIBLE);
        circle = (ImageView) findViewById(R.id.imageView2);
        Return.setVisibility(View.INVISIBLE);
        MediaPlayer wrong = MediaPlayer.create(MainActivity.this, R.raw.wrong);

        q1q1 = getDrawable(R.drawable.q1q1);
        q1q2 = getDrawable(R.drawable.q1q2);
        q1q3 = getDrawable(R.drawable.q1q3);
        q1q4 = getDrawable(R.drawable.q1q4);
        q1q5 = getDrawable(R.drawable.q1q5);


        questions = new Question[]{
                new Question(getString(R.string.Q1Q1_false), false, q1q1),
                new Question(getString(R.string.Q1Q2_true), true, q1q2),
                new Question(getString(R.string.Q1Q3_true), true, q1q3),
                new Question(getString(R.string.Q1Q4_false), false, q1q4),
                new Question(getString(R.string.Q1Q5_false), false, q1q5)
        };
        Statement.setText(questions[questionNum].getStatement());
        Question.setImage(questions[questionNum].getImage(), picture);


        startTime();
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                timer.start();
                if (questions[questionNum].getAnswer()) {
                    MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.correct);
                    correct.start();
                    Toast.makeText(v.getContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    numCorrect++;
                } else {
                    wrong.start();
                    Toast.makeText(v.getContext(), "Incorrect...", Toast.LENGTH_SHORT).show();

                }

                questionNum++;
                if (questionNum == questions.length) {
                    timer.cancel();
                    String a = numCorrect + "/" + questions.length;
                    result.setText(a);
                    trueButton.setVisibility(View.INVISIBLE);
                    falseButton.setVisibility(View.INVISIBLE);
                    picture.setVisibility(View.INVISIBLE);
                    Return.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                    Statement.setVisibility(View.INVISIBLE);
                    circle.setVisibility(View.INVISIBLE);
                    countdown.setVisibility(View.INVISIBLE);

                    return;
                }
                Statement.setText(questions[questionNum].getStatement());
                Question.setImage(questions[questionNum].getImage(), picture);

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);{
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "Text");
                    intent.putExtra(Intent.EXTRA_TITLE, "Text");
                    Intent chooser=Intent.createChooser(intent,"Share Text");
                    startActivity(chooser);
                }
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                timer.start();
                if (!(questions[questionNum].getAnswer())) {
                    MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.correct);

                    correct.start();
                    Toast.makeText(v.getContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    numCorrect++;
                } else {
                    MediaPlayer wrong = MediaPlayer.create(MainActivity.this, R.raw.wrong);
                    wrong.start();
                    Toast.makeText(v.getContext(), "Incorrect...", Toast.LENGTH_SHORT).show();

                }
                questionNum++;
                if (questionNum == questions.length) {
                    timer.cancel();
                    String a = numCorrect + "/" + questions.length;
                    result.setText(a);
                    trueButton.setVisibility(View.INVISIBLE);
                    falseButton.setVisibility(View.INVISIBLE);
                    picture.setVisibility(View.INVISIBLE);
                    Return.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                    Statement.setVisibility(View.INVISIBLE);
                    circle.setVisibility(View.INVISIBLE);
                    countdown.setVisibility(View.INVISIBLE);
                    share.setVisibility(View.VISIBLE);

                    return;
                }
                Statement.setText(questions[questionNum].getStatement());
                Question.setImage(questions[questionNum].getImage(), picture);
            }

        });


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(menu);
                finish();
            }
        });


    }

    private void startTime() {
        timer = new CountDownTimer(16000, 1000) {

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
                MediaPlayer wrong = MediaPlayer.create(MainActivity.this, R.raw.wrong);
                wrong.start();

                if (questionNum < 4) {
                    questionNum++;
                    timer.start();
                    Statement.setText(questions[questionNum].getStatement());
                    Question.setImage(questions[questionNum].getImage(), picture);
                } else {
                    String a = numCorrect + "/" + questions.length;
                    result.setText(a);
                    trueButton.setVisibility(View.INVISIBLE);
                    falseButton.setVisibility(View.INVISIBLE);
                    picture.setVisibility(View.INVISIBLE);
                    Return.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                    Statement.setVisibility(View.INVISIBLE);
                    circle.setVisibility(View.INVISIBLE);
                    countdown.setVisibility(View.INVISIBLE);
                    share.setVisibility(View.VISIBLE);
                }
            }
        }.start();
    }


    }
//sixsevennn