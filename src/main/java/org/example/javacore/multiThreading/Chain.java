package org.example.javacore.multiThreading;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class Chain {
    static class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    public static void main(String[] args) {
        CompletableFuture<User> cf = CompletableFuture.supplyAsync(() -> {
            try {
                return fetchUser(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Non-blocking — registers a callback, returns immediately
        cf.thenApply(User::getName)
                .thenAccept(name -> System.out.println("Hello " + name));
        cf.join(); // Wait for the chain to complete before exiting main
        System.out.println("Fetching user...");

    }


    public static User fetchUser(int id) throws InterruptedException {
        sleep(1000); // Simulate delay
        return new User("Alice");
    }

}
