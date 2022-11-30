package layer.domain.game;

import layer.domain.command.Command;
import layer.domain.command.CommandImplementation;
import layer.domain.command.CommandWords;
import layer.domain.command.CommandWordsImplementation;
import layer.domain.map.Map;
import layer.domain.map.Room;

import java.util.List;

public class Game implements GameSettings {

    private Map map;
    private Room currentRoom;
    private CommandWords commands;

    public Game() {
        createRooms();
        commands = new CommandWordsImplementation();
    }

    private void createRooms() {
        map = new Map(xMapSize, yMapSize);
        currentRoom = map.getMiddleRoom();
    }

    public boolean goRoom(Command command) {

        if (!command.hasCommandValue()) {
            //No direction on command.
            //Can't continue with GO command.
            return false;
        }

        String direction = command.getCommandValue();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return false;
        } else {
            currentRoom = nextRoom;
            return true;
        }
    }

    public boolean quit(Command command) {
        if (command.hasCommandValue()) {
            return false;
        } else {
            return true;
        }
    }

    public String getRoomDescription() {
        return currentRoom.getLongDescription();
    }

    public CommandWords getCommands() {
        return commands;
    }

    public List<String> getCommandDescriptions() {
        return commands.getCommandWords();
    }

    public Command getCommand(String word1, String word2) {
        return new CommandImplementation(commands.getCommand(word1), word2);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Map getMap() {
        return map;
    }
}
