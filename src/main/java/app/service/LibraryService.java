package app.service;

import app.model.Book;
import app.model.Library;
import app.repository.BookRepository;
import app.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    public LibraryService(LibraryRepository libraryRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
    }

    public Library save(Library library) {
        validateLibrary(library);
        return libraryRepository.save(library);
    }

    public Library update(Library library) {
        validateLibrary(library);
        if (!libraryRepository.existsById(library.getId())) {
            throw new IllegalArgumentException("Library with ID " + library.getId() + " not found");
        }
        return libraryRepository.save(library);
    }

    public boolean delete(int id) {
        if (libraryRepository.existsById(id)) {
            libraryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Library findById(int id) {
        return libraryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Library with ID " + id + " not found"));
    }

    public long count() {
        return libraryRepository.count();
    }

    public List<Library> findAllSorted() {
        return libraryRepository.findAllByOrderByNameAsc();
    }

    public List<Library> findAllByOrderByCityAsc() {
        return libraryRepository.findAllByOrderByCityAsc();
    }

    public List<Library> findByCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be empty");
        }
        return libraryRepository.findByCity(city);
    }

    // Relationship management methods
    public Library addBookToLibrary(int libraryId, int bookId) {
        Library library = findById(libraryId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));

        library.addBook(book);
        return libraryRepository.save(library);
    }

    public Library removeBookFromLibrary(int libraryId, int bookId) {
        Library library = findById(libraryId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));

        library.removeBook(book);
        return libraryRepository.save(library);
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