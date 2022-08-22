package com.son.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Level extends AppCompatActivity {
    Button btnEasy, btnNormal, btnHard, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);


        btnEasy = findViewById(R.id.easyButton);

        btnNormal = findViewById(R.id.normalButton);

        btnHard = findViewById(R.id.hardButton);

        btnCancel = findViewById(R.id.cancelButton);

        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level.this, QuestionNumber.class);
                String level = "Easy";
                Bundle bundle = new Bundle();
                bundle.putString("level", level);
                intent.putExtra("level", bundle);
                startActivity(intent);
            }
        });
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level.this, QuestionNumber.class);
                String level = "Normal";
                Bundle bundle = new Bundle();
                bundle.putString("level", level);
                intent.putExtra("level", bundle);
                startActivity(intent);
            }
        });
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level.this, QuestionNumber.class);
                String level = "Hard";
                Bundle bundle = new Bundle();
                bundle.putString("level", level);
                intent.putExtra("level", bundle);
                startActivity(intent);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Level.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
