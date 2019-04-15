package com.example.cwiczenie1smolka;

public class ModelOceny {
    private String nazwa;
    private int ocena;

    public ModelOceny(String nazwa) {
        this.nazwa = nazwa;
        this.ocena = 2;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }


}
