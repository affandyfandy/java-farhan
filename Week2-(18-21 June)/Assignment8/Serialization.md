# Serialization & serialVersionUID

## Se

Serialization is a mechanism of converting the state of an object into a byte stream.
Deserialization is the reverse process where the byte stream is used to recreate the actual Java object in memory.
!["serial vs deserial"](./serial.png)

`Here are some examples of using serialization`:

- Storing data in an object-oriented way to files on disk, e.g. storing a list of Student objects.

- Saving program’s states on disk, e.g. saving state of a game.

- Sending data over the network in form objects, e.g. sending messages as objects in chat application.

## SerialVersionUID

`serialVersionUID` is a unique identifier for a Serializable class in Java. It is used during the deserialization process to ensure that a loaded class corresponds exactly to a serialized object. If the serialVersionUID of the loaded class does not match the serialVersionUID of the serialized object, an InvalidClassException is thrown. `serialVersionUID` is the serialization runtime associates with each `serializable class a version number`

## Usage

The serialVersionUID attribute to remember versions of a Serializable class to verify that a loaded class and the serialized object are compatible.
A serializable class can declare its own `serialVersionUID` explicitly by declaring a field named serialVersionUID that must be `static`, `final`, and of type `long`:

```java
ANY-ACCESS-MODIFIER static final long serialVersionUID = 42L;
```

Advantages of Serialization

- To save/persist state of an object.
- To travel an object across a network.

## Example

```java
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization process from file
        List<Employee> deserializedEmployees = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedEmployees = (List<Employee>) ois.readObject();
            System.out.println("Employee list was deserialized: " + deserializedEmployees);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Print the deserialized employee list
        if (deserializedEmployees != null) {
            for (Employee employee : deserializedEmployees) {
                System.out.println(employee);
            }
        }
    }
}
```
