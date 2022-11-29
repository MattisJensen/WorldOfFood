package layer.presentation;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import layer.domain.item.Item;

public class GUIController {

    @FXML
    private Button collectButtom;

    @FXML
    private Button eatButtom;

    @FXML
    private ProgressBar foodBar;

    @FXML
    private GridPane gridPane;

    @FXML
    private ListView<Item> inventoryList;


    @FXML
    private Text scoreKlima;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textField;

    private ObservableList<Item> items;
}
