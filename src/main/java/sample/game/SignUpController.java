package sample.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DB.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field_register;

    @FXML
    private TextField password_field_register;

    @FXML
    private Button signUpButton;

    @FXML
    private Button signUp_ReturnLobbyButton;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        signUpButton.setOnAction(actionEvent -> {
            dbHandler.signUpUser(login_field_register.getText(),
                    password_field_register.getText());
        });
        signUp_ReturnLobbyButton.setOnAction(actionEvent -> {
            signUp_ReturnLobbyButton.getScene().getWindow().hide();
            // всё это для отображения нужного окна
            FXMLLoader loader =  new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/game/hello-view.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            // root параметр, который нужно подключить
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

}
