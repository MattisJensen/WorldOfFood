package worldOfFood;

import worldOfFood.implementation.FoodContainer;
import worldOfFood.implementation.GameSettings;

import java.util.Set;
import java.util.HashMap;

public class Room implements GameSettings {

    private String description;
    private HashMap<String, Room> exits;
    private FoodContainer fc;

    public Room(String description, FoodContainer fc) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.fc = fc;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + " at a " + fc.getName() + (fc.getFoodType() != EMPTY ? (" with " + fc.getFoodAmount() + " " + fc.getFoodType()) : "") + ".\n" + getExitString();
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public FoodContainer getFoodContainer() {
        return fc;
    }


    public Room getExit(String direction) {
        return exits.get(direction);
    }
}

