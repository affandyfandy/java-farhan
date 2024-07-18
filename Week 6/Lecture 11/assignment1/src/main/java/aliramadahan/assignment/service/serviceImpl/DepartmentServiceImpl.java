package aliramadahan.assignment.service.serviceImpl;

import aliramadahan.assignment.model.Department;
import aliramadahan.assignment.repository.DepartmentRepository;
import aliramadahan.assignment.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private final DepartmentRepository departmentRepository;



    @Override
    public Page<Department> findAll(Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("Invalid pagination and sorting parameters: null object");
        }
        return departmentRepository.findAll(pageable);
    }


    @Override
    public Department findById(String deptNo) {
        return departmentRepository.findById(deptNo).orElseThrow();
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteById(String deptNo) {
        departmentRepository.deleteById(deptNo);
    }

    @Override
    public Department updateDepartment(String deptNo, Department department) {
        Department existingDepartment = departmentRepository.findById(deptNo).orElse(null);
        if (existingDepartment == null) {
            logger.warn("Department with id {} not found.", deptNo);
            return null;
        }
        department.setDeptNo(deptNo);  // Ensure the ID is set to the path variable
        return departmentRepository.save(department);
    }

}