package app.controller;

/*
 * AI Usage Documentation:
 * - Claude AI (Anthropic) assisted with:
 *   1. Thymeleaf template syntax and Spring MVC integration
 *   2. Controller method structure for CRUD operations
 *   3. Model attribute binding and form handling patterns
 *   4. Redirect patterns after POST operations
 */

import app.model.Book;
import app.model.Library;
import app.service.BookService;
import app.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    private BookService bookService;

    @Autowired
    private LibraryService libraryService;

    // ============ HOME PAGE ============
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // ============ BOOK CRUD OPERATIONS ============

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books-list";
    }

    @GetMapping("/books/new")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("libraries", libraryService.findAllSorted());
        return "book-form";
    }

    @GetMapping("/books/edit/{id}")
    public String editBook(@PathVariable Integer id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("libraries", libraryService.findAllSorted());
        return "book-form";
    }

    @PostMapping("/books/save")
    public String saveBook(@ModelAttribute Book book, @RequestParam(required = false) Integer libraryId) {
        if (libraryId != null && libraryId > 0) {
            Library library = libraryService.findById(libraryId);
            book.setLibrary(library);
        }
        bookService.save(book);
        return "redirect:/web/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookService.delete(id);
        return "redirect:/web/books";
    }

    // ============ LIBRARY CRUD OPERATIONS ============

    @GetMapping("/libraries")
    public String listLibraries(Model model) {
        List<Library> libraries = libraryService.findAllSorted();
        model.addAttribute("libraries", libraries);
        return "libraries-list";
    }

    @GetMapping("/libraries/new")
    public String showLibraryForm(Model model) {
        model.addAttribute("library", new Library());
        return "library-form";
    }

    @GetMapping("/libraries/edit/{id}")
    public String editLibrary(@PathVariable Integer id, Model model) {
        Library library = libraryService.findById(id);
        model.addAttribute("library", library);
        return "library-form";
    }

    @PostMapping("/libraries/save")
    public String saveLibrary(@ModelAttribute Library library) {
        libraryService.save(library);
        return "redirect:/web/libraries";
    }

    @GetMapping("/libraries/delete/{id}")
    public String deleteLibrary(@PathVariable Integer id) {
        libraryService.delete(id);
        return "redirect:/web/libraries";
    }

    // ============ RELATIONSHIP MANAGEMENT ============

    @GetMapping("/relationships")
    public String manageRelationships(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("libraries", libraryService.findAllSorted());
        return "relationships";
    }

    @PostMapping("/relationships/add")
    public String addBookToLibrary(@RequestParam Integer bookId, @RequestParam Integer libraryId) {
        Book book = bookService.findById(bookId);
        Library library = libraryService.findById(libraryId);

        if (book != null && library != null) {
            library.addBook(book);
            libraryService.save(library);
        }

        return "redirect:/web/relationships";
    }

    @PostMapping("/relationships/remove")
    public String removeBookFromLibrary(@RequestParam Integer bookId) {
        Book book = bookService.findById(bookId);

        if (book != null && book.getLibrary() != null) {
            Library library = book.getLibrary();
            library.removeBook(book);
            libraryService.save(library);
        }

        return "redirect:/web/relationships";
    }
}