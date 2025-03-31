package com.mccayl;

import java.util.Random;
import java.util.Vector;

class WriterThread extends Thread {
    private final Vector<Double> vector;
    private final Random random = new Random();

    public WriterThread(Vector<Double> vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        for (int i = 0; i < vector.size(); i++) {
            double value = random.nextDouble() * 100;
            vector.set(i, value);
            System.out.println("Write: " + value + " to position " + i);
        }
    }
}

class ReaderThread extends Thread {
    private final Vector<Double> vector;

    public ReaderThread(Vector<Double> vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        for (int i = 0; i < vector.size(); i++) {
            double value = vector.get(i);
            System.out.println("Read: " + value + " from position " + i);
        }
    }
}

public class MainEx2 {
    public static void main(String[] args) {
        Vector<Double> vector = new Vector<>(10);
        for (int i = 0; i < 10; i++) vector.add(0.0);

        WriterThread writer = new WriterThread(vector);
        ReaderThread reader = new ReaderThread(vector);

        writer.setPriority(Thread.MAX_PRIORITY);
        reader.setPriority(Thread.MIN_PRIORITY);

        writer.start();
        reader.start();
    }
}