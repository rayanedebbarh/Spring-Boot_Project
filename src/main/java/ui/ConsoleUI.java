package ui;

import service.BookService;
import model.Book;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final BookService bookService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(BookService bookService) {
        this.bookService = bookService;
    }

    public void start() {
        int choice;

        do {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> listBooks();
                case 3 -> countBooks();
                case 4 -> findBookById();
                case 5 -> deleteBook();
                case 0 -> System.out.println("Exiting application...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void printMenu() {
        System.out.println("\n===== Library Menu =====");
        System.out.println("1. Add Book");
        System.out.println("2. List All Books");
        System.out.println("3. Count Books");
        System.out.println("4. Find Book by ID");
        System.out.println("5. Delete Book");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private void addBook() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        Book book = new Book(id, title, author);
        bookService.save(book);

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
        if (book != null) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        } else {
            System.out.println("Book not found.");
        }
    }

    private void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int id = scanner.nextInt();
        bookService.delete(id);
        System.out.println("Book deleted if it existed.");
    }
}
