package com.erturk.bilbakalim;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Anasayfa extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);


        //Menüyü tanımlıyoruz//
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId())
                {
                    case R.id.action_category:
                        selectedFragment = KategoriFragment.newInstance();
                        break;
                    case R.id.action_ranking:
                        selectedFragment = SiralamaFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,selectedFragment);
                transaction.commit();
                return true;
            }

        });
        setDefaultFregment();
    }


    private void setDefaultFregment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, KategoriFragment.newInstance());
        transaction.commit();
    }

}
