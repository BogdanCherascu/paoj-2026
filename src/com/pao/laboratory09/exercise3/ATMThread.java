package com.pao.laboratory09.exercise3;

import java.util.Locale;

public class ATMThread extends Thread {
    private int id;
    private CoadaTranzactii coada;

    public ATMThread(int id, CoadaTranzactii coada) {
        super("ATM-" + id);
        this.id = id;
        this.coada = coada;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 4; i++) {
                int txId = this.id * 100 + i;
                double suma = Math.round((Math.random() * 900 + 100) * 100.0) / 100.0;
                Tranzactie t = new Tranzactie(txId, suma, "2026-05-14");

                System.out.printf(Locale.US, "[%s] trimite: Tranzactie #%d %.2f RON%n",
                        getName(), t.getId(), t.getSuma());

                coada.adauga(t, this.id);

                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}