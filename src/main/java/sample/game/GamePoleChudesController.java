package sample.game;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.text.Normalizer;

import DB.DatabaseHandler;
import DB.NowLogInUser;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

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

    private List<String> playersList = new ArrayList<>();
    private int currentPlayerIndex = 0;

    @FXML
    void initialize() {
        login_field.setText(NowLogInUser.getLoggedInUsername());
        String NowUser =  NowLogInUser.getLoggedInUsername();
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


    }

    // Метод для вращения барабана
    private void rotateBaraban() {
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

            // Переход к следующему игроку
            if(sector != 1) {
                currentPlayerIndex = (currentPlayerIndex + 1) % playersList.size();

            }
            // Вывод информации о текущем игроке (опционально)
            System.out.println("Current Player: " + currentPlayer);
        });


        // Устанавливаем параметры анимации
        timeline.setCycleCount(1);
        timeline.play();
    }
    private void handleRotationResult(int sector, String player, String nowUser) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();  // Создайте экземпляр

        if (sector == 1) {

            system_field.setText("У " + player + " прекрасная возможность покрутить барабан еще раз!");
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
            system_field.setText(player + " банкрот, о нет!");
            chooseField(nowUser,currentPlayerIndex);
        }else if (sector == 6) {
            databaseHandler.updateScore(nowUser, player, 350);
            system_field.setText(player + " получил 350 очков, поздравляем!");
            chooseField(nowUser,currentPlayerIndex);
        } else if (sector == 7) {
            databaseHandler.updateScore(nowUser, player, 0);
            system_field.setText(player + " ничего не получает, ход переходит следующему игроку!");
            chooseField(nowUser,currentPlayerIndex);
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
        System.out.println("YA TUT "+ currentPlayer);
        System.out.println("ZDEC "+ CurrPlayerIndex);
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
        // Заменяем только буквы на символы " _ "
        String emptyAnswer = nowAnswer.replaceAll("[а-яА-ЯёЁ]", "_ ");

        // Предполагаем, что rightAnswer_field - это ваш элемент интерфейса (TextField или что-то подобное)
        rightAnswer_field.setText(emptyAnswer.trim());
    }

}
