package repository;

import model.Author;
import java.util.List;

public interface AuthorRepository {

    void addAuthor(Author author);
    Author getAuthorById(int id);
    List<Author> getAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthor(int id);
}
