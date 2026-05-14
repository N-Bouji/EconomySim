module economy {
    requires javafx.controls;
    requires javafx.fxml;

    opens economy to javafx.fxml;
    exports economy;
}
