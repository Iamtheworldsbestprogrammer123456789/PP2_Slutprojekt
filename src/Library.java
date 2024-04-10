import java.util.ArrayList;

public class Library {
    ArrayList<Book> books = new ArrayList<Book>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Library() {
        generateBooks();
        Loaner loaner = new Loaner();
        loaner.loanBook(books.get(0));
        loaner.loanBook(books.get(1));
        loaner.loanBook(books.get(2));
        loaner.loanBook(books.get(3));
        // todo: LIV: Enale loaning
        loaner.printLoanedBooks();
    }

    private void generateBooks() {
        this.books.add(new Book("Ljusets rike", "Axel Västerberg", "Fantasy-sektionen, hylla U1"));
        this.books.add(new Book("Hemligheternas kammare", "Ebba Åberg", "Skräck-sektionen, hylla V4"));
        this.books.add(new Book("Morgonbris", "Ludvig Östlund", "Roman-sektionen, hylla W2"));
        this.books.add(new Book("Stjärnfall", "Saga Öberg", "Science Fiction-sektionen, hylla X7"));
    }

}
