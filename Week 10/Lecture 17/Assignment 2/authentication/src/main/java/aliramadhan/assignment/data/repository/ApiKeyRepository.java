package aliramadhan.assignment.data.repository;


import java.util.Optional;

import aliramadhan.assignment.data.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    // Get the first API key, order by ID
    Optional<ApiKey> findFirstByOrderById();

    // Find the first active API key
    Optional<ApiKey> findFirstByActiveTrueOrderById();
}