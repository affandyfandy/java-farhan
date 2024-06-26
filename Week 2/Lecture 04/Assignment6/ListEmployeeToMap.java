package Assignment6;

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

public class ListEmployeeToMap {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "HR"),
                new Employee(2, "Bob", "IT"),
                new Employee(3, "Charlie", "Finance"),
                new Employee(2, "Charlie", "Finance"));
        // before convert
        for (Employee employee : employees) {
            System.out.println("internship " + employee.getDepartment());
        }
        // Convert List to Map using Id as key
        /*
         * not handle duplicate
         * Map<Integer, Employee> employeeMap = employees.stream()
         * .collect(Collectors.toMap(Employee::getId, emp -> emp)); 
         * will be error
         */
        Map<Integer, Employee> employeeMaps = employees.stream()
                .collect(Collectors.toMap(Employee::getId, emp -> emp, (existing, replacement) -> existing));

        // Print the resulting Map + after converts
        /*
         * not handle duplicate
         * employeeMap.forEach((id, emp) -> System.out.println("Employee ID: " + id +
         * ", Employee: " + emp));
         */

        System.out.println("---------------------------------");
        employeeMaps.forEach((id, emp) -> System.out.println("Employee ID 2: " + id + ", Employee: " + emp));
    }
    /*
     * List
     * internship HR
     * internship IT
     * internship Finance
     * Map
     * Employee ID: 1, Employee: Employee{Id=1, name='Alice', department='HR'}
     * Employee ID: 2, Employee: Employee{Id=2, name='Bob', department='IT'}
     * Employee ID: 3, Employee: Employee{Id=3, name='Charlie',
     * department='Finance'}
     */
}
