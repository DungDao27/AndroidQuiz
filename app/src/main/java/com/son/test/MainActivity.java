package com.son.test;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton Back;
    Button btn_start,btn_correct,btn_highest,btn_exit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent callerIntent = getIntent();

        final MediaPlayer relax = MediaPlayer.create(MainActivity.this, R.raw.relax);
        relax.start();
        btn_start = findViewById(R.id.button_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Level.class);
                startActivity(intent);
                finish();
            }
        });

        btn_exit = findViewById(R.id.button_quit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        btn_correct = findViewById(R.id.button_correct);
        btn_correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Anagram.class);
                startActivity(intent);
                finish();
            }
        });

        btn_highest = findViewById(R.id.button_highest);
        btn_highest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Hightscore.class);
                startActivity(intent);
                finish();
            }
        });
    }
}