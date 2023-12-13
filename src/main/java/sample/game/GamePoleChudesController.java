package sample.game;

import java.net.URL;
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
    private static final int ROTATION_DURATION = 500; // длительность вращения в миллисекундах
    private static final int ROTATION_CYCLES = 10; // количество циклов вращения
    private static final int MAX_ROTATION_CYCLES = 10;
    private static final int MIN_ROTATION_CYCLES = 3;
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


        // кнопка возвращения на главный экран
        ReturnLobbyButton.setOnAction(actionEvent -> {
            ReturnLobbyButton.getScene().getWindow().hide();
            WindowManager.showHelloView();
        });


    }
    private int result;
    // Метод для вращения барабана
    private double currentRotation = 0.0; // Добавьте это поле в ваш класс

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
            // Сохраняем конечное положение барабана
            // currentRotation = stopAngle; // Не используем более currentRotation
        });

        // Устанавливаем параметры анимации
        timeline.setCycleCount(1);
        timeline.play();
    }
    private void handleRotationResult(int result) {
        // Здесь вы можете добавить код для обработки различных результатов вращения
        // Например, изменение счета, вывод сообщений, и так далее...
        // ...
    }

}
