package app.llm;

public interface LlmService {
    String findIsbn(String title, String author, int year);
}