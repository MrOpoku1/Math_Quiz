package com.example.mathquiztrivia;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {
Button Q1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
        Q1=(Button)findViewById(R.id.Q1);

        Q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(MainMenuActivity.this, MainActivity.class);
                startActivity(menu);
            }
        });


        }
}