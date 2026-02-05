package app.ui;

import app.model.Book;
import app.model.Library;
import app.service.BookService;
import app.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final BookService bookService;
    private final LibraryService libraryService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(BookService bookService, LibraryService libraryService) {
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    public void start() {
        int choice;
        do {
            printMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> bookMenu();
                case 2 -> libraryMenu();
                case 0 -> System.out.println("Exiting application...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void printMainMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("1. Book Menu");
        System.out.println("2. Library Menu");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // ===================== BOOK MENU =====================
    private void bookMenu() {
        int choice;
        do {
            System.out.println("\n===== Book Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. List All Books");
            System.out.println("3. Count Books");
            System.out.println("4. Find Book by ID");
            System.out.println("5. Delete Book");
            System.out.println("6. Update Book");
            System.out.println("7. Find by Author");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> listBooks();
                case 3 -> countBooks();
                case 4 -> findBookById();
                case 5 -> deleteBook();
                case 6 -> updateBook();
                case 7 -> findBookByAuthor();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void addBook() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        bookService.save(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    private void listBooks() {
        List<Book> books = bookService.findAll();
        System.out.println("\nBooks:");
        for (Book b : books) {
            System.out.println(b.getId() + " - " + b.getTitle() + " by " + b.getAuthor());
        }
    }

    private void countBooks() {
        System.out.println("Total books: " + bookService.count());
    }

    private void findBookById() {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        Book book = bookService.findById(id);
        System.out.println(book != null ? book.getTitle() + " by " + book.getAuthor() : "Book not found.");
    }

    private void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int id = scanner.nextInt();
        bookService.delete(id);
        System.out.println("Book deleted if it existed.");
    }

    private void updateBook() {
        System.out.print("Enter book ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new author: ");
        String author = scanner.nextLine();

        bookService.update(new Book(id, title, author));
        System.out.println("Book updated successfully.");
    }

    private void findBookByAuthor() {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        List<Book> books = bookService.findByAuthor(author);
        if (books.isEmpty()) {
            System.out.println("No books found by this author.");
        } else {
            books.forEach(b -> System.out.println(b.getId() + " - " + b.getTitle() + " by " + b.getAuthor()));
        }
    }

    // ===================== LIBRARY MENU =====================
    private void libraryMenu() {
        int choice;
        do {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Library");
            System.out.println("2. List All Libraries");
            System.out.println("3. Count Libraries");
            System.out.println("4. Find Library by ID");
            System.out.println("5. Delete Library");
            System.out.println("6. Update Library");
            System.out.println("7. Find by City");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addLibrary();
                case 2 -> listLibraries();
                case 3 -> countLibraries();
                case 4 -> findLibraryById();
                case 5 -> deleteLibrary();
                case 6 -> updateLibrary();
                case 7 -> findLibraryByCity();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void addLibrary() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        libraryService.save(new Library(id, name, city));
        System.out.println("Library added successfully.");
    }

    private void listLibraries() {
        List<Library> libraries = libraryService.findAllSorted();
        System.out.println("\nLibraries:");
        for (Library l : libraries) {
            System.out.println(l.getId() + " - " + l.getName() + " in " + l.getCity());
        }
    }

    private void countLibraries() {
        System.out.println("Total libraries: " + libraryService.count());
    }

    private void findLibraryById() {
        System.out.print("Enter library ID: ");
        int id = scanner.nextInt();
        Library lib = libraryService.findById(id);
        System.out.println(lib != null ? lib.getName() + " in " + lib.getCity() : "Library not found.");
    }

    private void deleteLibrary() {
        System.out.print("Enter library ID to delete: ");
        int id = scanner.nextInt();
        libraryService.delete(id);
        System.out.println("Library deleted if it existed.");
    }

    private void updateLibrary() {
        System.out.print("Enter library ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new city: ");
        String city = scanner.nextLine();

        libraryService.update(new Library(id, name, city));
        System.out.println("Library updated successfully.");
    }

    private void findLibraryByCity() {
        System.out.print("Enter city name: ");
        String city = scanner.nextLine();

        List<Library> libraries = libraryService.findByCity(city);
        if (libraries.isEmpty()) {
            System.out.println("No libraries found in this city.");
        } else {
            libraries.forEach(l -> System.out.println(l.getId() + " - " + l.getName() + " in " + l.getCity()));
        }
    }
}
