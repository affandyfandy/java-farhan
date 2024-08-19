package aliramadhan.assignment.controller;

import aliramadhan.assignment.dto.StudentDTO;
import aliramadhan.assignment.dto.StudentSaveDTO;
import aliramadhan.assignment.dto.StudentShowDTO;
import aliramadhan.assignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentShowDTO> getStudentById(@PathVariable String id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<Page<StudentShowDTO>> getAllStudents(Pageable pageable) {
        return ResponseEntity.ok(studentService.getAllStudents(pageable));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentSaveDTO studentSaveDTO) {
        return ResponseEntity.ok(studentService.saveStudent(studentSaveDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String id, @RequestBody StudentSaveDTO studentSaveDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentSaveDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
