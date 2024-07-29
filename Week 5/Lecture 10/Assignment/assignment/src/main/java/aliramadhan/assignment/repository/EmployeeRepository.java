package aliramadhan.assignment.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import aliramadhan.assignment.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("SELECT e FROM Employee e WHERE e.department LIKE %:department%")
    List<Employee> findByDepartmentId(@Param("department") String department);
}
