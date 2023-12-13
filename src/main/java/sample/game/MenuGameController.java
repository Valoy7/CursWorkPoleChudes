package sample.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuGameController {

    @FXML
    private Text SecretText_field;

    @FXML
    private ComboBox<String> complexityDropDownButton;

    @FXML
    private ComboBox<String> numberPlayersDropDownButton;

    @FXML
    private Button returnLobbyButton;

    @FXML
    private Button startGameButton;

    @FXML
    void initialize() {
        ObservableList<String> complexity_list = FXCollections.observableArrayList("Сложно", "Средне", "Легко");
        complexityDropDownButton.setItems(complexity_list);

        // Добавляем слушателя событий для ComboBox со сложностью
        complexityDropDownButton.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ("Сложно".equals(newValue)) {
                SecretText_field.setText("Ого, а ты уверен, что сможешь?");
            } else {
                SecretText_field.setText("");
            }
        });

        ObservableList<String> numberPlayers_list = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        numberPlayersDropDownButton.setItems(numberPlayers_list);

        // кнопка возвращения на главный экран
        returnLobbyButton.setOnAction(actionEvent -> {
            returnLobbyButton.getScene().getWindow().hide();
            WindowManager.showHelloView();
        });
//
    }

}
