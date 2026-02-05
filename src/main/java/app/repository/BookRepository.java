package app.repository;

import app.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);                    // Changed: now returns Book
    Book update(Book book);                  // Changed: now returns Book
    boolean delete(int id);                  // Changed: now returns boolean
    Book findById(int id);
    long count();
    List<Book> findAllByOrderByTitle();
    List<Book> findByAuthor(String author);
}