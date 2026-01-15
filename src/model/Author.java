package model;

public class Author {

    private int id;
    private String name;
    private String email;
    private String affiliation;

    public Author() {
    }

    public Author(int id, String name, String email, String affiliation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.affiliation = affiliation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }
}
