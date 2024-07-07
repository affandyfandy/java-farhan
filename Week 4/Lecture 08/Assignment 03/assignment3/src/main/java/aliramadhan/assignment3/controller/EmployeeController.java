package aliramadhan.assignment3.controller;


import aliramadhan.assignment3.model.Employee;
import aliramadhan.assignment3.service.EmployeeService;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/ds1")
    public ResponseEntity<List<Employee>> getAllEmployeesDb1() {
        var listEmployee = employeeService.findAllFromDs1();
        if (listEmployee.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listEmployee);
    }

    @GetMapping("/ds2")
    public ResponseEntity<List<Employee>> getAllEmployeesDb2() {
        var listEmployee = employeeService.findAllFromDs2();
        if (listEmployee.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listEmployee);
    }

    @GetMapping("/ds1/{id}")
    public ResponseEntity<Employee> getEmployeeByIdDb1(@PathVariable String id) {
        var employee = employeeService.findByIdFromDs1(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/ds2/{id}")
    public ResponseEntity<Employee> getEmployeeByIdDb2(@PathVariable String id) {
        var employee = employeeService.findByIdFromDs2(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/ds1")
    public ResponseEntity<Employee> addEmployeeDb1(@RequestBody Employee employee) {
        try {
            var findEmployee = employeeService.findByIdFromDs1(employee.getId());
            if (findEmployee != null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(employeeService.saveToDs1(employee));
        } catch (Exception e) {
            System.out.println("Transaction failed. All successful operations will be rolled back.");
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/ds2")
    public ResponseEntity<Employee> addEmployeeDb2(@RequestBody Employee employee) {
        try {
            var findEmployee = employeeService.findByIdFromDs2(employee.getId());
            if (findEmployee != null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(employeeService.saveToDs2(employee));
        } catch (Exception e) {
            System.out.println("Transaction failed. All successful operations will be rolled back.");
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/ds1/{id}")
    public ResponseEntity<Employee> updateEmployeeDb1(@PathVariable String id, @RequestBody Employee employee) {
        try{
            var findEmployee = employeeService.findByIdFromDs1(id);
            if (findEmployee != null) {
                employee.setId(findEmployee.getId());
                return ResponseEntity.ok(employeeService.updateToDs1(employee));
            }
            return ResponseEntity.badRequest().build();}
        catch (Exception e) {
            System.out.println("Transaction failed. All successful operations will be rolled back.");
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/ds2/{id}")
    public ResponseEntity<Employee> updateEmployeeDb2(@PathVariable String id, @RequestBody Employee employee) {
        try{
            var findEmployee = employeeService.findByIdFromDs2(id);
            if (findEmployee != null) {
                employee.setId(findEmployee.getId());
                return ResponseEntity.ok(employeeService.updateToDs2(employee));
            }
            return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            System.out.println("Transaction failed. All successful operations will be rolled back.");
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/ds1/{id}")
    public ResponseEntity<String> deleteEmployeeOnDb1(@PathVariable String id) {
        try{
            var employee = employeeService.findByIdFromDs1(id);
            if (employee != null) {
                return ResponseEntity.ok(employeeService.deleteFromDs1(id));
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e) {
            System.out.println("Transaction failed. All successful operations will be rolled back.");
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/ds2/{id}")
    public ResponseEntity<String> deleteEmployeeOnDb2(@PathVariable String id) {
        try{
            var employee = employeeService.findByIdFromDs2(id);
            if (employee != null) {
                return ResponseEntity.ok(employeeService.deleteFromDs2(id));
            }
            return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            System.out.println("Transaction failed. All successful operations will be rolled back.");
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}