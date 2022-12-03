package layer.domain.map;

import layer.interfaces.GameSettings;
import layer.domain.item.Food;

public class FoodContainer implements GameSettings {

    private final String name;

    private final Food foodType;
    private final double foodPoints;
    private final double climatePoints;

    private int quantity;

    public FoodContainer(String name, Food foodItem, int amount) {
        this.name = name;

        foodType = new Food(foodItem.getName(), foodItem.getFoodPoints(), foodItem.getClimatePoints());
        foodPoints = foodItem.getFoodPoints();
        climatePoints = foodItem.getClimatePoints();

        this.quantity = amount;
    }

    public Food getFoodType() {
        return new Food(foodType.getName(), foodPoints, climatePoints);
    }

    public String getName() {
        return name;
    }

    public void addFood(int amount) {
        this.quantity += amount;
    }

    public double getFoodPoints() {
        return foodPoints;
    }

    public double getClimatePoints() {
        return climatePoints;
    }

    public int getFoodAmount() {
        return quantity;
    }

    /* Remove Food & Collect Food (ligner lidt en toString version af removeFood) */
    public void removeFood(int amount) {

        if (this.quantity - amount >= 0) {
            this.quantity -= amount;
        } else {
            System.out.println("You cannot remove more than " + getFoodAmount() + " " + getFoodType().getName() + ".");
        }
    }

    public void collect(int amount) {

        if (getFoodAmount() == 0) { // a row of else if statements so the next if statement only gets checked, if the current if statement is false - if an if statement is true, the if following statements don't need to be checked
            System.out.println("Here is nothing collect.");

        } else if (amount > getFoodAmount()) { // checker om nummeret er højere end antallet ledige items i foodcontaineren
            System.out.println("You cannot collect more than " + getFoodAmount() + " " + getFoodType() + ".");

        } else {
            removeFood(amount);
        }
    }

    public String getGrammaticalNumber() {

        String currentFoodType = foodType.getName();
        String grammaticalNumber = "";

        if (currentFoodType.equalsIgnoreCase(APPLE)) {
            grammaticalNumber = (quantity > 1 ? "æbler" : "æble");

        } else if (currentFoodType.equalsIgnoreCase(PEAR)) {
            grammaticalNumber = (quantity > 1 ? "pærer" : "pære");

        } else if (currentFoodType.equalsIgnoreCase(CARROT)) {
            grammaticalNumber = (quantity > 1 ? "gulerødder" : "gulerod");

        } else if (currentFoodType.equalsIgnoreCase(POTATO)) {
            grammaticalNumber = (quantity > 1 ? "kartofler" : "kartoffel");

        } else if (currentFoodType.equalsIgnoreCase(COW)) {
            grammaticalNumber = (quantity > 1 ? "køer" : "ko");

        } else if (currentFoodType.equalsIgnoreCase(DUCK)) {
            grammaticalNumber = (quantity > 1 ? "ænder" : "and");

        } else if (currentFoodType.equalsIgnoreCase(FISH)) {
            grammaticalNumber = "fisk";
        }

        return grammaticalNumber;
    }

    public String getGrammaticalSingularArticle() {

        String currentFoodType = foodType.getName();
        String singularArticle = "";

        if (currentFoodType.equalsIgnoreCase(APPLE)) {
            singularArticle = APPLE_A;

        } else if (currentFoodType.equalsIgnoreCase(PEAR)) {
            singularArticle = PEAR_A;

        } else if (currentFoodType.equalsIgnoreCase(CARROT)) {
            singularArticle = CARROT_A;

        } else if (currentFoodType.equalsIgnoreCase(POTATO)) {
            singularArticle = POTATO_A;

        } else if (currentFoodType.equalsIgnoreCase(COW)) {
            singularArticle = COW_A;

        } else if (currentFoodType.equalsIgnoreCase(DUCK)) {
            singularArticle = DUCK_A;

        } else if (currentFoodType.equalsIgnoreCase(FISH)) {
            singularArticle = FISH_A;
        }

        return singularArticle;
    }

    public String getGrammaticalPlural() {

        String currentFoodType = foodType.getName();
        String grammaticalPlural = "";

        if (currentFoodType.equalsIgnoreCase(APPLE)) {
            grammaticalPlural = APPLES;

        } else if (currentFoodType.equalsIgnoreCase(PEAR)) {
            grammaticalPlural = PEARS;

        } else if (currentFoodType.equalsIgnoreCase(CARROT)) {
            grammaticalPlural = CARROTS;

        } else if (currentFoodType.equalsIgnoreCase(POTATO)) {
            grammaticalPlural = POTATOES;

        } else if (currentFoodType.equalsIgnoreCase(COW)) {
            grammaticalPlural = COWS;

        } else if (currentFoodType.equalsIgnoreCase(DUCK)) {
            grammaticalPlural = DUCKS;

        } else if (currentFoodType.equalsIgnoreCase(FISH)) {
            grammaticalPlural = FISHS;
        }

        return grammaticalPlural;
    }


}