package com.erturk.bilbakalim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.erturk.bilbakalim.Modeller.SoruSkor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SonucEkrani extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtResultScore,getTxtResultQuestion;
    ProgressBar progressBar;

    FirebaseDatabase database;
    DatabaseReference question_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Skorlar");

        txtResultScore = findViewById(R.id.txtTotalScore);
        getTxtResultQuestion = findViewById(R.id.txtTotalQuestion);
        progressBar = findViewById(R.id.doneProgressBar);
        btnTryAgain = findViewById(R.id.btnTryAgain);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SonucEkrani.this, Anasayfa.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle extra = getIntent().getExtras();
        if (extra != null)
        {
            int score = extra.getInt("SKOR");
            int totalQuestion = extra.getInt("TSORU");
            int correctAnswer = extra.getInt("DCEVAP");

            txtResultScore.setText(String.format("SKORUN : %d",score));
            getTxtResultQuestion.setText(String.format("CEVAPLARIN : %d / %d",correctAnswer,totalQuestion));

            progressBar.setMax(totalQuestion);
            progressBar.setProgress(correctAnswer);

            //firebase yükleme yapıyoruz
            question_score.child(String.format("%s_%s", Common.currentKullanici.getKullaniciadi(),Common.kategoriId))
                    .setValue(new SoruSkor(String.format("%s_%s", Common.currentKullanici.getKullaniciadi(),Common.kategoriId),
                            Common.currentKullanici.getKullaniciadi(),
                            String.valueOf(score),
                            Common.kategoriId,
                            Common.kategoriAd));

        }
    }
}

