package app;

import repository.*;
import service.*;
import ui.ConsoleUI;

public class App {
    public static void main(String[] args) {

        BookRepository bookRepo = new BookRepositoryImpl();
        BookService bookService = new BookService(bookRepo);

        ConsoleUI ui = new ConsoleUI(bookService);
        ui.start();
    }
}
