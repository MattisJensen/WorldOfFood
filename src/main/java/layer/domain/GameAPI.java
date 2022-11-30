package layer.domain;

import layer.domain.game.Game;
import layer.domain.item.Food;
import layer.domain.map.Room;
import layer.domain.person.Person;
import layer.presentation.interfaces.FoodContainerInterface;
import layer.presentation.interfaces.GameInterface;
import layer.presentation.interfaces.MapInterface;
import layer.presentation.interfaces.PersonInterface;

import java.util.ArrayList;

public class GameAPI implements GameInterface, FoodContainerInterface, PersonInterface, MapInterface {
    private static Game game;
    private static Person person;

    @Override
    public void newGame() {
        game = new Game();
    }

    @Override
    public void newPerson() {
        person = new Person();
    }

    @Override
    public ArrayList getInvItems() {
        return person.getInventory().getItems();
    }

    @Override
    public void removeInvItem(String itemName) {
        person.getInventory().removeItem(new Food(itemName, 0, 0));
    }

    @Override
    public void eat(String food) {
        person.eat(food);

    }

    @Override
    public ArrayList<ArrayList<String>> getMapFoodFields() {
        return game.getMap().getMapFoodFields();
    }
}
