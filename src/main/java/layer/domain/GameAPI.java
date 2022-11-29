package layer.domain;

import layer.domain.game.Game;
import layer.domain.person.Person;
import layer.presentation.interfaces.FoodContainerInterface;
import layer.presentation.interfaces.GameInterface;
import layer.presentation.interfaces.PersonInterface;

public class GameAPI implements GameInterface, FoodContainerInterface, PersonInterface {
    private Game game;
    private Person person;


    @Override
    public void createGame() {
        game = new Game();
    }

    @Override
    public void createPerson() {
        person = new Person();
    }

}
