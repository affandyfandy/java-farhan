package aliramadhan.assignment.service;

import aliramadhan.assignment.dto.StudentDTO;
import aliramadhan.assignment.dto.StudentSaveDTO;
import aliramadhan.assignment.dto.StudentShowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    StudentShowDTO getStudentById(String id);

    Page<StudentShowDTO> getAllStudents(Pageable pageable);

    StudentDTO saveStudent(StudentSaveDTO studentSaveDTO);

    StudentDTO updateStudent(String id, StudentSaveDTO studentSaveDTO);

    void deleteStudent(String id);
}

