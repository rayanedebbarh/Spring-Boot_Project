package service;

import repository.BookRepository;
import model.Book;

import java.util.List;

public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public void save(Book book) {
        repository.save(book);
    }

    public void update(Book book) {
        repository.update(book);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Book findById(int id) {
        return repository.findById(id);
    }

    public long count() {
        return repository.count();
    }

    public List<Book> findAll() {
        return repository.findAllByOrderByTitle();
    }
}
