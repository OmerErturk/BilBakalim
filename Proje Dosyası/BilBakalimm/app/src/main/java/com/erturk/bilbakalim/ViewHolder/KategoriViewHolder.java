package com.erturk.bilbakalim.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erturk.bilbakalim.Interface.ItemClickListener;
import com.erturk.bilbakalim.R;

public class KategoriViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView kategori_adi;
    public ImageView kategori_resim;

    private ItemClickListener itemClickListener;

    public KategoriViewHolder(View itemView) {
        super(itemView);

        kategori_resim = itemView.findViewById(R.id.category_image);
        kategori_adi = itemView.findViewById(R.id.category_name);

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
//T2-->End//
