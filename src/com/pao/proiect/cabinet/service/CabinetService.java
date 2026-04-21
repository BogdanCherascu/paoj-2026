package com.pao.proiect.cabinet.service;

import com.pao.proiect.cabinet.model.Medic;
import com.pao.proiect.cabinet.model.Pacient;
import com.pao.proiect.cabinet.model.Programare;
import com.pao.proiect.cabinet.model.Specializare;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;

public class CabinetService {

    private List<Medic> medici;
    private List<Pacient> pacienti;
    private Set<Programare> programari;

    public CabinetService() {
        this.medici = new ArrayList<>();
        this.pacienti = new ArrayList<>();
        this.programari = new TreeSet<>();
    }

    public void adaugaMedic(Medic medic) {
        String cnp = medic.getCnp();

        if (!validitateCnp(cnp)) {
            System.out.println("CNP-ul trebuie să contina exact 13 cifre.");
            return;
        }
        medici.add(medic);
        System.out.println("Medicul " + medic.getNume() + " " + medic.getPrenume() + " a fost adaugat cu succes.");
    }
    public void adaugaPacient(Pacient pacient) {
        String cnp = pacient.getCnp();

        if (!validitateCnp(cnp)) {
            System.out.println("CNP-ul trebuie să contina exact 13 cifre.");
            return;
        }
        pacienti.add(pacient);
            System.out.println("Pacientul " + pacient.getNume() + " " + pacient.getPrenume() + " a fost adaugat cu succes.");
    }

    public void creeazaProgramare(Programare programare) {
        Medic medic = programare.getMedic();
        LocalDateTime dataOra = programare.getDataOra();
        int durata = programare.getServiciu().getDurataMinute();

        if (disponibilitateMedic(medic, dataOra, durata)) {
            programari.add(programare);
            System.out.println("Programarea a fost creata cu succes.");
        } else {
            System.out.println("Medicul " + medic.getNume() + " este deja ocupat in acel interval orar!");
        }
    }
    public void anuleazaProgramare(int idProgramare) {
        Programare programareAnulata = null;

        for(Programare p : programari) {
            if(p.getId() == idProgramare) {
                programareAnulata = p;
                break;
            }
        }
        if(programareAnulata != null) {
            System.out.println("Programarea cu ID: " + idProgramare + " a fost anulata.");

        }else{
            System.out.println("Aceasta programare nu exista.");
        }
    }

    public void stergeMedic(String cnp) {

        Medic medicSters = null;

        for(Medic m : medici) {
            if(m.getCnp().equals(cnp)) {
                medicSters = m;
                break;
            }
        }
        medici.remove(medicSters);
        if (medicSters != null) {
            System.out.println("Medicul " + medicSters.getNume() + " a fost sters.");
        } else {
            System.out.println("Acest medic nu exista.");
        }
    }

    public void afiseazaMedicii() {
        if (medici.isEmpty()) {
            System.out.println("Nu exista medici adaugati.");
        } else {
            System.out.println("Lista medici:");
            for (Medic m : medici) {
                System.out.println(m.toString());
            }
        }
    }

    public void afiseazaPacientii(){
        if (pacienti.isEmpty()) {
            System.out.println("Nu exista pacienti adaugati.");
        } else {
            System.out.println("Lista pacienti:");
            for (Pacient p : pacienti) {
                System.out.println(p.toString());
            }
        }
    }

    public void afiseazaProgramariCronologic(){
        System.out.println("Lista programari cronologic:");
        for (Programare p : programari) {
            System.out.println(p.toString());
        }
    }

    public void cautaMediciDupaSpecializare(Specializare s){
        System.out.println("Medici cu specializarea: " + s);
        for (Medic m : medici) {
            if(m.getSpecializare() == s){
                System.out.println(m.toString());
            }
        }
    }

    public void afiseazaProgramariPacient(String cnp){
        System.out.println("Programari pacient cu CNP: " + cnp);
        for (Programare p : programari) {
            if(p.getPacient().getCnp().equals(cnp)){
                System.out.println(p.toString());
            }
        }
    }

    public void afiseazaProgramariMedic(String cnp){
        System.out.println("Programari medici cu CNP: " + cnp);
        for (Programare p : programari) {
            if(p.getMedic().getCnp().equals(cnp)){
                System.out.println(p.toString());
            }
        }
    }

    public Medic gasesteMedic(String cnp) {
        for (Medic m : medici) {
            if (m.getCnp().equals(cnp)) return m;
        }
        return null;
    }

    public Pacient gasestePacient(String cnp) {
        for (Pacient p : pacienti) {
            if (p.getCnp().equals(cnp)) return p;
        }
        return null;
    }

    private boolean disponibilitateMedic(Medic medic, LocalDateTime dataOraNoua, int durataNoua) {
        LocalDateTime sfarsitNou = dataOraNoua.plusMinutes(durataNoua);

        for (Programare p : programari) {
            if (p.getMedic().getCnp().equals(medic.getCnp())) {

                LocalDateTime inceputExistent = p.getDataOra();
                int durataExistenta = p.getServiciu().getDurataMinute();
                LocalDateTime sfarsitExistent = inceputExistent.plusMinutes(durataExistenta);

                if (dataOraNoua.isBefore(sfarsitExistent) && inceputExistent.isBefore(sfarsitNou)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean validitateCnp(String cnp) {
        return cnp != null && cnp.matches("\\d{13}");
    }
}
