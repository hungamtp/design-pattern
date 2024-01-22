package org.example.creational.singleton;

import java.text.Normalizer;

public class EagerInitializedSingleton {

    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    //private constructor to avoid client applications to use constructor
    private EagerInitializedSingleton(){}

    public static EagerInitializedSingleton getInstance(){

        return instance;
    }

//    public static ThreadSafeSingleton getInstanceUsingDoubleLocking() {
//        if (instance == null) {
//            synchronized (ThreadSafeSingleton.class) {
//                if (instance == null) {
//                    instance = new ThreadSafeSingleton();
//                }
//            }
//        }
//        return instance;
//    }
}
