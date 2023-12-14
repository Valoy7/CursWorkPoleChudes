package sample.game;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

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
    private Label firts_playerName_field;

    @FXML
    private Label firts_playerScore_field;

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
    private int lastSector;
    @FXML
    void initialize() {
        login_field.setText(NowLogInUser.getLoggedInUsername());

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

        // Предположим, что quest_field - это TextField, но замените на соответствующий тип элемента интерфейса
        quest_field.setText(nowQuest);

        //NowPlayers nowPlayers = NowPlayers();
        String firstPlayer = NowPlayers.getFirstPlayer();
        String secondPlayer = NowPlayers.getSecondPlayer();
        String thirdPlayer = NowPlayers.getThirdPlayer();
        String fourthPlayer = NowPlayers.getFourthPlayer();
        String fifthPlayer = NowPlayers.getFifthPlayer();

       // System.out.println(firstPlayer);
        if(firstPlayer != null && !firstPlayer.isEmpty()) {
        firts_playerName_field.setText(firstPlayer);
            playersList.add(firstPlayer);
        int maxPlayers = 1;
        } else firts_playerName_field.setText("");
        if(secondPlayer != null && !secondPlayer.isEmpty()) {
            second_playerName_field.setText(secondPlayer);
            playersList.add(secondPlayer);
            int maxPlayers = 2;
        } else second_playerName_field.setText("");
        if(thirdPlayer != null && !thirdPlayer.isEmpty()) {
            third_playerName_field.setText(thirdPlayer);
            playersList.add(thirdPlayer);
            int maxPlayers = 3;
        } else third_playerName_field.setText("");
        if(fourthPlayer != null && !fourthPlayer.isEmpty()) {
            fourth_playerName_field.setText(fourthPlayer);
            playersList.add(fourthPlayer);
            int maxPlayers = 4;
        } else fourth_playerName_field.setText("");
        if(fifthPlayer != null && !fifthPlayer.isEmpty()) {
            fifth_playerName_field.setText(fifthPlayer);
            playersList.add(fifthPlayer);
            int maxPlayers = 5;
        } else fifth_playerName_field.setText("");


        // кнопка возвращения на главный экран
        ReturnLobbyButton.setOnAction(actionEvent -> {
            ReturnLobbyButton.getScene().getWindow().hide();
            WindowManager.showHelloView();
        });


    }

    // Метод для вращения барабана
    private void rotateBaraban() {
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
                handleRotationResult(sector, currentPlayer);
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
    private void handleRotationResult(int sector, String player) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();  // Создайте экземпляр

        if (sector == 1) {

            system_field.setText("У " + player + " прекрасная возможность покрутить барабан еще раз!");
            baraban_img.setOnMouseClicked(event -> {
                rotateBaraban();
            });
            int lastSector = 1;
        } else if (sector == 2) {
            databaseHandler.updateScore(player, 100);
            system_field.setText(player + " получил 100 очков!");
            int lastSector = 2;
        } else if (sector == 3) {
            databaseHandler.updateScore(player, databaseHandler.getLastScore(player) * 2);  // Используйте экземпляр
            system_field.setText(player + " удвоил свои очки!");
            int lastSector = 3;
        } else if (sector == 4) {
            databaseHandler.updateScore(player, 50);
            system_field.setText(player + " получил 50 очков!");
            int lastSector = 4;
        } else if (sector == 5) {
            databaseHandler.bankruptScore(player);
            system_field.setText(player + " банкрот, о нет!");
            int lastSector = 5;
        }else if (sector == 6) {
            databaseHandler.updateScore(player, 350);
            system_field.setText(player + " получил 350 очков, поздравляем!");
            int lastSector = 6;
        } else if (sector == 8) {
            databaseHandler.updateScore(player, 300);
            system_field.setText(player + " получил 300 очков, поздравляем!");
            int lastSector = 7;
        } else if (sector == 9) {
            databaseHandler.updateScore(player, 250);
            system_field.setText(player + " получил 250 очков!");
            int lastSector = 8;
        } else if (sector == 10) {
            databaseHandler.updateScore(player, 500);
            system_field.setText("Не может быть, " + player + " получил 500 очков!");
            int lastSector = 9;
        } else if (sector == 11) {
            databaseHandler.updateScore(player, 200);
            system_field.setText(player + " получил 200 очков!");
            int lastSector = 10;
        } else if (sector == 0) {
            databaseHandler.updateScore(player, 150);
            system_field.setText(player + " получил 150 очков!");
            int lastSector = 0;
        }

    }
    private int getSector(double angle) {
        // Приведем угол к положительному значению в пределах от 0 до 360 градусов
        angle = (angle + 360) % 360;

        // Определяем номер сектора (0-11)
        int sector = (int) (angle / 30);

        return sector;
    }

}
