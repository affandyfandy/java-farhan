package aliramadhan.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import aliramadhan.Models.Employee;

public class FileUtils {
    private static final String DEFAULT_FOLDER = "export";
    /**
     * Import using manual parsing.
     */
    public static List<Employee> readEmployeesFromCSVManual(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                Employee employee = Employee.fromCSV(attributes);
                employees.add(employee);
            }
            System.out.println("Data imported successfully.");
        } catch (IOException e) {
            System.out.println("Error reading employee (Manual) " + e);
        }
        return employees;
    }

    /**
     * Import using OpenCSV library.
     */
    public static List<Employee> readEmployeesFromCSVOpenCSV(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header
            while ((line = reader.readNext()) != null) {
                Employee employee = Employee.fromCSV(line);
                employees.add(employee);
            }
            System.out.println("Data imported successfully.");
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error reading employee (OpenCSV)" + e);
        }
        return employees;
    }

    /**
     * Writes a list of {@link Employee} objects to a CSV file or Export data into
     * CSV file.
     */
    public static void writeEmployeesToCSV(List<Employee> employees, String filePath) {
        if (!filePath.contains("/")) {
            filePath = Paths.get(DEFAULT_FOLDER, filePath).toString();
        }
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("ID,Name,DateOfBirth,Address,Department\n");
            for (Employee employee : employees) {
                writer.append(employee.toStringCSV()).append("\n");
            }
            System.out.println("Filtered data exported successfully.");
        } catch (IOException e) {
            System.out.println("Error writing employee" + e);
            System.out.println("Error creating directory: " + e);
            return;
        }
    }
}