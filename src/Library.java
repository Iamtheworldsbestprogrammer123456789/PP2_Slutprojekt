import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Library {
    BookFileManager bfm = new BookFileManager();
    ArrayList<Book> books = new ArrayList<>();
    HashMap<String, Loaner> loaners = new HashMap<>();
    HashMap<String, Librarian> librarians = new HashMap<>();

    public Library() {
        librarians.put("1", new Librarian("Defult", "1"));
    }

    public HashMap<String, Librarian> getLibrarians() {
        return librarians;
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
        loaners.put("202204160001", new Loaner("Alice", "202204160001"));
        loaners.put("192204160002", new Loaner("Bob", "192204160002"));
        loaners.put("202204160003", new Loaner("Catherine", "202204160003"));
        loaners.put("202204160004", new Loaner("David", "202204160004"));
        loaners.put("202204160005", new Loaner("Eva", "202204160005"));
        loaners.put("202204160006", new Loaner("Frank", "202204160006"));
        loaners.put("202204160007", new Loaner("Grace", "202204160007"));
        loaners.put("202204160008", new Loaner("Henry", "202204160008"));
        loaners.put("202204160009", new Loaner("Irene", "202204160009"));
        loaners.put("202204160010", new Loaner("Jack", "202204160010"));
    }

    public Boolean checkPersonnummer(String personnummer) {
        if (personnummer.length() == 12) {
            int year = Integer.parseInt(personnummer.substring(0, 4));
            int month = Integer.parseInt(personnummer.substring(4, 6));
            int day = Integer.parseInt(personnummer.substring(6, 8));
            if (year >= 1900 && year <= 2024 && month >= 1 && month <= 12 && day >= 1 && day <= 31) {
                return true;
            }
        }
        return false;
    }

    public void createUser() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Namn: ");
        String namn = scan.nextLine();
        while (true) {
            System.out.println("Ange ditt personnummer i formatet yyyymmddxxxx");
            System.out.print(": ");
            String id = scan.nextLine();
            if (checkPersonnummer(id)) {
                loaners.put(id, new Loaner(namn, id));
                break;
            } else {
                System.out.println("Ogiltigt personnummer.");
            }
        }
    }

    public void showLoanersLoans(Scanner scan) {
        System.out.println("Ange lånarens personnummer: ");
        String personnummer = scan.nextLine();
        Loaner loaner = loaners.get(personnummer);
        int num = 1;
        for (Loan loan : loaner.getLoans()) {
            Book book = loan.getBook();
            System.out.println(num + ": " + book);
            num++;
        }
    }

}
