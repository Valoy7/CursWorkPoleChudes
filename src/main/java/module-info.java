module sample.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens sample.game to javafx.fxml;
    opens DB to javafx.base;
    exports sample.game;
}