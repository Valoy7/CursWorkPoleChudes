package sample.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import DB.Configs;
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

    public void initialize(URL location, ResourceBundle resources) {
        dbEnter_Button.setOnAction(actionEvent -> {
            String dbUser2 = user_field.getText().trim();
            String dbPass2 = password_field.getText().trim();

            if (!dbUser2.equals("") && !dbPass2.equals("")) {
                setDBUserAndPass(dbUser2, dbPass2);

                dbEnter_Button.getScene().getWindow().hide();
                WindowManager.showHelloView();
            } else {
                System.out.println("Login and password are empty");
            }
        });
    }
}