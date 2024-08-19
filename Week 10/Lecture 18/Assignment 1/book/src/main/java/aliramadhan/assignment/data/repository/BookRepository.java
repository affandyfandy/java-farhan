package aliramadhan.assignment.data.repository;

import aliramadhan.assignment.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
