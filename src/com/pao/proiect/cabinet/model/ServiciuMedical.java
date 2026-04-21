package com.pao.proiect.cabinet.model;

public class ServiciuMedical {

    private String denumire;
    private double pret;
    private int durataMinute;

    public ServiciuMedical(String denumire, double pret, int durataMinute) {
        this.denumire = denumire;
        this.pret = pret;
        this.durataMinute = durataMinute;
    }

    public String getDenumire() {
        return denumire;
    }

    public int getDurataMinute() {
        return durataMinute;
    }


    @Override
    public String toString() {
        return "ServiciuMedical{" +
                "denumire='" + denumire + '\'' +
                ", pret=" + pret +
                ", durataMinute=" + durataMinute +
                '}';
    }
}

