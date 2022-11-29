package layer.domain.map;

import layer.domain.item.Food;

public class FoodContainer {

    private String name;
    private String description;

    private Food foodType;

    private double foodPoints;
    private double climatePoints;
    private int quantity;

    public FoodContainer(String name, Food foodItem, int amount) {
        this.name = name;
        foodType = new Food(foodItem.getName(), foodItem.getFoodPoints(), foodItem.getClimatePoints());
        foodPoints = foodItem.getFoodPoints();
        climatePoints = foodItem.getClimatePoints();

        this.quantity = amount;
    }

    public String getName() {
        return name;
    }

    public String description() {
        return description;
    }

    public Food getFoodType() {
        return new Food(foodType.getName(), foodPoints, climatePoints);
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

            System.out.println("You collected " + amount + " " + getFoodType() + ".");
        }

    }


}