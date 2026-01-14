package com.example.mathquiztrivia;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {
Button Q1, Q2;
TextView Q1_VAL, Q2_VAL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
        Q1=(Button)findViewById(R.id.Q1);
        Q2=(Button)findViewById(R.id.Q2);
        Q1_VAL = (TextView)findViewById(R.id.Q1_VAL);
        Intent intent=getIntent();


        Q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(MainMenuActivity.this, MainActivity.class);
                startActivity(menu);
            }
        });

        Q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent arithmetic = new Intent(MainMenuActivity.this, Arithmetic.class);
                startActivity(arithmetic);
            }
        });


        }
}