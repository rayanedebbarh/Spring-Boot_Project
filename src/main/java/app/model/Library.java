package app.model;

public class Library {
    private int id;
    private String name;
    private String city;

    // No-args constructor (required for JSON deserialization)
    public Library() {
    }

    // Full constructor
    public Library(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}