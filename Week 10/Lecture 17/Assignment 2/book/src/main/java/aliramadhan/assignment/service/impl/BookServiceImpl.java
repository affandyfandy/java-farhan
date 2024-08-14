package aliramadhan.assignment.service.impl;

import aliramadhan.assignment.data.model.Book;
import aliramadhan.assignment.data.repository.BookRepository;
import aliramadhan.assignment.dto.BookDTO;
import aliramadhan.assignment.dto.BookSaveDTO;
import aliramadhan.assignment.dto.BookShowDTO;
import aliramadhan.assignment.mapper.BookMapper;
import aliramadhan.assignment.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookShowDTO getBookById(String id) {
        return bookRepository.findById(id)
                .map(bookMapper::toBookShowDTO)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public Page<BookShowDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toBookShowDTO);
    }

    @Override
    public BookDTO saveBook(BookSaveDTO bookSaveDTO) {
        Book book = bookMapper.toBookEntity(bookSaveDTO);
        book.setId(UUID.randomUUID().toString());
        book.setCreatedAt(LocalDateTime.now());
        return bookMapper.toBookDTO(bookRepository.save(book));
    }

    @Override
    public BookDTO updateBook(String id, BookSaveDTO bookSaveDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookSaveDTO.getTitle());
        book.setAuthor(bookSaveDTO.getAuthor());
        book.setPublicationYear(bookSaveDTO.getPublicationYear());
        book.setGenre(bookSaveDTO.getGenre());
        book.setAvailableCopies(bookSaveDTO.getAvailableCopies());
        book.setUpdatedAt(LocalDateTime.now());
        return bookMapper.toBookDTO(bookRepository.save(book));
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

        @Override
        public void reduceAvailableCopies(String id, Integer quantity) {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + id));

            if (book.getAvailableCopies() < quantity) {
                throw new IllegalArgumentException("Insufficient copies available for reduction");
            }

            book.setAvailableCopies(book.getAvailableCopies() - quantity);
            bookRepository.save(book);
        }
}

