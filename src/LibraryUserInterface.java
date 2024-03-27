import java.util.Scanner;

public class LibraryUserInterface {
    public static int try_catch_int() {
        Scanner scan = new Scanner(System.in);
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
