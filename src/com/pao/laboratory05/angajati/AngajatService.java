package com.pao.laboratory05.angajati;

import java.util.Arrays;

public class AngajatService {
    private Angajat[] angajati = new Angajat[0];

    private AngajatService() {}

    private static class AngajatServiceHolder {
        private static final AngajatService INSTANCE = new AngajatService();
    }

    public static AngajatService getInstance() {
        return AngajatServiceHolder.INSTANCE;
    }

    public void addAngajat(Angajat a) {
        Angajat[] noiAngajati = new Angajat[angajati.length + 1];
        System.arraycopy(angajati, 0, noiAngajati, 0, angajati.length);
        noiAngajati[angajati.length] = a;
        angajati = noiAngajati;

        System.out.println("Angajat adăugat: " + a.getNume());
    }

    public void printAll() {
        for (Angajat a : angajati) {
            System.out.println(a);
        }
    }

    public void listBySalary() {
        System.out.println("--- Angajați după salariu (descrescător) ---");
        Angajat[] copy = angajati.clone();
        Arrays.sort(copy);

        for (int i = 0; i < copy.length; i++) {
            System.out.println((i + 1) + ". " + copy[i]);
        }
    }

    public void findByDepartament(String numeDept) {
        boolean found = false;

        for (Angajat a : angajati) {
            if (a.getDepartament().nume().equalsIgnoreCase(numeDept)) {
                if (!found) {
                    System.out.println("--- Angajați din " + numeDept + " ---");
                    found = true;
                }
                System.out.println(a);
            }
        }

        if (!found) {
            System.out.println("Niciun angajat în departamentul: " + numeDept);
        }
    }
}