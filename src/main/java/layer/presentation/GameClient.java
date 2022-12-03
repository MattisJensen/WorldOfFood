package layer.presentation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import layer.domain.GameAPI;

import java.io.IOException;

public class GameClient {

    private GameAPI api;

    private Scene scene;

    public GameClient() throws IOException {
        api = new GameAPI();
        api.newGame();
        api.newPerson();

        FXMLLoader fxmlLoader = new FXMLLoader(WorldOfFoodApplication.class.getResource("GUI.fxml"));
        scene = new Scene(fxmlLoader.load());
    }

    public Scene getScene() {
        return scene;
    }
}
