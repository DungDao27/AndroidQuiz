package com.son.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Anagram extends AppCompatActivity {

    TextView tv_info, tv_word;
    int kq = 0;
    EditText et_guess;

    Button b_check, b_new,b_cancel;

    Random ran;

    String currentWord;
    ProgressBar progressBar;
    CountDownTimer CountDown;
    final static long INTERVAL = 1000, TIMEOUT = 30000;
    int progressValue = 0;

    String[] dictionary = {
            "ACCOUNT", "ADDITION",
            "AGREEMENT", "ANGRY", "ANIMAL", "BEHAVIOUR", "BETWEEN", "BLACK", "CHEMICAL", "FOOLISH",
            "FREQUENT", "GOVERNMENT", "GRAIN", "GRASS", "HOSPITAL", "PAYMENT", "POLITICAL",
            "PROCESS", "SHAME", "SMASH", "SMOOTH", "STATEMENT", "SUBSTANCE", "TEACHING", "TENDENCY",
            "TOMORROW", "TOUCH", "UMBRELLA", "WEATHER", "YESTERDAY"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_word = (TextView) findViewById(R.id.tv_word);

        et_guess = (EditText) findViewById(R.id.et_guess);

        b_check = (Button) findViewById(R.id.b_check);
        b_new = (Button) findViewById(R.id.b_new);
        b_cancel = (Button) findViewById(R.id.cancelButton);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(30);

        ran = new Random();

        newGame();

        b_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountDown.cancel();
                Check();
            }
        });

        b_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Anagram.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private String shuffleWord(String word)
    {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for(String letter : letters)
        {
            shuffled += letter;
        }
        return shuffled;
    }
    protected void onResume() {
        super.onResume();
        CountDown = new CountDownTimer(TIMEOUT, INTERVAL) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                CountDown.cancel();
                Check();
            }
        }.start();
    }
    void Check()
    {
        if(et_guess.getText().toString().equalsIgnoreCase(currentWord))
        {
            tv_info.setText("Goob Job");
            b_check.setEnabled(false);
            b_new.setEnabled(true);
        }
        else
        {
            tv_info.setText("Better luck next time");
            b_check.setEnabled(false);
            b_new.setEnabled(true);
        }
    }
    private void newGame()
    {
        currentWord = dictionary[ran.nextInt(dictionary.length)];
        tv_word.setText(shuffleWord(currentWord));
        et_guess.setText("");
        b_new.setEnabled(false);
        b_check.setEnabled(true);
    }
}
