public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibraryUserInterface ui = new LibraryUserInterface(library);
        /*
        library.generateBooks();
        library.generateLoaners();
         */
        ui.start();
    }
}