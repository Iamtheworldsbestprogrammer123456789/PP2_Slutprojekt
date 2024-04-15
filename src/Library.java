import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Library {
    ArrayList<Book> books = new ArrayList<>();
    //ArrayList<Loaner> loaners = new ArrayList<>();
    HashMap<String, Loaner> loaners = new HashMap<>();

    public Library() {

        generateBooks();
        Loaner loaner = new Loaner("Carl", "20050626");
        loaners.put(loaner.getPersonnummer(), loaner);
        System.out.println(getLoanerId(1));
        loaner.loanBook(books.get(0));
        loaner.loanBook(books.get(1));
        loaner.loanBook(books.get(2));
        loaner.loanBook(books.get(3));
        // todo: LIV: Enale loaning
        loaner.printLoanedBooks();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public HashMap<String, Loaner> getLoaners() {
        return loaners;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    private void generateBooks() {
        this.books.add(new Book("Ljusets rike", "Axel Västerberg", "Fantasy-sektionen, hylla U1"));
        this.books.add(new Book("Hemligheternas kammare", "Ebba Åberg", "Skräck-sektionen, hylla V4"));
        this.books.add(new Book("Morgonbris", "Ludvig Östlund", "Roman-sektionen, hylla W2"));
        this.books.add(new Book("Stjärnfall", "Saga Öberg", "Science Fiction-sektionen, hylla X7"));
    }

    public void createUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Namn?");
        String namn = scan.nextLine();
        System.out.println("ID?");
        String id = scan.nextLine();
        loaners.put(id, new Loaner(namn, id));
    }

}
