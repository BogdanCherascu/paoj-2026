package com.pao.laboratory09.exercise3;

import java.util.LinkedList;
import java.util.Queue;

public class CoadaTranzactii {
    private final Queue<Tranzactie> banda = new LinkedList<>();
    private final int MAX_CAPACITY = 5;

    private ProcessorThread consumer;

    public void setConsumer(ProcessorThread consumer) {
        this.consumer = consumer;
    }

    public synchronized void adauga(Tranzactie t, int atmId) throws InterruptedException {
        while (banda.size() == MAX_CAPACITY) {
            System.out.println("[ATM-" + atmId + "] astept loc...");
            wait();
        }

        banda.add(t);
        notifyAll();
    }

    public synchronized Tranzactie extrage() throws InterruptedException {
        while (banda.isEmpty() && (consumer == null || consumer.activ)) {
            wait();
        }

        if (banda.isEmpty() && consumer != null && !consumer.activ) {
            return null;
        }

        Tranzactie t = banda.poll();
        notifyAll();
        return t;
    }

    public synchronized boolean isEmpty() {
        return banda.isEmpty();
    }
}