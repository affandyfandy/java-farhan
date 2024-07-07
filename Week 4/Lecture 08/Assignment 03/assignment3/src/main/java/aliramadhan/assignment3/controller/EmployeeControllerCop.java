//package aliramadhan.assignment3.controller;
//
//import aliramadhan.assignment3.model.Employee;
//import aliramadhan.assignment3.service.EmployeeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/v1/employee")
//@RequiredArgsConstructor
//public class EmployeeControllerCop {
//
//    private final EmployeeService employeeService;
//
//    @GetMapping("/ds1")
//    public ResponseEntity<List<Employee>> listAllEmployeeFromDataSource1() {
//        List<Employee> employees = employeeService.findAllFromDS1();
//        if (employees.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(employees);
//    }
//
//    @GetMapping("/ds2")
//    public ResponseEntity<List<Employee>> listAllEmployeeFromDataSource2() {
//        List<Employee> employees = employeeService.findAllFromDS2();
//        if (employees.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(employees);
//    }
//
//    @GetMapping("/ds1/{id}")
//    public ResponseEntity<Employee> findEmployeeByIdFromDataSource1(@PathVariable("id") String id) {
//        Optional<Employee> employeeOpt = employeeService.findByIdFromDS1(id);
//        return employeeOpt.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/ds2/{id}")
//    public ResponseEntity<Employee> findEmployeeByIdFromDataSource2(@PathVariable("id") String id) {
//        Optional<Employee> employeeOpt = employeeService.findByIdFromDS2(id);
//        return employeeOpt.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping("/ds1")
//    public ResponseEntity<Employee> insertEmployeeDataSource1(@RequestBody Employee employee) {
//        try {
//            Employee savedEmployee = employeeService.saveDS1(employee);
//            return ResponseEntity.ok(savedEmployee);
//        } catch (Exception e) {
//            System.out.println("Transaction failed. All successful operations will be rolled back.");
//            System.out.println(e.getMessage());
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @PostMapping("/ds2")
//    public ResponseEntity<Employee> insertEmployeeDataSource2(@RequestBody Employee employee) {
//        try {
//            Employee savedEmployee = employeeService.saveDS2(employee);
//            return ResponseEntity.ok(savedEmployee);
//        } catch (Exception e) {
//            System.out.println("Transaction failed. All successful operations will be rolled back.");
//            System.out.println(e.getMessage());
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id, @RequestBody Employee employeeForm) {
//        try {
//            employeeForm.setId(id);
//            Employee updatedEmployee = employeeService.update(employeeForm);
//            return ResponseEntity.ok(updatedEmployee);
//        } catch (Exception e) {
//            System.out.println("Transaction failed. All successful operations will be rolled back.");
//            System.out.println(e.getMessage());
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") String id) {
//        try {
//            employeeService.deleteById(id);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            System.out.println("Transaction failed. All successful operations will be rolled back.");
//            System.out.println(e.getMessage());
//            return ResponseEntity.badRequest().build();
//        }
//    }
//}
