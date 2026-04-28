package com.pao.laboratory09.exercise2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class RecordUtils {
    public static final int RECORD_SIZE = 32;

    public static void printRecord(RandomAccessFile raf, int idx) throws IOException {
        raf.seek((long) idx * RECORD_SIZE);

        byte[] record = new byte[RECORD_SIZE];
        raf.readFully(record);

        ByteBuffer bb = ByteBuffer.wrap(record).order(ByteOrder.LITTLE_ENDIAN);

        int id = bb.getInt();
        double suma = bb.getDouble();

        byte[] dataBytes = new byte[10];
        bb.get(dataBytes);
        String data = new String(dataBytes, StandardCharsets.US_ASCII).trim();

        byte tipByte = bb.get();
        String tip = (tipByte == 0) ? "CREDIT" : "DEBIT";

        byte statusByte = bb.get();
        String status = "PENDING";
        if (statusByte == 1) status = "PROCESSED";
        else if (statusByte == 2) status = "REJECTED";

        System.out.printf(Locale.US, "[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s%n",
                idx, id, data, tip, suma, status);
    }
}