package repository;

import model.Book;
import java.util.List;

public interface BookRepository {
    void save(Book book);
    void update(Book book);
    void delete(int id);
    Book findById(int id);
    long count();
    List<Book> findAllByOrderByTitle();
    List<Book> findByAuthor(String author);
}
