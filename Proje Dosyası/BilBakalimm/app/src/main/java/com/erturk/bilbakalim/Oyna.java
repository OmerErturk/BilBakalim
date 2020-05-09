package com.erturk.bilbakalim;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Oyna extends AppCompatActivity implements View.OnClickListener{

    final static long INTERVAL = 1000; // 1 saniye
    final static long TIMEOUT = 7000; // 7 yaniye
    int progressValue = 0;

    CountDownTimer mCountDown;

    int index=0, skor =0,thisQuestion=0, tsoru, dcevap;

    ProgressBar progressBar;
    ImageView question_image;
    Button btnA,btnB,btnC,btnD;
    TextView txtScore,txtQuestionNum,question_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyna);

        //Views
        txtScore = findViewById(R.id.txtScore);
        txtQuestionNum = findViewById(R.id.txtTotalQuestion);
        question_text = findViewById(R.id.question_text);
        question_image = findViewById(R.id.question_image);

        progressBar = findViewById(R.id.progressBar);

        btnA = findViewById(R.id.btnAnswerA);
        btnB = findViewById(R.id.btnAnswerB);
        btnC = findViewById(R.id.btnAnswerC);
        btnD = findViewById(R.id.btnAnswerD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        mCountDown.cancel();
        if (index < tsoru)
        {
            Button clickedButton = (Button)view;
            if (clickedButton.getText().equals(Common.sorularList.get(index).getDogruCevap()))
            {
                // her doğru cevap için
                skor +=10;
                dcevap++;
                showQuestion(++index); // bidahaki soru
            }
            else
            {
                // yanlış cevaplarda
                Intent intent = new Intent(this, SonucEkrani.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SKOR", skor);
                dataSend.putInt("TSORU", tsoru);
                dataSend.putInt("DCEVAP", dcevap);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }

            txtScore.setText(String.format("%d", skor));
        }

    }

    private void showQuestion(int index) {
        if (index < tsoru)
        {
            thisQuestion++;
            txtQuestionNum.setText(String.format("%d / %d",thisQuestion, tsoru));
            progressBar.setProgress(0);
            progressValue=0;

                if (Common.sorularList.get(index).getResimliSoru().equals("evet"))
                {
                    //eğer resimli soruysa
                    Picasso.with(getBaseContext())
                            .load(Common.sorularList.get(index).getSoru())
                            .into(question_image);
                    question_image.setVisibility(View.VISIBLE);
                    question_text.setVisibility(View.INVISIBLE);
                }
                else
                {
                    question_text.setText(Common.sorularList.get(index).getSoru());
                    //eğer yazılı soruysa
                    question_image.setVisibility(View.INVISIBLE);
                    question_text.setVisibility(View.VISIBLE);
                }

            btnA.setText(Common.sorularList.get(index).getCevapA());
            btnB.setText(Common.sorularList.get(index).getCevapB());
            btnC.setText(Common.sorularList.get(index).getCevapC());
            btnD.setText(Common.sorularList.get(index).getCevapD());

            mCountDown.start(); //
        }
        else
        {
            // artık bitirelim
            Intent intent = new Intent(this, SonucEkrani.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SKOR", skor);
            dataSend.putInt("TSORU", tsoru);
            dataSend.putInt("DCEVAP", dcevap);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        tsoru = Common.sorularList.size();

        mCountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long minisec) {
                progressBar.setProgress(progressValue);
                progressValue++;

            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                showQuestion(++index);
            }
        };
        showQuestion(index);
    }
}
