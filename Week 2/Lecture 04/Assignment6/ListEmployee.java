package Assignment6;

import java.util.*;
import java.util.stream.*;

class Employees {
    private int id;
    private String name;
    private int age;
    private double salary;

    public Employees(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}

public class ListEmployee {
    public static void main(String[] args) {
        List<Employees> employees = Arrays.asList(
                new Employees(1, "Alice", 30, 50000),
                new Employees(2, "Bob", 25, 60000),
                new Employees(3, "Charlie", 35, 70000),
                new Employees(4, "David", 28, 40000),
                new Employees(5, "Eve", 22, 30000));

        // a) Sort name in alphabetical, ascending using streams
        List<Employees> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employees::getName))
                .collect(Collectors.toList());
        System.out.println("Sorted Employees by Name:");
        sortedEmployees.forEach(System.out::println);

        // b) Find employee has max salary using streams
        Optional<Employees> maxSalaryEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employees::getSalary));
        maxSalaryEmployee.ifPresent(emp -> System.out.println("Employee with Max Salary: " + emp));

        // c) Check any employee names match with specific keywords or not
        String keyword = "Alice";
        boolean isMatch = employees.stream()
                .anyMatch(emp -> emp.getName().equalsIgnoreCase(keyword));
        System.out.println("Is there any employee with the name '" + keyword + "': " + isMatch);
    }
}
