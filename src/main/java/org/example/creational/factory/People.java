package org.example.creational.factory;

public abstract class People {
    public abstract String getName();
    public abstract Integer getAge();

    public String toString() {
        return getName() + "_" + getAge();
    }

}
