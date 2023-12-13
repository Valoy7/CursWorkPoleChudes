package sample.game;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DB.DatabaseHandler;
import DB.Quest;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableColumn<Quest, String> answer_name_Column;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private ComboBox<String> categoryDeleteComboBox;

    @FXML
    private TableColumn<Quest, String> category_name_Column;

    @FXML
    private ComboBox<String> complexityComboBox;

    @FXML
    private ComboBox<String> complexityDeleteComboBox;

    @FXML
    private TableColumn<Quest, String> complexity_name_Column;

    @FXML
    private Button deleteQuest_Button;

    @FXML
    private Button deleteUser_Button;

    @FXML
    private Label error_field;

    @FXML
    private Button insertCategoryButton;

    @FXML
    private TextField insertCategory_field;

    @FXML
    private Button insertComplexityButton;

    @FXML
    private TextField insertComplexity_field;

    @FXML
    private Button insertQuestButton;

    @FXML
    private Button insertQuestButton1;

    @FXML
    private Button insertQuestButton2;

    @FXML
    private TableColumn<String, String> loginUsers_Column;

    @FXML
    private TableColumn<String, String> passwordUsers_Column;

    @FXML
    private TextField questInsert_field;

    @FXML
    private TableColumn<Quest, String> quest_name_Column;

    @FXML
    private ScrollBar scrollBarDeleteQuest;

    @FXML
    private ScrollBar skrollBarDeleteUsers;

    @FXML
    private TableView<Quest> tableQuest;

    @FXML
    private TableView<?> tableUsers;

    @FXML
    private Button updateTableQuests_Button;

    @FXML
    private Button updateTableUsers_Button;

    @FXML
    void initialize() {

        // Инициализируем колонки таблицы
        quest_name_Column.setCellValueFactory(cellData -> cellData.getValue().questProperty());
        answer_name_Column.setCellValueFactory(cellData -> cellData.getValue().answerProperty());
        category_name_Column.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
        complexity_name_Column.setCellValueFactory(cellData -> cellData.getValue().complexityNameProperty());


        // Обновляем данные таблицы
        updateTableQuests();

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
            updateTableQuests();
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

    private void updateTableQuests() {
        // Получаем данные из БД и обновляем таблицу
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ObservableList<Quest> questList = FXCollections.observableArrayList();

            ResultSet questResultSet = databaseHandler.getAllQuests();
            while (questResultSet.next()) {
                String quest = questResultSet.getString("quest");
                String answer = questResultSet.getString("answer");
                int categoryId = questResultSet.getInt("idcategory");
                int complexityId = questResultSet.getInt("idcomplexity");

                // Получаем имена категории и сложности по их ID
                String categoryName = databaseHandler.getCategoryNameById(categoryId);
                String complexityName = databaseHandler.getComplexityNameById(complexityId);

                questList.add(new Quest(quest, answer, categoryName, complexityName));
            }

            tableQuest.setItems(questList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}