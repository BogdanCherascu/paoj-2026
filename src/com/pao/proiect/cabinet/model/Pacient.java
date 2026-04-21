package com.pao.proiect.cabinet.model;

public class Pacient extends Persoana {

    private String istoricMedical;
    private String adresa;

    public Pacient(String nume, String prenume, String cnp, String telefon, String istoricMedical, String adresa) {
        super(nume, prenume, cnp, telefon);
        this.istoricMedical = istoricMedical;
        this.adresa = adresa;
    }


    @Override
    public String toString() {
        return "Pacient{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", cnp='" + cnp + '\'' +
                '}';
    }
}
