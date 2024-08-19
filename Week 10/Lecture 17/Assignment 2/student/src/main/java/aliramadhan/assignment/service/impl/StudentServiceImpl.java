package aliramadhan.assignment.service.impl;

import aliramadhan.assignment.dto.StudentDTO;
import aliramadhan.assignment.dto.StudentSaveDTO;
import aliramadhan.assignment.dto.StudentShowDTO;
import aliramadhan.assignment.mapper.StudentMapper;
import aliramadhan.assignment.data.model.Student;
import aliramadhan.assignment.data.repository.StudentRepository;
import aliramadhan.assignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentShowDTO getStudentById(String id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toStudentShowDTO(student);
    }

    @Override
    public Page<StudentShowDTO> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(studentMapper::toStudentShowDTO);
    }

    @Override
    @Transactional
    public StudentDTO saveStudent(StudentSaveDTO studentSaveDTO) {
        Student student = studentMapper.toStudentEntity(studentSaveDTO);
        student.setId(UUID.randomUUID().toString());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toStudentDTO(savedStudent);
    }

    @Override
    @Transactional
    public StudentDTO updateStudent(String id, StudentSaveDTO studentSaveDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setEmail(studentSaveDTO.getEmail());
        existingStudent.setName(studentSaveDTO.getName());
        existingStudent.setPhoneNumber(studentSaveDTO.getPhoneNumber());
        existingStudent.setEnrollmentDate(studentSaveDTO.getEnrollmentDate());
        existingStudent.setUpdatedAt(LocalDateTime.now());
        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.toStudentDTO(updatedStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
