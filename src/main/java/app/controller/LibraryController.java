package app.controller;

import app.model.Library;
import app.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) { this.libraryService = libraryService; }

    @PostMapping
    public ResponseEntity<Library> createLibrary(@RequestBody Library library) {
        try {
            return new ResponseEntity<>(libraryService.save(library), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(libraryService.findById(id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Library>> getAllLibraries() {
        return new ResponseEntity<>(libraryService.findAllSorted(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countLibraries() {
        return new ResponseEntity<>(libraryService.count(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Library>> searchByCity(@RequestParam String city) {
        try {
            return new ResponseEntity<>(libraryService.findByCity(city), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Library> updateLibrary(@PathVariable int id, @RequestBody Library library) {
        try {
            library.setId(id);
            return new ResponseEntity<>(libraryService.update(library), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable int id) {
        return libraryService.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // NEW - Activity 7: Ordering endpoint
    @GetMapping("/sorted/city")
    public ResponseEntity<List<Library>> getAllLibrariesSortedByCity() {
        return new ResponseEntity<>(libraryService.findAllByOrderByCityAsc(), HttpStatus.OK);
    }

    // NEW - Activity 7: Relationship management endpoints
    @PostMapping("/{id}/books/{bookId}")
    public ResponseEntity<Library> addBookToLibrary(@PathVariable int id, @PathVariable int bookId) {
        try {
            Library library = libraryService.addBookToLibrary(id, bookId);
            return new ResponseEntity<>(library, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/books/{bookId}")
    public ResponseEntity<Library> removeBookFromLibrary(@PathVariable int id, @PathVariable int bookId) {
        try {
            Library library = libraryService.removeBookFromLibrary(id, bookId);
            return new ResponseEntity<>(library, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}