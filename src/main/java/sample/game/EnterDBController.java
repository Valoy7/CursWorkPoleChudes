package sample.game;

import java.net.URL;
import java.util.ResourceBundle;

import DB.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import DB.Configs;
import javafx.scene.layout.AnchorPane;

import static sample.game.WindowManager.showHelloView;

public class EnterDBController extends Configs implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button dbEnter_Button;

    @FXML
    private TextField password_field;

    @FXML
    private TextField user_field;

    @FXML
    private Label errorDB_field;
    public void initialize(URL location, ResourceBundle resources) {
        dbEnter_Button.setOnAction(actionEvent -> {
            String dbUser2 = user_field.getText().trim();
            String dbPass2 = password_field.getText().trim();

            if (!dbUser2.equals("") && !dbPass2.equals("")) {
                // Устанавливаем значения для подключения к базе данных
                setDBUserAndPass(dbUser2, dbPass2);

                // Создаем экземпляр DatabaseConnector
                DatabaseConnector connector = new DatabaseConnector(dbHost, dbPort, dbName, dbUser, dbPass);

                // Проверяем подключение к базе данных
                if (connector.isDbConnected()) {
                    System.out.println("Successfully connected to the database.");

                    // Ваши дальнейшие действия, например, переход к следующему окну
                    dbEnter_Button.getScene().getWindow().hide();
                    WindowManager.showHelloView();
                } else {
                    System.out.println("Unable to connect to the database. Check your connection details.");
                    // Ваши действия при ошибке подключения, например, отображение сообщения об ошибке
                    errorDB_field.setText("Неверная попытка подключения");
                }
            } else {
                System.out.println("Login and password are empty");
            }
        });
    }
    public class ExampleUsage {

        public static void main(String[] args) {
            DatabaseConnector connector = new DatabaseConnector("localhost", "3306", "test", "username", "password");

            if (connector.isDbConnected()) {
                System.out.println("Successfully connected to the database.");
            } else {
                System.out.println("Unable to connect to the database. Check your connection details.");
            }
        }
    }
}