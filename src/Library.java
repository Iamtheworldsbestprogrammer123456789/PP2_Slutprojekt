import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Library {
    ArrayList<Book> books = new ArrayList<>();
    HashMap<String, Loaner> loaners = new HashMap<>();

    public Library() {

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

    public void generateBooks() {
        this.books.add(new Book("Ljusets rike", "Axel Västerberg", "Fantasy-sektionen, hylla U1"));
        this.books.add(new Book("Hemligheternas kammare", "Ebba Åberg", "Skräck-sektionen, hylla V4"));
        this.books.add(new Book("Morgonbris", "Ludvig Östlund", "Roman-sektionen, hylla W2"));
        this.books.add(new Book("Stjärnfall", "Saga Öberg", "Science Fiction-sektionen, hylla X7"));
    }

    public void generateLoaners() {
        loaners.put("2204160001", new Loaner("Alice", "2204160001"));
        loaners.put("2204160002", new Loaner("Bob", "2204160002"));
        loaners.put("2204160003", new Loaner("Catherine", "2204160003"));
        loaners.put("2204160004", new Loaner("David", "2204160004"));
        loaners.put("2204160005", new Loaner("Eva", "2204160005"));
        loaners.put("2204160006", new Loaner("Frank", "2204160006"));
        loaners.put("2204160007", new Loaner("Grace", "2204160007"));
        loaners.put("2204160008", new Loaner("Henry", "2204160008"));
        loaners.put("2204160009", new Loaner("Irene", "2204160009"));
        loaners.put("2204160010", new Loaner("Jack", "2204160010"));
    }

    public void createUser() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Namn: ");
        String namn = scan.nextLine();
        System.out.print("Personnummer: ");
        String id = scan.nextLine();
        loaners.put(id, new Loaner(namn, id));
    }

}
