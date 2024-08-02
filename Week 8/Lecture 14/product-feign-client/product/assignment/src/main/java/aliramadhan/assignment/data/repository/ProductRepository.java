package aliramadhan.assignment.data.repository;

import aliramadhan.assignment.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
