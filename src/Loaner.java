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
    protected void printLoanedBooks() {
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
    protected void loanBook(String title, Library library) {
        for (Book book : library.getBooks()) {
            if (book.getTITLE().equalsIgnoreCase(title)) {
                if (book.getLoaned()) {
                    System.out.println("Boken är redan lånad.");
                    return;
                } else {
                    Loan loan = new Loan(book, this);
                    loans.add(loan);
                    book.setLoaned(true);
                    System.out.println("Du har lånat " + book.getTITLE());
                    return;
                }
            }
        }
        System.out.println("Boken hittades inte.");
    }

    //Lämnar tillbaka en bok
    protected void returnBook(String title, Loaner loaner) {
        ArrayList<Loan> loans = loaner.getLoans();
        if (!loaner.getLoans().isEmpty()) {
            for (int i = 0; i < loans.size(); i++) {
                Loan loan = loans.get(i);
                if (loan.getBook() != null && loan.getBook().getTITLE().equalsIgnoreCase(title)) {
                    loaner.getLoans().remove(loan);
                    loan.getBook().setLoaned(false);
                    System.out.println(loan.getBook().getTITLE() + " har lämnats tillbaka");
                    break;
                } else {
                    System.out.println("Hittade inte boken du angav");
                }
            }
        } else {
            System.out.println("Du har inga lånade böcker");
        }
    }

}
