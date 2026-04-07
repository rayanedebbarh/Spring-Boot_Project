// AI-assisted code generated with Claude (Anthropic)
// Prompt: "Create a JMS Consumer class called IsbnMessageListener that listens 
// to the isbn.queue using @JmsListener, receives a book ID as a string, looks up 
// the book using BookService, calls GroqLlmService.findIsbn() and prints the 
// ISBN result to the console."
package app.mq;

import app.llm.LlmService;
import app.model.Book;
import app.service.BookService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class IsbnMessageListener {
    private final LlmService llmService;
    private final BookService bookService;

    public IsbnMessageListener(LlmService llmService, BookService bookService) {
        this.llmService = llmService;
        this.bookService = bookService;
    }

    @JmsListener(destination = "${llm.queue.name}")
    public void receiveMessage(String msg) {
        try {
            System.out.println("Consumer received message: " + msg);
            int bookId = Integer.parseInt(msg.trim());
            Book book = bookService.findById(bookId);
            String isbn = llmService.findIsbn(book.getTitle(), book.getAuthor(), book.getYear());
            System.out.println("ISBN result for book " + bookId + ": " + isbn);
        } catch (Exception e) {
            System.out.println("Error processing message: " + e.getMessage());
        }
    }
}
