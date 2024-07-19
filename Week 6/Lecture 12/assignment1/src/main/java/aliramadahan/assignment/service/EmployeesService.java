package aliramadahan.assignment.service;

import aliramadahan.assignment.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeesService {
    List<Employee> findAll();
    Page<Employee> findAll(Pageable pageable);
    Optional<Employee> findById(Integer empNo);
    Employee updateEmployee(Employee title);
    Employee save(Employee title);
    void deleteById(Integer empNo);
}
