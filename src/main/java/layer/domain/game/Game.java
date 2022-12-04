package layer.domain.game;

import layer.domain.map.Map;
import layer.domain.map.Room;
import layer.domain.person.Person;
import layer.interfaces.GameSettings;

import java.util.ArrayList;

public class Game implements GameSettings {

    private Map map;
    private Room currentRoom;

    private Person person;

    public Game() {
        person = new Person();
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
            person.move();

            /* kalde grow metoden på hver foodcontainer så snart personen tager et skridt */
            for (ArrayList<Room> a : map.getMapList()) {
                for (Room r : a) {
                    if (r.getFoodContainer().getName() != EMPTY) {
                        r.getFoodContainer().grow();
                    }
                }
            }
            return true;
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Map getMap() {
        return map;
    }

    public Person getPerson() {
        return person;
    }

    public void newPerson() {
        person = new Person();
    }
}
