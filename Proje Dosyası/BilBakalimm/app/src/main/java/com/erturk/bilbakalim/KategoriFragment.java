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
import com.erturk.bilbakalim.Modeller.Kategori;
import com.erturk.bilbakalim.ViewHolder.KategoriViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class KategoriFragment extends Fragment {

    View myFregment;

    RecyclerView listCategory;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Kategori, KategoriViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference categories;

    public static KategoriFragment newInstance(){
        KategoriFragment kategoriFragment = new KategoriFragment();
        return kategoriFragment;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        categories = database.getReference("Kategori");
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        myFregment = inflater.inflate(R.layout.fragment_category,container,false);

        listCategory = myFregment.findViewById(R.id.listCategory);
        listCategory.setHasFixedSize(true);
        assert container != null;
        layoutManager = new LinearLayoutManager(container.getContext());
        listCategory.setLayoutManager(layoutManager);

        loadCategories();

        return myFregment;
    }

    private void loadCategories() {
        adapter = new FirebaseRecyclerAdapter<Kategori, KategoriViewHolder>(
                Kategori.class,
                R.layout.kategori_layout,
                KategoriViewHolder.class,
                categories
        ) {
            @Override
            protected void populateViewHolder(KategoriViewHolder viewHolder, final Kategori model, int position) {
                Picasso.with(getActivity()).load(model.getResim()).into(viewHolder.kategori_resim);
                viewHolder.kategori_adi.setText(model.getAd());



                viewHolder.setItemClickListener(new ItemClickListener(){
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent startGame = new Intent(getActivity(), Basla.class);
                        Common.kategoriId = adapter.getRef(position).getKey();
                        Common.kategoriAd = model.getAd();
                        startActivity(startGame);

                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);
    }
}
