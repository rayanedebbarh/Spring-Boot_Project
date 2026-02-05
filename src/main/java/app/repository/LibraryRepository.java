package app.repository;

import app.model.Library;
import java.util.List;

public interface LibraryRepository {
    Library save(Library library);           // Changed: now returns Library
    Library update(Library library);         // Changed: now returns Library
    boolean delete(int id);                  // Changed: now returns boolean
    Library findById(int id);
    long count();
    List<Library> findAllByOrderByName();
    List<Library> findByCity(String city);
}