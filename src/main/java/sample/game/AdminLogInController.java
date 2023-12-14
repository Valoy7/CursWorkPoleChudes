package sample.game;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DB.AdminData;
import DB.DatabaseHandler;
import DB.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.game.animations.Shake;

public class AdminLogInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUp_ReturnLobbyButton;

    @FXML
    private Button authLogInButton;

    @FXML
    private TextField login_field;

    @FXML
    private TextField password_field;

    @FXML
    void initialize() {
        //кнопка "войти"
        authLogInButton.setOnAction(actionEvent -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginAdmin(loginText, loginPassword);
            } else {
                System.out.println("Login and password are empty");
            }
        });

        //возвращение в лобби авторизации пользователя
        signUp_ReturnLobbyButton.setOnAction(actionEvent -> {
            signUp_ReturnLobbyButton.getScene().getWindow().hide();
            WindowManager.showHelloView();
        });

    }

    private void loginAdmin(String loginText, String loginPassword) {
        if (AdminData.checkCredentials(loginText, loginPassword)) {
            authLogInButton.getScene().getWindow().hide();
            // всё это для отображения нужного окна
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/game/adminFunc.fxml"));

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
        } else {
            Shake userLoginAnim = new Shake(login_field);
            Shake userPasswordAnim = new Shake(password_field);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
        }
    }

}
