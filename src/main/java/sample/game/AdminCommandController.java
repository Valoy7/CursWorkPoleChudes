package sample.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminCommandController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ReturnLobbyButton;

    @FXML
    private Button changeQuestButton;

    @FXML
    private Button changeUserButton;

    @FXML
    private Button deleteQuestButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button insertQuestButton;

    @FXML
    private Button insertUserButton;

    @FXML
    void initialize() {
        //кнопка возвращения в меню
        ReturnLobbyButton.setOnAction(actionEvent -> {
            ReturnLobbyButton.getScene().getWindow().hide();
            WindowManager.showHelloView();
        });

    }

}
