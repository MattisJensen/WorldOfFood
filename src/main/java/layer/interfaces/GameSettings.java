package layer.interfaces;

public interface GameSettings {

    /* Map Dimensions */
    final int XMAP_SIZE = 15;
    final int YMAP_SIZE = 15;

    final int FIELD_WIDTH = 140; // in pixels - height and width should have the same size
    final int FIELD_HEIGHT = 140; // in pixels - height and width should have the same size

    final int FIELD_GROWTH_TIME = 20; // in seconds - every x seconds new plants / animals will reproduce them

    /* Person */
    final double P_MAX_FOODPOINTS = 10000;
    final double P_START_FOODPOINTS = 5000;
    final double P_START_CLIMATEPOINTS = 0;
    final double P_MOVEENERGY = 300;

    final int P_XY_SIZE = 100; // in pixels - height and width from person should be smaller than field width * height

    final int P_MIN_REMOVETIME_ENERGY = 3; // in seconds - indstiller et interval, hvor der tilfældigt vælges et antal sekunder fra, hvornår der skal removes foodpoints
    final int P_MAX_REMOVETIME_ENERGY = 6; // in seconds

    final double P_MIN_REMOVE_ENERGYPOINTS = 40; // in energypoints - indstiller et interval, hvor der tilfældigt vælges, hvor mange foodpoints skal removes alle x sekunder indstillet ovenover
    final double P_MAX_REMOVE_ENERGYPOINTS = 170; // in energypoints


    /* Inventory */
    final int INVENTORY_SIZE = 20;


    /* Items */
    /* Foodpoints */
    final double APPLE_FOODPOINTS = 216;
    final double PEAR_FOODPOINTS = 227;

    final double CARROT_FOODPOINTS = 150;
    final double POTATO_FOODPOINTS = 342;
    final double COW_FOODPOINTS = 687;

    final double FISH_FOODPOINTS = 742;
    final double DUCK_FOODPOINTS = 497;

    /* Climatepoints */
    final double APPLE_CLIMATEPOINTS = 3.01;
    final double PEAR_CLIMATEPOINTS = 1.65;

    final double CARROT_CLIMATEPOINTS = 3.64;
    final double POTATO_CLIMATEPOINTS = 3.10;
    final double COW_CLIMATEPOINTS = -216.68;

    final double FISH_CLIMATEPOINTS = -41.02;
    final double DUCK_CLIMATEPOINTS = -6.16;

    /* Amount of items at creation */
    final int APPLE_MIN = 6;
    final int APPLE_MAX = 10;

    final int PEAR_MIN = 6;
    final int PEAR_MAX = 10;

    final int CARROT_MIN = 9;
    final int CARROT_MAX = 16;

    final int POTATO_MIN = 7;
    final int POTATO_MAX = 13;

    final int COW_MIN = 2;
    final int COW_MAX = 6;

    final int FISH_MIN = 4;
    final int FISH_MAX = 8;

    final int DUCK_MIN = 3;
    final int DUCK_MAX = 7;

    /* Spawnchance of roomtypes in % (100 - all spawnchances = chance for room is empty) */
    final int APPLE_SPAWNCHANCE = 3;
    final int PEAR_SPAWNCHANCE = 2;
    final int CARROT_SPAWNCHANCE = 3;
    final int POTATO_SPAWNCHANCE = 4;
    final int COW_SPAWNCHANCE = 18;
    final int FISH_SPAWNCHANCE = 8;
    final int DUCK_SPAWNCHANCE = 12;

    /* Item titles */
    final String APPLE = "Æble";
    final String PEAR = "Pære";
    final String CARROT = "Gulerod";
    final String POTATO = "Kartoffel";
    final String COW = "Steak";
    final String FISH = "Laks";
    final String DUCK = "And";

    final String APPLES = "æbler";
    final String PEARS = "pærer";
    final String CARROTS = "gulerødder";
    final String POTATOES = "kartofler";
    final String COWS = "køer";
    final String FISHS = "fisk";
    final String DUCKS = "ænder";

    /* Item titles with articles */
    final String EMPTY = "";
    final String TREE = "et træ";
    final String FIELD = "en mark";
    final String LAKE = "en sø";

    final String APPLE_A = "et æble";
    final String PEAR_A = "en pære";
    final String CARROT_A = "en gulerod";
    final String POTATO_A = "en kartoffel";
    final String COW_A = "en ko";
    final String FISH_A = "en fisk";
    final String DUCK_A = "en and";
}
