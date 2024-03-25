public class Book {
    private String title;
    private String author;
    private String location;

    public Book(String title, String author, String location) {
        this.title = title;
        this.author = author;
        this.location = location;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getLocation() {
        return this.location;
    }

}
