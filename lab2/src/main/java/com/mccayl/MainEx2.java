package com.mccayl;

class IncrementThread extends Thread {
    private static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            i++;
            System.out.println(Thread.currentThread().getName() + " i = " + i);
        }
    }
}
public class MainEx2 {
    public static void main(String[] args) {
        Thread t1 = new IncrementThread();
        Thread t2 = new IncrementThread();
        t1.start();
        t2.start();
    }
}
