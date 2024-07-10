package aliramadhan.assignment.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import aliramadhan.assignment.model.Employee;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
    public static List<Employee> readEmployeesFromCSV(MultipartFile file) throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                Employee employee = fromCSV(attributes);
                employees.add(employee);
            }
        } catch (IOException e) {
            throw new IOException("Error reading employee (Manual) " + e);
        }
        return employees;
    }

    /**
     * Parses an array of attributes into an Employee object.
     *
     * @param attributes an array of strings representing the employee's id, name,
     *                   date of birth, address, and department.
     * @return an Employee object created from the provided attributes.
     */
    public static Employee fromCSV(String[] attributes) {
        String id = attributes[0];
        String name = attributes[1];
        LocalDate dob = DateUtils.parseDate(attributes[2]);
        String address = attributes[3];
        String department = attributes[4];
        Integer salary = Integer.parseInt(attributes[5]); // Parse salary as Integer
        return new Employee(id, name, dob, address, department, salary);
    }
}
