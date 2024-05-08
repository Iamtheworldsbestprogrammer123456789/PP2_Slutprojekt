
//Ett loan innehåller en bok och en lånare
public class Loan {
    private Book book;
    private Loaner loaner;

    //Constructor
    public Loan(Book book, Loaner loaner) {
        setBook(book);
        this.loaner = loaner;
    }

    //getter & setters:
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}