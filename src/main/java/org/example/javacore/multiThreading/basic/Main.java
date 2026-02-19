package org.example.javacore.multiThreading.basic;

import static java.lang.Thread.sleep;

public class Main {

    //ABBAABBABABABABABABA
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        MyRunnable myRunnable = new MyRunnable();
        myThread.start();
        myRunnable.run();
        for (int i = 0; i < 10; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("A");
        }

    }
    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("B");
            }
        }
    }
    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("C");
            }
        }
    }
}
