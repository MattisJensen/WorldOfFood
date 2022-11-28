package worldOfFood.implementation;

import worldOfFood.textUI.CommandLineClient;

import java.util.Scanner;

public class Person implements GameSettings {

    private String name;
    private double foodPoints = P_START_FOODPOINTS;
    private double climatePoints = P_START_CLIMATEPOINTS;
    private Inventory inv;

    /* Constructor af Person klassen */
    public Person() {
        inv = new Inventory();
        setNameFromInput();
    }

    /* Inventory af personen */
    public Inventory getInventory() {
        return inv;
    }

    /* Setter og getter til navn på Person */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    /* Setter til navn ud fra konsolinput - metoden kan skelne mellem omdøbning og først navngivning */
    public void setNameFromInput() {
        Scanner scan = CommandLineClient.scan(); // tager input fra konsolen

        /* denne blok udføres kun ved omdøbning */
        if (name != null) { // hvis name ikke er 'null', blev der allerede oprettet et navn og koden i blokken udføres
            System.out.println("Do you want to change your username? \nType 'yes' or 'no'");
            while (!scan.hasNext("yes") && !scan.hasNext("no")) { // koden i while-loop bliver udført, hver gang der ikke blev typet yes/no -> hasNext() returner bool og 'kigger' kun på linjen, hvorved linjen efterfølgende stadig kan udlæses. Blev en linje udlæst, kan den ikke udlæses igen.
                scan.nextLine(); // hopper i nœste linje idet linjen uden et yes/no udlæses
            }
            if (scan.nextLine().trim().equalsIgnoreCase("no")) {  // linjen med yes/no udlæses, da koden i while-loopen ikke har udlæst den - trim() fjerner mellemrum fra string linjen
                System.out.println("Returning to game...");
                return;
            }
        }


        /* denne blok udføres altid (medmindre man svarede 'no' før */
        System.out.println("Please enter a new username: ");
        name = scan.nextLine().trim();

        if (name.trim().isEmpty()) {  // string -> trim() fjerner mellemrum af string -> is empty kigger om string er tom
            System.out.println("This is not a username.");
            name = null;    // for at være sikker på, at der ikke spørges (igen) efter om man vil omdøbe sig
            setNameFromInput();
        } else {
            System.out.println("Your username is now '" + name + "'");
        }
    }


    /* Tilføje, fjerne og get foodpoints  */
    public void addFoodPoints(double points) {
        if (foodPoints <= P_MAX_FOODPOINTS) {
            foodPoints += points;
        } else {
            System.out.println("You are already full!");
        }

        if (foodPoints >= P_MAX_FOODPOINTS) { // hvis foodpoints af personen skulle blive over maks ved additionen i koden før bliver den sat til max foodpoints
            foodPoints = P_MAX_FOODPOINTS;
            System.out.println("You have eaten yourself full!");
        }
    }

    public void removeFoodPoints(double points) {
        if (foodPoints - points > 0) {
            foodPoints -= points;
        } else if (foodPoints - points <= 0) {
            GameOver.endGame(climatePoints);
        }
    }

    public double getFoodPoints() {
        return foodPoints;
    }


    /* Tilføje, fjerne og get climatepoints */
    public void addClimatePoints(double points) {
        climatePoints += points;
    } // i tilfældet af, at der bliver sendt negative climate points i den metode, vil de også trækkes fra, da minus gange plus = minus

    public void removeClimatePoints(double points) {
        climatePoints -= points;
    }

    public double getClimatePoints() {
        return climatePoints;
    }


    /* Skal udføres hver gang spilleren bevæger sig (fra rum til rum) */
    public void move() {
        removeFoodPoints(P_MOVEENERGY);
    }
}
