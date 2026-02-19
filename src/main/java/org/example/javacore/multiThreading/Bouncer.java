package org.example.javacore.multiThreading;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// Rate limiting
public class Bouncer {
    public static void main(String[] args) {
        // Allow only 50 concurrent API calls at a time

        Semaphore bouncer = new Semaphore(5);
        int[] employeeIds = new int[1000];
        for (int i = 0; i < employeeIds.length; i++) {
            employeeIds[i] = i + 1;
        }


        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int id : employeeIds) {
                executor.submit(() -> {
                    bouncer.acquire(); // Wait here if 50 are already running
                    try {
                        return fetchEmployee(id);
                    } finally {
                        bouncer.release(); // Make room for the next one
                    }
                });
            }
        }
    }

    public static int fetchEmployee(int id) {
        System.out.println("Fetching employee " + id);
        // Simulate API call
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return id;
    }


}
