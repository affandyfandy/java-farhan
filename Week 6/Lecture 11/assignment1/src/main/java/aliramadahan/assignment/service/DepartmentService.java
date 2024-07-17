package aliramadahan.assignment.service;

import aliramadahan.assignment.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    Department findById(String deptNo);

    Department updateDepartment(String deptNo, Department department);

    void save(Department theEmployee);

    void deleteById(String deptNo);
}
