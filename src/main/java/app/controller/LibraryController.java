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

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    // CREATE - POST /libraries
    @PostMapping
    public ResponseEntity<Library> createLibrary(@RequestBody Library library) {
        try {
            Library savedLibrary = libraryService.save(library);
            return new ResponseEntity<>(savedLibrary, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // READ - GET /libraries/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable int id) {
        try {
            Library library = libraryService.findById(id);
            return new ResponseEntity<>(library, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // READ - GET /libraries (all libraries, sorted by name)
    @GetMapping
    public ResponseEntity<List<Library>> getAllLibraries() {
        List<Library> libraries = libraryService.findAllSorted();
        return new ResponseEntity<>(libraries, HttpStatus.OK);
    }

    // READ - GET /libraries/count
    @GetMapping("/count")
    public ResponseEntity<Long> countLibraries() {
        long count = libraryService.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // READ - GET /libraries/search?city=xxx
    @GetMapping("/search")
    public ResponseEntity<List<Library>> searchByCity(@RequestParam String city) {
        try {
            List<Library> libraries = libraryService.findByCity(city);
            return new ResponseEntity<>(libraries, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // UPDATE - PUT /libraries/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Library> updateLibrary(@PathVariable int id, @RequestBody Library library) {
        try {
            library.setId(id);  // Ensure the ID matches
            Library updatedLibrary = libraryService.update(library);
            return new ResponseEntity<>(updatedLibrary, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - DELETE /libraries/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable int id) {
        boolean deleted = libraryService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}