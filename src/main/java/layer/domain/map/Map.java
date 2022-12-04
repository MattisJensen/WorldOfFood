package layer.domain.map;

import layer.domain.item.Food;
import layer.interfaces.GameSettings;

import java.util.ArrayList;
import java.util.Random;

public class Map implements GameSettings {

    private int xSize;
    private int ySize;
    private ArrayList<ArrayList<Room>> map;

    public Map(int x, int y) { // x * y map size fx 9*9

        map = new ArrayList<>();

        xSize = x;
        ySize = y;

        Random random = new Random();

        /* danner rum */
        for (int i = 0; i < x; i++) {
            map.add(i, new ArrayList<Room>()); // danner en indre ArrayList<Room> i hver række af den ydre ArrayList<>

            for (int j = 0; j < y; j++) {

                /* Mulige rum */
                FoodContainer fc;

                int num = random.nextInt(99) + 1;

                if (num < APPLE_SPAWNCHANCE) { // isUnder checker om det tilfældige nummer er under det følgende nummer - ingen switch, da man i en switch ikke kan indstille en range numre
                    fc = new FoodContainer(TREE, new Food(APPLE, APPLE_FOODPOINTS, APPLE_CLIMATEPOINTS), random.nextInt(APPLE_MIN, APPLE_MAX + 1), APPLE_MAX);

                } else if (num < APPLE_SPAWNCHANCE + PEAR_SPAWNCHANCE) {
                    fc = new FoodContainer(TREE, new Food(PEAR, PEAR_FOODPOINTS, PEAR_CLIMATEPOINTS), random.nextInt(PEAR_MIN, PEAR_MAX + 1), PEAR_MAX);

                } else if (num < APPLE_SPAWNCHANCE + PEAR_SPAWNCHANCE + CARROT_SPAWNCHANCE) {
                    fc = new FoodContainer(FIELD, new Food(CARROT, CARROT_FOODPOINTS, CARROT_CLIMATEPOINTS), random.nextInt(CARROT_MIN, CARROT_MAX + 1), CARROT_MAX);

                } else if (num < APPLE_SPAWNCHANCE + PEAR_SPAWNCHANCE + CARROT_SPAWNCHANCE + POTATO_SPAWNCHANCE) {
                    fc = new FoodContainer(FIELD, new Food(POTATO, POTATO_FOODPOINTS, POTATO_CLIMATEPOINTS), random.nextInt(POTATO_MIN, POTATO_MAX + 1), POTATO_MAX);

                } else if (num < APPLE_SPAWNCHANCE + PEAR_SPAWNCHANCE + CARROT_SPAWNCHANCE + POTATO_SPAWNCHANCE + COW_SPAWNCHANCE) {
                    fc = new FoodContainer(FIELD, new Food(COW, COW_FOODPOINTS, COW_CLIMATEPOINTS), random.nextInt(COW_MIN, COW_MAX + 1), COW_MAX);

                } else if (num < APPLE_SPAWNCHANCE + PEAR_SPAWNCHANCE + CARROT_SPAWNCHANCE + POTATO_SPAWNCHANCE + COW_SPAWNCHANCE + FISH_SPAWNCHANCE) {
                    fc = new FoodContainer(LAKE, new Food(FISH, FISH_FOODPOINTS, FISH_CLIMATEPOINTS), random.nextInt(FISH_MIN, FISH_MAX + 1), FISH_MAX);

                } else if (num < APPLE_SPAWNCHANCE + PEAR_SPAWNCHANCE + CARROT_SPAWNCHANCE + POTATO_SPAWNCHANCE + COW_SPAWNCHANCE + FISH_SPAWNCHANCE + DUCK_SPAWNCHANCE) {
                    fc = new FoodContainer(LAKE, new Food(DUCK, DUCK_FOODPOINTS, DUCK_CLIMATEPOINTS), random.nextInt(DUCK_MIN, DUCK_MAX + 1), DUCK_MAX);

                } else {
                    fc = new FoodContainer(EMPTY, new Food(EMPTY, 0, 0), 0, 0);
                }

                /* tilføjer rum til mappet */
                map.get(i).add(j, new Room(i, j, fc)); // laver nyt rum på plads 0.0 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 1.0 1.1 1.2 ... 1.8 2.0 2.1 2.2 ... osv., hvor første nummer svarer til pladsen i første array og andet nummer svarer til pladsen i arrayet, som ligger på pladsen i det første nummer. På hver nyt første nummer, er der altså et andet array med størelsen givet ved y.
            }
        }

        /* tilføjer udgangene */
        for (int i = 0; i < 4; i++) {
            int g, h, a, b, c, d;
            g = h = a = b = c = d = 0;

            String command = "";

            switch (i) {
                case 0 -> {
                    b = -1; // y-1 stops 1 on y-aksis before last
                    d = 1;  // sets the north room to the room in row above this current room, and therefore stops 1 row before the last row
                    command = "north";
                }
                case 1 -> {
                    h = 1;  // k=1 starts at row 1
                    d = -1; // sets the south room to the room in row under this current room, and therefore starts with row 1 and not 0
                    command = "south";
                }
                case 2 -> {
                    a = -1; // x-1 stops 1 x-aksis before last
                    c = 1;  // sets the east room to the room on the right from current room, and therefore stops 1 index before the last one
                    command = "east";
                }
                case 3 -> {
                    g = 1;  // j=1 starts at index 1 instead of 0
                    c = -1; // sets the west room to the room on the left from current room, and therefore starts at index 1
                    command = "west";
                }
            }

            for (int j = g; j < x + a; j++) {
                for (int k = h; k < y + b; k++) {
                    map.get(j).get(k).setExit(command, map.get(j + c).get(k + d));
                }
            }
        }
    }

    public ArrayList<ArrayList<Room>> getMapList() {
        return map;
    }

    public ArrayList<ArrayList<String>> getMapFoodFields() {

        ArrayList<ArrayList<String>> stringMap = new ArrayList<>();

        for (int i = 0; i < map.size(); i++) {

            stringMap.add(new ArrayList<String>());

            for (int j = 0; j < map.get(i).size(); j++) {

                String foodType = map.get(i).get(j).getFoodContainer().getFoodType().getName();

                stringMap.get(i).add(foodType);
            }
        }

        return stringMap;
    }


    public Room getMiddleRoom() {
        return getMapList().get(xSize / 2).get(ySize / 2);
    }

}
