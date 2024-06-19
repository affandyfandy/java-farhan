package Assignment5;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

class Employee {
    private int Id;
    private String name;
    private String department;

    public Employee(int Id, String name, String department) {
        this.Id = Id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

public class Q5 {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "HR"),
                new Employee(2, "Bob", "IT"),
                new Employee(3, "Charlie", "Finance"));
        // before convert
        for (Employee employee : employees) {
            System.out.println("internship " + employee.getDepartment());
        }
        // Convert List to Map using Id as key
        Map<Integer, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getId, emp -> emp));

        // Print the resulting Map + after converts
        employeeMap.forEach((id, emp) -> System.out.println("Employee ID: " + id + ", Employee: " + emp));
    }

}
