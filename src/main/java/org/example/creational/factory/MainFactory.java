package org.example.creational.factory;

public class MainFactory {
    public static void main(String[] args) {
        People nurse = PeopleFactory.getComputer("N" , "Nurse" , 14);
        People doctor = PeopleFactory.getComputer("D" , "Doctor" , 14);
        System.out.println(doctor.toString());
        System.out.println(nurse.toString());
    }
}
