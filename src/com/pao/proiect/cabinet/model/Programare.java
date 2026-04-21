package com.pao.proiect.cabinet.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Programare implements Comparable<Programare>{

    private Pacient pacient;
    private Medic medic;
    private ServiciuMedical serviciu;
    private LocalDateTime dataOra;

    private static int contorId = 1;
    private int id;

    public Programare(Pacient pacient, Medic medic, ServiciuMedical serviciu, LocalDateTime dataOra) {
        this.id = contorId++;
        this.pacient = pacient;
        this.medic = medic;
        this.serviciu = serviciu;
        this.dataOra = dataOra;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public Medic getMedic() {
        return medic;
    }

    public ServiciuMedical getServiciu() {
        return serviciu;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Programare p) {
        return this.dataOra.compareTo(p.dataOra);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "[ID: " + id + "]Programare: " + dataOra.format(formatter) +
                " | Pacient: " + pacient.getNume() + " " + pacient.getPrenume() +
                " | Medic: Dr. " + medic.getNume() + " (" + medic.getSpecializare() + ")" +
                " | Serviciu: " + serviciu.getDenumire();
    }
}