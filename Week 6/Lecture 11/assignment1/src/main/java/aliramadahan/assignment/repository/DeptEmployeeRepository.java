package aliramadahan.assignment.repository;

import aliramadahan.assignment.model.DeptEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptEmployeeRepository extends JpaRepository<DeptEmployee, String>  {
}

