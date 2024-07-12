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

    public static Employee fromCSV(String[] attributes) {
        String id = attributes[0];
        String name = attributes[1];
        int age = Integer.parseInt(attributes[2]);
        String department = attributes[3];
        String position = attributes[4];
        double salary = Double.parseDouble(attributes[5]);
        String email = attributes[6];
        String phoneNumber = attributes[7];
        LocalDate dob = DateUtils.parseDate(attributes[8]);
        return new Employee(id, name, age, department, position, salary, email, phoneNumber,dob);
    }
}



//private static Employee fromCSV(String[] attributes) {
//    String id = attributes[0];
//    String name = attributes[1];
//    int age = Integer.parseInt(attributes[2]);
//    String department = attributes[3];
//    String position = attributes[4];
//    double salary = Double.parseDouble(attributes[5]);
//    String email = attributes[6];
//    String phoneNumber = attributes[7];
//    LocalDate dob = DateUtils.parseDate(attributes[8]);
//    return new Employee(id, name, age, department, position, salary, email, phoneNumber,dob);
//}