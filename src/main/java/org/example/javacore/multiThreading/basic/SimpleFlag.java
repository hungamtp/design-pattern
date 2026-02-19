package org.example.javacore.multiThreading.basic;

public class SimpleFlag {
    volatile boolean featureEnabled = false; // read from main memory always

    // Thread 1
    void enable() {
        featureEnabled = true;
    }

    // Thread 2
    void check() {
        if (featureEnabled) applyFeature(); // always sees latest value ✅
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleFlag flag = new SimpleFlag();
        Thread t1 = Thread.ofVirtual().start(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag.enable();
        });
        Thread t2 = Thread.ofVirtual().start(() -> {
            while (!flag.featureEnabled) {
                System.out.println("Feature not enabled yet, waiting...");
            }
            System.out.println("Feature enabled, applying...");
        });
        t1.join();
        t2.join();
    }

    static void applyFeature() {
        System.out.println("Applying feature...");
    }
}
