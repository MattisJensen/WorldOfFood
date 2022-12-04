package layer.domain;

import layer.domain.game.Game;
import layer.domain.item.Food;
import layer.domain.map.FoodContainer;
import layer.domain.person.Person;
import layer.interfaces.GameInterface;
import layer.interfaces.GameSettings;
import layer.interfaces.MapInterface;
import layer.interfaces.PersonInterface;

import java.util.ArrayList;

public class GameAPI implements GameSettings, GameInterface, PersonInterface, MapInterface {
    private static Game game;
    private static Person person;
    private static boolean newGame = false;

    /* (In)Game related methods */
    @Override
    public void newGame() {
        game = new Game();
    }

    @Override
    public void restartGame() {
        /* Java garbage collector burde selv fjerne det gamle Game- og Personobject, da der ikke længere vil være en reference i til dem fra applikationen */
        game = new Game();
        person = new Person();
    }

    @Override
    public void go(String direction) {
        switch (direction) {
            case "UP", "W" -> game.goRoom("north");
            case "DOWN", "S" -> game.goRoom("south");
            case "LEFT", "A" -> game.goRoom("west");
            case "RIGHT", "D" -> game.goRoom("east");
        }
        person.move();
    }

    @Override
    public int[] getCurrentPosition() {
        return game.getCurrentRoom().getCoordinates();
    }

    @Override
    public String getCurrentFoodName() {
        return game.getCurrentRoom().getFoodContainer().getFoodType().getName();
    }

    @Override
    public String getCurrentGrammaticalNumber() {
        return game.getCurrentRoom().getFoodContainer().getGrammaticalNumber();
    }

    @Override
    public String getGrammaticalSingularArticle() {
        return game.getCurrentRoom().getFoodContainer().getGrammaticalSingularArticle();
    }

    @Override
    public String getGrammaticalPlural() {
        return game.getCurrentRoom().getFoodContainer().getGrammaticalPlural();
    }

    @Override
    public int getCurrentFoodAmount() {
        return game.getCurrentRoom().getFoodContainer().getFoodAmount();
    }

    @Override
    public String getCurrentDescription() {
        return game.getCurrentRoom().getLongDescription();
    }

    @Override
    public boolean hasCurrentFood() {

        return !game.getCurrentRoom().getFoodContainer().getName().equals(EMPTY);
    }


    /* Person related methods */
    @Override
    public void newPerson() {
        person = new Person();
    }

    @Override
    public void setName(String name) {
        person.setName(name);
    }

    @Override
    public String getName() {
        return person.getName();
    }

    @Override
    public void addFoodItem(String foodItem, int amount) {
        for (int i = 0; i < game.getMap().getMapFoodFields().size(); i++) {

            for (int j = 0; j < game.getMap().getMapFoodFields().get(i).size(); j++) {

                if (game.getMap().getMapFoodFields().get(i).get(j).equalsIgnoreCase(foodItem)) { // prøver at finde et fooditem med samme navn i mappet for bagefter at kunne hente dette foodobjekt, da presentationlayer kun sender en String, men inventaret skal have et fooditem med tilsvarende food- og klimapoints

                    Food mapItem = game.getMap().getMapList().get(i).get(j).getFoodContainer().getFoodType(); //returns a duplicate a fooditem
                    person.getInventory().addFoodItem(mapItem, 1); //adds the new generated duplicate to inventory

                    return; //ends the method (returns void)
                }
            }
        }
    }

    @Override
    public String collectFoodItem() {
        FoodContainer currentFC = game.getCurrentRoom().getFoodContainer();
        int amount = 1;

        currentFC.collect(amount); // colllects the choosen amount of items from the foodcontainer

        person.getInventory().addFoodItem(currentFC.getFoodType(), amount); // adds the collected food to inventory

        return currentFC.getFoodType().getName();
    }

    @Override
    public boolean eat(String food) {
        return person.eat(food);
    }

    @Override
    public boolean isGameOver() {
        return person.isGameOver();
    }

    @Override
    public double getFoodPoints() {
        return person.getFoodPoints();
    }

    @Override
    public void removeFoodPoints(double amount) {
        person.removeFoodPoints(amount);
    }

    @Override
    public void startFoodPointTimer() {
        person.timer(true);
    }

    @Override
    public double getClimatePoints() {
        return person.getClimatePoints();
    }

    @Override
    public boolean enoughInvSpace(int amount) {
        return person.getInventory().enoughInvSpace(amount);
    }

    @Override
    public ArrayList getInvItems() {
        return person.getInventory().getItems();
    }


    /* Map related methods */
    @Override
    public ArrayList<ArrayList<String>> getMapFoodFields() {
        return game.getMap().getMapFoodFields();
    }
}
