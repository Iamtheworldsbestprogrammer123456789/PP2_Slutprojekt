import java.util.ArrayList;

public class Loaner extends User {
    ArrayList<Book> loans = new ArrayList<>();

    public Loaner() {
        loanBook();
    }

    public void setLoan(Book book) {
        this.loans = loans;
    }
}
