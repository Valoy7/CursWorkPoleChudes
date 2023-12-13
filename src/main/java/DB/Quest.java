package DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    public String getAnswer() {
        return answer.get();
    }

    public void setQuest(String quest) {
        this.quest.set(quest);
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }

    public String getIdcategory() {
        return idcategory.get();
    }

    public StringProperty idcategoryProperty() {
        return idcategory;
    }

    public String setIdcategory() {
        return idcategory.get();
        //this.idcategory.set(idcategory);
    }

    public String getIdcomplexity() {
        return idcomplexity.get();
    }

    public StringProperty idcomplexityProperty() {
        return idcomplexity;
    }

    public void setIdcomplexity(String idcomplexity) {
        this.idcomplexity.set(idcomplexity);
    }



// Аналогично для других свойств
}
