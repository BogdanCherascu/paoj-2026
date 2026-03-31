package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS {
    private List<String> smsTrimise = new ArrayList<>();
    private double sold = 20000.0;

    public PersoanaJuridica(String nume, String prenume, String telefon) {
        super(nume, prenume, telefon);
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.isEmpty() || parola == null || parola.isEmpty()) {
            throw new IllegalArgumentException("Eroare: Userul sau parola nu pot fi goale");
        }
        System.out.println("PJ autentificata cu succes: " + user);
    }

    @Override
    public double consultareSold() {
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0 || suma > sold) return false;
        sold -= suma;
        return true;
    }

    @Override
    public boolean trimiteSMS(String mesaj) {
        if (telefon == null || telefon.isEmpty() || mesaj == null || mesaj.isEmpty()) {
            System.out.println("Nu se poate trimite SMS. Telefon invalid sau mesaj gol pentru: " + nume);
            return false;
        }

        smsTrimise.add(mesaj);
        System.out.println("SMS expediat catre " + telefon + " cu mesajul: " + mesaj);
        return true;
    }

    public List<String> getSmsTrimise() {
        return smsTrimise;
    }
}