package com.erturk.bilbakalim.Modeller;


public class Siralama {
    private String kullaniciadi;
    private long skor;

    public Siralama() {
    }

    public Siralama(String kullaniciadi, long skor) {
        this.kullaniciadi = kullaniciadi;
        this.skor = skor;
    }

    public String getKullaniciadi() {
        return kullaniciadi;
    }

    public void setKullaniciadi(String kullaniciadi) {
        this.kullaniciadi = kullaniciadi;
    }

    public long getSkor() {
        return skor;
    }

    public void setSkor(long skor) {
        this.skor = skor;
    }


}
//T4-->End//
