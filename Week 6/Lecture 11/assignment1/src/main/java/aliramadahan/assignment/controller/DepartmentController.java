package aliramadahan.assignment.controller;

import aliramadahan.assignment.model.Department;
import aliramadahan.assignment.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> listDepartments() {
        try {
            List<Department> departments = departmentService.findAll();
            if (departments.isEmpty()) {
                logger.warn("No departments found.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving departments", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{deptNo}")
    public ResponseEntity<Department> getDepartment(@PathVariable String deptNo) {
        try {
            Department department = departmentService.findById(deptNo);
            if (department == null) {
                logger.warn("Department with id {} not found.", deptNo);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving department with id " + deptNo, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveDepartment(@RequestBody Department department) {
        try {
            departmentService.save(department);
            logger.info("Successfully created department: {}", department);
            return new ResponseEntity<>("Department created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving department", e);
            return new ResponseEntity<>("Error saving department: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{deptNo}")
    public ResponseEntity<Void> updateDepartment(@PathVariable String deptNo, @RequestBody Department department) {
        try {
            Department updatedDepartment = departmentService.updateDepartment(deptNo, department);
            if (updatedDepartment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating department with id " + deptNo, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{deptNo}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String deptNo) {
        try {
            departmentService.deleteById(deptNo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting department with id " + deptNo, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
