package layer.interfaces;

import java.util.ArrayList;

public interface PersonInterface {

    public void newPerson();

    public void setName(String name);

    public String getName();

    public void addFoodItem(String foodItem, int amount);

    public String collectFoodItem();

    public boolean eat(String food);

    public boolean isGameOver();

    public double getFoodPoints();

    public double getClimatePoints();

    public boolean enoughInvSpace(int amount);

    public ArrayList getInvItems();
}
