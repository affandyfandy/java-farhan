package aliramadhan.assignment.service;

import aliramadhan.assignment.dto.StudentBookDTO;
import aliramadhan.assignment.dto.StudentBookSaveDTO;
import aliramadhan.assignment.dto.StudentBookShowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentBookService {
    StudentBookShowDTO getStudentBookById(String id);

    Page<StudentBookShowDTO> getAllStudentBooks(Pageable pageable);

    StudentBookDTO saveBook(StudentBookSaveDTO studentBookSaveDTO);

    StudentBookDTO updateBook(String id, StudentBookSaveDTO studentbookSaveDTO);

    void deleteBook(String id);
}

