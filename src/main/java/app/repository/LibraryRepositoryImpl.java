package app.repository;

import app.model.Library;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class LibraryRepositoryImpl implements LibraryRepository {

    private final List<Library> libraries = new ArrayList<>();
    private int nextId = 1;  // Auto-increment ID

    @Override
    public Library save(Library library) {
        // Auto-generate ID if not set
        if (library.getId() == 0) {
            library.setId(nextId++);
        }
        libraries.add(library);
        return library;  // Return the saved library
    }

    @Override
    public Library update(Library library) {
        // Find and replace the library
        for (int i = 0; i < libraries.size(); i++) {
            if (libraries.get(i).getId() == library.getId()) {
                libraries.set(i, library);
                return library;  // Return updated library
            }
        }
        return null;  // Not found
    }

    @Override
    public boolean delete(int id) {
        return libraries.removeIf(l -> l.getId() == id);  // Returns true if removed
    }

    @Override
    public Library findById(int id) {
        return libraries.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public long count() {
        return libraries.size();
    }

    @Override
    public List<Library> findAllByOrderByName() {
        return libraries.stream()
                .sorted(Comparator.comparing(Library::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Library> findByCity(String city) {
        return libraries.stream()
                .filter(l -> l.getCity().toLowerCase().contains(city.toLowerCase()))
                .collect(Collectors.toList());
    }
}