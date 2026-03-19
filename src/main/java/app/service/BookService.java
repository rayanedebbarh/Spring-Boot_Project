package app.service;

import app.model.Book;
import app.model.Library;
import app.repository.BookRepository;
import app.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    public BookService(BookRepository bookRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findAllByOrderByTitleAsc() {
        return bookRepository.findAllByOrderByTitleAsc();
    }

    public List<Book> findAllByOrderByYearDesc() {
        return bookRepository.findAllByOrderByYearDesc();
    }

    public Book update(Book book) {
        if (!bookRepository.existsById(book.getId())) {
            throw new IllegalArgumentException("Book not found with id: " + book.getId());
        }
        return bookRepository.save(book);
    }

    public boolean delete(int id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public long count() {
        return bookRepository.count();
    }

    // Relationship management methods
    public Book assignToLibrary(int bookId, int libraryId) {
        Book book = findById(bookId);
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new IllegalArgumentException("Library not found with id: " + libraryId));

        book.setLibrary(library);
        return bookRepository.save(book);
    }

    public Book removeFromLibrary(int bookId) {
        Book book = findById(bookId);
        book.removeLibrary();
        return bookRepository.save(book);
    }
}