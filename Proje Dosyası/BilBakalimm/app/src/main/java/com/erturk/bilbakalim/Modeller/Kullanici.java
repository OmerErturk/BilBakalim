package com.erturk.bilbakalim.Modeller;


public class Kullanici {
    private String kullaniciadi;
    private String sifre;
    private String email;

    public Kullanici() {
    }

    public Kullanici(String kullaniciadi, String sifre, String email) {
        this.kullaniciadi = kullaniciadi;
        this.sifre = sifre;
        this.email = email;
    }

    public String getKullaniciadi() {
        return kullaniciadi;
    }

    public void setKullaniciadi(String kullaniciadi) {
        this.kullaniciadi = kullaniciadi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

