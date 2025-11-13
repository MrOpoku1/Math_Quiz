package com.example.mathquiztrivia;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button falseButton, menuButton,trueButton;
   TextView result;
   TextView Statement;
   ImageView picture;
   Drawable q1q1,q1q2, q1q3,q1q4,q1q5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result=(TextView)findViewById(R.id.Result);
        Statement=(TextView)findViewById(R.id.Statement);
        trueButton=(Button)findViewById(R.id.True);
        falseButton=(Button)findViewById(R.id.False);
        result.setVisibility(View.INVISIBLE);
        picture =(ImageView)findViewById(R.id.picture);
        menuButton=(Button)findViewById(R.id.menu);

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




        int questionNum=1;
        Statement.setText(questions[questionNum-1].getStatement());
        Question.setImage(questions[questionNum-1].getImage(),picture);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
               questionNum++;

                Toast.makeText(v.getContext(), "Incorrect...", Toast.LENGTH_SHORT).show();

            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                result.setVisibility(View.INVISIBLE);
                Statement.setVisibility(View.INVISIBLE);
                trueButton.setVisibility(View.INVISIBLE);
                falseButton.setVisibility(View.INVISIBLE);
                picture.setVisibility(View.INVISIBLE);
                Toast.makeText(v.getContext(), "Correct!", Toast.LENGTH_SHORT).show();

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
}