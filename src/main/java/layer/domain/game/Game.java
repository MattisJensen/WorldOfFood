package layer.domain.game;

import layer.domain.map.Map;
import layer.domain.map.Room;
import layer.domain.person.Person;
import layer.interfaces.GameSettings;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Game implements GameSettings {

    private Map map;
    private Room currentRoom;

    private Person person;
    private Timer timer;
    private boolean timerStarted = false;

    public Game() {
        person = new Person();
        createMap();
        timer = new Timer();
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

    public void growthTimer(boolean b) {
        timerStarted = b;

        if (timerStarted) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    /* kalde grow metoden på hver foodcontainer så snart personen tager et skridt */
                    for (ArrayList<Room> a : map.getMapList()) {
                        for (Room r : a) {
                            if (r.getFoodContainer().getName() != EMPTY) {
                                r.getFoodContainer().grow();
                            }
                        }
                    }

                    if (person.isGameOver()) {
                        timer.cancel();
                        timer.purge();
                    }
                }
            }, 0, FIELD_GROWTH_TIME * 1000);
        } else if (timerStarted) {
            timer.cancel();
            timer.purge();
        }
    }
}
