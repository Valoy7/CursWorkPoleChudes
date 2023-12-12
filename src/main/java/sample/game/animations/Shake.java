package sample.game.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;
    // Node -- любой объект на экране
    public  Shake(Node node) {
        // в качестве значения передаем как долго будет длиться анимация
        // и сам объект, который нужно анимировать
        tt = new TranslateTransition(Duration.millis(70), node);
        //какое сейчас отклонение от Х
        tt.setFromX(0f);
        //насколько он передвинется относительно позиции сейчас на другую позицию
        tt.setByX(10f);
        //сколько раз будет повторяться
        tt.setCycleCount(3);
        // чтобы когда перетаскивали куда-то, восстанавливал обратно
        tt.setAutoReverse(true);
    }

    public void playAnim() {
        //чтобы анимация проигрывалась
        tt.playFromStart();
    }
}
