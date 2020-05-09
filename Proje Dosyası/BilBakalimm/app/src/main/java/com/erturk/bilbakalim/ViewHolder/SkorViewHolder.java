package com.erturk.bilbakalim.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.erturk.bilbakalim.R;


public class SkorViewHolder extends RecyclerView.ViewHolder {

    public TextView txt_ad, txt_skor;

    public SkorViewHolder(View itemView) {
        super(itemView);

        txt_ad = itemView.findViewById(R.id.txt_name);
        txt_skor = itemView.findViewById(R.id.txt_score);
    }
}

