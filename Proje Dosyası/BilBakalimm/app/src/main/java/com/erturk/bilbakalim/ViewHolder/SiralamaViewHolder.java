package com.erturk.bilbakalim.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.erturk.bilbakalim.Interface.ItemClickListener;
import com.erturk.bilbakalim.R;


public class SiralamaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txt_ad, txt_skor;

    private ItemClickListener itemClickListener;

    public SiralamaViewHolder(View itemView) {
        super(itemView);
        txt_ad = itemView.findViewById(R.id.txt_name);
        txt_skor = itemView.findViewById(R.id.txt_score);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
