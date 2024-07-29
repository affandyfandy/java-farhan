package aliramadhan.assignment.repository;

import aliramadhan.assignment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    // Retrieves a paginated list of all employees from the database, sorted by
    // their names in ascending order.
    // Page<Employee> findAllByOrderByNameAsc(Pageable pageable);
}
