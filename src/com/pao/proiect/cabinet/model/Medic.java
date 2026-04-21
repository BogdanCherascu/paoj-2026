package com.pao.proiect.cabinet.model;

public class Medic extends Persoana {
    private Specializare specializare;
    private double salariu;


    public Medic(String nume, String prenume, String cnp, String telefon, Specializare specializare, double salariu) {
        super(nume, prenume, cnp, telefon);
        this.specializare = specializare;
        this.salariu = salariu;
    }

    public Specializare getSpecializare() {
        return specializare;
    }


    @Override
    public String toString() {
        return "Medic{" +
                "nume='" + nume +
                ", prenume='" + prenume + '\'' +
                ", specializare=" + specializare + '\'' +
                ", cnp='" + cnp +
                '}';
    }
}
