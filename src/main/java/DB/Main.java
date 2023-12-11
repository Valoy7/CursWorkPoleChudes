package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String quest = "проба";
        if (!CheckData.checkDuplicate(quest)) {
            InsertData.insertQuest("ПробаВопр", "Пробаответик", "легкая", "музыка");
            // InsertData.insertAnswer("Новый ответ");
            // DeleteData.deleteQuest("Новый вопрос");
            SelectData.selectAllQuestionsAndAnswers();
        }else {
            System.out.println("уже есть такой вопрос");
        }
    }
}

