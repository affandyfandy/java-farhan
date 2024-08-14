package aliramadhan.assignment.data.repository;

import aliramadhan.assignment.data.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
