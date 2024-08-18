package aliramadhan.assignment.data.repository;

import aliramadhan.assignment.data.model.StudentBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentBookRepository extends JpaRepository<StudentBook, String> {
}
