import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Library {

    //Listan med alla böcker
    ArrayList<Book> books = new ArrayList<>();
    //Listan med alla lånare
    HashMap<String, Loaner> loaners = new HashMap<>();
    //Listan med alla bibliotekarier
    HashMap<String, Librarian> librarians = new HashMap<>();

    //Constructor
    public Library() {
        LibraryFileManager.loadData(books, loaners, librarians);
    }

    //getter & setters:
    public HashMap<String, Librarian> getLibrarians() {
        return librarians;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public HashMap<String, Loaner> getLoaners() {
        return loaners;
    }

    //Tar ett personumer i formatet yyyymmddxxxx. Kollar så att längden är korrekt och sedan att datumet är rimligt.
    public Boolean checkPersonnummer(String personnummer) {
        //Kollar så att personnumret är den bestämda längden
        if (personnummer.length() == 12) {
            //tar ut året från personnumret
            int year = Integer.parseInt(personnummer.substring(0, 4));
            //Tar ut månaden från personnumret
            int month = Integer.parseInt(personnummer.substring(4, 6));
            //Tar ut dagen från personnumret
            int day = Integer.parseInt(personnummer.substring(6, 8));
            //Kollar så att personnumret är inom rimliga gränser
            return year >= 1900 && year <= 2024 && month >= 1 && month <= 12 && day >= 1 && day <= 31;
        }
        return false;
    }

    //Skapar ett konto för en lånare
    protected void createLoaner() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Namn: ");
        String namn = scan.nextLine();
        while (true) {
            System.out.println("Ange ditt personnummer i formatet yyyymmddxxxx");
            System.out.print(": ");
            String id = scan.nextLine();
            if (checkPersonnummer(id)) {
                loaners.put(id, new Loaner(namn, id));
                break;
            } else {
                System.out.println("Ogiltigt personnummer.");
            }
        }
    }

    //Skapar ett konto för en biblotikarie
    protected void createLibrarian() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Namn: ");
        String namn = scan.nextLine();
        //Random id
        String id = idGenerator();
        while (true) {
            //Kollar så att det random genererade id inte redan finns
            if (!librarians.containsKey(id)) {
                librarians.put(id, new Librarian(namn, id));
                System.out.println("\nAnställd registrerad.");
                System.out.println("Namn: " + namn);
                System.out.println("ID: " + id);
                System.out.print("Tryck enter för att gå tillbaka till menyn.");
                scan.nextLine();
                break;
            }
            //Om det redan finns ett konto med samma id genereras ett nytt
            else {
                id = idGenerator();
            }
        }
    }

    //Genererar ett random 9 siffrigt nummer som används till personal id
    private String idGenerator() {
        Random random = new Random();
        int num = random.nextInt(999999999 - 100000000 + 1) + 100000000;
        return String.valueOf(num);
    }

    //Denna metod visar en specifik lånares lån
    protected void showLoanersLoans(Scanner scan) {
        System.out.println("Ange lånarens personnummer: ");
        scan.nextLine();
        String personnummer = scan.nextLine();
        Loaner loaner = loaners.get(personnummer);
        //Kollar så att lånaren har lån
        if (!loaner.getLoans().isEmpty()) {
            int num = 1;
            //Går igenom och printar alla lån
            for (Loan loan : loaner.getLoans()) {
                Book book = loan.getBook();
                System.out.println(num + ": " + book);
                num++;
            }
            //Om inte lånaren har några lån
        } else {
            System.out.println("Lånaren har inga lånade böcker.");
        }
    }

}
