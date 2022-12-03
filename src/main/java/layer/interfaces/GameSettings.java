package layer.interfaces;

public interface GameSettings {

    /* Map Dimensions */
        final int XMAP_SIZE = 15;
        final int YMAP_SIZE = 15;

        final int FIELD_WIDTH = 140; //in pixels - height and width should have the same size
        final int FIELD_HEIGHT = 140; //in pixels - height and width should have the same size

    /* Person */
        final double P_MAX_FOODPOINTS = 100;
        final double P_START_FOODPOINTS = 60;
        final double P_START_CLIMATEPOINTS = 0;
        final double P_MOVEENERGY = 10;


    /* Inventory */
        final int INVENTORY_SIZE = 20;


    /* Items */
        /* Foodpoints */
        final double APPLE_FOODPOINTS = 8;
        final double PEAR_FOODPOINTS = 8;

        final double CARROT_FOODPOINTS = 5;
        final double POTATO_FOODPOINTS = 5;
        final double COW_FOODPOINTS = 20;

        final double FISH_FOODPOINTS = 12;
        final double DUCK_FOODPOINTS = 12;

        /* Climatepoints */
        final double APPLE_CLIMATEPOINTS = 10;
        final double PEAR_CLIMATEPOINTS = 10;

        final double CARROT_CLIMATEPOINTS = 16;
        final double POTATO_CLIMATEPOINTS = 16;
        final double COW_CLIMATEPOINTS = -30;

        final double FISH_CLIMATEPOINTS = -10;
        final double DUCK_CLIMATEPOINTS = -15;

        /* Amount of items at creation */
        final int APPLE_MIN = 6;
        final int APPLE_MAX = 12;

        final int PEAR_MIN = 6;
        final int PEAR_MAX = 12;

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
        final int APPLE_SPAWNCHANCE = 9;
        final int PEAR_SPAWNCHANCE = 9;
        final int CARROT_SPAWNCHANCE = 13;
        final int POTATO_SPAWNCHANCE = 13;
        final int COW_SPAWNCHANCE = 5;
        final int FISH_SPAWNCHANCE = 7;
        final int DUCK_SPAWNCHANCE = 4;

        /* Item titles */
        final String APPLE = "Æble";
        final String PEAR = "Pære";
        final String CARROT = "Gulerod";
        final String POTATO = "Kartoffel";
        final String COW = "Steak";
        final String FISH = "Fisk";
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
