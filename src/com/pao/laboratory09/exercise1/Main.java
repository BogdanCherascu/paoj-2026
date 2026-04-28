package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex1.ser";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        List<Tranzactie> tranzactii = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            double suma = scanner.nextDouble();
            String data = scanner.next();
            String contSursa = scanner.next();
            String contDestinatie = scanner.next();
            TipTranzactie tip = TipTranzactie.valueOf(scanner.next());

            Tranzactie t = new Tranzactie(id, suma, data, contSursa, contDestinatie, tip);

            t.setNote("procesat");
            tranzactii.add(t);
        }

        new File(OUTPUT_FILE).getParentFile().mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            oos.writeObject(tranzactii);
        }

        List<Tranzactie> tranzactiiDeserializate;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OUTPUT_FILE))) {
            @SuppressWarnings("unchecked")
            List<Tranzactie> deserializate = (List<Tranzactie>) ois.readObject();
            tranzactiiDeserializate = deserializate;
        }

        while (scanner.hasNext()) {
            String comanda = scanner.next();

            switch (comanda) {
                case "LIST":
                    for (Tranzactie t : tranzactiiDeserializate) {
                        System.out.println(t.toString());
                    }
                    break;

                case "FILTER":
                    String prefixLuna = scanner.next();
                    boolean gasit = false;
                    for (Tranzactie t : tranzactiiDeserializate) {
                        if (t.getData().startsWith(prefixLuna)) {
                            System.out.println(t.toString());
                            gasit = true;
                        }
                    }
                    if (!gasit) {
                        System.out.println("Niciun rezultat.");
                    }
                    break;

                case "NOTE":
                    int idCautat = scanner.nextInt();
                    Tranzactie tranzactieGasita = null;

                    for (Tranzactie t : tranzactiiDeserializate) {
                        if (t.getId() == idCautat) {
                            tranzactieGasita = t;
                            break;
                        }
                    }

                    if (tranzactieGasita != null) {
                        System.out.println("NOTE[" + idCautat + "]: " + tranzactieGasita.getNote());
                    } else {
                        System.out.println("NOTE[" + idCautat + "]: not found");
                    }
                    break;
            }
        }

        scanner.close();
    }
}