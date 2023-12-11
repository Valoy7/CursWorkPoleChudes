package sample.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private Button signInButton;

    @FXML
    void initialize() {

        authLogInButton.setOnAction(actionEvent -> {
            System.out.println("Вы нажали на кнопку Войти!");
        });

    }

}
