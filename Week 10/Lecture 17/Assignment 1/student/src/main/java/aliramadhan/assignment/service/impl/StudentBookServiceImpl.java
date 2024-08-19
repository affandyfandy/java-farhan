package aliramadhan.assignment.service.impl;

import aliramadhan.assignment.client.BookClient;
import aliramadhan.assignment.data.model.Student;
import aliramadhan.assignment.data.repository.StudentRepository;
import aliramadhan.assignment.dto.BookDTO;
import aliramadhan.assignment.dto.StudentBookDTO;
import aliramadhan.assignment.dto.StudentBookSaveDTO;
import aliramadhan.assignment.dto.StudentBookShowDTO;
import aliramadhan.assignment.exception.BookNotFoundException;
import aliramadhan.assignment.exception.StudentNotFoundException;
import aliramadhan.assignment.mapper.StudentBookMapper;
import aliramadhan.assignment.data.model.StudentBook;
import aliramadhan.assignment.data.repository.StudentBookRepository;
import aliramadhan.assignment.service.StudentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentBookServiceImpl implements StudentBookService {
    private final StudentBookRepository studentBookRepository;
    private final StudentRepository studentRepository;
    private final StudentBookMapper studentBookMapper;
    private final BookClient bookClient;

    @Autowired
    public StudentBookServiceImpl(StudentBookRepository studentBookRepository,
                                  StudentBookMapper studentBookMapper,
                                  BookClient bookClient,
                                  StudentRepository studentRepository) {
        this.studentBookMapper = studentBookMapper;
        this.studentBookRepository = studentBookRepository;
        this.bookClient = bookClient;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentBookShowDTO getStudentBookById(String id) {
        StudentBook studentBook = studentBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));
        return studentBookMapper.toStudentBookShowDTO(studentBook);
    }

    @Override
    public Page<StudentBookShowDTO> getAllStudentBooks(Pageable pageable) {
        // Use the provided Pageable without additional sorting logic
        return studentBookRepository.findAll(pageable).map(studentBook -> {
            StudentBookShowDTO studentBookShowDTO = studentBookMapper.toStudentBookShowDTO(studentBook);

            if (studentBook.getBookId() != null) {
                BookDTO bookDTO = bookClient.getBookById(studentBook.getBookId());
                studentBookShowDTO.setBook(bookDTO);
            }

            return studentBookShowDTO;
        });
    }


    @Override
    @Transactional
    public StudentBookDTO saveBook(StudentBookSaveDTO studentBookSaveDTO) {
        // Check if the studentId exists in the database
        Optional<Student> studentOptional = studentRepository.findById(studentBookSaveDTO.getStudentId());
        if (!studentOptional.isPresent()) {
            throw new StudentNotFoundException("Student not found");
        }

        // Map the DTO to the entity
        StudentBook studentBook = studentBookMapper.toStudentBookEntity(studentBookSaveDTO);

        // Check if the bookId exists via the bookClient
        if (studentBook.getBookId() != null) {
            BookDTO bookDTO = bookClient.getBookById(studentBook.getBookId());
            if (bookDTO == null) {
                throw new BookNotFoundException("Book not found");
            }

            try {
                String response = bookClient.reduceAvailableCopies(studentBook.getBookId(), 1);
                if (!"Available copies reduced successfully".equals(response)) {
                    throw new RuntimeException("Unexpected response: " + response);
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to reduce available copies: " + e.getMessage());
            }

        }

        // Set the ID, borrowDate, and returnDate
        studentBook.setId(UUID.randomUUID().toString());
        LocalDate borrowDate = LocalDate.now();
        studentBook.setBorrowDate(borrowDate);
        studentBook.setReturnDate(borrowDate.plusDays(7)); // Set returnDate to 7 days after borrowDate

        // Save the entity and return the DTO
        StudentBook savedBook = studentBookRepository.save(studentBook);
        return studentBookMapper.toStudentBookDTO(savedBook);
    }


    @Override
    @Transactional
    public StudentBookDTO updateBook(String id, StudentBookSaveDTO studentBookSaveDTO) {
        StudentBook existingBook = studentBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));

        // Fetch the Student entity using the studentId from the DTO
        Student student = studentRepository.findById(studentBookSaveDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existingBook.setBookId(studentBookSaveDTO.getBookId());
        existingBook.setStudent(student); // Set the student entity
        existingBook.setBorrowDate(studentBookSaveDTO.getBorrowDate());
        existingBook.setReturnDate(studentBookSaveDTO.getReturnDate());

        StudentBook updatedBook = studentBookRepository.save(existingBook);
        return studentBookMapper.toStudentBookDTO(updatedBook);
    }


    @Override
    public void deleteBook(String id) {
        studentBookRepository.deleteById(id);
    }
}
