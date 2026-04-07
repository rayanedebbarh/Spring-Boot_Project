package app.model;

// Activity 7: AI assistance used for debugging JSON circular reference issue

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private int year;


    @ManyToOne
    @JoinColumn(name = "library_id")
    @JsonIgnore
    private Library library;

    public Book() {}

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public Library getLibrary() { return library; }
    public void setLibrary(Library library) { this.library = library; }

    // Relationship management methods
    public void removeLibrary() {
        if (this.library != null) {
            this.library.getBooks().remove(this);
            this.library = null;
        }
    }
}