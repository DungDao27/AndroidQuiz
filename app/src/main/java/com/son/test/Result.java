package com.son.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Result extends Activity {
    TextView txt_PlayerScore, txt_title, txt_congra;
    Button btn_ok;
    int score;
    int kq;
    Button btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MediaPlayer finsih = MediaPlayer.create(Result.this, R.raw.congratulations);
        finsih.start();
        super.onCreate(savedInstanceState);
        this.setFinishOnTouchOutside(false);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_result);


        txt_title = findViewById(R.id.clt_title);

        txt_congra = findViewById(R.id.txt_congratulation);

        txt_PlayerScore = findViewById(R.id.txt_playerscore);

        btn_ok = findViewById(R.id.clt_button);
        Bundle bundle = getIntent().getBundleExtra("KEY_BUNDLE");
        String level = bundle.getString("KEY_LEVEL");
        switch (level) {
            case "EASY":
                EasyScoring();
                break;
            case "MEDIUM":
                MediumScoring();
                break;
            case "HARD":
                HardScoring();
                break;
        }

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    //----- SCORING ----//
    public void EasyScoring() {
        Intent intent = getIntent();
        Bundle bundleEASY = getIntent().getBundleExtra("KEY_BUNDLE");
        score = bundleEASY.getInt("KEYSCORE_EASY");
        txt_PlayerScore.setText(score + " " + "Points");
        SharedPreferences preferencesEASY = getSharedPreferences("BESTSCORE_EASY", Context.MODE_PRIVATE);
        kq = preferencesEASY.getInt("BESTSCORE_EASY", 0);
        SharedPreferences.Editor editorEASY = preferencesEASY.edit();
    }

    public void MediumScoring() {
        Bundle bundleMEDIUM = getIntent().getBundleExtra("KEY_BUNDLE");
        score = bundleMEDIUM.getInt("KEYSCORE_MEDIUM");
        txt_PlayerScore.setText(String.valueOf(score) + " " + "Points");
        SharedPreferences preferencesMEDIUM = getSharedPreferences("BESTSCORE_MEDIUM", Context.MODE_PRIVATE);
        kq = preferencesMEDIUM.getInt("BESTSCORE_MEDIUM", 0);
        SharedPreferences.Editor editorMEDIUM = preferencesMEDIUM.edit();
    }

    public void HardScoring() {
        Bundle bundleHARD = getIntent().getBundleExtra("KEY_BUNDLE");
        score = bundleHARD.getInt("KEYSCORE_HARD");
        txt_PlayerScore.setText(String.valueOf(score) + " " + "Points");
        SharedPreferences preferencesHARD = getSharedPreferences("BESTSCORE_HARD", Context.MODE_PRIVATE);
        kq = preferencesHARD.getInt("BESTSCORE_HARD", 0);
        SharedPreferences.Editor editorHARD = preferencesHARD.edit();
    }
}

