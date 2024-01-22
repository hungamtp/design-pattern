package org.example.creational.factory;

public abstract class People {
    public String color;
    public abstract String getName();
    public abstract Integer getAge();

    public String toString() {
        return getName() + "_" + getAge();
    }

}
