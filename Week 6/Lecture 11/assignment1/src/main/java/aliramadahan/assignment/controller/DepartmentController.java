package aliramadahan.assignment.controller;

import aliramadahan.assignment.model.Department;
import aliramadahan.assignment.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @Autowired
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Department>> listDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "deptNo") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Pageable pageable = PageRequest.of(page, size,
                sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<Department> departmentPage = departmentService.findAll(pageable);
        return new ResponseEntity<>(departmentPage, HttpStatus.OK);
    }

    @GetMapping("/{deptNo}")
    public ResponseEntity<Department> getDepartment(@PathVariable String deptNo) {
        Optional<Department> department = departmentService.findById(deptNo);
        return department.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.save(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{deptNo}")
    public ResponseEntity<Department> updateDepartment(@PathVariable String deptNo, @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(deptNo, department);
        if (updatedDepartment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/{deptNo}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String deptNo) {
        try {
            departmentService.deleteById(deptNo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
