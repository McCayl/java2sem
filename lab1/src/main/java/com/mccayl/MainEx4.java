package com.mccayl;

import java.util.Vector;

class SynchronizedVectorWrapper<E> extends Vector<E> {
    @Override
    public synchronized boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SynchronizedVectorWrapper)) return false;
        return super.equals(obj);
    }

    @Override
    public synchronized int hashCode() {
        return super.hashCode();
    }

    @Override
    public synchronized String toString() {
        return super.toString();
    }
}

public class MainEx4 {
    public static void main(String[] args) {
        SynchronizedVectorWrapper<Integer> vector = new SynchronizedVectorWrapper<>();
        new Thread(() -> {
            vector.add(10);
            vector.add(20);
        }).start();
        new Thread(() -> {
            System.out.println("Vector content: " + vector);
            System.out.println("Vector content: " + vector.hashCode());
        }).start();
    }
}