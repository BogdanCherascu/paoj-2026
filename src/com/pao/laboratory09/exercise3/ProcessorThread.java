package com.pao.laboratory09.exercise3;

import java.util.Locale;

public class ProcessorThread implements Runnable {
    private CoadaTranzactii coada;
    public volatile boolean activ = true;

    public ProcessorThread(CoadaTranzactii coada) {
        this.coada = coada;
        this.coada.setConsumer(this);
    }

    @Override
    public void run() {
        try {
            while (activ) {
                Tranzactie t = coada.extrage();

                if (t != null) {
                    System.out.printf(Locale.US, "[Processor] Factura #%d - %.2f RON | %s%n",
                            t.getId(), t.getSuma(), t.getData());
                    Thread.sleep(80);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}