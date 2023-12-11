module sample.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens sample.game to javafx.fxml;
    exports sample.game;
}