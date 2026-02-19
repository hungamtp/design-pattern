package org.example.javacore.multiThreading.basic;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Main {

    //ABBAABBABABABABABABA
    public static void main(String[] args) throws ExecutionException, InterruptedException {
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

        // 3. Implement Callable — returns value, can throw checked exception
        Callable<String> callable = () -> {
            Thread.sleep(500);
            return "Callable result";
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(callable);
        System.out.println(future.get());

        // 4. Virtual Thread (Java 21)
        Thread.ofVirtual().start(() -> System.out.println("Virtual thread"));
        executor.shutdown(); // Don't forget to shut down the executor
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
