package Assignment8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int id;
    private transient double salary; // transient field will not be serialized

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + ", salary=" + salary + "}";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 101, 50000.0));
        employees.add(new Employee("Bob", 102, 60000.0));

        String filename = "./Assignment8/Employees.ser";

        // Serialization process into file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(employees);
            System.out.println("Employee list was serialized: " + employees);
            /*
             * Employee list was serialized: [Employee{name='Alice', id=101,
             * salary=50000.0}, Employee{name='Bob', id=102, salary=60000.0}]
             */
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization process from file
        List<Employee> deserializedEmployees = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedEmployees = (List<Employee>) ois.readObject();
            System.out.println("Employee list was deserialized: " + deserializedEmployees);
            /*
             * Employee list was deserialized: [Employee{name='Alice', id=101, salary=0.0},
             */
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Print the deserialized employee list
        if (deserializedEmployees != null) {
            for (Employee employee : deserializedEmployees) {
                System.out.println(employee);
                /*
                 * Employee{name='Bob', id=102, salary=0.0}]
                 * Employee{name='Alice', id=101, salary=0.0}
                 * Employee{name='Bob', id=102, salary=0.0}
                 */
            }
        }
    }
}
