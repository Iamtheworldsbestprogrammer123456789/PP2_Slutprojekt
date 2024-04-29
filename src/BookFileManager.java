import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

//Denna klass Läser och skriver data som tillhör programmet
//Data klassen Läser och skriver: ID och namn till alla Bibliotekarier. Namn, personnummer och alla lån som tillhör alla lånare. Alla böcker som finns i biblioteket
//I detta fallet använder jag PrintWriter istället för FileWriter
public class BookFileManager {

    //Filnamnet som all data sparas i
    private static final String FILE_NAME = "library_data.txt";

    //Denna metod skriver all data till text filen
    public static void writeData(ArrayList<Book> books, HashMap<String, Loaner> loaners, HashMap<String, Librarian> librarians) {
        //writer är det som skriver datan i textfilen
        //Om
        //Om inte filen finns Skapar den filen
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            // Skriver alla böcker från programmet till text filen
            writer.println("Böcker:");
            for (Book book : books) {
                //Separerar alla element som tillhör arraylist/HashmMap objektet med "," så den vet när ett nytt element kommer
                writer.println(book.getTITLE() + "," + book.getAUTHOR() + "," + book.getLOCATION());
            }
            writer.println();

            // Skriver alla lånare från programmet till text filen
            writer.println("Lånare:");
            for (Loaner loaner : loaners.values()) {
                //Separerar alla element som tillhör arraylist/HashmMap objektet med "," så den vet när ett nytt element kommer
                writer.println(loaner.getName() + "," + loaner.getId());
                for (Loan loan : loaner.getLoans()) {
                    //Samma sak fast för lånen som tillhör lånaren
                    writer.println(loan.getBook().getTITLE() + "," + loan.getBook().getAUTHOR() + "," + loan.getBook().getLOCATION());
                }
                writer.println(); // Sepererar lånen
            }
            writer.println();

            // Skriver alla Bibliotekarier från programmet till text filen
            writer.println("Bibliotekarier:");
            for (Librarian librarian : librarians.values()) {
                //Separerar alla element som tillhör arraylist objektet med "," så den vet när ett nytt element kommer
                writer.println(librarian.getName() + "," + librarian.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Denna metod kollar vilken typ av data det är som den ska läsa. Böcker, Lånare eller Bibliotekarier och kallar på respektive metod för att ladda in datan
    public static void loadData(ArrayList<Book> books, HashMap<String, Loaner> loaners, HashMap<String, Librarian> librarians) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                //Känner av
                if (line.equals("Böcker:")) {
                    loadBooks(reader, books);
                } else if (line.equals("Lånare:")) {
                    loadLoaners(reader, loaners, books);
                } else if (line.equals("Bibliotekarier:")) {
                    loadLibrarians(reader, librarians);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Läser böcker från text filen och sparar det i ArrayListen
    private static void loadBooks(BufferedReader reader, ArrayList<Book> books) throws IOException {
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            //Känner av när ett "," kommer och ett nytt element ska läsas in
            String[] parts = line.split(",");
            //När den har fått alla elementen som ska vara i ett objektet lägger den in det i HashMapen
            if (parts.length == 3) {
                books.add(new Book(parts[0], parts[1], parts[2]));
            }
        }
    }

    //Läser datan som tillhör alla lånare och sparar det i Hashmapen
    private static void loadLoaners(BufferedReader reader, HashMap<String, Loaner> loaners, ArrayList<Book> books) throws IOException {
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            //Bryter upp line till parts med hjälp av ","
            String[] parts = line.split(",");
            //När den har fått alla elementen som ska vara i objektet lägger den in det i HashMapen
            if (parts.length == 2) {
                Loaner loaner = new Loaner(parts[0], parts[1]);
                //Läser in lånarens lån
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    //Bryter upp line till Bookparts med hjälp av ","
                    String[] bookParts = line.split(",");
                    //När den har fått alla elementen som ska vara i ett objektet lägger den in det i HashMapen
                    if (bookParts.length == 3) {
                        Book book = findBook(books, bookParts[0], bookParts[1], bookParts[2]);
                        if (book != null) {
                            Loan loan = new Loan(book, loaner);
                            loaner.loans.add(loan);
                        }
                    }
                }
                loaners.put(parts[1], loaner);
            }
        }
    }

    //Hittar en specifik bok
    private static Book findBook(ArrayList<Book> books, String title, String author, String location) {
        for (Book book : books) {
            if (book.getTITLE().equals(title) && book.getAUTHOR().equals(author) && book.getLOCATION().equals(location)) {
                return book;
            }
        }
        return null;
    }

    //Laddar data från text filen som tillhör alla bibliotekarier och sparar det i Hashmapen för alla Bibliotekarier
    private static void loadLibrarians(BufferedReader reader, HashMap<String, Librarian> librarians) throws IOException {
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            //Bryter upp line till parts med hjälp av ","
            String[] parts = line.split(",");
            //När den har fått alla elementen i objektet lägger den in det i HashMapen
            if (parts.length == 2) {
                librarians.put(parts[1], new Librarian(parts[0], parts[1]));
            }
        }
    }
}



