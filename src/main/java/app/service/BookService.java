package app.service;

import org.springframework.stereotype.Service;
import app.model.Book;
import app.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book save(Book book) {
        validateBook(book);
        return repository.save(book);
    }

    public Book update(Book book) {
        validateBook(book);
        Book updated = repository.update(book);
        if (updated == null) {
            throw new IllegalArgumentException("Book with ID " + book.getId() + " not found");
        }
        return updated;
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public Book findById(int id) {
        Book book = repository.findById(id);
        if (book == null) {
            throw new IllegalArgumentException("Book with ID " + id + " not found");
        }
        return book;
    }

    public long count() {
        return repository.count();
    }

    public List<Book> findAll() {
        return repository.findAllByOrderByTitle();
    }

    public List<Book> findByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty");
        }
        return repository.findByAuthor(author);
    }

    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty");
        }
    }
}