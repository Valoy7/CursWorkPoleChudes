package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void insertQuest(String quest, String answer, String complexity, String category) {
        String sql = "INSERT INTO quest_answercurs (quest, answer, complexity, category) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, quest);
            statement.setString(2, answer);
            statement.setString(3, complexity);
            statement.setString(4, category);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
