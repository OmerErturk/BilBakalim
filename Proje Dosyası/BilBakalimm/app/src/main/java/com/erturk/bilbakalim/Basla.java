package com.erturk.bilbakalim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.erturk.bilbakalim.Modeller.Sorular;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;


public class Basla extends AppCompatActivity {

    Button btnPlay;

    FirebaseDatabase database;
    DatabaseReference questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Sorular");

        loadQuestion(Common.kategoriId);

        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basla.this, Oyna.class);
                startActivity(intent);
                finish();
            }
        });
        
    }

    private void loadQuestion(String categoryId) {

        //eski soruları temizle
        if (Common.sorularList.size()>0)
            Common.sorularList.clear();

        questions.orderByChild("KategoriID").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                        {
                            Sorular ques = postSnapshot.getValue(Sorular.class);
                            Common.sorularList.add(ques);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        //Soruları karıştır
        Collections.shuffle(Common.sorularList);
    }
}

