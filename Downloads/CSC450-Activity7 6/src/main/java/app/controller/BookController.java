// AI-assisted code generated with Claude (Anthropic)
// Prompt: "Add a new REST endpoint POST /books/{id}/isbn-async to an existing 
// Spring Boot BookController that validates the book exists, then uses 
// IsbnRequestProducer to send the book ID to an ActiveMQ queue and returns 
// HTTP 202 Accepted with a confirmation message."
package app.controller;

import app.llm.LlmService;
import app.model.Book;
import app.mq.IsbnRequestProducer;
import app.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final LlmService llmService;
    private final IsbnRequestProducer isbnRequestProducer;

    public BookController(BookService bookService, LlmService llmService, IsbnRequestProducer isbnRequestProducer) {
        this.bookService = bookService;
        this.llmService = llmService;
        this.isbnRequestProducer = isbnRequestProducer;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countBooks() {
        return new ResponseEntity<>(bookService.count(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByAuthor(@RequestParam String author) {
        try {
            return new ResponseEntity<>(bookService.findByAuthor(author), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        try {
            book.setId(id);
            return new ResponseEntity<>(bookService.update(book), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        return bookService.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/isbn")
    public ResponseEntity<String> getBookIsbn(@PathVariable int id) {
        try {
            Book book = bookService.findById(id);
            String isbn = llmService.findIsbn(book.getTitle(), book.getAuthor(), book.getYear());
            return new ResponseEntity<>(isbn, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/isbn-async")
    public ResponseEntity<String> requestIsbnAsync(@PathVariable int id) {
        try {
            bookService.findById(id);
            isbnRequestProducer.sendRequest(String.valueOf(id));
            return new ResponseEntity<>("ISBN request queued for book " + id, HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }

    // NEW - Activity 7: Ordering endpoints
    @GetMapping("/sorted/title")
    public ResponseEntity<List<Book>> getAllBooksSortedByTitle() {
        return new ResponseEntity<>(bookService.findAllByOrderByTitleAsc(), HttpStatus.OK);
    }

    @GetMapping("/sorted/year")
    public ResponseEntity<List<Book>> getAllBooksSortedByYear() {
        return new ResponseEntity<>(bookService.findAllByOrderByYearDesc(), HttpStatus.OK);
    }

    // NEW - Activity 7: Relationship management endpoints
    @PutMapping("/{id}/library/{libraryId}")
    public ResponseEntity<Book> assignBookToLibrary(@PathVariable int id, @PathVariable int libraryId) {
        try {
            Book book = bookService.assignToLibrary(id, libraryId);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/library")
    public ResponseEntity<Book> removeBookFromLibrary(@PathVariable int id) {
        try {
            Book book = bookService.removeFromLibrary(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}