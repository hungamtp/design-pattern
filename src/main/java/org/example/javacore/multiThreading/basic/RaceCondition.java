package org.example.javacore.multiThreading.basic;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition {
    static class SafeCounter {
        AtomicInteger count = new AtomicInteger(0);

        public void SafeCounter() {
            count.set(0);
        }

        public int getCount() {
            return count.get();
        }

        public void Increment() {
            count.incrementAndGet(); // Atomic operation
        }
    }

    static class Counter {
        int count = 0;

        void increment() {
            count++; // NOT atomic — 3 steps: read → modify → write
        }
    }

    // Thread 1 reads count = 5
    // Thread 2 reads count = 5  ← both read same value
    // Thread 1 writes count = 6
    // Thread 2 writes count = 6  ← Thread 1's increment LOST 💀
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        Thread t6 =Thread.ofVirtual().start(() -> {
            for (int i = 0; i < 1000; i++) {
                c.increment();
            }
        });
        Thread t5 =Thread.ofVirtual().start(() -> {
            for (int i = 0; i < 1000; i++) {
                c.increment();
            }
        });
        Thread t4 =Thread.ofVirtual().start(() -> {
            for (int i = 0; i < 1000; i++) {
                c.increment();
            }
        });
        t4.join();
        t5.join();
        t6.join();

        System.out.println(c.count); // Expected 3000, but often less due to race condition


        // safe counter
        SafeCounter sc = new SafeCounter();
        Thread t1 = Thread.ofVirtual().start(() -> {
            for (int i = 0; i < 1000; i++) sc.Increment();
        });
        Thread t2 = Thread.ofVirtual().start(() -> {
            for (int i = 0; i < 1000; i++) sc.Increment();
        });
        Thread t3 = Thread.ofVirtual().start(() -> {
            for (int i = 0; i < 1000; i++) sc.Increment();
        });

        t1.join(); // main thread waits for t1 to finish
        t2.join(); // main thread waits for t2 to finish
        t3.join(); // main thread waits for t3 to finish

        System.out.println(sc.getCount()); // ✅ guaranteed 3000
    }
}
