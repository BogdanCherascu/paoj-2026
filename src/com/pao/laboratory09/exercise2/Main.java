package com.pao.laboratory09.exercise2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        new File(OUTPUT_FILE).getParentFile().mkdirs();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            for (int i = 0; i < n; i++) {
                int id = scanner.nextInt();
                double suma = scanner.nextDouble();
                String data = scanner.next();
                String tip = scanner.next();

                byte[] idBytes = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(id).array();
                dos.write(idBytes);

                byte[] sumaBytes = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(suma).array();
                dos.write(sumaBytes);

                String paddedData = String.format("%-10s", data);
                dos.write(paddedData.getBytes(StandardCharsets.US_ASCII));

                byte tipByte = (byte) (tip.equals("CREDIT") ? 0 : 1);
                dos.writeByte(tipByte);

                dos.writeByte(0);

                dos.write(new byte[8]);
            }
        }

        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
            while (scanner.hasNext()) {
                String comanda = scanner.next();

                switch (comanda) {
                    case "READ":
                        int idxRead = scanner.nextInt();
                        RecordUtils.printRecord(raf, idxRead);
                        break;

                    case "UPDATE":
                        int idxUpdate = scanner.nextInt();
                        String newStatus = scanner.next();

                        byte statusByte = 0;
                        if (newStatus.equals("PROCESSED")) statusByte = 1;
                        else if (newStatus.equals("REJECTED")) statusByte = 2;

                        raf.seek((long) idxUpdate * RecordUtils.RECORD_SIZE + 23);
                        raf.writeByte(statusByte);

                        System.out.println("Updated [" + idxUpdate + "]: " + newStatus);
                        break;

                    case "PRINT_ALL":
                        long numRecords = raf.length() / RecordUtils.RECORD_SIZE;
                        for (int i = 0; i < numRecords; i++) {
                            RecordUtils.printRecord(raf, i);
                        }
                        break;
                }
            }
        }

        scanner.close();
    }
}