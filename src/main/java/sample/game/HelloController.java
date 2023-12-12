package sample.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminLogInButton;

    @FXML
    private Button authLogInButton;

    @FXML
    private TextField login_field;

    @FXML
    private TextField password_field;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {
        signUpButton.setOnAction(actionEvent -> {
            signUpButton.getScene().getWindow().hide();
            // всё это для отображения нужного окна
            FXMLLoader loader =  new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/game/SignUp.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            // root параметр, который нужно подключить
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

}
