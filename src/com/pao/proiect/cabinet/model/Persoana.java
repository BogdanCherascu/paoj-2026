package com.pao.proiect.cabinet.model;

public abstract class Persoana {
    protected String nume;
    protected String prenume;
    protected String cnp;
    protected String telefon;

    public Persoana(String nume, String prenume, String cnp, String telefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.telefon = telefon;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCnp() {
        return cnp;
    }


}
