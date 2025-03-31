package com.mccayl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer extends Thread {
    private final BlockingQueue<Integer> buffer;

    public Producer(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = (int) (Math.random() * 100);
                buffer.put(item);
                System.out.println("Producer " + Thread.currentThread().getName() + " produced: " + item);
                Thread.sleep(1000); // Симулируем задержку
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer extends Thread {
    private final BlockingQueue<Integer> buffer;

    public Consumer(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = buffer.take();
                System.out.println("Consumer " + Thread.currentThread().getName() + " consumed: " + item);
                Thread.sleep(1500); // Симулируем задержку
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class MainEx4 {
    public static void main(String[] args) {
        BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(10); // Кольцевой буфер с размером 10

        // Запускаем несколько производителей
        for (int i = 0; i < 1; i++) {
            new Producer(buffer).start();
        }

        // Запускаем несколько потребителей
        for (int i = 0; i < 1; i++) {
            new Consumer(buffer).start();
        }
    }
}
