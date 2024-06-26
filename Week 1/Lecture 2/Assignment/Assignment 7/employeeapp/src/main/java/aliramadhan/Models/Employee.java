package aliramadhan.Models;

import java.time.LocalDate;

import aliramadhan.Utils.DateUtils;

public class Employee {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String department;

    public Employee(String id, String name, LocalDate dateOfBirth, String address, String department) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.department = department;
    }

    /**
     * Parses an array of attributes into an Employee object.
     *
     * @param attributes an array of strings representing the employee's id, name, date of birth, address, and department.
     * @return an Employee object created from the provided attributes.
     */
    public static Employee fromCSV(String[] attributes) {
        String id = attributes[0];
        String name = attributes[1];
        LocalDate dateOfBirth = DateUtils.parseDate(attributes[2]);
        String address = attributes[3];
        String department = attributes[4];
        return new Employee(id, name, dateOfBirth, address, department);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getDepartment() {
        return department;
    }

    public String toStringCSV() {
        return id + "," + name + "," + dateOfBirth + "," + address + "," + department;
    }
}
