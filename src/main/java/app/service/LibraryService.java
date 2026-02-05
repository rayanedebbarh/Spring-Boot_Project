package app.service;

import org.springframework.stereotype.Service;
import app.model.Library;
import app.repository.LibraryRepository;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepository repo;

    public LibraryService(LibraryRepository repo) {
        this.repo = repo;
    }

    public Library save(Library library) {
        validateLibrary(library);
        return repo.save(library);
    }

    public Library update(Library library) {
        validateLibrary(library);
        Library updated = repo.update(library);
        if (updated == null) {
            throw new IllegalArgumentException("Library with ID " + library.getId() + " not found");
        }
        return updated;
    }

    public boolean delete(int id) {
        return repo.delete(id);
    }

    public Library findById(int id) {
        Library library = repo.findById(id);
        if (library == null) {
            throw new IllegalArgumentException("Library with ID " + id + " not found");
        }
        return library;
    }

    public long count() {
        return repo.count();
    }

    public List<Library> findAllSorted() {
        return repo.findAllByOrderByName();
    }

    public List<Library> findByCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be empty");
        }
        return repo.findByCity(city);
    }

    private void validateLibrary(Library library) {
        if (library.getName() == null || library.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Library name cannot be empty");
        }
        if (library.getCity() == null || library.getCity().trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be empty");
        }
    }
}