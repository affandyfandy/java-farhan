package aliramadahan.assignment.service;

import aliramadahan.assignment.model.Department;
import aliramadahan.assignment.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAll();
    Page<Department> findAll(Pageable pageable);
    Optional<Department> findById(String deptNo);
    Department updateDepartment(String deptNo, Department department);

    Department save(Department department);

    void deleteById(String deptNo);
}
