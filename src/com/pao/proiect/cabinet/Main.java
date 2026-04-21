package com.pao.proiect.cabinet;

import com.pao.proiect.cabinet.model.Medic;
import com.pao.proiect.cabinet.model.Pacient;
import com.pao.proiect.cabinet.model.Programare;
import com.pao.proiect.cabinet.model.ServiciuMedical;
import com.pao.proiect.cabinet.model.Specializare;
import com.pao.proiect.cabinet.service.CabinetService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CabinetService service = new CabinetService();
        Scanner scanner = new Scanner(System.in);
        boolean ruleaza = true;

        while (ruleaza) {
            System.out.println("\n       MENIU CABINET MEDICAL      ");
            System.out.println("1. Adauga Medic nou");
            System.out.println("2. Adauga Pacient nou");
            System.out.println("3. Creeaza Programare");
            System.out.println("4. Anuleaza o programare (dupa ID)");
            System.out.println("5. Sterge un medic (dupa CNP)");
            System.out.println("6. Afiseaza toti medicii");
            System.out.println("7. Afiseaza toti pacientii");
            System.out.println("8. Afiseaza toate programarile (Cronologic)");
            System.out.println("9. Cauta medici dupa specializare");
            System.out.println("10. Afiseaza programarile unui pacient (dupa CNP)");
            System.out.println("11. Afiseaza programarile unui medic (dupa CNP)");
            System.out.println("0. Iesire");
            System.out.print("Alege o opțiune: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    System.out.print("Nume Medic: "); String numeM = scanner.nextLine();
                    System.out.print("Prenume: "); String prenumeM = scanner.nextLine();
                    System.out.print("CNP: "); String cnpM = scanner.nextLine();
                    System.out.print("Specializare (CARDIOLOGIE, DERMATOLOGIE, STOMATOLOGIE, PEDIATRIE, MEDICINA_DE_FAMILIE): ");
                    Specializare spec = Specializare.valueOf(scanner.nextLine().toUpperCase());
                    service.adaugaMedic(new Medic(numeM, prenumeM, cnpM, "0000", spec, 5000));
                    break;

                case 2:
                    System.out.print("Nume Pacient: "); String numeP = scanner.nextLine();
                    System.out.print("Prenume: "); String prenumeP = scanner.nextLine();
                    System.out.print("CNP: "); String cnpP = scanner.nextLine();
                    service.adaugaPacient(new Pacient(numeP, prenumeP, cnpP, "0000", "Fără", "Necunoscută"));
                    break;

                case 3:
                    System.out.println("\nCreare Programare Noua");
                    System.out.print("Introdu CNP-ul pacientului: ");
                    String cnpPacient = scanner.nextLine();
                    Pacient pacientGasit = service.gasestePacient(cnpPacient);

                    System.out.print("Introdu CNP-ul medicului: ");
                    String cnpMedic = scanner.nextLine();
                    Medic medicGasit = service.gasesteMedic(cnpMedic);

                    if (pacientGasit == null || medicGasit == null) {
                        System.out.println("Pacientul sau medicul nu exista in sistem.");
                        break;
                    }

                    System.out.println("Detalii Serviciu Medical");
                    System.out.print("Denumire interventie: ");
                    String denumireServiciu = scanner.nextLine();
                    System.out.print("Pret (RON): ");
                    double pret = scanner.nextDouble();
                    System.out.print("Durata estimata (minute): ");
                    int durata = scanner.nextInt();
                    scanner.nextLine();
                    ServiciuMedical serviciuNou = new ServiciuMedical(denumireServiciu, pret, durata);

                    System.out.print("Introdu data si ora programarii (Format: YYYY-MM-DD HH:MM): ");
                    String dataText = scanner.nextLine();
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime dataOra = LocalDateTime.parse(dataText, formatter);

                        Programare programareNoua = new Programare(pacientGasit, medicGasit, serviciuNou, dataOra);
                        service.creeazaProgramare(programareNoua);
                    } catch (Exception e) {
                        System.out.println("Formatul datei este incorect, programarea nu a putut fi efectuata cu success.");
                    }
                    break;

                case 4:
                    System.out.print("Introdu ID-ul programarii pe care vrei sa o anulezi: ");
                    int idDeSters = scanner.nextInt();
                    scanner.nextLine();
                    service.anuleazaProgramare(idDeSters);
                    break;

                case 5:
                    System.out.print("Introdu CNP-ul medicului pe care vrei sa il stergi: ");
                    String cnpDeSters = scanner.nextLine();
                    service.stergeMedic(cnpDeSters);
                    break;

                case 6:
                    service.afiseazaMedicii();
                    break;

                case 7:
                    service.afiseazaPacientii();
                    break;

                case 8:
                    service.afiseazaProgramariCronologic();
                    break;

                case 9:
                    System.out.print("Introdu specializarea cautata (ex: CARDIOLOGIE): ");
                    try {
                        Specializare specCautata = Specializare.valueOf(scanner.nextLine().toUpperCase());
                        service.cautaMediciDupaSpecializare(specCautata);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Specializarea introdusa nu exista in sistem.");
                    }
                    break;

                case 10:
                    System.out.print("Introdu CNP-ul pacientului: ");
                    String cnpPacientProgramari = scanner.nextLine();
                    service.afiseazaProgramariPacient(cnpPacientProgramari);
                    break;

                case 11:
                    System.out.print("Introdu CNP-ul medicului: ");
                    String cnpMedicProgramari = scanner.nextLine();
                    service.afiseazaProgramariMedic(cnpMedicProgramari);
                    break;

                case 0:
                    System.out.println("La revedere!");
                    ruleaza = false;
                    break;

                default:
                    System.out.println("Optiune invalida, alegeti un numar intre 0 si 11.");
            }
        }
        scanner.close();
    }
}
