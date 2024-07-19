package aliramadahan.assignment.service;

import aliramadahan.assignment.model.Salary;
import aliramadahan.assignment.model.key.SalaryId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SalaryService {
    List<Salary> findAll();
    Page<Salary> findAll(Pageable pageable);
    Optional<Salary> findById(SalaryId id);
    Salary updateSalary(Salary title);
    Salary save(Salary title);
    void deleteById(SalaryId id);
}
