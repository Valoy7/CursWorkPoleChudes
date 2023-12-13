package DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Quest {
    private StringProperty quest;
    private StringProperty answer;
    private StringProperty idcategory;
    private StringProperty idcomplexity;

    public Quest(String quest, String answer, String idcategory, String idcomplexity) {
        this.quest = new SimpleStringProperty(quest);
        this.answer = new SimpleStringProperty(answer);
        this.idcategory = new SimpleStringProperty(idcategory);
        this.idcomplexity = new SimpleStringProperty(idcomplexity);
    }

    public StringProperty questProperty() {
        return quest;
    }

    public StringProperty answerProperty() {
        return answer;
    }

    public StringProperty categoryNameProperty() {
        return idcategory;
    }

    public StringProperty complexityNameProperty() {
        return idcomplexity;
    }

    // Геттеры и сеттеры для StringProperty
    public String getQuest() {
        return quest.get();
    }

    public void setQuest(String quest) {
        this.quest.set(quest);
    }

    // Аналогично для других свойств
}
