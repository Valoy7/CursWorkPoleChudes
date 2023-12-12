//package DB;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class SelectData {
//    public static void selectAllQuestionsAndAnswers() {
//        String sql = "SELECT * FROM test.quest_answercurs";
//
//        try (Connection connection = DatabaseConnector.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                String quest = resultSet.getString("quest");
//                String answer = resultSet.getString("answer");
//                String complexity = resultSet.getString("complexity");
//                String category = resultSet.getString("category");
//                System.out.println("Question: " + quest + ", Answer: " + answer +
//                        ", complexity: " + complexity+ ", category: " + category);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
