package layer.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import layer.domain.GameAPI;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    @FXML
    private Button downButton;

    @FXML
    private Button eatButton;

    @FXML
    private Button enterButton;

    @FXML
    private Button plusButton;

    @FXML
    private Button minusButton;

    @FXML
    private ProgressBar foodBar;

    @FXML
    private Text foodBarText;

    @FXML
    private HBox gameHBox;

    @FXML
    private VBox gameOverVBox;

    @FXML
    private Button gameOverButton;

    @FXML
    private TextField inputText;

    @FXML
    private ListView<String> invList;

    @FXML
    private Text invSizeText;

    @FXML
    private Text invTitle;

    @FXML
    private VBox invVBox;

    @FXML
    private Button leftButton;

    @FXML
    private GridPane mapGrid;

    @FXML
    private TextArea outputText;

    @FXML
    private Button rightButton;

    @FXML
    private Text score;

    @FXML
    private Text scoreText;

    @FXML
    private ScrollPane keyListener;

    @FXML
    private ScrollPane scrollPaneMap;

    @FXML
    private Label stats;

    @FXML
    private Button upButton;

    private GameAPI api;

    private ObservableList<String> items;

    private ImageView person;

    private String leftRight;

    /* Intro related variables */
    private boolean step1;
    private boolean step2;
    private boolean step3;
    private boolean nameCorretWaiting;
    private boolean nameCorrect;
    private boolean introFinished;
    private String inputTextString;

    /* initialize er metoden, der bliver udført når Controller klassen bliver loadet - altså lidt som en constructor til klassen */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        api = new GameAPI();

        /* Intro related stuff */
        step1 = false;
        step2 = false;
        step3 = false;
        nameCorretWaiting = false;
        nameCorrect = false;
        introFinished = false;
        inputTextString = ""; //inputTextString bruges for at gemme inputtet i en String, da inputFeltet bliver resettet efter hver klik og vi således kan beholde inputtet i denne String

        /* Inventar ListView */
        items = FXCollections.observableArrayList();
        invList.setItems(items);

        /* Set init values */
        foodBar.setProgress(api.getFoodPoints() / 100);
        invSizeText.setText("0 af " + api.INVENTORY_SIZE + " items");
        scrollPaneMap.setHvalue(0.5);
        scrollPaneMap.setVvalue(0.5);
        score.setText("0");
        inputText.setText("");
        inputText.setPromptText("her kan du skrive...");
        leftRight = "Left";


        setMapGUI();

        /* Start intro */
        setGUIVisible(false);
        initIntro();
    }


    private void initIntro() {

        String name = "";


        // først når spilleren har udført alle intro steps, bliver personen sat på mappet
        if (!step1) {
            enterButton.setText("Gem navn");
            enterButton.setDisable(false);
            minusButton.setVisible(false);
            minusButton.setText("Ja");
            plusButton.setVisible(false);
            plusButton.setText("Nej");

            outputText.setText("Velkommen til World of Food!\n\nFør vi går i gang, må jeg spørge hvad du hedder?");
            name = inputTextString.trim(); //trim() fjerner mellemrum i før og efter sætningen

            if (!name.equalsIgnoreCase("") && !isNum(name)) {
                api.setName(name);
                outputText.setText("Mange tak for svaret! Skal jeg gemme \"" + name + "\" som dit navn?\n\nHvis du trykker nej, så kan du efterfølgende indtaste et nyt navn.");
                enterButton.setDisable(true);
                minusButton.setVisible(true);
                minusButton.setDisable(false);
                minusButton.setText("Ja");
                plusButton.setVisible(true);
                plusButton.setDisable(false);
                plusButton.setText("Nej");

                nameCorretWaiting = true;

                if (nameCorrect) {
                    invTitle.setText("Inventar af " + name);
                    enterButton.setDisable(false);
                    minusButton.setDisable(true);
                    plusButton.setDisable(true);
                    step1 = true;

                    enterButton.setText("Videre");
                    outputText.setText("Hi " + name + ", godt at se dig!\n\nDu vågnede et eller andet sted udenfor og ser søer, træer og marker. Da du selvfølgelig er sulten, skal du spise noget for at overleve.\n\nDet betyder, at hvis du ikke længere har energi tilbage, slutter spillet.");
                }
            }
        } else if (!step2) {
            step2 = true;
            enterButton.setText("Videre");

            outputText.setText("Du kan spise, hvad du vil. Dog vær opmærksom på, at ikke alt mad er lige godt for klimaet.\n\nDerudover får du forskelligt meget energi fra forskelligt mad.\n\nNår det er sagt, ønsker jeg dig rigtig god spiseløst!");
        } else if (!step3) {
            step3 = true;
            enterButton.setText("Start");
            minusButton.setText("-");
            plusButton.setText("+");

            upButton.setVisible(true);
            downButton.setVisible(true);
            leftButton.setVisible(true);
            rightButton.setVisible(true);
            upButton.setDisable(true);
            downButton.setDisable(true);
            leftButton.setDisable(true);
            rightButton.setDisable(true);

            outputText.setText("Med W A S D eller de 4 knapper med pilene nedenunder kan du navigere gennem mappet og ved hjælp af dit trackpad kan du bevæge mappet.\n\nMed + og - tasten eller de to knapper til højre kan du vælge, hvor meget mad du gerne vil høste. \n\nMed tasten M eller Spis knappen kan du spise det mad, du har valgt i dit inventar. \n\nKlik på start så snart du er klar.");
        } else {
            enterButton.setText("Saml op");
            minusButton.setDisable(false);
            plusButton.setDisable(false);
            introFinished = true;
            setGUIVisible(true);

            /* sætter personen på mappet */
            person = new ImageView();
            person.setFitWidth(api.FIELD_WIDTH);
            person.setFitHeight(api.FIELD_HEIGHT);
            movePerson("UP");
        }
    }

    private void nameCorrect() {
        if (inputText.getText().equalsIgnoreCase("ja")) {
            inputText.setText("");
            nameCorrect = true;
        } else {
            inputText.setText("");
            inputTextString = ""; //derved skal der igen spørges efter navnet, da if statmentet er forkert fordi name bliver sat til ""
            api.setName("");
            nameCorrect = false;
            nameCorretWaiting = false;
        }
        initIntro();
    }


    void setMapGUI() {
        /* mapgrid & image view instantiation */

        /* henter antal af felter fra domainlayer */
        int xMapSize = api.XMAP_SIZE;
        int yMapSize = api.YMAP_SIZE;

        /* fastlægge hvor stor i pixel et enkelt felte skal være */
        int xSingleGridSize = api.FIELD_WIDTH;
        int ySingleGridSize = api.FIELD_HEIGHT;

        /* fastlægger mappets hele størrelse ud fra antallet af felter og størrelsen et felte har */
        mapGrid.setPrefSize(xMapSize * xSingleGridSize, yMapSize * ySingleGridSize);

        /* opretter en 2D arraylist af den visuelle mapgrid på samme måde som fx Map constructoren opretter mappet */
        ArrayList<ArrayList<ImageView>> imageViews = new ArrayList<>();

        for (int i = 0; i < xMapSize; i++) {
            imageViews.add(new ArrayList<ImageView>());

            for (int j = 0; j < yMapSize; j++) {
                imageViews.get(i).add(new ImageView()); //tilføjer en imageview (placegolder for pictures) på den aktuelle plads

                ImageView currentIV = imageViews.get(i).get(j);

                currentIV.setFitWidth(xSingleGridSize);
                currentIV.setFitHeight(ySingleGridSize);
                currentIV.setCache(true);
                currentIV.preserveRatioProperty();

                String currentFoodField = api.getMapFoodFields().get(i).get(j);

                /* choosing the right picture, depending on fieldtype - java cant compile the following statements as a switch, but as if statements, because the switch has to be sure, that the api Strings are initialized, but the if statements not */
                if (currentFoodField.equalsIgnoreCase(api.APPLE)) {
                    currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/appleTree.jpg"));

                } else if (currentFoodField.equalsIgnoreCase(api.PEAR)) {
                    currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/pearTree.jpg"));

                } else if (currentFoodField.equalsIgnoreCase(api.CARROT)) {
                    currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/carrotField.jpg"));

                } else if (currentFoodField.equalsIgnoreCase(api.POTATO)) {
                    currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/potatoField.jpg"));

                } else if (currentFoodField.equalsIgnoreCase(api.COW)) {
                    currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/cowField.jpg"));

                } else if (currentFoodField.equalsIgnoreCase(api.DUCK)) {
                    currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/duckLake.jpg"));

                } else if (currentFoodField.equalsIgnoreCase(api.FISH)) {
                    currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/fishLake.jpg"));

                } else {
                    currentIV.setImage(new Image("file:src/main/java/layer/presentation/pictures/path.jpg"));
                }

                mapGrid.add(currentIV, i, j);
            }
        }
    }


    private void setGUIVisible(boolean b) {
        invVBox.setVisible(b);
        scrollPaneMap.setDisable(!b);
        scoreText.setVisible(b);
        score.setVisible(b);
        foodBarText.setVisible(b);
        foodBar.setVisible(b);
        upButton.setVisible(b);
        downButton.setVisible(b);
        leftButton.setVisible(b);
        rightButton.setVisible(b);
    }


    /* collect items methods */
    @FXML
    void enterButton(ActionEvent event) {
        if (introFinished && api.hasCurrentFood() && isNum(inputText.getText().trim())) { // checker om feltet er tom, hvis ja kan der ikke lægges noget i inventaret    og isNum checker om der blev skrevet et nummer i feltet - trim() fjerner mellemrum i starten og slutning af sætningen

            int amount = Integer.parseInt(inputText.getText().trim());

            if (!api.enoughInvSpace(amount)) {
                outputText.setText("Du har ikke nok plads i dit inventar til at tilføje yderligere " + amount + " items. Du kan maksimalt have " + api.INVENTORY_SIZE + " items med dig.");

            } else if (amount > api.getCurrentFoodAmount()) {
                outputText.setText("Du kan ikke høste " + amount + " " + api.getCurrentGrammaticalNumber() + " op, fordi der ikke er nok.");

            } else if (amount > 0) {
                outputText.setText("Du har indsamlet " + amount + " " + api.getCurrentGrammaticalNumber() + "."); // teksten skal printes før item bliver fjernet, da hvis den først fjerner items, kan det være at det grammatiske ikke passer lœngere

                for (int i = 0; i < amount; i++) {
                    items.add(api.collectFoodItem());
                }

                invSizeText.setText(api.getInvItems().size() + " af " + api.INVENTORY_SIZE + " items");
            }
        } else if (!introFinished) {
            inputTextString = inputText.getText();
            initIntro();
        }

        inputText.setText("");
        keyListener.requestFocus(); // deselecter textfield således at man ikke skriver
    }

    private boolean isNum(String input) {
        if (input == null) {
            return false;
        }
        try {
            int t = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    void minusButton(ActionEvent event) {
        if (introFinished) {
            if (isNum(inputText.getText())) {
                String newInput = String.valueOf(Integer.parseInt(inputText.getText()) - 1);
                inputText.setText(newInput);
            } else {
                inputText.setText("-1");
            }
        } else {
            inputText.setText("ja");
            nameCorrect();
        }
    }

    @FXML
    void plusButton(ActionEvent event) {
        if (introFinished) {
            if (isNum(inputText.getText())) {
                String newInput = String.valueOf(Integer.parseInt(inputText.getText()) + 1);
                inputText.setText(newInput);
            } else {
                inputText.setText("1");
            }
        } else {
//            inputText.setText("nej");
            nameCorrect();
        }
    }

    @FXML
    void eatButton(ActionEvent event) {

        String selectedItem = invList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            boolean eaten = api.eat(selectedItem);

            if (eaten) {
                items.remove(selectedItem);

                score.setText("" + api.getClimatePoints());

                foodBar.setProgress(api.getFoodPoints() / 100);

                outputText.setText("Mmmm... yummmy " + api.getCurrentFoodName() + ".");

                invSizeText.setText(api.getInvItems().size() + " af " + api.INVENTORY_SIZE + " items");

            } else {
                outputText.setText("Du er allerede mæt!");
            }
        }

        invList.getSelectionModel().clearSelection();
    }


    /* Move person methods */
    public void movePerson(String direction) {
        int[] coordinates = api.getCurrentPosition();

        mapGrid.getChildren().remove(person);

        setPersonDirection(direction);

        mapGrid.add(person, coordinates[0], coordinates[1]);

        foodBar.setProgress(api.getFoodPoints() / api.P_MAX_FOODPOINTS > 0 ? api.getFoodPoints() / api.P_MAX_FOODPOINTS : 0);

        gameOver();

        if (api.hasCurrentFood() && api.getCurrentFoodAmount() > 0) {
            outputText.setText(api.getCurrentDescription() + "\n\nHvis du gerne vil høste " + api.getGrammaticalPlural() + ", så angiv bare antallet af, hvor mange du gerne vil have.");
            inputText.setPromptText("indtast her et nummer...");
        } else {
            outputText.setText("Her er der ikke noget du kan høste.");
        }
    }

    public void setPersonDirection(String direction) {

        //switches left and right everytime this method is called
        if (leftRight.equalsIgnoreCase("Right")) {
            leftRight = "Left";
        } else {
            leftRight = "Right";
        }

        switch (direction) {
            case "UP", "W" ->
                    person.setImage(new Image("file:src/main/java/layer/presentation/pictures/person" + leftRight + "3.png"));
            case "DOWN", "S" ->
                    person.setImage(new Image("file:src/main/java/layer/presentation/pictures/person" + leftRight + "1.png"));
            case "LEFT", "A" ->
                    person.setImage(new Image("file:src/main/java/layer/presentation/pictures/person" + leftRight + "2.png"));
            case "RIGHT", "D" ->
                    person.setImage(new Image("file:src/main/java/layer/presentation/pictures/person" + leftRight + "4.png"));
        }
    }

    /* The GridView coordinatesystem are opposite to a normal coordinatesystem - therefor up is down and down is up - left and right remains the same */
    @FXML
    void goDown(ActionEvent event) {
        api.go("UP");
        movePerson("UP");
    }

    @FXML
    void goLeft(ActionEvent event) {
        api.go("LEFT");
        movePerson("A");
    }

    @FXML
    void goRight(ActionEvent event) {
        api.go("RIGHT");
        movePerson("D");
    }

    @FXML
    void goUp(ActionEvent event) {
        api.go("DOWN");
        movePerson("DOWN");
    }

    @FXML
    void go(KeyEvent event) {
        String key = event.getCode().toString();
        if (introFinished) {
            if (key.matches("LEFT|A|RIGHT|D")) {
                api.go(key);
                movePerson(key);
            } else if (key.matches("UP|W")) { // The GridView coordinatesystem are opposite to a normal coordinatesystem - therefor up is down and down is up - left and right remains the same
                api.go("DOWN");
                movePerson("DOWN");
            } else if (key.matches("DOWN|S")) { // The GridView coordinatesystem are opposite to a normal coordinatesystem - therefor up is down and down is up - left and right remains the same
                api.go("UP");
                movePerson("UP");
            } else if (key.matches("M")) {
                eatButton(null);
            } else if (key.matches("MINUS|CLOSE_BRACKET")) { // + ligger på det danske keyboard hvor us keyboard har minus - + ligger på det tyske keyboard hvor us keyboard har CLOSE_BRACKET
                plusButton(null);
            } else if (key.matches("SLASH")) { // - ligger på det danske/tyske keyboard hvor us keyboard har /
                minusButton(null);
            } else if (key.matches("ENTER")) {
                enterButton(null);
            }
            System.out.println(key);
        }
    }

    public void gameOver() {
        if (api.isGameOver()) {
            gameHBox.setVisible(false);
            gameOverVBox.setPrefWidth(keyListener.getPrefWidth());
            gameOverVBox.setVisible(true);

            if (api.getClimatePoints() > 0) {
                stats.setText("Åh nej " + api.getName() + ", så har du ikke\nlængere energi tilbage.\n\nDog kan jeg se, at du med dine\n"
                        + api.getClimatePoints() + " klimapoint har haft spisevaner,\nsom ikke har påvirket vores klima negativ.\n\nVil du prøve igen og se,\nom du kan klare dig endnu bedre?");
            } else {
                stats.setText("Åh nej " + api.getName() + ", så har du ikke\nlængere energi tilbage.\n\nJeg kan se, at du med dine\n"
                        + api.getClimatePoints() + " klimapoint har haft spisevaner,\nsom har påvirket vores klima negativ.\n\nPrøv igen, jeg er sikker på\n det kan du bedre!");
            }
        }
    }

    @FXML
    void newGame(ActionEvent event) {
        api.restartGame();
        try {
            initialize(new URL("file:src/main/resources/layer/presentation/GUI.fxml"), null);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        gameOverVBox.setVisible(false);
        gameHBox.setVisible(true);
    }
}