package app.repository;

import app.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {
    // JpaRepository provides: save, findById, findAll, delete, count, etc.

    // Custom query methods
    List<Library> findByCity(String city);
    List<Library> findAllByOrderByNameAsc();
    List<Library> findAllByOrderByCityAsc();
}