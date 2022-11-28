package layer.domain.map;

import layer.domain.item.Item;
import layer.domain.item.Food;

public class FoodContainer {

    private String name;
    private String description;
    private double respawnSpeed;
    private String foodType;


    private double foodPoints;
    private double climatePoints;
    private int amount;

    public FoodContainer(String name, Item item, int amount) {
        this.name = name;
        foodType = item.getName();

        if(item instanceof Food) {
            foodPoints = ((Food) item).getFoodPoints();
            climatePoints = ((Food) item).getClimatePoints();
        }

        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String description() {
        return description;
    }

    public String getFoodType() {
        return foodType;
    }

    public void removeFood(int amount) {
        this.amount -= amount;
    }

    public void addFood(int amount) {
        this.amount += amount;
    }

    public double getFoodPoints() {
        return foodPoints;
    }

    public double getClimatePoints() {
        return climatePoints;
    }

    public int getFoodAmount() {
        return amount;
    }

    public void setRespawnSpeed(double respawnSpeed) {
        this.respawnSpeed = respawnSpeed;
    }
}