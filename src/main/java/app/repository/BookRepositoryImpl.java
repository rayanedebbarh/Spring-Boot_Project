package app.repository;

import app.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final List<Book> books = new ArrayList<>();
    private int nextId = 1;  // Auto-increment ID

    @Override
    public Book save(Book book) {
        // Auto-generate ID if not set
        if (book.getId() == 0) {
            book.setId(nextId++);
        }
        books.add(book);
        return book;  // Return the saved book
    }

    @Override
    public Book update(Book book) {
        // Find and replace the book
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                books.set(i, book);
                return book;  // Return updated book
            }
        }
        return null;  // Not found
    }

    @Override
    public boolean delete(int id) {
        return books.removeIf(b -> b.getId() == id);  // Returns true if removed
    }

    @Override
    public Book findById(int id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public long count() {
        return books.size();
    }

    @Override
    public List<Book> findAllByOrderByTitle() {
        return books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return books.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }
}