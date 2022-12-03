/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer.presentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WorldOfFoodApplication extends Application {

    public static void main(String[] args) {
//        client.play();
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        GameClient client = new GameClient();
        Scene scene = client.getScene();
        stage.setTitle("World of Food");
        stage.setScene(scene);
        stage.show();
    }
}
