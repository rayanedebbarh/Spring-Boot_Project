package app.repository;

import app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // JpaRepository provides: save, findById, findAll, delete, count, etc.

    // Custom query methods
    List<Book> findByAuthor(String author);
    List<Book> findAllByOrderByTitleAsc();
    List<Book> findAllByOrderByYearDesc();
}