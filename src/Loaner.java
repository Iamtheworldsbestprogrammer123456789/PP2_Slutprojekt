import java.util.ArrayList;
import java.util.Scanner;

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
        int num = 1;
        for (Loan loan : loans) {
            Book book = loan.getBook();
            System.out.println(num + ": " + book);
            num++;
        }
    }

    //Lånar en specifik bok om den inte är lånad
    public void loanBook(Book book) {
        Loan loan = new Loan(book, this);
        loans.add(loan);
        book.setLoaned(true);
        System.out.println("Du har nu lånat " + book.getTITLE());
    }

    //Lämnar tillbaka en bok
    public void returnBook(Loaner loaner, Scanner scan) {
        if (!loaner.getLoans().isEmpty()) {
            loaner.printLoanedBooks();
            System.out.println("Skriv titeln på boken som du vill Lämna tillbaka:");
            scan.nextLine();
            String title = scan.nextLine();
            ArrayList<Loan> loans = loaner.getLoans();
            for (int i = 0; i < loans.size(); i++) {
                Loan loan = loans.get(i);
                if (loan.getBook().getTITLE().equalsIgnoreCase(title)) {
                    loaner.getLoans().remove(loan);
                    loan.getBook().setLoaned(false);
                    System.out.println(loan.getBook().getTITLE() + "har lämnats tillbaka");
                    i--;
                    break;
                }
            }
        }
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }
}
