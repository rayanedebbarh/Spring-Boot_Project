package repository;

import model.Author;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    private List<Author> authors = new ArrayList<>();

    @Override
    public void addAuthor(Author author) {
        authors.add(author);
    }

    @Override
    public Author getAuthorById(int id) {
        for (Author author : authors) {
            if (author.getId() == id) return author;
        }
        return null;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authors;
    }

    @Override
    public void updateAuthor(Author updatedAuthor) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId() == updatedAuthor.getId()) {
                authors.set(i, updatedAuthor);
                return;
            }
        }
    }

    @Override
    public void deleteAuthor(int id) {
        authors.removeIf(author -> author.getId() == id);
    }
}
