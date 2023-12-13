package sample.game;

import DB.Const;
import DB.DatabaseHandler;
import DB.NowLogInUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class MenuGameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text SecretText_field;

    @FXML
    private ComboBox<String> complexityDropDownButton;

    @FXML
    private ComboBox<String> numberPlayersDropDownButton;

    @FXML
    private TextField fifthPlayer_field;

    @FXML
    private TextField firstPlayer_field;

    @FXML
    private TextField fourthPlayer_field;
    @FXML
    private Button returnLobbyButton;

    @FXML
    private TextField secondPlayer_field;

    @FXML
    private Button startGameButton;

    @FXML
    private TextField thirdPlayer_field;
    @FXML
    private Label login_field;


    @FXML
    void initialize() {

        login_field.setText(NowLogInUser.getLoggedInUsername());


        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ObservableList<String> complexity_list = FXCollections.observableArrayList();

            // Получаем данные из базы данных
            ResultSet resultSet = databaseHandler.getAllComplexities();
            while (resultSet.next()) {
                String complexityName = resultSet.getString(Const.COMPLEXITY_NAME);
                complexity_list.add(complexityName);
            }

            complexityDropDownButton.setItems(complexity_list);

            // Добавляем слушателя событий для ComboBox со сложностью
            complexityDropDownButton.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            });

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Обработка ошибок подключения к базе данных
        }

        ObservableList<String> numberPlayers_list = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        numberPlayersDropDownButton.setItems(numberPlayers_list);

        // кнопка возвращения на главный экран
        returnLobbyButton.setOnAction(actionEvent -> {
            returnLobbyButton.getScene().getWindow().hide();
            WindowManager.showHelloView();
        });

        // Добавляем слушателя событий для ComboBox с числом игроков
        numberPlayersDropDownButton.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Скрываем все текстовые поля игроков
            firstPlayer_field.setVisible(false);
            secondPlayer_field.setVisible(false);
            thirdPlayer_field.setVisible(false);
            fourthPlayer_field.setVisible(false);
            fifthPlayer_field.setVisible(false);

            // В зависимости от выбранного числа игроков показываем соответствующие текстовые поля
            switch (newValue) {
                case "1":
                    firstPlayer_field.setVisible(true);
                    break;
                case "2":
                    firstPlayer_field.setVisible(true);
                    secondPlayer_field.setVisible(true);
                    break;
                case "3":
                    firstPlayer_field.setVisible(true);
                    secondPlayer_field.setVisible(true);
                    thirdPlayer_field.setVisible(true);
                    break;
                case "4":
                    firstPlayer_field.setVisible(true);
                    secondPlayer_field.setVisible(true);
                    thirdPlayer_field.setVisible(true);
                    fourthPlayer_field.setVisible(true);
                    break;
                case "5":
                    firstPlayer_field.setVisible(true);
                    secondPlayer_field.setVisible(true);
                    thirdPlayer_field.setVisible(true);
                    fourthPlayer_field.setVisible(true);
                    fifthPlayer_field.setVisible(true);
                    break;
                default:
                    break;
            }
        });



        startGameButton.setOnAction(actionEvent -> {
            // Записываем выбранную сложность
            String selectedComplexity = complexityDropDownButton.getValue();
            if (selectedComplexity != null) {
                NowPlayers.setComplexity(selectedComplexity);
            }

            if (!firstPlayer_field.getText().isEmpty()) {
                NowPlayers.setFirstPlayer(firstPlayer_field.getText());
            }

            if (!secondPlayer_field.getText().isEmpty()) {
                NowPlayers.setSecondPlayer(secondPlayer_field.getText());
            }

            if (!thirdPlayer_field.getText().isEmpty()) {
                NowPlayers.setThirdPlayer(thirdPlayer_field.getText());
            }

            if (!fourthPlayer_field.getText().isEmpty()) {
                NowPlayers.setFourthPlayer(fourthPlayer_field.getText());
            }

            if (!fifthPlayer_field.getText().isEmpty()) {
                NowPlayers.setFifthPlayer(fifthPlayer_field.getText());
            }
            startGameButton.getScene().getWindow().hide();
            // всё это для отображения нужного окна
            FXMLLoader loader =  new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/game/gamePoleChudes.fxml"));

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
