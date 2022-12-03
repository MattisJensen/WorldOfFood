package layer.domain.game;

import layer.interfaces.GameSettings;
import layer.domain.map.Map;
import layer.domain.map.Room;

public class Game implements GameSettings {

    private Map map;
    private Room currentRoom;

    public Game() {
        createMap();
    }

    private void createMap() {
        map = new Map(XMAP_SIZE, YMAP_SIZE);
        currentRoom = map.getMiddleRoom();
    }

    public boolean goRoom(String direction) {

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return false;
        } else {
            currentRoom = nextRoom;
            return true;
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Map getMap() {
        return map;
    }
}
