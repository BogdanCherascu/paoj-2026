package com.pao.laboratory10.exercise3;

import com.pao.laboratory10.exercise1.TipTranzactie;

import java.util.*;
import java.util.stream.Collectors;

class TranzactieExtinsa {
    private int id;
    private double suma;
    private String data;
    private TipTranzactie tip;
    private String contSursa;

    public TranzactieExtinsa(int id, double suma, String data, TipTranzactie tip, String contSursa) {
        this.id = id;
        this.suma = suma;
        this.data = data;
        this.tip = tip;
        this.contSursa = contSursa;
    }

    public double getSuma() { return suma; }
    public String getData() { return data; }
    public TipTranzactie getTip() { return tip; }
    public String getContSursa() { return contSursa; }

    @Override
    public String toString() {
        return String.format(Locale.US, "[%d] %s %s: %.2f RON (Sursa: %s)", id, data, tip, suma, contSursa);
    }
}

public class Main {
    public static void main(String[] args) {
        List<TranzactieExtinsa> lista = Arrays.asList(
                new TranzactieExtinsa(1, 1500.00, "2024-01-15", TipTranzactie.CREDIT, "RO11BANC"),
                new TranzactieExtinsa(2, 200.50, "2024-01-20", TipTranzactie.DEBIT,  "RO22BANC"),
                new TranzactieExtinsa(3, 50.00,  "2024-01-22", TipTranzactie.DEBIT,  "RO11BANC"),
                new TranzactieExtinsa(4, 3000.00, "2024-01-28", TipTranzactie.CREDIT, "RO33BANC"),
                new TranzactieExtinsa(5, 120.00, "2024-02-05", TipTranzactie.DEBIT,  "RO22BANC"),
                new TranzactieExtinsa(6, 450.00, "2024-02-14", TipTranzactie.CREDIT, "RO44BANC"),
                new TranzactieExtinsa(7, 80.00,  "2024-02-20", TipTranzactie.DEBIT,  "RO11BANC"),
                new TranzactieExtinsa(8, 2500.00, "2024-03-01", TipTranzactie.CREDIT, "RO33BANC"),
                new TranzactieExtinsa(9, 150.00, "2024-03-10", TipTranzactie.DEBIT,  "RO44BANC"),
                new TranzactieExtinsa(10, 600.00, "2024-03-15", TipTranzactie.CREDIT, "RO22BANC")
        );


        System.out.println("1. Lista tranzactii CREDIT");
        lista.stream()
                .filter(t -> t.getTip() == TipTranzactie.CREDIT)
                .forEach(System.out::println);

        System.out.println("\n2. Suma totala procesata");
        double totalProcesat = lista.stream()
                .mapToDouble(TranzactieExtinsa::getSuma)
                .sum();
        System.out.printf(Locale.US, "Total procesat: %.2f RON\n", totalProcesat);

        System.out.println("\n3. Suma cumulata per luna");
        Map<String, Double> sumePerLuna = lista.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.summingDouble(TranzactieExtinsa::getSuma)
                ));

        sumePerLuna.forEach((luna, sumaTotala) ->
                System.out.printf(Locale.US, "Per lună: %s: %.2f RON\n", luna, sumaTotala)
        );

        System.out.println("\n4. Top 3 tranzactii");
        lista.stream()
                .sorted(Comparator.comparingDouble(TranzactieExtinsa::getSuma).reversed())
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\n5. Conturi sursa unice implicate");
        List<String> conturiUnice = lista.stream()
                .map(TranzactieExtinsa::getContSursa)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Conturi sursa unice: " + conturiUnice);

        System.out.println("\n6. Media sumelor tranzactionate");
        double medie = lista.stream()
                .mapToDouble(TranzactieExtinsa::getSuma)
                .average()
                .orElse(0.0);
        System.out.printf(Locale.US, "Suma medie: %.2f RON\n", medie);

        System.out.println("\n7. Generare EXTRAS DE CONT per luna");
        Map<String, List<TranzactieExtinsa>> extrasePerLuna = lista.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.toList()
                ));

        extrasePerLuna.forEach((luna, tranzactiiLuna) -> {
            double sumaLuna = tranzactiiLuna.stream().mapToDouble(TranzactieExtinsa::getSuma).sum();
            System.out.printf(Locale.US, "EXTRAS DE CONT - %s: %d tranzactii, total: %.2f RON per lună\n",
                    luna, tranzactiiLuna.size(), sumaLuna);
        });
    }
}