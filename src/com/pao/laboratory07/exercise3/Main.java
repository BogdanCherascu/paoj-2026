package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Comanda> comenzi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().trim().split(" ");
            String tip = tokens[0];
            Comanda c = null;

            if (tip.equals("STANDARD")) {
                c = new ComandaStandard(tokens[1], Double.parseDouble(tokens[2]), tokens[3]);
            } else if (tip.equals("DISCOUNTED")) {
                c = new ComandaRedusa(tokens[1], Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]), tokens[4]);
            } else if (tip.equals("GIFT")) {
                c = new ComandaGratuita(tokens[1], tokens[2]);
            }

            if (c != null) {
                comenzi.add(c);
                System.out.println(c.descriere());
            }
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split(" ");
            String command = tokens[0];

            switch (command) {
                case "STATS" -> {
                    System.out.println("\n STATS:");
                    Map<String, Double> medii = comenzi.stream()
                            .collect(Collectors.groupingBy(Comanda::getTip, Collectors.averagingDouble(Comanda::pretFinal)));

                    if (medii.containsKey("STANDARD")) System.out.printf(Locale.US, "STANDARD: medie = %.2f lei\n", medii.get("STANDARD"));
                    if (medii.containsKey("DISCOUNTED")) System.out.printf(Locale.US, "DISCOUNTED: medie = %.2f lei\n", medii.get("DISCOUNTED"));
                    if (medii.containsKey("GIFT")) System.out.printf(Locale.US, "GIFT: medie = %.2f lei\n", medii.get("GIFT"));
                }
                case "FILTER" -> {
                    double threshold = Double.parseDouble(tokens[1]);
                    System.out.printf(Locale.US, "\nFILTER (>= %.2f):\n", threshold);
                    comenzi.stream()
                            .filter(c -> c.pretFinal() >= threshold)
                            .forEach(c -> System.out.println(c.descriere()));
                }
                case "SORT" -> {
                    System.out.println("\n SORT (by client, then by pret):");
                    comenzi.stream()
                            .sorted(Comparator.comparing(Comanda::getClient).thenComparingDouble(Comanda::pretFinal))
                            .forEach(c -> System.out.println(c.descriere()));
                }
                case "SPECIAL" -> {
                    System.out.println("\n SPECIAL (discount > 15%):");
                    comenzi.stream()
                            .filter(c -> c instanceof ComandaRedusa cr && cr.getDiscountProcent() > 15)
                            .forEach(c -> System.out.println(c.descriere()));
                }
                case "QUIT" -> {
                    return;
                }
            }
        }
        sc.close();
    }
}