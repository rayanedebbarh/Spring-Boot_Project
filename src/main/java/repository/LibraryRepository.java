package repository;

import model.Library;
import java.util.List;

public interface LibraryRepository {
    void save(Library library);
    void update(Library library);
    void delete(int id);
    Library findById(int id);
    long count();
    List<Library> findAllByOrderByName();
    List<Library> findByCity(String city);
}
