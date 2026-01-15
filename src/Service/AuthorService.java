package Service;

import data.AuthorRepository;
import model.Author;
import java.util.List;

public class AuthorService {

    private AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public void save(Author author) { repository.save(author); }
    public void update(Author author) { repository.update(author); }
    public void delete(int id) { repository.delete(id); }
    public Author findById(int id) { return repository.findById(id); }
    public List<Author> findAll() { return repository.findAllByOrderByName(); }
}
