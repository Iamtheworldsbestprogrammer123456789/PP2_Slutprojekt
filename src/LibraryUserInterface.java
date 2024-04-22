import java.util.Scanner;

public class LibraryUserInterface {
    //Gör en instans av liberary och den nuvarande inloggade lånaren
    private Library library;
    private Loaner currentLoaner;
    static Scanner scan = new Scanner(System.in);

    public LibraryUserInterface(Library library) {
        this.library = library;
    }
    //TODO: Lägga till kommentarer som förklarar koden

    public void Start() {
        System.out.println("Välkommen till NTIs bibliotek!");
        System.out.println("____________________________________________");
        System.out.println("|        STARTMENY NTIs  BIBLIOTEK          |");
        System.out.println("|   1. Logga in som lånare                  |");
        System.out.println("|   2. Skapa ett konto                      |");
        System.out.println("|   3. Se alla böcker                       |");
        System.out.println("|   4. Avsluta                              |");
        System.out.println("|___________________________________________|");
        while (true) {
            System.out.print("Välj ett alternativ: ");
            int choice = try_catch_int();
            switch (choice) {
                case 1:
                    LogInLoaner();
                    break;
                case 2:
                    library.createUser();
                    startOptionsWindow();
                    break;
                case 3:
                    BrowseBooks(true);
                    break;
                case 4:
                    System.out.println("Hejdå!");
                    return;
                default:
                    startOptionsWindow();
                    System.out.println("Ogiltigt alternativ, försök igen.");
            }
        }
    }


    private void LoanerMenu() {

        //TODO: gör denna popup till en metod
        System.out.println("____________________________________________");
        System.out.print("|  Inloggad: " + currentLoaner.getName());
        fillInBlank();
        System.out.println("|");
        System.out.println("|   1. Se alla böcker                       |");
        System.out.println("|   2. Låna en bok                          |");
        System.out.println("|   3. Visa lånade böcker                   |");
        System.out.println("|   4. Lämna tillbaka en bok                |");
        System.out.println("|   5. Logga ut                             |");
        System.out.println("|___________________________________________|");

        while (true) {
            System.out.print("Välj ett alternativ:");
            int choice = try_catch_int();
            switch (choice) {
                case 1:
                    BrowseBooks(true);
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
                    startOptionsWindow();
                    System.out.println("Hejdå!");
                    return;
                default:
                    loanerOptionsWindow();
                    System.out.println("Ogiltigt alternativ, försök igen.");
            }
        }

    }

    public static void startOptionsWindow() {
        System.out.println("____________________________________________");
        System.out.println("|        STARTMENY NTIs  BIBLIOTEK          |");
        System.out.println("|   1. Logga in som lånare                  |");
        System.out.println("|   2. Skapa ett konto                      |");
        System.out.println("|   3. Se alla böcker                       |");
        System.out.println("|   4. Avsluta                              |");
        System.out.println("|___________________________________________|");
    }

    public void loanerOptionsWindow() {
        System.out.println("____________________________________________");
        System.out.print("|  Inloggad: " + currentLoaner.getName());
        fillInBlank();
        System.out.println("|");
        System.out.println("|   1. Se alla böcker                       |");
        System.out.println("|   2. Låna en bok                          |");
        System.out.println("|   3. Visa lånade böcker                   |");
        System.out.println("|   4. Lämna tillbaka en bok                |");
        System.out.println("|   5. Logga ut                             |");
        System.out.println("|___________________________________________|");
    }

    //Denna metod tar in länden på lånarens namn printar sedan ett viss antal mellanslag så att menyn ser bra ut
    public void fillInBlank() {
        int numb = 31 - currentLoaner.getName().length();
        for (int i = 0; i < numb; i++) {
            System.out.print(" ");
        }
    }

    private void LogInLoaner() {
        if (!library.getLoaners().isEmpty()) {
            scan.nextLine();
            while (true) {
                System.out.print("Ange ditt personnummer: ");
                String loanerID = scan.nextLine();
                if (library.getLoaners().containsKey(loanerID)) {
                    currentLoaner = library.getLoaners().get(loanerID);
                    LoanerMenu();
                    break;
                } else {
                    startOptionsWindow();
                    System.out.println("Kunde inte hitta en lånare med det angivna personnummret!");
                    break;
                }
            }
        } else {
            loanerOptionsWindow();
            System.out.println("Det finns inga tillgängliga lånare\n");
        }

    }

    private void BrowseBooks(boolean show) {
        System.out.println("Böcker:");
        for (Book book : library.getBooks()) {
            System.out.println(book);
        }
        if (show) {
            System.out.print("\nTRYCK ENTER FÖR ATT GÅ TILLBAKA TILL MENYN");
            scan.nextLine();
            scan.nextLine();

            if (currentLoaner == null) {
                startOptionsWindow();
            } else {
                loanerOptionsWindow();
            }
        }
    }

    private void LoanBook() {
        BrowseBooks(false);
        System.out.println("Ange titel på boken du vill låna: ");
        scan.nextLine();
        String titel = scan.nextLine();
        loanerOptionsWindow();
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

    public static int try_catch_int() {
        int tal = 0;

        try {
            tal = scan.nextInt();
            //om inputen är mottagbar så sparas den och skippar catch
        } catch (Exception e) {
            //om det inte är en acceptabel input printas det och metoden kallas sig själv igen
            System.out.println("Ange en siffra!");
            System.out.print("Välj ett alternativ: ");
            scan.nextLine();
            try_catch_int();
        }

        return tal;
    }

}
