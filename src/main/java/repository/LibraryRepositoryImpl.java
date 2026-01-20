package repository;

import model.Library;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryRepositoryImpl implements LibraryRepository {

    private final List<Library> libraries = new ArrayList<>();

    public void save(Library library) {
        libraries.add(library);
    }

    public void update(Library library) {
        delete(library.getId());
        libraries.add(library);
    }

    public void delete(int id) {
        libraries.removeIf(l -> l.getId() == id);
    }

    public Library findById(int id) {
        return libraries.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
    }

    public long count() {
        return libraries.size();
    }

    public List<Library> findAllByOrderByName() {
        return libraries.stream()
                .sorted(Comparator.comparing(Library::getName))
                .collect(Collectors.toList());
    }

    public List<Library> findByCity(String city) {
        return libraries.stream()
                .filter(l -> l.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }
}
