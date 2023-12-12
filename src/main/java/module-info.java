module sample.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens sample.game to javafx.fxml;
    exports sample.game;
}