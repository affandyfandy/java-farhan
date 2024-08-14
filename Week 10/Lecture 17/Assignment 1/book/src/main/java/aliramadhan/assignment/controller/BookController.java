package aliramadhan.assignment.controller;

import aliramadhan.assignment.dto.BookDTO;
import aliramadhan.assignment.dto.BookSaveDTO;
import aliramadhan.assignment.dto.BookShowDTO;
import aliramadhan.assignment.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@Valid @RequestBody BookSaveDTO bookSaveDTO) {
        BookDTO bookDTO = bookService.saveBook(bookSaveDTO);
        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookShowDTO> getBookById(@PathVariable("id") String id) {
        BookShowDTO bookShowDTO = bookService.getBookById(id);
        return new ResponseEntity<>(bookShowDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<BookShowDTO>> getAllBooks(Pageable pageable) {
        Page<BookShowDTO> books = bookService.getAllBooks(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable("id") String id,
            @Valid @RequestBody BookSaveDTO bookSaveDTO) {
        BookDTO bookDTO = bookService.updateBook(id, bookSaveDTO);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") String id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/reduce-copies")
    public ResponseEntity<String> reduceAvailableCopies(
            @PathVariable String id,
            @RequestParam Integer quantity) {
        try {
            bookService.reduceAvailableCopies(id, quantity);
            return ResponseEntity.ok("Available copies reduced successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
