package com.erturk.bilbakalim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erturk.bilbakalim.Interface.ItemClickListener;
import com.erturk.bilbakalim.Interface.SiralamaCallBack;
import com.erturk.bilbakalim.Modeller.Siralama;
import com.erturk.bilbakalim.Modeller.SoruSkor;
import com.erturk.bilbakalim.ViewHolder.SiralamaViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SiralamaFragment extends Fragment {
    View myFregment;

    RecyclerView rankingList;
    LinearLayoutManager layoutManager;
    FirebaseRecyclerAdapter<Siralama, SiralamaViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference questionScore,rankingTbl;

    int sum=0;


    public static SiralamaFragment newInstance(){
        SiralamaFragment siralamaFragment = new SiralamaFragment();
        return siralamaFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        questionScore = database.getReference("Skorlar");
        rankingTbl = database.getReference("Siralama");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFregment = inflater.inflate(R.layout.fragment_ranking,container,false);


        rankingList = myFregment.findViewById(R.id.rankingList);
        layoutManager = new LinearLayoutManager(getActivity());
        rankingList.setHasFixedSize(true);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rankingList.setLayoutManager(layoutManager);

        updateScore(Common.currentKullanici.getKullaniciadi(), new SiralamaCallBack<Siralama>() {
            @Override
            public void callBack(Siralama siralama) {
                rankingTbl.child(siralama.getKullaniciadi())
                        .setValue(siralama);
            }
        });

        adapter = new FirebaseRecyclerAdapter<Siralama, SiralamaViewHolder>(
                Siralama.class,
                R.layout.layout_siralama,
                SiralamaViewHolder.class,
                rankingTbl.orderByChild("skor")
        ) {
            @Override
            protected void populateViewHolder(SiralamaViewHolder viewHolder, final Siralama model, int position) {

                viewHolder.txt_ad.setText(model.getKullaniciadi());
                viewHolder.txt_skor.setText(String.valueOf(model.getSkor()));

                viewHolder.setItemClickListener(new ItemClickListener(){
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent scoreDetail = new Intent(getActivity(), Skorlar.class);
                        scoreDetail.putExtra("viewKullanici",model.getKullaniciadi());
                        startActivity(scoreDetail);
                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        rankingList.setAdapter(adapter);

        return myFregment;
    }



    private void updateScore(final String username, final SiralamaCallBack<Siralama> callback) {
        questionScore.orderByChild("kullanici").equalTo(username)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data:dataSnapshot.getChildren())
                        {
                            SoruSkor ques = data.getValue(SoruSkor.class);
                            sum+=Integer.parseInt(ques.getSkor());
                        }

                        Siralama siralama = new Siralama(username,sum);
                        callback.callBack(siralama);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
