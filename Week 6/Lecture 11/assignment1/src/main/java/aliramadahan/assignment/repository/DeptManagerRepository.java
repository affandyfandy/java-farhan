package aliramadahan.assignment.repository;

import aliramadahan.assignment.model.DeptManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptManagerRepository extends JpaRepository<DeptManager, String>  {
}
