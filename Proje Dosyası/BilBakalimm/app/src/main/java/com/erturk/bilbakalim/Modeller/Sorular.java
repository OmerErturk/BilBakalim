package com.erturk.bilbakalim.Modeller;
public class Sorular {
    private String Soru, CevapA, CevapB, CevapC, CevapD, DogruCevap, KategoriID,ResimliSoru;

    public Sorular() {
    }

    public Sorular(String soru, String cevapA, String answerB, String cevapC, String cevapD, String dogruCevap, String kategoriID,String resimliSoru) {
        Soru = soru;
        CevapA = cevapA;
        CevapB = answerB;
        CevapC = cevapC;
        CevapD = cevapD;
        DogruCevap = dogruCevap;
        KategoriID = kategoriID;
        ResimliSoru = resimliSoru;
    }

    public String getSoru() {
        return Soru;
    }

    public void setSoru(String soru) {
        Soru = soru;
    }

    public String getCevapA() {
        return CevapA;
    }

    public void setCevapA(String cevapA) {
        CevapA = cevapA;
    }

    public String getCevapB() {
        return CevapB;
    }

    public void setCevapB(String cevapB) {
        CevapB = cevapB;
    }

    public String getCevapC() {
        return CevapC;
    }

    public void setCevapC(String cevapC) {
        CevapC = cevapC;
    }

    public String getCevapD() {
        return CevapD;
    }

    public void setCevapD(String cevapD) {
        CevapD = cevapD;
    }

    public String getDogruCevap() {
        return DogruCevap;
    }

    public void setDogruCevap(String dogruCevap) {
        DogruCevap = dogruCevap;
    }

    public String getKategoriID() {
        return KategoriID;
    }

    public void setKategoriID(String kategoriID) {
        KategoriID = kategoriID;
    }

    public String getResimliSoru() {
        return ResimliSoru;
    }

    public void setResimliSoru(String resimliSoru) {
        ResimliSoru = resimliSoru;
    }



}
//T3-->End//
