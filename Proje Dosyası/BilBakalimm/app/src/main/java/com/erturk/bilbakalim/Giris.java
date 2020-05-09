package com.erturk.bilbakalim;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.erturk.bilbakalim.Modeller.Kullanici;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Giris extends AppCompatActivity {


    MaterialEditText edtNewUser,edtNewPassword,edtNewEmail;  // kayıt ol
    MaterialEditText edtUser,edtPassword;   // giriş yap

    Button btnSingUp,btnSignIn;

    FirebaseDatabase database;
    DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        //Firebase bağlantısı
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Kullanicilar");

        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);

        btnSignIn = findViewById(R.id.btn_sign_in);
        btnSingUp = findViewById(R.id.btn_sign_up);

        //giriş yap
        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignUpDialod();
            }

        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin(edtUser.getText().toString(),edtPassword.getText().toString());
                
            }
        });


    }


    private void signin(final String user, final String pwd) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(user).exists()){
                    if (!user.isEmpty()){
                        Kullanici login = dataSnapshot.child(user).getValue(Kullanici.class);
                        if (login.getSifre().equals(pwd)){
                            Intent homeActivity = new Intent(Giris.this, Anasayfa.class);
                            Common.currentKullanici = login;
                            startActivity(homeActivity);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Giris.this, "Beni kandırmaya çalışma, şifren yanlış!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Giris.this, "Ama kullanıcı adını girmeyi unuttun", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Giris.this, "Üzgünüm ama öyle bir üyelik yok.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showSignUpDialod() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Giris.this);
        alertDialog.setTitle("KAYIT OL");
        alertDialog.setMessage("Tüm Bilgileri Lütfen Eksik Doldur");

        LayoutInflater inflater = this.getLayoutInflater();
        View sign_up_layout = inflater.inflate(R.layout.kayit_ol_layout,null);

        edtNewUser = sign_up_layout.findViewById(R.id.edtNewUserName);
        edtNewEmail = sign_up_layout.findViewById(R.id.edtNewEmail);
        edtNewPassword = sign_up_layout.findViewById(R.id.edtNewPassword);

        alertDialog.setView(sign_up_layout);
        alertDialog.setIcon(R.drawable.bilbakalim_logo_icon);

        alertDialog.setNegativeButton("İptal Et", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.setPositiveButton("Kayıt Ol", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final Kullanici kullanici = new Kullanici(edtNewUser.getText().toString(),edtNewPassword.getText().toString(),edtNewEmail.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(kullanici.getKullaniciadi()).exists()){
                            Toast.makeText(Giris.this, "Çoktan üye olmuşsun sen :)", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            users.child(kullanici.getKullaniciadi()).setValue(kullanici);
                            Toast.makeText(Giris.this, "Kayıt başarılı! Yarışa Hazır mısın :)", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();

    }
}
