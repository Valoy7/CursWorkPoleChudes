module sample.game {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.game to javafx.fxml;
    exports sample.game;
}