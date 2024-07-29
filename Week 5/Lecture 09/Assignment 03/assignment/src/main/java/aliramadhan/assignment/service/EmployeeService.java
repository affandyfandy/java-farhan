package aliramadhan.assignment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import aliramadhan.assignment.model.Employee;
import aliramadhan.assignment.utils.FileUtils;
import aliramadhan.assignment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployees(Pageable pageable) {
        // return employeeRepository.findAll(pageable);
        if (pageable == null) {
            throw new IllegalArgumentException("Invalid pagination and sorting parameters: null object");
        }
        return employeeRepository.findAll(pageable);
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(String id) {
        Optional<Employee> result = employeeRepository.findById(id);
        return result.orElse(null);
    }

    public void deleteEmployeeById(String id) {
        employeeRepository.deleteById(id);
    }

    public void saveAllEmployees(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    public void uploadCsv(MultipartFile file) {
        try {
            List<Employee> employees = FileUtils.readEmployeesFromCSV(file);
            employeeRepository.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload CSV file: " + e.getMessage());
        }
    }

    // Generate PDF Data

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Double findAverageSalary() {
        return employeeRepository.findAverageSalary();
    }

    public Optional<Integer> findMaxSalary() {
        return employeeRepository.findMaxSalary();
    }

    public Optional<Integer> findMinSalary() {
        return employeeRepository.findMinSalary();
    }

    public List<String> findEmployeeWithHighestSalary() {
        return employeeRepository.findEmployeeHighestSalary();
    }

    public List<String> findEmployeeWithLowestSalary() {
        return employeeRepository.findEmployeeLowestSalary();
    }
}
