package Hunddagis;

import java.util.ArrayList;
import java.util.Scanner;

public class Owner {
    private String name;
    private int phone;
    private String adress;
    private ArrayList< String> payments= new ArrayList<>();

    public Owner(){
        generateOwner();
    }

    private void generateOwner() {
        Scanner scan= new Scanner(System.in);
        System.out.println("What is your name?");
        name= scan.nextLine();
    }
}
