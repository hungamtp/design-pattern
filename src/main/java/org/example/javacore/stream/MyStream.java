package org.example.javacore.stream;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class MyStream {
    record Employee(String name, String dept, double salary) {}
    CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>(List.of("A", "B", "C"));
    static List<Employee> employees = List.of(
            new Employee("Alice", "Engineering", 90000),
            new Employee("Bob",   "Engineering", 85000),
            new Employee("Carol", "Marketing",   70000),
            new Employee("Dave",  "Marketing",   75000)
    );
    public static void main(String[] args) {
        // 3 Part in Stream : Source -> Intermediate Operation -> Terminal Operation
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.forEach(System.out::println);
        list.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
        list.stream().findFirst().ifPresent(System.out::println);
        list.stream().max(Integer::compareTo).ifPresent(System.out::println);
        list.stream().reduce(Integer::sum).ifPresent(System.out::println);

        Map<Integer, List<Integer>> byEvenOrOdd = list.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0 ? 0 : 1));

        // Average salary per department
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.averagingDouble(Employee::salary)
                ));

        // Top earner per department
        Map<String, Optional<Employee>> topEarner = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary))
                ));
    }
}
