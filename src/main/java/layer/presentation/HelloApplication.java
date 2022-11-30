package layer.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import layer.domain.GameAPI;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("World of Food");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        GameAPI api  = new GameAPI();
        api.newGame();
        api.newPerson();

        launch();
    }
}