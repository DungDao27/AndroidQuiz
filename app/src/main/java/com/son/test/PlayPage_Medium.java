package com.son.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;





public class PlayPage_Medium extends AppCompatActivity {
    TextView Cauhoi, Ketqua, RA, Num, Type,txt_PlayerScore,txt_BestScoreEasy;
    RadioGroup RG;
    Button BT;
    RadioButton A,B,C,D;
    int HighScore = 0;
    Random r = new Random();
    int questionLength = 0;
    int pos=0;
    int currentQuestion = 1;
    int total;
    int bestscoreEasy = 0;
    int kq=0;
    ArrayList<QuestionNare> Array = new ArrayList();
    ArrayList N = new ArrayList();
    ProgressBar progressBar;
    CountDownTimer CountDown;
    final static long INTERVAL = 1000, TIMEOUT =20000;
    int progressValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ReadData();

        Intent callerIntent = getIntent();
        Bundle numberQuestionFromCaller = callerIntent.getBundleExtra("questionNum");

        final int questionNum = numberQuestionFromCaller.getInt("questionNum");


        questionLength = Array.size();
        pos = r.nextInt(questionLength );
        N.add(pos);
        Log.d("Qlength", String.valueOf(questionLength));
        Log.d("pos", String.valueOf(pos));

        RG = (RadioGroup)findViewById(R.id.RadioGroupTraloi);
        Ketqua = (TextView)findViewById(R.id.Txt_Score) ;
        BT = (Button) findViewById(R.id.BtnTraloi);
        A = (RadioButton)findViewById(R.id.RdbA);
        B = (RadioButton)findViewById(R.id.RdbB);
        C = (RadioButton)findViewById(R.id.RdbC);
        D = (RadioButton)findViewById(R.id.RdbD);

        Type = findViewById(R.id.questionType);

        Type.setText("Normal");


        //A.setOnClickListener((View.OnClickListener) this);
        // B.setOnClickListener((View.OnClickListener) this);
        //C.setOnClickListener((View.OnClickListener) this);
        // D.setOnClickListener((View.OnClickListener) this);

        //RA = (TextView) findViewById(R.id.Txt_Score);

        Cauhoi = (TextView) findViewById(R.id.TxtCauhoi);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(20);
        Display(pos);
        //---- Hien diem ----//
        txt_PlayerScore = findViewById(R.id.Txt_Score);
        SharedPreferences preferences = getSharedPreferences("BESTSCORE_EASY", Context.MODE_PRIVATE);
        kq = preferences.getInt("BESTSCORE_EASY",0);
        //count_question = findViewById(R.id.Txt_Number);
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDown.cancel();
                Check();
            }

        });

    }

    int CheckPOS(int i) {
        Log.d("i", String.valueOf(i));
        Log.d("N", String.valueOf(N));
        if(!N.contains(i)) {
            N.add(i);
            return i;
        }
        return CheckPOS(r.nextInt(questionLength));
    }

    void Display(int i) {
        Log.d("Display", String.valueOf(i));

        Cauhoi.setText(Array.get(i).Q);
        A.setText(Array.get(i).AnswerA);
        B.setText(Array.get(i).AnswerB);
        C.setText(Array.get(i).AnswerC);
        D.setText(Array.get(i).AnswerD);
        Ketqua.setText("Score : "+kq);
        RG.clearCheck();
    }

    void ReadData() {
        try {
//T???o ?????i t?????ng DocumentBuilder (builder )
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
//T???o FileInputStream t??? t???p tin XML ngu???n
            InputStream in = getAssets().open("denormal.xml");
//D??ng ph????ng th???c parse c???a ?????i t?????ng builder ????? t???o Document
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();//l???y tag Root
            NodeList list = root.getChildNodes();// l???y to??n b??? node con c???a Root
            for (int i = 0; i < list.getLength(); i++) {// duy???t t??? node ?????u ti??n cho t???i

                Node node = list.item(i);// m???i l???n duy???t th?? l???y ra 1 node
// ki???m tra xem node ???? c?? ph???i l?? Element hay kh??ng

                if (node instanceof Element) {
                    Element Item = (Element) node;// l???y ???????c tag Item
// l???y tag ID b??n trong c???a tag Item
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();//l???y n???i dung c???a tag ID
                    listChild = Item.getElementsByTagName("Question");// l???y tag Question
                    String Question = listChild.item(0).getTextContent();//l???y n???i dung Question
                    listChild = Item.getElementsByTagName("AnswerA");
                    String AnswerA = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerB");
                    String AnswerB = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerC");
                    String AnswerC = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerD");
                    String AnswerD = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Answer");
                    String Answer = listChild.item(0).getTextContent();
//l??u v??o list

                    QuestionNare Q1 = new QuestionNare();
                    Q1.ID = ID;
                    Q1.Q = Question;
                    Q1.AnswerA = AnswerA;
                    Q1.AnswerB = AnswerB;
                    Q1.AnswerC = AnswerC;
                    Q1.AnswerD = AnswerD;
                    Q1.Answer = Answer;
                    Array.add(Q1);

                };
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void onResume()
    {
        super.onResume();
        CountDown = new CountDownTimer(TIMEOUT, INTERVAL)
        {
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
        progressBar.setProgress(0);
        progressValue = 0;
        CountDown.start();
        int idCheck = RG.getCheckedRadioButtonId();
        switch (idCheck) {
            case R.id.RdbA:
                //N???u ????p ??n l?? c??u A th?? c???ng kq l??n 1
                if (Array.get(pos).Answer.compareTo("A")==0)
                    kq = kq+1;
                break;
            case R.id.RdbB:
//N???u ????p ??n l?? c??u B th?? c???ng kq l??n 1

                if (Array.get(pos).Answer.compareTo("B")==0)
                    kq = kq+1;
                break;
            case R.id.RdbC:
//N???u ????p ??n l?? c??u C th?? c???ng kq l??n 1

                if (Array.get(pos).Answer.compareTo("C")==0)
                    kq = kq+1;
                break;
            case R.id.RdbD:
//N???u ????p ??n l?? c??u D th?? c???ng kq l??n 1

                if (Array.get(pos).Answer.compareTo("D")==0)
                    kq = kq+1;
                break;
        }
        Intent callerIntent = getIntent();
        Bundle numberQuestionFromCaller = callerIntent.getBundleExtra("questionNum");

        final int questionNum = numberQuestionFromCaller.getInt("questionNum");
        currentQuestion++;
        if ((currentQuestion - 1) == questionNum) {
            currentQuestion = questionNum;
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("KQ", kq);
            bundle.putInt("Socau", pos);
            intent.putExtra("MyPackage", bundle);
            // startActivity(intent);
            if (kq > HighScore) {
                HighScore = kq;

            }
            setResults();
        }
        else Display(CheckPOS(pos)); //Hi???n th??? c??u h???i k??? ti???p
    }
    public void setResults() {
        Intent intent = new Intent(getApplicationContext(), Result.class);
        Bundle bundle = new Bundle();
        bundle.putInt("KEYSCORE_MEDIUM", kq);
        bundle.putString("KEY_LEVEL", "MEDIUM");
        //Show Result
        intent.putExtra("KEY_BUNDLE", bundle);
        startActivity(intent);
    }
}