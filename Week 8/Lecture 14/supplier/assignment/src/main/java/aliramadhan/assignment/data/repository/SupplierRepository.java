package aliramadhan.assignment.data.repository;

import aliramadhan.assignment.data.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
