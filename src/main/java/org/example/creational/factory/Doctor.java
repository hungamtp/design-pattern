package org.example.creational.factory;


public class Doctor extends People{
    private String name;
    private Integer age;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getAge() {
        return age;
    }
    public Doctor(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
