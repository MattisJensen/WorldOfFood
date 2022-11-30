package layer.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import layer.domain.GameAPI;
import layer.domain.game.GameSettings;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUIController implements Initializable, GameSettings {

    @FXML
    private Button collectButton;

    @FXML
    private Button eatButton;

    @FXML
    private ProgressBar foodBar;

    @FXML
    private TextField inputText;

    @FXML
    private ListView<String> invList;

    @FXML
    private GridPane mapGrid;

    @FXML
    private TextArea outputText;

    @FXML
    private Text invTitle;

    @FXML
    private Text score;

    private GameAPI api = new GameAPI();

    private ObservableList<String> items;

    private ArrayList<ArrayList<ImageView>> imageViews;

    @FXML
    void eatButton(ActionEvent event) {
        String selectedItem = invList.getSelectionModel().getSelectedItem();
        api.removeInvItem(selectedItem);
        items.remove(selectedItem);
        outputText.setText("Mmmmm... yummmy du har spist en / et " + selectedItem);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        items = FXCollections.observableArrayList();
        invList.setItems(items);

        /* image view instantiation */
        imageViews = new ArrayList<>();

        int x = mapGrid.getColumnCount();
        int y = mapGrid.getRowCount();



        for (int i = 0; i < x; i++) {
            imageViews.add(new ArrayList<ImageView>());

            for (int j = 0; j < y; j++) {
                imageViews.get(i).add(new ImageView());
                ImageView currentIV = imageViews.get(i).get(j);

                currentIV.setFitHeight(100);
                currentIV.setFitWidth(100);
                currentIV.setCache(true);
                currentIV.preserveRatioProperty();

                switch(api.getMapFoodFields().get(i).get(j)) {
                    case APPLE -> currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/appleTree.jpg"));
                    case PEAR -> currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/pearTree.jpg"));
                    case CARROT -> currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/field.jpg"));
                    case POTATO -> currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/field.jpg"));
                    case COW -> currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/cowField.jpg"));
                    case DUCK -> currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/duckLake.jpg"));
                    case FISH -> currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/fishLake.jpg"));
                    case EMPTY -> currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/path.jpg"));
                }



                mapGrid.add(currentIV,i,9-j);



            }
        }

    }


    @FXML
    void enterButton(ActionEvent event) {
        items.add("carrots");
    }
}
