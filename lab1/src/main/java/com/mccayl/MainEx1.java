package com.mccayl;

// Реализация через наследование от Thread
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Поток от Thread: " + Thread.currentThread().getName());
    }
}

// Реализация через Runnable
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Поток от Runnable: " + Thread.currentThread().getName());
    }
}

public class MainEx1 {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        thread1.start();

        Thread thread2 = new Thread(new MyRunnable());
        thread2.start();
    }
}