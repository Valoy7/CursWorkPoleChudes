package sample.game;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import DB.Const;
import DB.DatabaseHandler;
import DB.NowLogInUser;
import DB.PlayerScore;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePoleChudesController {
    @FXML
    private Button repeatGameButton;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button A_button;

    @FXML
    private Button ReturnLobbyButton;
    @FXML
    private TableColumn<PlayerScore, String> player_name_column;

    @FXML
    private TableColumn<PlayerScore, Integer> last_score_column;

    @FXML
    private TableColumn<PlayerScore, Integer> best_score_column;
    @FXML
    private TableView<PlayerScore> score_table;
    @FXML
    private Button look_score_button;
    @FXML
    private ImageView baraban_img;
    private static final int ROTATION_DURATION = 200; // длительность вращения в миллисекундах
    private static final int ROTATION_CYCLES = 10; // количество циклов вращения
    private static final int MAX_ROTATION_CYCLES = 20;
    private static final int MIN_ROTATION_CYCLES = 5;
    private Timeline timeline;
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
    private Label fifth_playerName_field;

    @FXML
    private Label fifth_playerScore_field;

    @FXML
    private Label first_playerName_field;

    @FXML
    private Label first_playerScore_field;

    @FXML
    private Label fourth_playerName_field;

    @FXML
    private Label fourth_playerScore_field;
    @FXML
    private Label second_playerName_field;

    @FXML
    private Label second_playerScore_field;
    @FXML
    private Label third_playerName_field;

    @FXML
    private Label third_playerScore_field;


    @FXML
    private Label login_field;
    private AtomicBoolean gameWon = new AtomicBoolean(false);
    private List<String> playersList = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private boolean letterChosen = false;
    private boolean canSpin = true;
    private boolean canletterChosen = false;
    private boolean wordEntered = false; // флаг, указывающий, было ли введено слово целиком
    //private boolean letterChosen = false; // флаг, указывающий, была ли выбрана буква
    @FXML
    <ScoreRecord>
    void initialize() {
       // AtomicBoolean gameWon = new AtomicBoolean(false);
        login_field.setText(NowLogInUser.getLoggedInUsername());
        String NowUser =  NowLogInUser.getLoggedInUsername();

// Инициализация колонок таблицы
        player_name_column.setCellValueFactory(cellData -> cellData.getValue().playerNameProperty());
        last_score_column.setCellValueFactory(cellData -> cellData.getValue().lastScoreProperty().asObject());
        best_score_column.setCellValueFactory(cellData -> cellData.getValue().bestScoreProperty().asObject());


// Обновление данных таблицы
        updateTableGamers();

        // обработчик события для кнопки кручения барабана
        baraban_img.setOnMouseClicked(event -> {
            rotateBaraban();
        });

        // взять выбранную ранее сложность
        String complexity = NowPlayers.getComplexity();

        // Создаем экземпляр DatabaseHandler
        DatabaseHandler databaseHandler = new DatabaseHandler();

        // Вызываем метод getRandomQuestion через созданный экземпляр
        String nowQuest = databaseHandler.getRandomQuestion(complexity);
        String nowAnswer = databaseHandler.getAnswerByQuest(nowQuest);
        System.out.println("ETO OTVET: " + nowAnswer);
        // Предположим, что quest_field - это TextField, но замените на соответствующий тип элемента интерфейса
        quest_field.setText(nowQuest);
        displayEmptyAnswer(nowAnswer);

        //NowPlayers nowPlayers = NowPlayers();
        String firstPlayer = NowPlayers.getFirstPlayer();
        String secondPlayer = NowPlayers.getSecondPlayer();
        String thirdPlayer = NowPlayers.getThirdPlayer();
        String fourthPlayer = NowPlayers.getFourthPlayer();
        String fifthPlayer = NowPlayers.getFifthPlayer();
        yourTurn_field.setText(firstPlayer);

       // System.out.println(firstPlayer);
        if(firstPlayer != null && !firstPlayer.isEmpty()) {
            first_playerName_field.setText(firstPlayer);
            playersList.add(firstPlayer);

         if(DatabaseHandler.checkIncludeGamer(NowUser, firstPlayer) == 0) {
             DatabaseHandler.addGamersInOneAcc(NowUser, firstPlayer);

         }

        } else first_playerName_field.setText("");
        if(secondPlayer != null && !secondPlayer.isEmpty()) {
            second_playerName_field.setText(secondPlayer);
            playersList.add(secondPlayer);
            if(DatabaseHandler.checkIncludeGamer(NowUser, secondPlayer) == 0) {
                DatabaseHandler.addGamersInOneAcc(NowUser,secondPlayer);

            }

        } else second_playerName_field.setText("");
        if(thirdPlayer != null && !thirdPlayer.isEmpty()) {
            third_playerName_field.setText(thirdPlayer);
            playersList.add(thirdPlayer);
            if(DatabaseHandler.checkIncludeGamer(NowUser, thirdPlayer) == 0) {
                DatabaseHandler.addGamersInOneAcc(NowUser,thirdPlayer);

            }


        } else third_playerName_field.setText("");
        if(fourthPlayer != null && !fourthPlayer.isEmpty()) {
            fourth_playerName_field.setText(fourthPlayer);
            playersList.add(fourthPlayer);
            if(DatabaseHandler.checkIncludeGamer(NowUser, fourthPlayer) == 0) {
                DatabaseHandler.addGamersInOneAcc(NowUser,fourthPlayer);

            }

        } else fourth_playerName_field.setText("");
        if(fifthPlayer != null && !fifthPlayer.isEmpty()) {
            fifth_playerName_field.setText(fifthPlayer);
            playersList.add(fifthPlayer);
            if(DatabaseHandler.checkIncludeGamer(NowUser, fifthPlayer) == 0) {
                DatabaseHandler.addGamersInOneAcc(NowUser,fifthPlayer);

            }

        } else fifth_playerName_field.setText("");


        // кнопка возвращения на главный экран
        ReturnLobbyButton.setOnAction(actionEvent -> {
            ReturnLobbyButton.getScene().getWindow().hide();
            WindowManager.showHelloView();
        });


        checkAnswerButton.setOnAction(event -> {
            // Получаем значение из поля fullAnswer_field
            String userAnswer = fullAnswer_field.getText().trim();

            // Проверка на пустоту
            if (userAnswer.isEmpty()) {
                // Если поле пустое, выводим сообщение в system_field
                system_field.setText("Введите слово целиком!");
            } else {
                // Получаем правильный ответ из базы данных
                String correctAnswer = databaseHandler.getAnswerByQuest(quest_field.getText());

                // Сравниваем введенный ответ с правильным
                if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                    // Если ответ правильный, выводим сообщение о победе и делаем кнопку repeatGameButton видимой
                    String currentPlayer = playersList.get(currentPlayerIndex);
                    system_field.setText("Победил " + currentPlayer + ", поздравляем!");
                    rightAnswer_field.setText(correctAnswer);
                    gameWon.set(true);
                    repeatGameButton.setVisible(true);
                    look_score_button.setVisible(true);
                } else {
                    // Если ответ неверный, выводим сообщение в system_field
                    system_field.setText("Неверный ответ!");
                    wordEntered = true;
                    //letterChosen = true;
                    return;

                }
            }
        });

        repeatGameButton.setOnAction(event -> {
            // Закрываем текущее окно
            Stage currentStage = (Stage) repeatGameButton.getScene().getWindow();
            currentStage.close();

            // Открываем новое окно
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/game/gamePoleChudes.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        });


        look_score_button.setOnAction(event -> {

            score_table.setVisible(true);
            // Очищаем старые данные
            score_table.getItems().clear();

            try {
                ObservableList<PlayerScore> playerScores = databaseHandler.getPlayerScores(NowUser);
// Получение данных из базы данных


// Вывод данных в консоль
                for (PlayerScore playerScore : playerScores) {
                    System.out.println("Player Name: " + playerScore.getPlayerName());
                    System.out.println("Last Score: " + playerScore.getLastScore());
                    System.out.println("Best Score: " + playerScore.getBestScore());
                    System.out.println();
                }
                // Заполняем таблицу новыми данными
                score_table.setItems(playerScores);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    // Метод для вращения барабана
    private void rotateBaraban() {
        letterChosen = false;
        if (gameWon.get()) {
            system_field.setText("Игра закончена, нельзя крутить барабан!");
            // Если игра уже выиграна, не запускаем новую анимацию
            return;
        } else  if (!canSpin) {
            // Если нельзя крутить барабан, выход из метода
            return;
        }
        login_field.setText(NowLogInUser.getLoggedInUsername());
        String NowUser =  NowLogInUser.getLoggedInUsername();
        Random random = new Random();

        // Если timeline уже был создан и он еще выполняется, не создаем новую анимацию
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            return;
        }

        // Если timeline уже был создан, остановим его
        if (timeline != null) {
            timeline.stop();
        }

        // Инициализируем timeline
        timeline = new Timeline();

        // Установим начальный угол вращения
        double startAngle = baraban_img.getRotate();

        // Определение случайного количества циклов вращения
        int rotationCycles = random.nextInt(MAX_ROTATION_CYCLES - MIN_ROTATION_CYCLES + 1) + MIN_ROTATION_CYCLES;

        // Добавим KeyFrames для каждого кадра анимации
        for (int i = 0; i < rotationCycles; i++) {
            // Определение случайного значения для результата вращения

            // Добавление кадра анимации
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(ROTATION_DURATION * (i + 1)),
                    new KeyValue(baraban_img.rotateProperty(), startAngle - 30 * (i + 1), Interpolator.LINEAR)
            );

            // Добавление KeyFrame в timeline
            timeline.getKeyFrames().add(keyFrame);
        }

        // Определение случайного угла для остановки
        int stopAngle = random.nextInt(360);

        // Добавление кадра анимации для остановки
        KeyFrame stopKeyFrame = new KeyFrame(
                Duration.millis(ROTATION_DURATION * rotationCycles),
                new KeyValue(baraban_img.rotateProperty(), stopAngle, Interpolator.LINEAR)
        );

        // Добавление KeyFrame для остановки в timeline
        timeline.getKeyFrames().add(stopKeyFrame);

        // Обработчик события завершения анимации
        timeline.setOnFinished(event -> {
            int sector = getSector(baraban_img.getRotate() % 360);
            System.out.println("Sectror: " + sector);

            // Обновление счета для текущего игрока
            String currentPlayer = playersList.get(currentPlayerIndex);

            try {
                handleRotationResult(sector, currentPlayer, NowUser);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Вывод информации о текущем игроке (опционально)
            System.out.println("Current Player: " + currentPlayer);
        });

        canSpin = false;
        canletterChosen = true;
        // Устанавливаем параметры анимации
        timeline.setCycleCount(1);
        timeline.play();

    }
    private void handleRotationResult(int sector, String player, String nowUser) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();  // Создайте экземпляр

        if (sector == 1) {

            system_field.setText("У " + player + " возможность покрутить барабан еще раз! Или лучше буква?");
            baraban_img.setOnMouseClicked(event -> {
                rotateBaraban();
            });

        } else if (sector == 2) {
            databaseHandler.updateScore(nowUser, player, 100);
            system_field.setText(player + " получил 100 очков!");
            chooseField(nowUser,currentPlayerIndex);

        } else if (sector == 3) {
            databaseHandler.updateScore(nowUser, player, databaseHandler.getLastScore(nowUser,player) * 2);
            system_field.setText(player + " удвоил свои очки!");
            chooseField(nowUser,currentPlayerIndex);

        } else if (sector == 4) {
            databaseHandler.updateScore(nowUser,player, 50);
            system_field.setText(player + " получил 50 очков!");
            chooseField(nowUser,currentPlayerIndex);

        } else if (sector == 5) {
            databaseHandler.bankruptScore(nowUser, player);
            system_field.setText(player + " банкрот, о нет! Ход следующего!");
            chooseField(nowUser, currentPlayerIndex);
            canSpin = true;
            canletterChosen = false;
            currentPlayerIndex = (currentPlayerIndex + 1) % playersList.size();
            System.out.println("TUTOCHKI_BANCROT " + playersList.get(currentPlayerIndex));

            String curPlayer = playersList.get(currentPlayerIndex);
            yourTurn_field.setText(curPlayer);
            canSpin = true;
            canletterChosen = false;
            System.out.println("Code after setText is executed" + playersList.get(currentPlayerIndex));
        }else if (sector == 6) {
            databaseHandler.updateScore(nowUser, player, 350);
            system_field.setText(player + " получил 350 очков, поздравляем!");
            chooseField(nowUser,currentPlayerIndex);

        } else if (sector == 7) {
            databaseHandler.updateScore(nowUser, player, 0);
            system_field.setText(player + " ничего не получает, ход переходит следующему игроку!");
            chooseField(nowUser, currentPlayerIndex);
            canSpin = true;
            canletterChosen = false;
            currentPlayerIndex = (currentPlayerIndex + 1) % playersList.size();
            System.out.println("TUTOCHKI" + playersList.get(currentPlayerIndex));

            String curPlayer = playersList.get(currentPlayerIndex);
            yourTurn_field.setText(curPlayer);
            canSpin = true;
            canletterChosen = false;

            System.out.println("Code after setText is executed" + playersList.get(currentPlayerIndex));
        } else if (sector == 8) {
            databaseHandler.updateScore(nowUser, player, 300);
            system_field.setText(player + " получил 300 очков, поздравляем!");
            chooseField(nowUser,currentPlayerIndex);

        } else if (sector == 9) {
            databaseHandler.updateScore(nowUser, player, 250);
            system_field.setText(player + " получил 250 очков!");
            chooseField(nowUser,currentPlayerIndex);

        } else if (sector == 10) {
            databaseHandler.updateScore(nowUser, player, 500);
            system_field.setText("Не может быть, " + player + " получил 500 очков!");
            chooseField(nowUser,currentPlayerIndex);

        } else if (sector == 11) {
            databaseHandler.updateScore(nowUser, player, 200);
            system_field.setText(player + " получил 200 очков!");
            chooseField(nowUser,currentPlayerIndex);

        } else if (sector == 0) {
            databaseHandler.updateScore(nowUser, player, 150);
            system_field.setText(player + " получил 150 очков!");
            chooseField(nowUser,currentPlayerIndex);

        }


    }
    private int getSector(double angle) {
        // Приведем угол к положительному значению в пределах от 0 до 360 градусов
        angle = (angle + 360) % 360;

        // Определяем номер сектора (0-11)
        int sector = (int) (angle / 30);

        return sector;
    }

    private void chooseField (String nowUser, int CurrPlayerIndex) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();  // Создайте экземпляр
        String currentPlayer = playersList.get(CurrPlayerIndex);

        int last_score = databaseHandler.getLastScore(nowUser, currentPlayer);

        Integer maxScore = databaseHandler.getMaxScore(nowUser, currentPlayer);

        if (maxScore == null) {
            databaseHandler.insertMaxScore(nowUser, currentPlayer, last_score);
        }
        if (last_score >= maxScore) {
            databaseHandler.updateMaxScore(nowUser, currentPlayer, last_score);
        }
        if(CurrPlayerIndex == 0) {
            first_playerScore_field.setText(String.valueOf(databaseHandler.getLastScore(nowUser, currentPlayer)));
        } else if(CurrPlayerIndex == 1) {
            second_playerScore_field.setText(String.valueOf(databaseHandler.getLastScore(nowUser, currentPlayer)));
        }else if(CurrPlayerIndex == 2) {
            third_playerScore_field.setText(String.valueOf(databaseHandler.getLastScore(nowUser, currentPlayer)));
        }else if(CurrPlayerIndex == 3) {
            fourth_playerScore_field.setText(String.valueOf(databaseHandler.getLastScore(nowUser, currentPlayer)));
        }else if(CurrPlayerIndex == 4) {
            fifth_playerScore_field.setText(String.valueOf(databaseHandler.getLastScore(nowUser, currentPlayer)));
        }

    }

    public void displayEmptyAnswer(String nowAnswer) {
        // Заменяем только буквы на символы "_"
        String emptyAnswer = nowAnswer.replaceAll("[а-яА-ЯёЁ]", "_");

        rightAnswer_field.setText(emptyAnswer);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (gameWon.get()) {
            // Если игра уже выиграна, не обрабатываем нажатия на кнопки
            return;
        }

        if (!canletterChosen) {
            // Если буква уже была выбрана, выводим сообщение и не обрабатываем нажатие
            system_field.setText("Сначала игрок должен покрутить барабан!");
            return;
        }
        if (letterChosen) {
            // Если буква уже была выбрана, выводим сообщение и не обрабатываем нажатие
            system_field.setText("Вы уже выбрали букву! Следующий игрок должен покрутить барабан!");
            return;
        }

        // Получаем текст нажатой кнопки
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();
        System.out.println("ETO TUT: " + buttonText);
        DatabaseHandler databaseHandler = new DatabaseHandler();  // Создайте экземпляр
        // Получаем правильный ответ
        String correctAnswer = databaseHandler.getAnswerByQuest(quest_field.getText());

        // Помечаем, что буква уже выбрана
       // letterChosen = true;

        // Проверяем, содержится ли буква в правильном ответе
        if (correctAnswer.toLowerCase().contains(buttonText.toLowerCase())) {
            // Заменяем "_" на букву в rightAnswer_field
            updateRightAnswerField(buttonText, correctAnswer);
            // Делаем кнопку зеленой
            clickedButton.setStyle("-fx-background-color: green;");
            letterChosen = true;
            // Буква была выбрана

            // Разрешаем крутить барабан
            canSpin = true;
        } else {
            // Выводим сообщение об ошибке в system_field
            system_field.setText("Вы не угадали! Переход хода!");
            currentPlayerIndex = (currentPlayerIndex + 1) % playersList.size();
            yourTurn_field.setText(playersList.get(currentPlayerIndex));
            // Делаем кнопку темно-серой
            clickedButton.setStyle("-fx-background-color: darkgray;");
            letterChosen = true;
            canSpin = true;
        }

    }

    private void updateRightAnswerField(String guessedLetter, String correctAnswer) {
        StringBuilder updatedAnswer = new StringBuilder(rightAnswer_field.getText());
        int currentPosition = 0;

        for (int i = 0; i < correctAnswer.length(); i++) {
            char currentChar = correctAnswer.charAt(i);
            char letter = guessedLetter.charAt(0);

            if (Character.toLowerCase(letter) == Character.toLowerCase(currentChar)) {
                updatedAnswer.setCharAt(currentPosition, currentChar);
            }

            currentPosition++;
        }

        rightAnswer_field.setText(updatedAnswer.toString());

        // Проверка, все ли буквы открыты
        if (updatedAnswer.indexOf("_") == -1) {
            String currentPlayer = playersList.get(currentPlayerIndex);
            system_field.setText("Победил " + currentPlayer + ", поздравляем!");
            gameWon.set(true);
            repeatGameButton.setVisible(true);
            look_score_button.setVisible(true);
        }
    }
    private void updateTableGamers() {
        // Получаем данные из БД и обновляем таблицу
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ObservableList<PlayerScore> gamerList = FXCollections.observableArrayList();

            ResultSet gamerResultSet = databaseHandler.getAllGamers();
            while (gamerResultSet.next()) {
                String playerName = gamerResultSet.getString(Const.NICKNAME);
                int lastScore = gamerResultSet.getInt(Const.LAST_SCORE);
                int bestScore = gamerResultSet.getInt(Const.MAX_SCORE);

                gamerList.add(new PlayerScore(playerName, lastScore, bestScore));
            }

            score_table.setItems(gamerList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
