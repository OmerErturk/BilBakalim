package com.erturk.bilbakalim.Modeller;



public class SoruSkor {
    private String Skorlar;
    private String Kullanici;
    private String Skor;
    private String KategoriID;
    private String KategoriAdi;

    public SoruSkor() {
    }


    public SoruSkor(String skorlar, String kullanici, String skor, String kategoriID, String kategoriAdi) {
        Skorlar = skorlar;
        Kullanici = kullanici;
        Skor = skor;
        KategoriID = kategoriID;
        KategoriAdi = kategoriAdi;
    }

    public String getSkorlar() {
        return Skorlar;
    }

    public void setSkorlar(String skorlar) {
        Skorlar = skorlar;
    }

    public String getKullanici() {
        return Kullanici;
    }

    public void setKullanici(String kullanici) {
        Kullanici = kullanici;
    }

    public String getSkor() {
        return Skor;
    }

    public void setSkor(String skor) {
        Skor = skor;
    }

    public String getKategoriID() {
        return KategoriID;
    }

    public void setKategoriID(String kategoriID) {
        KategoriID = kategoriID;
    }

    public String getKategoriAdi() {
        return KategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        KategoriAdi = kategoriAdi;
    }
    //T5-->End//
}
//T3-->End//