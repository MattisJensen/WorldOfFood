package layer.domain;

import layer.domain.game.Game;
import layer.domain.item.Food;
import layer.domain.map.FoodContainer;
import layer.interfaces.GameInterface;
import layer.interfaces.GameSettings;
import layer.interfaces.MapInterface;
import layer.interfaces.PersonInterface;

import java.util.ArrayList;

public class GameAPI implements GameSettings, GameInterface, PersonInterface, MapInterface {
    private static Game game;
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
    }

    @Override
    public void go(String direction) {
        switch (direction) {
            case "UP", "W" -> game.goRoom("north");
            case "DOWN", "S" -> game.goRoom("south");
            case "LEFT", "A" -> game.goRoom("west");
            case "RIGHT", "D" -> game.goRoom("east");
        }
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

    @Override
    public void startGrowthTimer() {
        game.growthTimer(true);
    }


    /* Person related methods */
    @Override
    public void newPerson() {
        game.newPerson();
    }

    @Override
    public void setName(String name) {
        game.getPerson().setName(name);
    }

    @Override
    public String getName() {
        return game.getPerson().getName();
    }

    @Override
    public void addFoodItem(String foodItem, int amount) {
        for (int i = 0; i < game.getMap().getMapFoodFields().size(); i++) {

            for (int j = 0; j < game.getMap().getMapFoodFields().get(i).size(); j++) {

                if (game.getMap().getMapFoodFields().get(i).get(j).equalsIgnoreCase(foodItem)) { // prøver at finde et fooditem med samme navn i mappet for bagefter at kunne hente dette foodobjekt, da presentationlayer kun sender en String, men inventaret skal have et fooditem med tilsvarende food- og klimapoints

                    Food mapItem = game.getMap().getMapList().get(i).get(j).getFoodContainer().getFoodType(); //returns a duplicate a fooditem
                    game.getPerson().getInventory().addFoodItem(mapItem, 1); //adds the new generated duplicate to inventory

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

        game.getPerson().getInventory().addFoodItem(currentFC.getFoodType(), amount); // adds the collected food to inventory

        return currentFC.getFoodType().getName();
    }

    @Override
    public boolean eat(String food) {
        return game.getPerson().eat(food);
    }

    @Override
    public boolean isGameOver() {
        return game.getPerson().isGameOver();
    }

    @Override
    public double getFoodPoints() {
        return game.getPerson().getFoodPoints();
    }

    @Override
    public void removeFoodPoints(double amount) {
        game.getPerson().removeFoodPoints(amount);
    }

    @Override
    public void startFoodPointTimer() {
        game.getPerson().energyTimer(true);
    }

    @Override
    public double getClimatePoints() {
        return game.getPerson().getClimatePoints();
    }

    @Override
    public boolean enoughInvSpace(int amount) {
        return game.getPerson().getInventory().enoughInvSpace(amount);
    }

    @Override
    public ArrayList getInvItems() {
        return game.getPerson().getInventory().getItems();
    }


    /* Map related methods */
    @Override
    public ArrayList<ArrayList<String>> getMapFoodFields() {
        return game.getMap().getMapFoodFields();
    }

    @Override
    public void emptyFoodContainer(int[] postionXY) {
        game.getMap().getMapList().get(postionXY[0]).get(postionXY[1]).setFoodContainer(new FoodContainer(EMPTY, new Food(EMPTY, 0, 0), 0, 0));
    }
}
