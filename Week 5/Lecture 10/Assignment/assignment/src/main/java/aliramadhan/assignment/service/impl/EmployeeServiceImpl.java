package aliramadhan.assignment.service.impl;

import aliramadhan.assignment.dto.EmployeeDTO;
import aliramadhan.assignment.exception.ResourceNotFoundException;
import aliramadhan.assignment.mapper.EmployeeMapper;
import aliramadhan.assignment.model.Employee;
import aliramadhan.assignment.repository.EmployeeRepository;
import aliramadhan.assignment.service.EmployeeService;
import aliramadhan.assignment.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    @Override
    public List<EmployeeDTO> getAllEmployees(String department) {
        List<Employee> employees;
        if (department != null && !department.isEmpty()) {
            employees = employeeRepository.findByDepartmentId(department);
        } else {
            employees = employeeRepository.findAll();
        }

        if (employees.isEmpty()) {
            logger.warn("No employees found in the database");
        } else {
            employees.forEach(employee -> logger.info("Employee found: {}", employee));
        }
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());

        employeeDTOs.forEach(dto -> logger.info("Mapped EmployeeDTO: {}", dto));
        return employeeDTOs;
    }

    @Override
    public EmployeeDTO getEmployeeById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        logger.info("Fetched Employee: {}", employee);
        EmployeeDTO employeeDTO = employeeMapper.toDTO(employee.get());
//        EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(employeeOpt.get());
        logger.info("Mapped EmployeeDTO: {}", employeeDTO);
        return employeeDTO;
    }


    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        logger.info("Created Employee: {}", employee);
        return employeeMapper.toDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setAge(employeeDTO.getAge());
        existingEmployee.setDepartment(employeeDTO.getDepartment());
        existingEmployee.setPosition(employeeDTO.getPosition());
        existingEmployee.setSalary(employeeDTO.getSalary());
        existingEmployee.setEmail(employeeDTO.getEmail());
        existingEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
        existingEmployee.setDob(employeeDTO.getDob());

        existingEmployee = employeeRepository.save(existingEmployee);
        logger.info("Updated Employee: {}", existingEmployee);
        EmployeeDTO updatedDTO = employeeMapper.toDTO(existingEmployee);
        logger.info("Mapped Updated EmployeeDTO: {}", updatedDTO);
        return updatedDTO;
    }

    @Override
    public void deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
        logger.info("Deleted Employee: {}", employee);
    }
}
