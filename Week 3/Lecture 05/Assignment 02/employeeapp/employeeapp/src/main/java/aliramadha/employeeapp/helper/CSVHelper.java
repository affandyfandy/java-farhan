package aliramadha.employeeapp.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import aliramadha.employeeapp.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "name", "age", "department", "position", "salary" };
    private static final Logger logger = LoggerFactory.getLogger(CSVHelper.class);

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            logger.error("Invalid file type: " + file.getContentType());
            return false;
        }
        return true;
    }

    public static List<Employee> csvToEmployees(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Employee> employees = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Employee employee = new Employee();

                employee.setName(csvRecord.get("name"));
                employee.setAge(Integer.parseInt(csvRecord.get("age")));
                employee.setDepartment(csvRecord.get("department"));
                employee.setPosition(csvRecord.get("position"));
                employee.setSalary(Double.parseDouble(csvRecord.get("salary")));

                employees.add(employee);
            }

            return employees;
        } catch (IOException e) {
            logger.error("Failed to parse CSV file: " + e.getMessage());
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }
}
