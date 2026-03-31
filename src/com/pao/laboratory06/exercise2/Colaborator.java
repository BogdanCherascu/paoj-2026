package com.pao.laboratory06.exercise2;

public abstract class Colaborator implements IOperatiiCitireScriere {
    protected String nume;
    protected String prenume;
    protected double venitBrutLunar;

    public static final double SALARIU_MINIM_BRUT_ANUAL = 48600.0;

    public abstract double calculeazaVenitNetAnual();

    public abstract TipColaborator getTip();
}