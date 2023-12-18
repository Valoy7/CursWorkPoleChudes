package sample.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DB.DatabaseHandler;
import DB.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static sample.game.AdminFuncController.addTextLimiter;

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
    private Label error_field;
    @FXML
    private Button signUpButton;
    @FXML
    private Label successRegister_field;
    @FXML
    private Button signUp_ReturnLobbyButton;
    private static final int MAX_CHARACTERS_USERNAME = 20;
    private static final int MAX_CHARACTERS_PASSWORD = 30;
    @FXML
    void initialize() {
        addTextLimiter(login_field_register, MAX_CHARACTERS_USERNAME);
        addTextLimiter(password_field_register, MAX_CHARACTERS_PASSWORD);

        signUpButton.setOnAction(actionEvent -> {

            signUpNewUser();

        });
        signUp_ReturnLobbyButton.setOnAction(actionEvent -> {
            signUp_ReturnLobbyButton.getScene().getWindow().hide();
            WindowManager.showHelloView();
        });
//
    }

    private void signUpNewUser() {
        String username = login_field_register.getText().trim();
        String password = password_field_register.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            // Если хотя бы одно поле пустое, выводим ошибку
            error_field.setText("Неверный ввод! Заполните все поля.");
            successRegister_field.setText("");
        } else {
            // Если поля не пустые, продолжаем регистрацию пользователя
            DatabaseHandler dbHandler = new DatabaseHandler();
            User user = new User(username, password);
            dbHandler.signUpUser(user);
            // Очищаем error_field, если предыдущая ошибка была отображена
            successRegister_field.setText("Успешная регистрация!");
            error_field.setText("");
        }
    }

}
