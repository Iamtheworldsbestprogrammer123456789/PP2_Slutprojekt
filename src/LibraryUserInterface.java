import java.util.Scanner;

public class LibraryUserInterface {
    //Gör en instans av liberary och den nuvarande inloggade lånaren
    private Library library;
    private Loaner currentLoaner;
    private Librarian currentLibrarian;

    static Scanner scan = new Scanner(System.in);

    public LibraryUserInterface(Library library) {
        this.library = library;
    }

    //TODO: Lägga till kommentarer som förklarar koden
    //TODO: Fixa try_catch_int
    public void start() {
        System.out.println("Välkommen till NTIs bibliotek!");
        startOptionsWindow();
        while (true) {
            System.out.print("Välj ett alternativ: ");
            int choice = try_catch_int();
            switch (choice) {
                case 1:
                    logInLoaner();
                    break;
                case 2:
                    library.createLoaner();
                    startOptionsWindow();
                    break;
                case 3:
                    browseBooks(true);
                    break;
                case 4:
                    logInLibrarian();
                    break;
                case 5:
                    System.out.println("Hejdå!");
                    return;
                default:
                    startOptionsWindow();
                    System.out.println("Ogiltigt alternativ, försök igen.");
            }
        }
    }


    private void loanerMenu() {

        loanerOptionsWindow();
        while (true) {
            System.out.print("Välj ett alternativ:");
            int choice = try_catch_int();
            switch (choice) {
                case 1:
                    browseBooks(true);
                    break;
                case 2:
                    loanBook();
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

    private void librarianMenu() {

        librarianOptionsWindow();
        while (true) {
            System.out.print("Välj ett alternativ:");
            int choice = try_catch_int();
            switch (choice) {
                case 1:
                    removeLoaner();
                    break;
                case 2:
                    library.showLoanersLoans(scan);
                    break;
                case 3:
                    library.createLibrarian();
                    librarianOptionsWindow();
                    break;
                case 4:
                    currentLibrarian = null;
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
        System.out.println("|   4. Logga in som bibliotekarie           |");
        System.out.println("|   5. Avsluta                              |");
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

    public void librarianOptionsWindow() {
        System.out.println("____________________________________________");
        System.out.print("|  Inloggad: " + currentLibrarian.getName());
        fillInBlank();
        System.out.println("|");
        System.out.println("|   1. Ta bort ett lånar konto              |");
        System.out.println("|   2. Visa en lånares lånade böcker        |");
        System.out.println("|   3. Lägg till en anställd i systemet     |");
        System.out.println("|   4. Logga ut                             |");
        System.out.println("|___________________________________________|");
    }

    //Denna metod tar in länden på användarens namn printar sedan ett viss antal mellanslag så att menyn ser bra ut
    public void fillInBlank() {
        if (currentLoaner != null) {
            int numb = 31 - currentLoaner.getName().length();
            for (int i = 0; i < numb; i++) {
                System.out.print(" ");
            }
        } else {
            int numb = 31 - currentLibrarian.getName().length();
            for (int i = 0; i < numb; i++) {
                System.out.print(" ");
            }
        }
    }

    private void logInLoaner() {
        if (!library.getLoaners().isEmpty()) {
            scan.nextLine();
            while (true) {
                System.out.print("Ange ditt personnummer: ");
                String personnummer = scan.nextLine();
                if (library.getLoaners().containsKey(personnummer)) {
                    currentLoaner = library.getLoaners().get(personnummer);
                    loanerMenu();
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

    private void logInLibrarian() {
        if (!library.getLibrarians().isEmpty()) {
            scan.nextLine();
            while (true) {
                System.out.print("Ange ditt personal ID: ");
                String id = scan.nextLine();
                if (library.getLibrarians().containsKey(id)) {
                    currentLibrarian = library.getLibrarians().get(id);
                    librarianMenu();
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

    private void browseBooks(boolean show) {
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

    public void loanBook() {
        browseBooks(false);
        System.out.println("Ange titeln på boken som du vill låna: ");
        scan.nextLine();
        String title = scan.nextLine();
        loanerOptionsWindow();
        currentLoaner.loanBook(title, library);

    }

    private void removeLoaner() {
        System.out.println("Ange lånarens personnummer: ");
        scan.nextLine();
        String personnummer = scan.nextLine();
        librarianOptionsWindow();
        if (library.getLoaners().containsKey(personnummer)) {
            library.getLoaners().remove(personnummer);
            System.out.println("Lånarens konto har nu tagits bort");
        } else {
            System.out.println("Ingen lånare med personnummret " + personnummer + " hittades.");
        }
    }

    public static int try_catch_int() {
        int tal = 0;

        try {
            tal = scan.nextInt();
            //om inputen är mottagbar så sparas den och skippar catch
        } catch (Exception e) {
            //om det inte är en acceptabel input printas det och metoden kallas sig själv igen
            System.out.println("Ogiltig input, försök igen!");
            System.out.print("Välj ett alternativ: ");
            scan.nextLine();
            tal = try_catch_int();
        }

        return tal;
    }

}
