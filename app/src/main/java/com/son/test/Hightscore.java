package com.son.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Hightscore extends Activity {
    Button btnCancel;
    TextView txtEasy,txtMedium,txtHard;
    int ScoreEasy, ScoreMedium, ScoreHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hightscore);

        btnCancel = findViewById(R.id.cancelButton);
        txtEasy = findViewById(R.id.TxtHighscoreEasy);
        txtMedium = findViewById(R.id.TxtHighscoreMedium);
        txtHard = findViewById(R.id.TxtHighscoreHard);

        {
            LoadHighScore1();
            txtEasy.setText("" + ScoreEasy);
        }

        {
            LoadHighScore1();
            txtMedium.setText("" + ScoreMedium);
        }

        {
            LoadHighScore1();
            txtHard.setText("" + ScoreHard);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hightscore.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    void LoadHighScore1() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyData1", Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            ScoreEasy = sharedPreferences.getInt("E", 0);
        }
    }
    void LoadHighScore2() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyData2", Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            ScoreMedium = sharedPreferences.getInt("N", 0);
        }
    }

    void LoadHighScore3() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyData3", Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            ScoreHard = sharedPreferences.getInt("H", 0);
        }
    }
}
