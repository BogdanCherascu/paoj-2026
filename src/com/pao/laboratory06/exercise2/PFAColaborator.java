package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class PFAColaborator extends Colaborator implements PersoanaFizica {
    private double cheltuieliLunare;

    @Override
    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitBrutLunar = in.nextDouble();
        this.cheltuieliLunare = in.nextDouble();
    }

    @Override
    public void afiseaza() {
        System.out.printf("PFA: %s %s, venit net anual: %.2f lei\n", nume, prenume, calculeazaVenitNetAnual());
    }

    @Override
    public String tipContract() {
        return "PFA";
    }

    @Override
    public TipColaborator getTip() {
        return TipColaborator.PFA;
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double netInitial = (venitBrutLunar - cheltuieliLunare) * 12;

        double impozit = 0.10 * netInitial;

        double cass = 0;
        if (netInitial < 6 * SALARIU_MINIM_BRUT_ANUAL) {
            cass = 0.10 * (6 * SALARIU_MINIM_BRUT_ANUAL);
        } else if (netInitial <= 72 * SALARIU_MINIM_BRUT_ANUAL) {
            cass = 0.10 * netInitial;
        } else {
            cass = 0.10 * (72 * SALARIU_MINIM_BRUT_ANUAL);
        }

        double cas = 0;
        if (netInitial < 12 * SALARIU_MINIM_BRUT_ANUAL) {
            cas = 0;
        } else if (netInitial <= 24 * SALARIU_MINIM_BRUT_ANUAL) {
            cas = 0.25 * (12 * SALARIU_MINIM_BRUT_ANUAL);
        } else {
            cas = 0.25 * (24 * SALARIU_MINIM_BRUT_ANUAL);
        }

        return netInitial - impozit - cass - cas;
    }
}