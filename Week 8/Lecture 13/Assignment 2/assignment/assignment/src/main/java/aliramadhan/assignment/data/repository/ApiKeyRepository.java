package aliramadhan.assignment.data.repository;

import aliramadhan.assignment.data.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey, String> {
}
