/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldOfFood.textUI;

import worldOfFood.Command;
import worldOfFood.Commands;
import worldOfFood.Game;
import worldOfFood.implementation.*;

/**
 * @author ancla
 */
public class CommandLineClient implements GameSettings {
    private Person person;
    private Parser parser;
    private Game game;
    private static boolean finished;

    public CommandLineClient() {
        person = new Person();
        System.out.println();
        game = new Game();
        parser = new Parser(game);
    }

    public void play() {
        printWelcome();
        finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    public static void setFinished(boolean b) {
        finished = b;
    }


    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Food!");
        System.out.println("World of Food is a new, incredibly sustainable adventure game about eating food.");
        System.out.println("Type '" + Commands.HELP + "' if you need help.");
        System.out.println();
        System.out.println(game.getRoomDescription());
    }

    private void printHelp() {
        for (String str : game.getCommandDescriptions()) {
            System.out.println("- " + str + " ");
        }
    }

    //Controller
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        Commands commandWord = command.getCommandName();

        /* Unknown command */
        if (commandWord == Commands.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        /* Help command */
        if (commandWord == Commands.HELP) {
            System.out.println("\n\nYou are lost. You are alone. You wander");
            System.out.println("around in the open.");
            System.out.println();
            System.out.println("Your command words are:");
            printHelp();

        /* Go command */
        } else if (commandWord == Commands.GO) {
            if (game.goRoom(command)) {
                System.out.println(game.getRoomDescription());
                person.move();
            } else {
                System.out.println("Can't walk in that direction.");
            }

        /* Quit command */
        } else if (commandWord == Commands.QUIT) {
            if (game.quit(command)) {
                wantToQuit = true;
            } else {
                System.out.println("Quit what?");
            }

        /* Print Inventory command */
        } else if (commandWord == Commands.INVENTORY) {
            person.getInventory().printInventory();

        /* Eat command */
        } else if (commandWord == Commands.EAT) {
            if (command.hasCommandValue()) { // checker om der blevet skrevet, hvad der skal spises
                int c = person.getInventory().getItems().size(); // sætter c til den aktuelle størrelse af inventory

                for (Item i : person.getInventory().getItems()) {
                    if (command.getCommandValue().equalsIgnoreCase(i.getName()) && i instanceof Food) { // checker hvert item i inventaret indtil der er et, som matcher det indtastede item, remover det og stopper med at checke de resterende items
                        person.getInventory().getItems().remove(i);
                        person.addFoodPoints(((Food) i).getFoodPoints());
                        person.addClimatePoints(((Food) i).getClimatePoints());
                        System.out.println("Mmmmm... yummmy a " + command.getCommandValue());
                        break;
                    }
                }

                if (c == person.getInventory().getItems().size()) { // hvis der ikke blev fjernet et item fra inventaret, så er c stadig så stor som inventaret og der blev ikke fundet det søgte item
                    System.out.println("You don't have " + command.getCommandValue() + " to eat in your inventory.");
                }

            } else {
                System.out.println("You forgot to say what you want to eat from your inventory.");
                System.out.println("Try again!");
            }

        /* Collect item command */
        } else if (commandWord == Commands.COLLECT) {
            FoodContainer currentFC = game.getCurrentRoom().getFoodContainer(); // gemmer reference til den aktuelle fc

            if (currentFC.getFoodAmount() == 0) { // a row of else if statements so the next if statement only gets checked, if the current if statement is false - if an if statement is true, the if following statements don't need to be checked
                System.out.println("Here is nothing collect.");

            } else if (isNum(command.getCommandValue())) { //checker om string indholdet indeholder en integer
                int amount = Integer.parseInt(command.getCommandValue()); // converts the amount given as string to an int

                if (amount > currentFC.getFoodAmount()) { // checker om nummeret er højere end antallet ledige items i rummet
                    System.out.println("You cannot collect more than " + currentFC.getFoodAmount() + " " + currentFC.getFoodType() + ".");

                } else if (person.getInventory().getItems().size() + amount > INVENTORY_SIZE) {
                    System.out.println("Your inventory is to small to collect " + amount + " items.");

                } else {
                    currentFC.removeFood(amount);
                    System.out.println("You collected " + amount + " " + currentFC.getFoodType() + ".");

                    for (int i = 0; i < amount; i++) {
                        person.getInventory().addItem(new Food(currentFC.getFoodType(), currentFC.getFoodPoints(), currentFC.getClimatePoints()));
                    }
                }

            } else {
                System.out.println("You have to enter a number in order to collect something.");
            }

        /* Stats command */
        } else if (commandWord == Commands.STATS) {
            System.out.println("You have " + person.getFoodPoints() + "/" + P_MAX_FOODPOINTS + " foodpoints and " + person.getClimatePoints() + " climatepoints.");
        }

        return wantToQuit;
    }


    /* Metode der checker om String indeholder en interger */
    private boolean isNum(String commandValue) {
        if (commandValue == null) {
            return false;
        }
        try {
            int t = Integer.parseInt(commandValue);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}