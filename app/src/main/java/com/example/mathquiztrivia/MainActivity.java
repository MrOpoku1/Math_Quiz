package com.example.mathquiztrivia;
import android.content.Intent;
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
  private ImageView picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

             Question[] quesitons = new Question[]{
                new Question (getString(R.string.Q1Q1_false), false),
                new Question (getString(R.string.Q1Q2_true),true, ),
                new Question (getString(R.string.Q1Q3_true), true),
                new Question (getString(R.string.Q1Q4_false), false),
                     new Question(getString(R.string.Q1Q5_false), false)
        };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=(TextView)findViewById(R.id.Result);
        Statement=(TextView)findViewById(R.id.Statement);
        trueButton=(Button)findViewById(R.id.True);
        falseButton=(Button)findViewById(R.id.False);
        result.setVisibility(View.INVISIBLE);
        picture =(ImageView)findViewById(R.id.picture);
        menuButton=(Button)findViewById(R.id.menu);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                result.setVisibility(View.VISIBLE);
               // RIGHT.setVisibility(View.INVISIBLE);
                Statement.setVisibility(View.INVISIBLE);
                trueButton.setVisibility(View.INVISIBLE);
                falseButton.setVisibility(View.INVISIBLE);
                picture.setVisibility(View.INVISIBLE);
                Toast.makeText(v.getContext(), "Incorrect...", Toast.LENGTH_SHORT).show();

            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                RIGHT.setVisibility(View.VISIBLE);
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