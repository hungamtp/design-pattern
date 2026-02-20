package org.example.javacore.multiThreading.reentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Run it
        BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(3);

        // 2 producers
        Thread p1 = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) queue.put(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread p2 = new Thread(() -> {
            try {
                for (int i = 6; i <= 10; i++) queue.put(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

// 1 consumer (slower)
        Thread c1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(200); // consume slower than produce
                    queue.take();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        p1.start();
        p2.start();
        c1.start();
        p1.join();
        p2.join();
        c1.join();
//```
//
//**Output:**
//```
//        Produced: 1 | size: 1
//        Produced: 2 | size: 2
//        Produced: 3 | size: 3
//        Queue full — producer waiting...   ← p1 waits in notFull room
//        Queue full — producer waiting...   ← p2 waits in notFull room
//        Consumed: 1 | size: 2              ← consumer takes, signals notFull
//        Produced: 4 | size: 3              ← p1 wakes up
//        Queue full — producer waiting...
//        Consumed: 2 | size: 2
//...
//```
//
//        ---
//
//### How await() Works Internally
//```
//        Thread calls await():
//        1. releases the lock  ← other threads can now acquire it
//        2. suspends itself    ← goes into Condition's wait queue
//        3. waits for signal()
//
//        Thread receives signal():
//        1. moves from Condition wait queue → lock wait queue
//        2. re-acquires the lock
//        3. continues from where it left off
//```
//```
//        notFull wait queue:  [Producer1, Producer2]  ← await() here
//        notEmpty wait queue: [Consumer1]             ← await() here
//
//        Consumer takes item → notFull.signal()
//  → Producer1 moves to lock queue
//  → Producer1 re-acquires lock
//  → Producer1 continues past the while loop
    }
}
