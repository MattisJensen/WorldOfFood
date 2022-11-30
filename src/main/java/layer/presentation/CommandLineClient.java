/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer.presentation;

import layer.domain.GameAPI;
import layer.domain.command.Command;
import layer.domain.command.Commands;
import layer.domain.game.Game;
import layer.domain.map.FoodContainer;
import layer.domain.person.Person;

import java.util.Scanner;

public class CommandLineClient {
    private Person person;
    private Parser parser;
    private Game game;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean finished;

    public CommandLineClient() {

//        parser = new Parser(game);
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

            /* Collect command */
        }  else if (commandWord == Commands.COLLECT) {

            if (isNum(command.getCommandValue())) { //checker om string indholdet indeholder en integer
                int amount = Integer.parseInt(command.getCommandValue()); // converts the amount given as string to an int

                FoodContainer currentFC = game.getCurrentRoom().getFoodContainer();

                currentFC.collect(amount); // colllects the choosen amount of items from the foodcontainer

                person.getInventory().addFoodItem(currentFC.getFoodType(), amount); // adds the collected food to inventory

            } else {
                System.out.println("You have to enter a number in order to collect something.");
            }


        }

        return wantToQuit;
    }


    /* Metode der checker om String indeholder en integer */
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


    /* Statisk metode af System.in Scanner */
    public static Scanner scan() {
        return scanner;
    }

}