package com.mccayl;

import java.util.Vector;

class SharedBuffer {
    private final Vector<Double> vector = new Vector<>();
    private boolean canRead = false;

    public synchronized void write(double value) {
        while (canRead) {
            try { wait(); } catch (InterruptedException ignored) {}
        }
        vector.add(value);
        System.out.println("Write: " + value);
        canRead = true;
        notify();
    }

    public synchronized double read() {
        while (!canRead) {
            try { wait(); } catch (InterruptedException ignored) {}
        }
        double value = vector.remove(0);
        System.out.println("Read: " + value);
        canRead = false;
        notify();
        return value;
    }
}

class WriterRunnable implements Runnable {
    private final SharedBuffer buffer;

    public WriterRunnable(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            buffer.write(Math.random() * 100);
        }
    }
}

class ReaderRunnable implements Runnable {
    private final SharedBuffer buffer;

    public ReaderRunnable(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            buffer.read();
        }
    }
}

public class MainEx3 {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();
        new Thread(new WriterRunnable(buffer)).start();
        new Thread(new ReaderRunnable(buffer)).start();
    }
}