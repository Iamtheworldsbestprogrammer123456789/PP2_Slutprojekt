package Hunddagis;

import java.util.ArrayList;

public class Hunddagis {
    Owner firstOwner;
    ArrayList<Owner> allOwners= new ArrayList<>();
    public Hunddagis(){
        createOwners();
    }

    private void createOwners() {
        firstOwner= new Owner(); //skapar en instasns av Owner
        allOwners.add(firstOwner); // lägger till instansen i Arraylistan
        allOwners.add(new Owner()); // skapare en instans ohc lägger till i arrray listan
    }

}
