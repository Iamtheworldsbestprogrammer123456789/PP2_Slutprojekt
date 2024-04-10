import java.util.ArrayList;

public class Loaner extends User {
    ArrayList<Loan> loans = new ArrayList<>();

    public Loaner() {
        //loanBook();
    }

    //Prints all the users loans
    public void printLoanedBooks() {
        if (loans.isEmpty()) {
            System.out.println("You have no loaned books.");
            return;
        }
        System.out.println("Loaned books: ");
        for (Loan loan : loans) {
            Book book = loan.getBook();
            System.out.println(book);
        }
    }

    //Loans a book if it's not loaned
    public void loanBook(Book book) {
        Loan loan = new Loan(book, this);
        loans.add(loan);
    }

    //Returns a book
    public void returnBook(Book book) {
        Loan loan = new Loan(book, this);
        loans.remove(loan);
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }
}
