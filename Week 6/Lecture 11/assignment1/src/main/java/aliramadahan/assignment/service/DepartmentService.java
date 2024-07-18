package aliramadahan.assignment.service;

import aliramadahan.assignment.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    Page<Department> findAll(Pageable pageable);

    Department findById(String deptNo);

    Department updateDepartment(String deptNo, Department department);

    void save(Department theEmployee);

    void deleteById(String deptNo);
}
