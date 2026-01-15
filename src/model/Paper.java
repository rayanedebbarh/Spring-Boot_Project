package model;

public class Paper {

    private int id;
    private String title;
    private String abstractText;
    private int year;
    private Author author;

    public Paper() {
    }

    public Paper(int id, String title, String abstractText, int year, Author author) {
        this.id = id;
        this.title = title;
        this.abstractText = abstractText;
        this.year = year;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public int getYear() {
        return year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
