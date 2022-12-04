package layer.domain.map;

import layer.domain.item.Food;
import layer.interfaces.GameSettings;

public class FoodContainer implements GameSettings {

    private final String name;

    private final Food foodType;
    private final double foodPoints;
    private final double climatePoints;

    private int foodAmount;

    private int maksGrowth;

    public FoodContainer(String name, Food foodItem, int amount, int maksGrowth) {
        this.name = name;

        foodType = new Food(foodItem.getName(), foodItem.getFoodPoints(), foodItem.getClimatePoints());
        foodPoints = foodItem.getFoodPoints();
        climatePoints = foodItem.getClimatePoints();

        this.foodAmount = amount;

        this.maksGrowth = maksGrowth;
    }

    public Food getFoodType() {
        return new Food(foodType.getName(), foodPoints, climatePoints);
    }

    public String getName() {
        return name;
    }

    public void addFood(int amount) {
        this.foodAmount += amount;
    }

    public double getFoodPoints() {
        return foodPoints;
    }

    public double getClimatePoints() {
        return climatePoints;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    /* Remove Food & Collect Food (ligner lidt en toString version af removeFood) */
    public void removeFood(int amount) {

        if (this.foodAmount - amount >= 0) {
            this.foodAmount -= amount;
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
            grammaticalNumber = (foodAmount > 1 ? "æbler" : "æble");

        } else if (currentFoodType.equalsIgnoreCase(PEAR)) {
            grammaticalNumber = (foodAmount > 1 ? "pærer" : "pære");

        } else if (currentFoodType.equalsIgnoreCase(CARROT)) {
            grammaticalNumber = (foodAmount > 1 ? "gulerødder" : "gulerod");

        } else if (currentFoodType.equalsIgnoreCase(POTATO)) {
            grammaticalNumber = (foodAmount > 1 ? "kartofler" : "kartoffel");

        } else if (currentFoodType.equalsIgnoreCase(COW)) {
            grammaticalNumber = (foodAmount > 1 ? "køer" : "ko");

        } else if (currentFoodType.equalsIgnoreCase(DUCK)) {
            grammaticalNumber = (foodAmount > 1 ? "ænder" : "and");

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

    public void grow() {
        if (foodAmount != 0) { //hvis det er 0, så vil der ikke komme noget tilbage

            /* make 3 grow intervals a,b og under a, with their own growspeed. More foodamount = higher grow speed */
            double b = maksGrowth / 1.33;
            double a = b / 1.33;

            if (foodAmount >= b) {
                foodAmount += 3;
            } else if (foodAmount >= a) {
                foodAmount += 2;
            } else if (foodAmount < a) {
                foodAmount++;
            }

            if (foodAmount > maksGrowth) {
                foodAmount = maksGrowth;
            }
        }
    }
}