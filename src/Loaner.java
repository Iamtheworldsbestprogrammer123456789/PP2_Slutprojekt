import java.util.ArrayList;

public class Loaner extends User {
    ArrayList<Loan> loans = new ArrayList<>();

    //Constructor
    public Loaner(String name, String personnummer) {
        super(name, personnummer);
    }

    //getter
    public ArrayList<Loan> getLoans() {
        return loans;
    }

    //Printar en specifik lånares lån
    protected void printLoanedBooks() {
        if (loans.isEmpty()) {
            System.out.println("Du har inga lånade böcker");
            return;
        }
        System.out.println("Lånade böcker: ");
        int num = 1;
        //Går igenom alla lång och printar dem
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
                //Kollar ifall boken redan är lånad
                if (book.getLoaned()) {
                    System.out.println("Boken är redan lånad.");
                    //Om boken inte är lånad lånas den
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
        //Kollar så att lånaren har böcker att lämna tillbaka
        if (!loaner.getLoans().isEmpty()) {
            //Går igenom lånarens lån tills den hittar den specifika boken
            for (int i = 0; i < loans.size(); i++) {
                Loan loan = loans.get(i);
                if (loan.getBook() != null && loan.getBook().getTITLE().equalsIgnoreCase(title)) {
                    loaner.getLoans().remove(loan);
                    loan.getBook().setLoaned(false);
                    System.out.println(loan.getBook().getTITLE() + " har lämnats tillbaka");
                    break;
                    //Om den går igenom alla lån och inte hittar boken printas detta
                } else {
                    System.out.println("Hittade inte boken du angav");
                }
            }
            //Om inte lånaren har några lånade böcker
        } else {
            System.out.println("Du har inga lånade böcker");
        }
    }

}
