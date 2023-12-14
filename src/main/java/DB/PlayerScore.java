package DB;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class PlayerScore {
    private final SimpleStringProperty playerName;
    private final SimpleIntegerProperty lastScore;
    private final SimpleIntegerProperty bestScore;

    public PlayerScore(String playerName, int lastScore, int bestScore) {
        this.playerName = new SimpleStringProperty(playerName);
        this.lastScore = new SimpleIntegerProperty(lastScore);
        this.bestScore = new SimpleIntegerProperty(bestScore);
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public int getLastScore() {
        return lastScore.get();
    }

    public int getBestScore() {
        return bestScore.get();
    }

    public ObservableValue<String> playerNameProperty() { return playerName;
    }

    public SimpleIntegerProperty lastScoreProperty() { return lastScore;
    }

    public SimpleIntegerProperty bestScoreProperty() {return bestScore;
    }
}