package org.example.creational.factory;


public class Nurse extends People{
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
    public Nurse (String name , Integer age) {
        this.name = name;
        this.age = age;
    }
}
