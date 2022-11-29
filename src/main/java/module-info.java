module layers.presentation {
    requires javafx.controls;
    requires javafx.fxml;


    opens layer.presentation to javafx.fxml;
    exports layer.presentation;
}