package aliramadhan.assignment3.service;


import java.util.List;
import java.util.Optional;

import aliramadhan.assignment3.model.Employee;
import aliramadhan.assignment3.repository.EmployeeRepository;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllFromDs1() {
        var listEmployee = employeeRepository.findAllDS1();
        return listEmployee;
    }


    public List<Employee> findAllFromDs2() {
        var listEmployee = employeeRepository.findAllDS2();
        return listEmployee;
    }

    public Employee findByIdFromDs1(String id) {
        var employee = employeeRepository.findDs1(id);
        if (employee != null){
            return employee;
        }
        return null;
    }


    public Employee findByIdFromDs2(String id) {
        var employee = employeeRepository.findDs2(id);
        if (employee != null){
            return employee;
        }
        return null;
    }

    public Employee saveToDs1(Employee employee) {
        int res = employeeRepository.saveToDs1(employee);
        if (res > 0){
            return employee;
        }
        return null;
    }

    public Employee saveToDs2(Employee employee) {
        int res = employeeRepository.saveToDs2(employee);
        if (res > 0){
            return employee;
        }
        return null;
    }

    public Employee updateToDs1(Employee employee) {
        int res = employeeRepository.updateDs1(employee);
        if (res > 0){
            return employee;
        }
        return null;
    }


    public Employee updateToDs2(Employee employee) {
        int res = employeeRepository.updateDs2(employee);
        if (res > 0){
            return employee;
        }
        return null;
    }

    public String deleteFromDs1(String id) {
        int res = employeeRepository.deleteDs1(id);
        if (res > 0){
            return "success";
        }
        return "failed";
    }

    public String deleteFromDs2(String id) {
        int res = employeeRepository.deleteDs2(id);
        if (res > 0){
            return "success";
        }
        return "failed";
    }
}