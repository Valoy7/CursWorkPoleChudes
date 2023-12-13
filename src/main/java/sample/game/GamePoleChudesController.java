package sample.game;

import java.net.URL;
import java.util.ResourceBundle;

import DB.NowLogInUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GamePoleChudesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button A_button;

    @FXML
    private Button ReturnLobbyButton;

    @FXML
    private ImageView baraban_img;

    @FXML
    private Button checkAnswerButton;

    @FXML
    private TextField fullAnswer_field;

    @FXML
    private Label quest_field;

    @FXML
    private Label rightAnswer_field;

    @FXML
    private Label system_field;

    @FXML
    private Pane word_field;

    @FXML
    private Label yourTurn_field;

    @FXML
    private Button Ё_button;

    @FXML
    private Button Б_button;

    @FXML
    private Button В_button;

    @FXML
    private Button Г_button;

    @FXML
    private Button Д_button;

    @FXML
    private Button Е_button;

    @FXML
    private Button Ж_button;

    @FXML
    private Button З_button;

    @FXML
    private Button И_button;

    @FXML
    private Button Й_button;

    @FXML
    private Button К_button;

    @FXML
    private Button Л_button;

    @FXML
    private Button М_button;

    @FXML
    private Button Н_button;

    @FXML
    private Button О_button;

    @FXML
    private Button П_button;

    @FXML
    private Button Р_button;

    @FXML
    private Button С_button;

    @FXML
    private Button Т_button;

    @FXML
    private Button У_button;

    @FXML
    private Button Ф_button;

    @FXML
    private Button Х_button;

    @FXML
    private Button Ц_button;

    @FXML
    private Button Ч_button;

    @FXML
    private Button Ш_button;

    @FXML
    private Button Щ_button;

    @FXML
    private Button Ъ_button;

    @FXML
    private Button Ы_button;

    @FXML
    private Button Ь_button;

    @FXML
    private Button Э_button;

    @FXML
    private Button Ю_button;

    @FXML
    private Button Я_button;
    @FXML
    private Label login_field;
    public GamePoleChudesController() {
    }

    @FXML
    void initialize() {
        login_field.setText(NowLogInUser.getLoggedInUsername());


        // кнопка возвращения на главный экран
        ReturnLobbyButton.setOnAction(actionEvent -> {
            ReturnLobbyButton.getScene().getWindow().hide();
            WindowManager.showHelloView();
        });
    }

}
