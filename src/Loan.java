public class Loan {
    private Book book;
    private Loaner loaner;

    public Loan(Book book, Loaner loaner) {
        setBook(book);
        this.loaner = loaner;
    }

    public Book getBook() {
        return book;
    }

    public User getLoaner() {
        return loaner;
    }

    public void setBook(Book book) {
        if (!book.getLoaned()) {
            this.book = book;
        } else {
            System.out.println("Boken är lånad");
        }
    }

    public void setLoaner(Loaner loaner) {
        this.loaner = loaner;
    }

    //Todo: LIV:fix view return date
}
