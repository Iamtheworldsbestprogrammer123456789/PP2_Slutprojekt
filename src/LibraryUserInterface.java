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
            System.out.println("1. Logga in som lånare");
            System.out.println("2. Kolla på böcker");
            System.out.println("3. Avsluta");
            System.out.print("Välj ett alternativ: ");

            int choice = try_catch_int();
            switch (choice) {
                case 1:
                    LogInLoaner();
                    break;
                case 2:
                    BrowseBooks();
                case 3:
                    System.out.println("Hejdå!");
                    return;
                default:
                    System.out.println("Ogiltigt alternativ, försök igen.");
            }
        }
    }

    private void LoanerMenu() {
        while (true) {
            System.out.println("1. Kolla på böcker");
            System.out.println("2. Visa lånade böcker");
            System.out.println("3. Lämna tillbaka en bok");
            System.out.println("4. Logga ut");
            System.out.print("Välj ett alternativ: ");
        }
    }

    private void LogInLoaner() {
        if (!library.getLoaners().isEmpty()) {
            System.out.println("Ange ditt personnummer:");
            String loanerID = scan.nextLine();
            currentLoaner = library.getLoaners().get(loanerID);
            System.out.println("Inloggad som" + currentLoaner.getName());
            LoanerMenu();
        } else {
            System.out.println("Det finns inga tillgängliga lånare");
        }

    }

    private void BrowseBooks() {
        System.out.println("Böcker:");
        for (Book book : library.getBooks()) {
            System.out.println(book);
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
