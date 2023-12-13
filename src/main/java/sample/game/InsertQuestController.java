package sample.game;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DB.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InsertQuestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ReturnLobbyButton;

    @FXML
    private TextField answerInsert_field;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private ComboBox<String> complexityComboBox;

    @FXML
    private Label error_field;

    @FXML
    private Button insertCategoryButton;

    @FXML
    private Button insertComplexityButton;

    @FXML
    private Button insertQuestButton;

    @FXML
    private TextField questInsert_field;

    @FXML
    void initialize() {
        ReturnLobbyButton.setOnAction(actionEvent -> {
            ReturnLobbyButton.getScene().getWindow().hide();
            WindowManager.showHelloView();
        });

        // Заполняем выпадающие списки данными из БД
        fillComboBoxes();

        insertQuestButton.setOnAction(actionEvent -> {
            String insertQuest = questInsert_field.getText();
            String insertAnswer = answerInsert_field.getText();
            String selectedCategory = categoryComboBox.getValue();
            String selectedComplexity = complexityComboBox.getValue();

            // Получаем id категории и сложности из БД
            int categoryId = getCategoryId(selectedCategory);
            int complexityId = getComplexityId(selectedComplexity);

            // Вызываем метод для вставки данных в БД
            DatabaseHandler databaseHandler = new DatabaseHandler();
            databaseHandler.insertQuest(insertQuest, insertAnswer, String.valueOf(complexityId), String.valueOf(categoryId));
        });
    }

    private void fillComboBoxes() {
        // Заполняем выпадающий список categoryComboBox данными из БД
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ResultSet categoryResultSet = databaseHandler.getAllCategories();
            while (categoryResultSet.next()) {
                categoryComboBox.getItems().add(categoryResultSet.getString("category_name"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Заполняем выпадающий список complexityComboBox данными из БД
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ResultSet complexityResultSet = databaseHandler.getAllComplexities();
            while (complexityResultSet.next()) {
                complexityComboBox.getItems().add(complexityResultSet.getString("complexity_name"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getCategoryId(String categoryName) {
        // Получаем id категории из БД по её названию
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ResultSet categoryResultSet = databaseHandler.getCategoryIdByName(categoryName);
            if (categoryResultSet.next()) {
                return categoryResultSet.getInt("idcategory");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1; // Возвращаем -1 в случае ошибки
    }

    private int getComplexityId(String complexityName) {
        // Получаем id сложности из БД по её названию
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ResultSet complexityResultSet = databaseHandler.getComplexityIdByName(complexityName);
            if (complexityResultSet.next()) {
                return complexityResultSet.getInt("idcomplexity");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1; // Возвращаем -1 в случае ошибки
    }
}