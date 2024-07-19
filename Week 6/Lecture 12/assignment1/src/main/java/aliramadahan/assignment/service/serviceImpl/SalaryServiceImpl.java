package aliramadahan.assignment.service.serviceImpl;

import aliramadahan.assignment.model.Salary;
import aliramadahan.assignment.model.key.SalaryId;
import aliramadahan.assignment.repository.SalaryRepository;
import aliramadahan.assignment.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public List<Salary> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public Page<Salary> findAll(Pageable pageable) {
        return salaryRepository.findAll(pageable);
    }

    @Override
    public Optional<Salary> findById(SalaryId id) {
        return salaryRepository.findById(id);
    }

    @Override
    public Salary save(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public void deleteById(SalaryId id) {
        salaryRepository.deleteById(id);
    }

    @Override
    public Salary updateSalary(Salary salary) {
        return salaryRepository.save(salary);
    }
}
