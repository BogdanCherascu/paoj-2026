package com.pao.laboratory06.exercise3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("1.Enum Constante Financiare");
        System.out.println("Valoare TVA: " + ConstanteFinanciare.TVA.getValoare());
        System.out.println("Salariul minim: " + ConstanteFinanciare.SALARIU_MINIM.getValoare());


        System.out.println("\n2.Sortare Ingineri");
        Inginer[] ingineri = {
                new Inginer("Popescu", "Ion", "0700000000", 6000),
                new Inginer("Ionescu", "Andrei", "0700000000", 8000),
                new Inginer("Avram", "Mihai", "0700000000", 5000)
        };

        Arrays.sort(ingineri);
        System.out.println("Sortare dupa nume: " + Arrays.toString(ingineri));


        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        System.out.println("Sortare cu Comparator (dupa salariu descrescator): " + Arrays.toString(ingineri));


        System.out.println("\n=== 3. Acces prin referinta de tip Interfata (CAN_DO) ===");
        PlataOnline inginerPlatitor = new Inginer("Gheorghe", "Vasile", "0700000000", 7000);
        inginerPlatitor.autentificare("vasileG", "parola123");
        System.out.println("Plata reusita? " + inginerPlatitor.efectuarePlata(150.5));


        System.out.println("\n=== 4. Demonstrație SMS și PersoanaJuridica ===");
        PlataOnlineSMS firmaCorecta = new PersoanaJuridica("TechSRL", "", "0700000000");
        firmaCorecta.trimiteSMS("Factura a fost emisa.");

        PlataOnlineSMS firmaFaraTelefon = new PersoanaJuridica("SRLSRL", "", null);
        // Edge case: returneaza false daca nu are telefon valid
        boolean statusSms = firmaFaraTelefon.trimiteSMS("Mesaj edge case.");
        System.out.println("Status trimitere SMS fara telefon: " + statusSms);


        System.out.println("\n5. Tratarea exceptiilor");

        //apelare trimiteSMS pe o entitate care nu are aceasta capabilitate
        try {
            System.out.print("Incercare SMS de pe Inginer: ");
            inginerPlatitor.trimiteSMS("Salut");
        } catch (UnsupportedOperationException e) {
            System.out.println("Exceptie prinsa corect: " + e.getMessage());
        }

        //autentificare cu argumente invalide (null/gol)
        try {
            System.out.print("Incercare autentificare cu user null: ");
            inginerPlatitor.autentificare(null, "parolaBuna");
        } catch (IllegalArgumentException e) {
            System.out.println("Exceptie prinsa corect: " + e.getMessage());
        }
    }
}