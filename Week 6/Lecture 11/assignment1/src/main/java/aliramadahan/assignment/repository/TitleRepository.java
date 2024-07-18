package aliramadahan.assignment.repository;

import aliramadahan.assignment.model.Title;
import aliramadahan.assignment.model.key.TitleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, TitleId> {
}
