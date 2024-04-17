import java.util.Scanner;

public class LibraryUserInterface {
    //Gör en instans av liberary och den nuvarande inloggade lånaren
    private Library library;
    private Loaner currentLoaner;
    static Scanner scan = new Scanner(System.in);

    public LibraryUserInterface(Library library) {
        this.library = library;
    }
    //TODO: Göra klart LoanerMenu(), printa en lånares lån och lämna tillbaka en bok
    //TODO: Låna en bok med menyn
    //TODO: Lägga till kommentarer som förklarar koden

    public void Start() {
        System.out.println("Välkommen till NTIs bibliotek!");
        while (true) {
            //TODO: gör denna popup till en metod
            System.out.println("___________________________________________");
            System.out.println("|  1. Logga in som lånare                 |");
            System.out.println("|  2. Skapa ett konto                     |");
            System.out.println("|  3. Kolla på böcker                     |");
            System.out.println("|  4. Avsluta                             |");
            System.out.println("|__________________________________________");
            System.out.print("Välj ett alternativ: ");

            int choice = try_catch_int();
            switch (choice) {
                case 1:
                    LogInLoaner();
                    break;
                case 2:
                    library.createUser();
                    break;
                case 3:
                    BrowseBooks();
                    break;
                case 4:
                    System.out.println("Hejdå!");
                    return;
                default:
                    System.out.println("Ogiltigt alternativ, försök igen.");
            }
        }
    }

    private void LoanerMenu() {
        while (true) {
            //TODO: gör denna popup till en metod
            System.out.println("___________________________________________");
            System.out.println("|  1. Kolla på böcker                      |");
            System.out.println("|  2. Låna en bok                          |");
            System.out.println("|  3. Visa lånade böcker                   |");
            System.out.println("|  4. Lämna tillbaka en bok                |");
            System.out.println("|  5. Logga ut                             |");
            System.out.println("|__________________________________________|");
            System.out.print("Välj ett alternativ:");

            int choice = try_catch_int();
            switch (choice) {
                case 1:
                    BrowseBooks();
                    break;
                case 2:
                    LoanBook();
                    break;
                case 3:
                    currentLoaner.printLoanedBooks();
                    break;
                case 4:
                    currentLoaner.returnBook(currentLoaner, scan);
                    break;
                case 5:
                    currentLoaner = null;
                    System.out.println("Hejdå!");
                    return;
                default:
                    System.out.println("Ogiltigt alternativ, försök igen.");
            }
        }

    }

    private void LogInLoaner() {
        if (!library.getLoaners().isEmpty()) {
            scan.nextLine();
            while (true) {
                System.out.print("Ange ditt personnummer:");
                String loanerID = scan.nextLine();
                if (library.getLoaners().containsKey(loanerID)) {
                    currentLoaner = library.getLoaners().get(loanerID);
                    System.out.println("Inloggad som " + currentLoaner.getName());
                    LoanerMenu();
                    break;
                } else {
                    System.out.println("Kunde inte hitta en lånare med det angivna personnummret");
                }
            }
        } else {
            System.out.println("Det finns inga tillgängliga lånare\n");
        }

    }

    private void BrowseBooks() {
        System.out.println("Böcker:");
        for (Book book : library.getBooks()) {
            System.out.println(book);
        }
    }

    private void LoanBook() {
        BrowseBooks();
        System.out.println("Ange titel på boken du vill låna: ");
        scan.nextLine();
        String titel = scan.nextLine();
        for (Book book : library.getBooks()) {
            if (book.getTITLE().equalsIgnoreCase(titel)) {
                if (book.getLoaned()) {
                    System.out.println("Boken är lånad.");
                } else {
                    currentLoaner.loanBook(book);
                }
            }
        }
    }

    private void ReturnBook() {
        if (!currentLoaner.getLoans().isEmpty()) {
            currentLoaner.printLoanedBooks();
            System.out.println("Skriv titeln på boken som du vill Lämna tillbaka:");
            String title = scan.nextLine();
            for (Loan loan : currentLoaner.getLoans()) {
                if (loan.getBook().getTITLE().equalsIgnoreCase(title)) {
                    currentLoaner.getLoans().remove(loan);
                    loan.getBook().setLoaned(false);
                    System.out.println(loan.getBook().getTITLE() + "har lämnats tillbaka");
                }
            }
        }
    }

    public static int try_catch_int() {
        int tal = 0;

        try {
            tal = scan.nextInt();
            //om inputen är mottagbar så sparas den och skippar catch
        } catch (Exception e) {
            //om det inte är en acceptabel input printas det och metoden kallas sig själv igen
            System.out.print("Ange en siffra!\n: ");
            scan.nextLine();
            try_catch_int();
        }

        return tal;
    }

}
