package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;


public class Main {
    // Calea către fișierul cu date
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        List<Student> studenti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String nume = parts[0].trim();
                    int varsta = Integer.parseInt(parts[1].trim());
                    String oras = parts[2].trim();
                    String strada = parts[3].trim();

                    Adresa adresa = new Adresa(oras, strada);
                    Student student = new Student(nume, varsta, adresa);
                    studenti.add(student);
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            String[] comandaParts = input.split(" ", 2);
            String actiune = comandaParts[0].toUpperCase();

            if (actiune.equals("PRINT")) {
                for (Student s : studenti) {
                    System.out.println(s);
                }
            } else if (actiune.equals("SHALLOW") || actiune.equals("DEEP")) {
                if (comandaParts.length < 2) return;
                String numeCautat = comandaParts[1];
                Student studentGasit = null;

                for (Student s : studenti) {
                    if (s.getNume().equals(numeCautat)) {
                        studentGasit = s;
                        break;
                    }
                }

                if (studentGasit != null) {
                    Student clona;

                    if (actiune.equals("SHALLOW")) {
                        clona = studentGasit.shallowClone();
                    } else {
                        clona = studentGasit.deepClone();
                    }

                    clona.getAdresa().setOras("MODIFICAT");

                    System.out.println("Original: " + studentGasit);
                    System.out.println("Clona: " + clona);
                }
            }
        }
        scanner.close();
    }
}
