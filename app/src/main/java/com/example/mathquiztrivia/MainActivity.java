package com.example.mathquiztrivia;
import android.content.Intent;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {

    Button falseButton, menuButton;
    Button trueButton;
    TextView RIGHT;
   TextView WRONG;
   TextView Statement;
   ImageView Imaginary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WRONG=(TextView)findViewById(R.id.WRONG);
        RIGHT=(TextView)findViewById(R.id.RIGHT);
        Statement=(TextView)findViewById(R.id.Statement);
        trueButton=(Button)findViewById(R.id.True);
        falseButton=(Button)findViewById(R.id.False);
        WRONG.setVisibility(View.INVISIBLE);
        Imaginary=(ImageView)findViewById(R.id.imaginary);
        RIGHT.setVisibility(View.INVISIBLE);
        menuButton=(Button)findViewById(R.id.menu);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                WRONG.setVisibility(View.VISIBLE);
                RIGHT.setVisibility(View.INVISIBLE);
                Statement.setVisibility(View.INVISIBLE);
                trueButton.setVisibility(View.INVISIBLE);
                falseButton.setVisibility(View.INVISIBLE);
                Imaginary.setVisibility(View.INVISIBLE);
                Toast.makeText(v.getContext(), "Incorrect...", Toast.LENGTH_SHORT).show();

            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                RIGHT.setVisibility(View.VISIBLE);
                WRONG.setVisibility(View.INVISIBLE);
                Statement.setVisibility(View.INVISIBLE);
                trueButton.setVisibility(View.INVISIBLE);
                falseButton.setVisibility(View.INVISIBLE);
                Imaginary.setVisibility(View.INVISIBLE);
                Toast.makeText(v.getContext(), "Correct!", Toast.LENGTH_SHORT).show();

            }
        });
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent menu = new Intent(MainActivity.this, MainMenuActivity2.class);
                startActivity(menu);
            }
        });
    }
}