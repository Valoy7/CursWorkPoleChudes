package sample.game;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import DB.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminFuncController {

    private static final int MAX_CHARACTERS = 70;
    private static final int MAX_CHARACTERS_ANSW = 20;
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
    private Button deleteCategoryButton;

    @FXML
    private Button deleteComplexityButton;

    @FXML
    private TableColumn<String, String> loginUsers_Column;

    @FXML
    private TableColumn<String, String> passwordUsers_Column;

    @FXML
    private TextField questInsert_field;

    @FXML
    private TableColumn<Quest, String> quest_name_Column;


    @FXML
    private TableView<Quest> tableQuest;

    @FXML
    private TableView<User> tableUsers;


    @FXML
    void initialize() {
// Инициализируем колонки таблицы пользователей
        loginUsers_Column.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordUsers_Column.setCellValueFactory(new PropertyValueFactory<>("password"));

        updateTableUsers();

        // Добавляем обработчик события для кнопки удаления пользователя
        deleteUser_Button.setOnAction(actionEvent -> {
            User selectedUser = tableUsers.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.deleteUser(selectedUser);
                updateTableUsers(); // Обновляем таблицу после удаления пользователя
            } else {
                // Обработка случая, когда не выбран пользователь для удаления
                error_field.setText("Выберите пользователя для удаления");
            }
        });
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

        addTextLimiter(questInsert_field, MAX_CHARACTERS);
        addTextLimiter(answerInsert_field, MAX_CHARACTERS_ANSW);

        insertQuestButton.setOnAction(actionEvent -> {
            String selectedCategory = categoryComboBox.getValue();
            String selectedComplexity = complexityComboBox.getValue();

            String insertQuest = questInsert_field.getText();


            // Получаем id категории и сложности из БД
            int categoryId = getCategoryId(selectedCategory);
            // Регулярное выражение для проверки наличия только русских букв и пробелов
            String russianLettersRegexWithSpaces = "^[а-яА-ЯёЁ\\s]+$";

// Проверка с использованием регулярного выражения
            if (Pattern.matches(russianLettersRegexWithSpaces, answerInsert_field.getText())) {
                // Если строка состоит только из русских букв и пробелов
                String insertAnswer = answerInsert_field.getText();

                int complexityId = getComplexityId(selectedComplexity);

                // Проверяем, что категория и сложность существуют
                if (categoryId != -1 && complexityId != -1) {
                    error_field.setText("");
                    // Вызываем метод для вставки данных в БД
                    DatabaseHandler databaseHandler = new DatabaseHandler();
                    databaseHandler.insertQuest(insertQuest, insertAnswer, String.valueOf(complexityId), String.valueOf(categoryId));
                    updateTableQuests();
                    // Обновляем выпадающий список категорий
                    fillComboBoxes();
                    answerInsert_field.setText("");
                    questInsert_field.setText("");
                } else {
                    // Если категория или сложность не существует, выводим сообщение об ошибке
                    error_field.setText("Ошибка добавления! Несуществующая категория или сложность.");
                }

            } else {
                // Если в строке есть не русские буквы или другие символы, кроме пробелов
                error_field.setText("Можно вводить только русские буквы и пробелы!");
            }



        });

        insertCategoryButton.setOnAction(actionEvent -> {
            String newCategoryName = insertCategory_field.getText().trim();

            // Проверяем, существует ли категория
            if (!newCategoryName.isEmpty()) {
                if (!isCategoryExists(newCategoryName)) {
                    error_field.setText("");
                    // Если не существует, добавляем
                    DatabaseHandler databaseHandler = new DatabaseHandler();
                    databaseHandler.insertCategory(newCategoryName);
                    insertCategory_field.setText("");
                    // Обновляем выпадающий список категорий
                    fillComboBoxes();
                } else {
                    // Если категория уже существует, выводим сообщение об ошибке
                    error_field.setText("Такая категория уже существует!");
                }
            } else {
                // Если поле пустое, выводим сообщение об ошибке
                error_field.setText("Поле пустое!");
            }
        });

        deleteCategoryButton.setOnAction(actionEvent -> {
            String selectedCategory = categoryDeleteComboBox.getValue();

            if (selectedCategory != null) {
                // Вызываем метод для удаления выбранной категории из БД
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.deleteCategory(selectedCategory);

                // Обновляем выпадающий список категорий
                fillComboBoxes();

                // Обновляем данные таблицы
                updateTableQuests();
            }
        });

        deleteComplexityButton.setOnAction(actionEvent -> {
            String selectedComplexity = complexityDeleteComboBox.getValue();

            if (selectedComplexity != null) {
                // Вызываем метод для удаления выбранной сложности из БД
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.deleteComplexity(selectedComplexity);

                // Обновляем выпадающий список сложностей
                fillComboBoxes();

                // Обновляем данные таблицы
                updateTableQuests();
            }
        });

        insertComplexityButton.setOnAction(actionEvent -> {
            String newComplexityName = insertComplexity_field.getText().trim(); // Убираем пробелы с обеих сторон

            // Проверяем, что строка не пуста после удаления пробелов
            if (!newComplexityName.isEmpty()) {
                // Вызываем метод для вставки новой сложности в БД
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.insertComplexity(newComplexityName);
                insertComplexity_field.setText("");
                // Обновляем выпадающий список сложностей
                fillComboBoxes();
            } else {
                // Если строка пуста, выводим сообщение об ошибке
                error_field.setText("Нельзя добавить пустую сложность!");
            }
        });

        deleteQuest_Button.setOnAction(actionEvent -> {
            // Получаем выбранный вопрос из таблицы
            Quest selectedQuest = tableQuest.getSelectionModel().getSelectedItem();

            if (selectedQuest != null) {
                // Вызываем метод для удаления вопроса из БД
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.deleteQuest(selectedQuest);

                // Обновляем данные в таблице
                updateTableQuests();
            } else {
                // Если ничего не выбрано, выведите предупреждение или обработайте ситуацию по вашему усмотрению
                System.out.println("Please select a question to delete.");
            }
        });

    }

    // Метод для проверки существования категории
    private boolean isCategoryExists(String categoryName) {
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ResultSet resultSet = databaseHandler.getCategoryByName(categoryName);
            return resultSet.next(); // Если есть следующая запись, значит, категория существует
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false; // Обработка ошибок, в данном случае, считаем, что категории не существует
        }
    }

    private void fillComboBoxes() {
        // Очищаем текущие элементы в выпадающих списках
        categoryComboBox.getItems().clear();
        complexityComboBox.getItems().clear();
        categoryDeleteComboBox.getItems().clear();
        complexityDeleteComboBox.getItems().clear();
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();

            // Заполняем выпадающий список categoryComboBox данными из БД
            ResultSet categoryResultSet = databaseHandler.getAllCategories();
            while (categoryResultSet.next()) {
                String categoryName = categoryResultSet.getString(Const.CATEGORY_NAME);

                // Проверяем, не содержится ли уже такая категория в списке
                if (!categoryComboBox.getItems().contains(categoryName)) {
                    categoryComboBox.getItems().add(categoryName);
                }
            }

            // Заполняем выпадающий список categoryDeleteComboBox данными из БД
            ResultSet categoryDeleteResultSet = databaseHandler.getAllCategories();
            while (categoryDeleteResultSet.next()) {

                String categoryName = categoryDeleteResultSet.getString(Const.CATEGORY_NAME);

                // Проверяем, не содержится ли уже такая категория в списке
                if (!categoryDeleteComboBox.getItems().contains(categoryName)) {
                    categoryDeleteComboBox.getItems().add(categoryName);
                }
            }

            // Заполняем выпадающий список complexityComboBox данными из БД
            ResultSet complexityResultSet = databaseHandler.getAllComplexities();
            while (complexityResultSet.next()) {
                String complexityName = complexityResultSet.getString(Const.COMPLEXITY_NAME);

                // Проверяем, не содержится ли уже такая сложность в списке
                if (!complexityComboBox.getItems().contains(complexityName)) {
                    complexityComboBox.getItems().add(complexityName);
                }
            }

            // Заполняем выпадающий список complexityComboBox данными из БД
            ResultSet complexityDeleteResultSet = databaseHandler.getAllComplexities();
            while (complexityDeleteResultSet.next()) {
                String complexityName = complexityDeleteResultSet.getString(Const.COMPLEXITY_NAME);

                // Проверяем, не содержится ли уже такая сложность в списке
                if (!complexityDeleteComboBox.getItems().contains(complexityName)) {
                    complexityDeleteComboBox.getItems().add(complexityName);
                }
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
                return categoryResultSet.getInt(Const.CATEGORY_ID);
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
                return complexityResultSet.getInt(Const.COMPLEXITY_ID);
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
                String quest = questResultSet.getString(Const.QUEST_TEXT);
                String answer = questResultSet.getString(Const.ANSWER_TEXT);
                int categoryId = questResultSet.getInt(Const.QUEST_CATEGORY_ID);
                int complexityId = questResultSet.getInt(Const.QUEST_COMPLEXITY_ID);

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
    private void updateTableUsers() {
        // Получаем данные из БД и обновляем таблицу пользователей
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ObservableList<User> userList = FXCollections.observableArrayList();

            ResultSet userResultSet = databaseHandler.getAllUsers();
            while (userResultSet.next()) {
                String username = userResultSet.getString(Const.USER_NAME);
                String password = userResultSet.getString(Const.USER_PASS);

                userList.add(new User(username, password));
            }

            tableUsers.setItems(userList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }


}