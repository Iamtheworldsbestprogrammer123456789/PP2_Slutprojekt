import java.util.ArrayList;
import java.util.Scanner;

public class Loaner extends User {
    ArrayList<Loan> loans = new ArrayList<>();

    public Loaner(String name, String id) {
        super(name, id);
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    //Printar alla lånarens lån
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
    public boolean loanBook(String title, Library library) {
        for (Book book : library.getBooks()) {
            if (book.getTITLE().equalsIgnoreCase(title)) {
                if (book.getLoaned()) {
                    System.out.println("Boken är redan lånad.");
                    return false;
                } else {
                    Loan loan = new Loan(book, this);
                    loans.add(loan);
                    book.setLoaned(true);
                    System.out.println("Du har lånat " + book.getTITLE());
                    return true;
                }
            }
        }
        System.out.println("Boken hittades inte.");
        return false;
    }

    //Lämnar tillbaka en bok
    protected void returnBook(Loaner loaner, Scanner scan) {
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
                    break;
                }
            }
        } else {
            System.out.println("Du har inga lånade böcker");
        }
    }

}
