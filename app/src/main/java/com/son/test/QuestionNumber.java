package com.son.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class QuestionNumber extends AppCompatActivity {
    TextView Level;
    Button btSelect5, btSelect10, btSelect15, btCancel;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnumber);

        Level = findViewById(R.id.level);
        btSelect5 = findViewById(R.id.select5);
        btSelect10 = findViewById(R.id.select10);
        btCancel = findViewById(R.id.cancelButton);

        Intent callerIntent = getIntent();
        Bundle levelFromCaller = callerIntent.getBundleExtra("level");

        final String level = levelFromCaller.getString("level");
        Level.setText(level);

        btSelect5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                int questionNum = 5;
                switch (level) {
                    case "Easy":
                        Intent easy = new Intent(QuestionNumber.this, PlayPage_Easy.class);
                        bundle.putInt("questionNum", questionNum);
                        easy.putExtra("questionNum", bundle);
                        startActivity(easy);
                        break;
                    case "Normal":
                        Intent normal = new Intent(QuestionNumber.this, PlayPage_Medium.class);
                        bundle.putInt("questionNum", questionNum);
                        normal.putExtra("questionNum", bundle);
                        startActivity(normal);
                        break;
                    case "Hard":
                        Intent hard = new Intent(QuestionNumber.this, PlayPage_Hard.class);
                        bundle.putInt("questionNum", questionNum);
                        hard.putExtra("questionNum", bundle);
                        startActivity(hard);
                        break;
                }
            }
        });
        btSelect10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                int questionNum = 10;
                switch (level) {
                    case "Easy":
                        Intent easy = new Intent(QuestionNumber.this, PlayPage_Easy.class);
                        bundle.putInt("questionNum", questionNum);
                        easy.putExtra("questionNum", bundle);
                        startActivity(easy);
                        break;
                    case "Normal":
                        Intent normal = new Intent(QuestionNumber.this, PlayPage_Medium.class);
                        bundle.putInt("questionNum", questionNum);
                        normal.putExtra("questionNum", bundle);
                        startActivity(normal);
                        break;
                    case "Hard":
                        Intent hard = new Intent(QuestionNumber.this, PlayPage_Hard.class);
                        bundle.putInt("questionNum", questionNum);
                        hard.putExtra("questionNum", bundle);
                        startActivity(hard);
                        break;
                }
            }
        });


        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
