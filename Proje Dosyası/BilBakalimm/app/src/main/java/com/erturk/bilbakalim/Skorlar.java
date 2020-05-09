package com.erturk.bilbakalim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.erturk.bilbakalim.Modeller.SoruSkor;
import com.erturk.bilbakalim.ViewHolder.SkorViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//T5-->Basla//
public class Skorlar extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference question_score;

    RecyclerView scoreList;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<SoruSkor, SkorViewHolder> adapter;

    String viewUser="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor_detay);

        //Firebase
        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Skorlar");

        //view
        scoreList = (RecyclerView)findViewById(R.id.scoreList);
        scoreList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        scoreList.setLayoutManager(layoutManager);

        if (getIntent() != null)
            viewUser = getIntent().getStringExtra("viewKullanici");
        if (!viewUser.isEmpty())
            loadScoreDetail(viewUser);

    }

    private void loadScoreDetail(String viewUser) {
        adapter = new FirebaseRecyclerAdapter<SoruSkor, SkorViewHolder>(
                SoruSkor.class,
                R.layout.skor_layout,
                SkorViewHolder.class,
                question_score.orderByChild("kullanici").equalTo(viewUser)
        ) {
            @Override
            protected void populateViewHolder(SkorViewHolder viewHolder, SoruSkor model, int position) {
                viewHolder.txt_ad.setText(model.getKategoriAdi());
                viewHolder.txt_skor.setText(model.getSkor());
            }
        };
        adapter.notifyDataSetChanged();
        scoreList.setAdapter(adapter);
    }
}
//T5-->End//
