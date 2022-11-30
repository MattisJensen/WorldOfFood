package layer.presentation;

import layer.domain.GameAPI;

public class GameClient {

    private GameAPI api;

    public GameClient() {
        api  = new GameAPI();
        api.newGame();
        api.newPerson();

    }
}
