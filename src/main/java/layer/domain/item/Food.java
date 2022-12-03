package layer.domain.item;

import layer.interfaces.GameSettings;

public class Food extends Item implements GameSettings {

    private double foodPoints;
    private double climatePoints;

    public Food(String name, double foodPoints, double climatePoints) {
        super(name);
        this.climatePoints = climatePoints;
        this.foodPoints = foodPoints;
    }

    public double getFoodPoints() {
        return foodPoints;
    }

    public double getClimatePoints() {
        return climatePoints;
    }
}


