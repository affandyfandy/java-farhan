package aliramadhan.assignment1.service.impl;

import aliramadhan.assignment1.model.Employee;
import aliramadhan.assignment1.repository.EmployeeRepository;
import aliramadhan.assignment1.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int theId) {
        return employeeRepository.findById(theId).orElseThrow();
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }

}