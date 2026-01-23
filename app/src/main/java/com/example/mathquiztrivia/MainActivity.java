package com.example.mathquiztrivia;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.content.SharedPreferences;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button falseButton, menuButton, trueButton, share;
    TextView result, Return, countdown,Q1_VAL;
    TextView Statement;
    ImageView picture, circle;
    Drawable q1q1, q1q2, q1q3, q1q4, q1q5;
    CountDownTimer timer;
    int questionNum = 0;
    int numCorrect = 0;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    EditText initials;
    Question[] questions;
    int q1_VAL;
    private void checkAndSaveHighScore() {
        int currentHighScore = prefs.getInt("highScore", 0);

        // Check if the current score is higher
        if (numCorrect > currentHighScore) {
            // Save the new high score
            this.editor = prefs.edit();
            editor.putInt("HIGHSCORE", numCorrect);
            editor.apply();


        }
    }

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
        initials=(EditText)findViewById(R.id.initials);

        result.setVisibility(View.INVISIBLE);
        circle = (ImageView) findViewById(R.id.imageView2);
        Return.setVisibility(View.INVISIBLE);


        MediaPlayer wrong = MediaPlayer.create(MainActivity.this, R.raw.wrong);

        prefs =getSharedPreferences("highScores", Context.MODE_PRIVATE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        q1q1 = getDrawable(R.drawable.q1q1);
        q1q2 = getDrawable(R.drawable.q1q2);
        q1q3 = getDrawable(R.drawable.q1q3);
        q1q4 = getDrawable(R.drawable.q1q4);
        q1q5 = getDrawable(R.drawable.q1q5);

        initials.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event == null) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        // Capture soft enters in a singleLine EditText that is the last EditText.
                        Toast.makeText(v.getContext(), "Correct!", Toast.LENGTH_SHORT).show();

                    } else if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        // Capture soft enters in other singleLine EditTexts
                    } else {
                        return false;  // Let system handle all other null KeyEvents
                    }
                }
                return true;  // Added return statement for when event is null and action is DONE or NEXT
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);{
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "I got " + numCorrect + "/" + questions.length +" on " + getString(R.string.Q1_title) + "!") ;
                    intent.putExtra(Intent.EXTRA_TITLE, "Check Out My Score on Math Trivia!");
                    Intent chooser=Intent.createChooser(intent,"Share Text");
                    startActivity(chooser);
                }
            }
        });

        questions = new Question[]{
                new Question(getString(R.string.Q1Q1_false), false, q1q1),
                new Question(getString(R.string.Q1Q2_true), true, q1q2),
                new Question(getString(R.string.Q1Q3_true), true, q1q3),
                new Question(getString(R.string.Q1Q4_false), false, q1q4),
                new Question(getString(R.string.Q1Q5_false), false, q1q5)
        };
        Statement.setText(questions[questionNum].getStatement());
        Question.setImage(questions[questionNum].getImage(), picture);


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndSaveHighScore();
                Intent menu = new Intent(MainActivity.this, MainMenuActivity.class);
                DatabaseReference myRef = database.getReference("highscore");
                myRef.setValue("Hello, World!");
                startActivity(menu);
                finish();
            }
        });

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
                    share.setVisibility(View.VISIBLE);
                    initials.setVisibility(View.VISIBLE);
                    return;
                }
                Statement.setText(questions[questionNum].getStatement());
                Question.setImage(questions[questionNum].getImage(), picture);

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
                    initials.setVisibility(View.VISIBLE);
                    return;
                }
                Statement.setText(questions[questionNum].getStatement());
                Question.setImage(questions[questionNum].getImage(), picture);
            }

        });





    }
    //public void checkHighScore{

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
                    initials.setVisibility(View.VISIBLE);
                }
            }
        }.start();
    }




}
