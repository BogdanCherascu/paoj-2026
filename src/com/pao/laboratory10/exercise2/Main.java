package com.pao.laboratory10.exercise2;

import com.pao.laboratory10.exercise1.TipTranzactie;
import com.pao.laboratory10.exercise1.Tranzactie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        List<Tranzactie> lista = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            double suma = Double.parseDouble(scanner.next());
            String data = scanner.next();
            TipTranzactie tip = TipTranzactie.valueOf(scanner.next());
            lista.add(new Tranzactie(id, suma, data, tip));
        }

        Comparator<Tranzactie> comparatorSuma = Comparator.comparingDouble(Tranzactie::getSuma);

        while (scanner.hasNext()) {
            String comanda = scanner.next();

            switch (comanda) {
                case "UNIQUE_IDS": {
                    LinkedHashSet<Integer> uniqueIds = new LinkedHashSet<>();
                    for (Tranzactie t : lista) {
                        uniqueIds.add(t.getId());
                    }
                    System.out.println("IDs unice (" + uniqueIds.size() + "): " + uniqueIds);
                    break;
                }
                case "MONTHLY_REPORT": {
                    TreeMap<String, double[]> report = new TreeMap<>();
                    for (Tranzactie t : lista) {
                        String luna = t.getData().substring(0, 7);
                        report.putIfAbsent(luna, new double[]{0.0, 0.0});

                        if (t.getTip() == TipTranzactie.CREDIT) {
                            report.get(luna)[0] += t.getSuma();
                        } else {
                            report.get(luna)[1] += t.getSuma();
                        }
                    }
                    for (Map.Entry<String, double[]> entry : report.entrySet()) {
                        System.out.printf(Locale.US, "%s: CREDIT %.2f RON, DEBIT %.2f RON\n",
                                entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
                    }
                    break;
                }
                case "TOP": {
                    int k = scanner.nextInt();
                    System.out.println("Top " + k + ":");
                    List<Tranzactie> copie = new ArrayList<>(lista);
                    copie.sort(comparatorSuma.reversed());

                    List<Tranzactie> topList = copie.subList(0, Math.min(k, copie.size()));
                    for (Tranzactie t : topList) {
                        System.out.println(t);
                    }
                    break;
                }
                case "SORT_ASC": {
                    Collections.sort(lista, comparatorSuma);
                    afiseazaLista(lista);
                    break;
                }
                case "SORT_DESC": {
                    Collections.sort(lista, comparatorSuma.reversed());
                    afiseazaLista(lista);
                    break;
                }
                case "REVERSE": {
                    Collections.reverse(lista);
                    afiseazaLista(lista);
                    break;
                }
                case "MIN_MAX": {
                    if (!lista.isEmpty()) {
                        Tranzactie min = Collections.min(lista, comparatorSuma);
                        Tranzactie max = Collections.max(lista, comparatorSuma);
                        System.out.println("MIN: " + min);
                        System.out.println("MAX: " + max);
                    }
                    break;
                }
                case "CME_DEMO": {
                    try {
                        for (Tranzactie t : lista) {
                            lista.remove(t);
                        }
                    } catch (ConcurrentModificationException e) {
                        System.out.println("ConcurrentModificationException prins: modificare in iteratie detectata.");
                    }
                    break;
                }
            }
        }
        scanner.close();
    }

    private static void afiseazaLista(List<Tranzactie> lista) {
        for (Tranzactie t : lista) {
            System.out.println(t);
        }
    }
}