package service;

import model.Library;
import repository.LibraryRepository;
import java.util.List;

public class LibraryService {

    private final LibraryRepository repo;

    public LibraryService(LibraryRepository repo) {
        this.repo = repo;
    }

    public void save(Library library) { repo.save(library); }
    public void update(Library library) { repo.update(library); }
    public void delete(int id) { repo.delete(id); }
    public Library findById(int id) { return repo.findById(id); }
    public long count() { return repo.count(); }
    public List<Library> findAllSorted() { repo.findAllByOrderByName(); return repo.findAllByOrderByName(); }
    public List<Library> findByCity(String city) { return repo.findByCity(city); }
}
