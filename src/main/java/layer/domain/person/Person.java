package layer.domain.person;

import layer.domain.item.Food;
import layer.domain.item.Item;
import layer.interfaces.GameSettings;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Person implements GameSettings {

    private String name;
    private double foodPoints = P_START_FOODPOINTS;
    private double climatePoints = P_START_CLIMATEPOINTS;

    private final Inventory inv;

    private boolean timerStarted = false;
    private Timer timer;
    private Random random;

    private int removeRandomFoodPointsTime;

    /* Constructor af Person klassen */
    public Person() {
        inv = new Inventory();

        timer = new Timer();
        random = new Random();
        removeRandomFoodPointsTime = 1000 * P_MIN_REMOVETIME_ENERGY;
    }


    /* Inventory af personen */
    public Inventory getInventory() {
        return inv;
    }

    public boolean eat(String food) {
        boolean itemEaten = false;

        for (Item i : getInventory().getItems()) {
            if (food.equalsIgnoreCase(i.getName()) && i instanceof Food) { // checker hvert item i inventaret indtil der er et, som matcher det indtastede item, remover det og stopper med at checke de resterende items
                if (foodPoints < P_MAX_FOODPOINTS) {
                    getInventory().removeItem((Food) i);
                    addFoodPoints(((Food) i).getFoodPoints());
                    addClimatePoints(((Food) i).getClimatePoints());
                    return itemEaten = true; //ends the method as soon it found 1 item to eat (returns void)
                }
            }
        }
        return itemEaten;
    }


    /* Setter og getter til navn på Person */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    /* Tilføje, fjerne og get foodpoints  */
    public void addFoodPoints(double points) {
        if (foodPoints <= P_MAX_FOODPOINTS) {
            foodPoints += points;
        }

        if (foodPoints >= P_MAX_FOODPOINTS) { // hvis foodpoints af personen skulle blive over maks ved additionen i koden før bliver den sat til max foodpoints
            foodPoints = P_MAX_FOODPOINTS;
        }

        if (foodPoints < 0) {
            foodPoints = 0;
        }
    }

    public void removeFoodPoints(double points) {
        foodPoints -= points;
        if (foodPoints < 0) {
            foodPoints = 0;
        }
    }

    public void setRemoveRandomFoodPointsTime(int time) {
        removeRandomFoodPointsTime = time;
    }

    public void energyTimer(boolean b) {
        timerStarted = b;

        if (timerStarted) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    setRemoveRandomFoodPointsTime(1000 * random.nextInt(P_MIN_REMOVETIME_ENERGY, P_MAX_REMOVETIME_ENERGY + 1));

                    if (isGameOver()) {
                        timer.cancel();
                        timer.purge();
                    }
                }
            }, 0, P_MIN_REMOVETIME_ENERGY);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    removeFoodPoints(random.nextDouble(P_MIN_REMOVE_ENERGYPOINTS, P_MAX_REMOVE_ENERGYPOINTS + 1));

                    if (isGameOver()) {
                        timer.cancel();
                        timer.purge();
                    }
                }
            }, 0, removeRandomFoodPointsTime); // wait 0 milliseconds before doing the action and do it every removeRandomFoodPointsTime time
        } else if (timerStarted) {
            timer.cancel();
            timer.purge();
        }
    }

    public boolean isGameOver() {
        return foodPoints <= 0;
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


    /* Ting som udføres, hver gang spilleren bevæger sig (fra rum til rum) */
    public void move() {
        removeFoodPoints(P_MOVEENERGY);
    }


    /* Setter til navn ud fra konsolinput - metoden kan skelne mellem omdøbning og først navngivning */
    public void setNameFromInput() {
        Scanner scan = new Scanner(System.in); // tager input fra konsolen

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

}
