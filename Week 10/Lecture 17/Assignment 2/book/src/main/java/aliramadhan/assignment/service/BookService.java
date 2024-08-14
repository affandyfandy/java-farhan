package aliramadhan.assignment.service;

import aliramadhan.assignment.dto.BookDTO;
import aliramadhan.assignment.dto.BookSaveDTO;
import aliramadhan.assignment.dto.BookShowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookShowDTO getBookById(String id);

    Page<BookShowDTO> getAllBooks(Pageable pageable);

    BookDTO saveBook(BookSaveDTO bookSaveDTO);

    BookDTO updateBook(String id, BookSaveDTO bookSaveDTO);

    void deleteBook(String id);

    void reduceAvailableCopies(String id, Integer quantity);
}

