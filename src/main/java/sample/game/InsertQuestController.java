package sample.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InsertQuestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ReturnLobbyButton;

    @FXML
    private Label error_field;

    @FXML
    void initialize() {
        assert ReturnLobbyButton != null : "fx:id=\"ReturnLobbyButton\" was not injected: check your FXML file 'insertQuest.fxml'.";
        assert error_field != null : "fx:id=\"error_field\" was not injected: check your FXML file 'insertQuest.fxml'.";

    }

}
