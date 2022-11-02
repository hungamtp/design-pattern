package org.example.creational.factory;

public class PeopleFactory {
    public static People getComputer(String type, String name , Integer age){
        if("N".equalsIgnoreCase(type)) return new Nurse(name , age);
        else if("D".equalsIgnoreCase(type)) return new Doctor(name , age);
        return null;
    }
}
