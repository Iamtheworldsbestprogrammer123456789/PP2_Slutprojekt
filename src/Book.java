import java.io.Serializable;

public class Book implements Serializable {
    private final String TITLE;
    private final String AUTHOR;
    private final String LOCATION;
    private Boolean loaned = false;
    //Lägg till borrowed

    public Book(String title, String AUTHOR, String LOCATION) {
        this.TITLE = title;
        this.AUTHOR = AUTHOR;
        this.LOCATION = LOCATION;
    }

    public String getTITLE() {
        return this.TITLE;
    }

    public String getAUTHOR() {
        return this.AUTHOR;
    }

    public String getLOCATION() {
        return this.LOCATION;
    }

    public Boolean getLoaned() {
        return loaned;
    }

    public void setLoaned(Boolean loaned) {
        this.loaned = loaned;
    }

    @Override
    public String toString() {
        return "Titel: " + TITLE + '\'' +
                ", Författare: " + AUTHOR + '\'' +
                ", Plats: " + LOCATION;
    }
}
