public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibraryUserInterface vetintevadjagskakalladenhär = new LibraryUserInterface(library);
        library.generateBooks();
        library.generateLoaners();
        vetintevadjagskakalladenhär.start();
    }
}