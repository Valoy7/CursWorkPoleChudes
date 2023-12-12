//package DB;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.ResultSet;
//
//
//public class CheckData {
//    public static boolean checkDuplicate(String quest) {
//        String sql = "SELECT\n" +
//                "\t`quest`,\n" +
//                "\tCOUNT(`quest`) AS `count`\n" +
//                "FROM\n" +
//                "\t`quest_answercurs`\n" +
//                "WHERE\n" +
//                "\t`quest` = ?\n" +  // добавлено условие для конкретного вопроса
//                "GROUP BY\n" +
//                "\t`quest`\n" +
//                "HAVING \n" +
//                "\t`count` > 1;";
//
////        try (Connection connection = DatabaseHandler.getDbConnection();
////             PreparedStatement statement = connection.prepareStatement(sql)) {
////
////            statement.setString(1, quest);
////
////            try (ResultSet resultSet = statement.executeQuery()) {
////                return resultSet.next();  // Вернуть true, если есть результат
////            }
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////
////        return false;  // Вернуть false в случае ошибки или отсутствия повторяющихся элементов
//  //  }
//}