package com.mccayl;

class SharedCounter {
    private int count = 0;
    private final Object lock = new Object();

    public void waitForCondition() {
        synchronized (lock) {
            while (count < 10) {
                try { lock.wait(); } catch (InterruptedException ignored) {}
            }
            System.out.println("Condition met, thread A resumes.");
        }
    }

    public void increment() {
        synchronized (lock) {
            count++;
            System.out.println("Incremented count: " + count);
            if (count == 10) {
                lock.notify();
            }
        }
    }
}

public class MainEx1 {
    public static void main(String[] args) {
        SharedCounter counter = new SharedCounter();

        Thread threadA = new Thread(counter::waitForCondition);
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();
            }
        });

        threadA.start();
        threadB.start();
    }
}
