package aliramadhan.assignment.service;

import java.util.List;
import java.util.UUID;

import aliramadhan.assignment.dto.EmployeeDTO;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeById(String id);
    List<EmployeeDTO> getAllEmployees(String department);
    EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO);
    void deleteEmployee(String id);
}
