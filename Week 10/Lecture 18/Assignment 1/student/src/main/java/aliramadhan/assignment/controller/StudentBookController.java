package aliramadhan.assignment.controller;

import aliramadhan.assignment.dto.StudentBookDTO;
import aliramadhan.assignment.dto.StudentBookSaveDTO;
import aliramadhan.assignment.dto.StudentBookShowDTO;
import aliramadhan.assignment.service.StudentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student-books")
public class StudentBookController {

    private final StudentBookService studentBookService;

    @Autowired
    public StudentBookController(StudentBookService studentBookService) {
        this.studentBookService = studentBookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentBookShowDTO> getStudentBookById(@PathVariable String id) {
        return ResponseEntity.ok(studentBookService.getStudentBookById(id));
    }

    @GetMapping
    public ResponseEntity<Page<StudentBookShowDTO>> getAllStudentBooks(Pageable pageable) {
        return ResponseEntity.ok(studentBookService.getAllStudentBooks(pageable));
    }

    @PostMapping
    public ResponseEntity<StudentBookDTO> saveBook(@RequestBody StudentBookSaveDTO studentBookSaveDTO) {
        return ResponseEntity.ok(studentBookService.saveBook(studentBookSaveDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentBookDTO> updateBook(@PathVariable String id, @RequestBody StudentBookSaveDTO studentBookSaveDTO) {
        return ResponseEntity.ok(studentBookService.updateBook(id, studentBookSaveDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        studentBookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
