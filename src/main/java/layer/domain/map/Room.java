package layer.domain.map;

import layer.interfaces.GameSettings;

import java.util.HashMap;

public class Room implements GameSettings {

    private String description;
    private final HashMap<String, Room> exits;
    private final FoodContainer fc;
    private final int[] coordinates;

    public Room(int x, int y, FoodContainer fc) {
        exits = new HashMap<String, Room>();
        coordinates = new int[]{x, y};
        this.fc = fc;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getShortDescription() {
        return coordinates[0] + "." + coordinates[1];
    }

    public String getLongDescription() {
        return "Du er ved " + fc.getName() + ", hvor der er " + fc.getFoodAmount() + " " + fc.getGrammaticalNumber() + ".";
    }

    public FoodContainer getFoodContainer() {
        return fc;
    }

}

