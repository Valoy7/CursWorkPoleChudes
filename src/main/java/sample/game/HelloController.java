package sample.game;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DB.DatabaseHandler;
import DB.NowLogInUser;
import DB.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.game.animations.Shake;
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
    private Button returnBD_button;
    @FXML
    void initialize() {
    //кнопка "войти"
        authLogInButton.setOnAction(actionEvent -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword);
            } else {
                System.out.println("Login and password is empty");
            }
        });
        //кнопка "зарегистрироваться"
        signUpButton.setOnAction(actionEvent -> {
            openNewScene("/sample/game/SignUp.fxml");
        });
        returnBD_button.setOnAction(actionEvent -> {
            openNewScene("/sample/game/enterDBdata.fxml");
        });

        adminLogInButton.setOnAction(actionEvent -> {
            adminLogInButton.getScene().getWindow().hide();
            // всё это для отображения нужного окна
            FXMLLoader loader =  new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/game/adminLogIn.fxml"));

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

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        // пробегаем всех пользователей полученных getUser-ом
        while(true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        if(counter >= 1) {
            NowLogInUser.setLoggedInUsername(loginText);
            openNewScene("/sample/game/menuGame.fxml");
        } else {
            Shake userLoginAnim = new Shake(login_field);
            Shake userPasswordAnim = new Shake(password_field);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
        }
    }

    public void openNewScene(String window) {
        signUpButton.getScene().getWindow().hide();
        // всё это для отображения нужного окна
        FXMLLoader loader =  new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

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
    }
}
