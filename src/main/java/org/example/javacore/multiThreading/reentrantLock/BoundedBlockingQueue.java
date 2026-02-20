package org.example.javacore.multiThreading.reentrantLock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int MAX_SIZE;
    private final ReentrantLock lock = new ReentrantLock();

    // two separate waiting rooms
    private final Condition notFull  = lock.newCondition(); // producers wait here
    private final Condition notEmpty = lock.newCondition(); // consumers wait here

    BoundedBlockingQueue(int maxSize) {
        this.MAX_SIZE = maxSize;
    }

    // PRODUCER
    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            // wait while full
            while (queue.size() == MAX_SIZE) {
                System.out.println("Queue full — producer waiting...");
                notFull.await(); // releases lock + waits in notFull room
            }

            queue.add(item);
            System.out.println("Produced: " + item + " | size: " + queue.size());

            notEmpty.signal(); // wake ONE consumer — queue has item now
        } finally {
            lock.unlock();
        }
    }

    // CONSUMER
    public T take() throws InterruptedException {
        lock.lock();
        try {
            // wait while empty
            while (queue.isEmpty()) {
                System.out.println("Queue empty — consumer waiting...");
                notEmpty.await(); // releases lock + waits in notEmpty room
            }

            T item = queue.poll();
            System.out.println("Consumed: " + item + " | size: " + queue.size());

            notFull.signal(); // wake ONE producer — queue has space now
            return item;
        } finally {
            lock.unlock();
        }
    }
}
