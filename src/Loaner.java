import java.util.ArrayList;

public class Loaner extends User {
    ArrayList<Loan> loans = new ArrayList<>();

    public Loaner(String name, String id) {
        super(name, id);
    }

    // todo: lägg till addloans
    public void createLoaner() {

    }

    //Printar alla loanerns lån
    public void printLoanedBooks() {
        if (loans.isEmpty()) {
            System.out.println("Du har inga lånade böcker");
            return;
        }
        System.out.println("Lånade böcker: ");
        for (Loan loan : loans) {
            Book book = loan.getBook();
            System.out.println(book);
        }
    }

    //Lånar en specifik bok om den inte är lånad
    public void loanBook(Book book) {
        Loan loan = new Loan(book, this);
        loans.add(loan);
    }

    //Lämnar tillbaka en bok
    public void returnBook(Book book) {
        Loan loan = new Loan(book, this);
        loans.remove(loan);
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }
}
