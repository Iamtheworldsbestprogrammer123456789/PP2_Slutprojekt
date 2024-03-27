import java.util.ArrayList;

public class Loaner extends User {
    ArrayList<Book> loans = new ArrayList<>();

    public Loaner() {
        loanBook();
    }

    public void printLoans() {
        for (Book book : loans) {
            System.out.println(book);
        }
    }

    public void loanBook() {

    }

    public void returnBook() {

    }

    public void setLoan(Book book) {
        this.loans.add(book);
    }

    public ArrayList<Book> getLoans() {
        return loans;
    }
}
