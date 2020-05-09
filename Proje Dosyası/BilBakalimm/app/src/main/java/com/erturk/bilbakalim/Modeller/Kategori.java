package com.erturk.bilbakalim.Modeller;



public class Kategori {
    private String Ad;
    private String Resim;

    public Kategori() {
    }

    public Kategori(String ad, String resim) {
        this.Ad = ad;
        Resim = resim;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String ad) {
        this.Ad = ad;
    }

    public String getResim() {
        return Resim;
    }

    public void setResim(String resim) {
        Resim = resim;
    }


}
