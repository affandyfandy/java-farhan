package aliramadha.employeeapp.controller;

import aliramadha.employeeapp.helper.CSVHelper;
import aliramadha.employeeapp.model.Employee;
import aliramadha.employeeapp.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> listAllEmployee(){
        List<Employee> listEmployee= employeeRepository.findAll();
        if(listEmployee.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployee(@PathVariable("id") String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> findEmployeeByDepartment(@PathVariable("department") String department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        if (employees.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees);
    }

//    @GetMapping("/department/{department}")
//    public ResponseEntity<List<Employee>> findEmployeeByDepartment(@PathVariable("department") String department) {
//        List<Employee> allEmployees = employeeRepository.findAll();
//        List<Employee> employeesInDepartment = allEmployees.stream()
//                .filter(employee -> department.equalsIgnoreCase(employee.getDepartment()))
//                .collect(Collectors.toList());
//
//        if (employeesInDepartment.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(employeesInDepartment);
//    }


    @PostMapping("/create")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employeee){
        return ResponseEntity.ok(employeeRepository.save(employeee));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> addEmployeewithCsv(@RequestParam("file") MultipartFile file) {
        if (!CSVHelper.hasCSVFormat(file)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file format");
        }
        try {
            List<Employee> employees = CSVHelper.csvToEmployees(file.getInputStream());
            employeeRepository.saveAll(employees);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed to upload file: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public  ResponseEntity<Employee> editEmployee(@PathVariable(value = "id") String id,
                                                  @RequestBody Employee employeeform){
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if(existingEmployee.isPresent()){
            Employee employee = existingEmployee.get();
            employee.setName(employeeform.getName());
            employee.setAge(employeeform.getAge());
            employee.setSalary(employee.getSalary());
            employee.setPosition(employeeform.getPosition());
            employee.setDepartment(employeeform.getDepartment());
            Employee updateEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(updateEmployee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") String id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.notFound().build();
    }

}
