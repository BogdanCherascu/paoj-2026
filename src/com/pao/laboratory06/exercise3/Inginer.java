package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer> {
    private double sold = 5000.0;

    public Inginer(String nume, String prenume, String telefon, double salariu) {
        super(nume, prenume, telefon, salariu);
    }

    @Override
    public void autentificare(String user, String parola) {

        if (user == null || user.isEmpty() || parola == null || parola.isEmpty()) {
            throw new IllegalArgumentException("Eroare: Userul sau parola nu pot fi goale!");
        }
        System.out.println("Inginer autentificat cu succes: " + user);
    }

    @Override
    public double consultareSold() {
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0 || suma > sold) {
            return false;
        }
        sold -= suma;
        return true;
    }

    @Override
    public int compareTo(Inginer altInginer) {
        if (this.nume == null || altInginer.nume == null) return 0;
        return this.nume.compareTo(altInginer.nume);
    }

    @Override
    public String toString() {
        return "Inginer{nume='" + nume + "', salariu=" + salariu + "}";
    }
}