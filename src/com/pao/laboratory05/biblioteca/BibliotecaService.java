package com.pao.laboratory05.biblioteca;

import java.util.Arrays;
import java.util.Comparator;

public class BibliotecaService {
    private Carte[] carti = new Carte[0];

    private BibliotecaService() {}

    private static class BibliotecaServiceHolder {
        private static final BibliotecaService INSTANCE = new BibliotecaService();
    }

    public static BibliotecaService getInstance() {
        return BibliotecaServiceHolder.INSTANCE;
    }

    public void addCarte(Carte carte) {
        Carte[] noiCarti = new Carte[carti.length + 1];
        System.arraycopy(carti, 0, noiCarti, 0, carti.length);
        noiCarti[carti.length] = carte;
        carti = noiCarti;

        System.out.println("Carte adăugată: " + carte.getTitlu());
    }

    public void listSortedByRating() {
        Carte[] copy = carti.clone();
        Arrays.sort(copy);
        printArray(copy);
    }

    public void listSortedBy(Comparator<Carte> comparator) {
        Carte[] copy = carti.clone();
        Arrays.sort(copy, comparator);
        printArray(copy);
    }

    private void printArray(Carte[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println((i + 1) + ". " + array[i]);
        }
    }
}