import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class BookFileManager {

    private void readFromFile() {
        /*
        try {
            System.out.println("reading");

        } catch (FileNotFoundException e) {
            System.out.println("file does not exist");
        }

         */


    }

    //Writes Strings to a file
    public void writeBook2File(Book book) {
        try {
            FileOutputStream fos = new FileOutputStream("Books.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(book);

            fos.close();
            oos.close();
            System.out.println("I have written to the file");

        } catch (IOException e) {
            System.out.println("FileWriter could no be created");
        }


    }
}



