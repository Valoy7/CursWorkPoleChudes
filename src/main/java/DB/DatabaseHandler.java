package DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler extends Configs {

    Connection dbConnection;


    public Connection getDbConnection() throws ClassNotFoundException, SQLException {


        // Остальной код
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
       // System.out.println("Connecting with user: " + dbUser + ", password: " + dbPass);

        // Вызываем getConnection с использованием полученных значений
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    //запись в таблицу users2
    public  void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_NAME + "," + Const.USER_PASS + ")" +
                "VALUE(?, ?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());
            //Закидываем в БД
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
// получаем данные из БД. ResultSet будет массив из данных пользователя
    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USER_NAME + "=? AND " + Const.USER_PASS + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());
            //Получаем из БД
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return  resSet;
    }

    public void insertQuest(String quest, String answer, String idcomplexity, String idcategory) {
        String sql = "INSERT INTO quest_answercurs (quest, answer, idcomplexity, idcategory) VALUES (?, ?, ?, ?)";

        try (Connection connection = getDbConnection();  // вызываем не через класс, а через текущий объект
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, quest);
            statement.setString(2, answer);
            statement.setString(3, idcomplexity);
            statement.setString(4, idcategory);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllCategories() throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM category";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        return prSt.executeQuery();
    }

    public ResultSet getAllComplexities() throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM complexity";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        return prSt.executeQuery();
    }

    public ResultSet getCategoryIdByName(String categoryName) throws SQLException, ClassNotFoundException {
        String select = "SELECT idcategory FROM category WHERE category_name=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, categoryName);
        return prSt.executeQuery();
    }

    public ResultSet getComplexityIdByName(String complexityName) throws SQLException, ClassNotFoundException {
        String select = "SELECT idcomplexity FROM complexity WHERE complexity_name=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, complexityName);
        return prSt.executeQuery();
    }

    // Получить все квесты
    public ResultSet getAllQuests() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM quest_answercurs";
        PreparedStatement prSt = getDbConnection().prepareStatement(query);
        return prSt.executeQuery();
    }

    // Получить название категории по её ID
    public String getCategoryNameById(int categoryId) throws SQLException, ClassNotFoundException {
        String query = "SELECT category_name FROM category WHERE idcategory = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(query);
        prSt.setInt(1, categoryId);
        ResultSet resultSet = prSt.executeQuery();
        return resultSet.next() ? resultSet.getString("category_name") : null;
    }

    // Получить название сложности по её ID
    public String getComplexityNameById(int complexityId) throws SQLException, ClassNotFoundException {
        String query = "SELECT complexity_name FROM complexity WHERE idcomplexity = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(query);
        prSt.setInt(1, complexityId);
        ResultSet resultSet = prSt.executeQuery();
        return resultSet.next() ? resultSet.getString("complexity_name") : null;
    }

    public void insertCategory(String categoryName) {
        String sql = "INSERT INTO category (category_name) VALUES (?)";

        try (Connection connection = getDbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, categoryName);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertComplexity(String complexityName) {
        String sql = "INSERT INTO complexity (complexity_name) VALUES (?)";

        try (Connection connection = getDbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, complexityName);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteQuest(Quest quest) {
        String deleteQuery = "DELETE FROM quest_answercurs WHERE quest = ? AND answer = ? AND idcategory = ? AND idcomplexity = ?";

        try (Connection connection = getDbConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setString(1, quest.getQuest());
            statement.setString(2, quest.getAnswer());
            statement.setInt(3, getIdcategory(quest.setIdcategory()));
            statement.setInt(4, getComplexityId(quest.getIdcomplexity()));
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getIdcategory(String categoryName) {
        int categoryId = -1; // Инициализируем значение по умолчанию

        String select = "SELECT idcategory FROM category WHERE category_name=?";
        try (Connection connection = getDbConnection();
             PreparedStatement prSt = connection.prepareStatement(select)) {

            prSt.setString(1, categoryName);
            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                categoryId = resultSet.getInt("idcategory");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return categoryId;
    }

    private int getComplexityId(String complexityName) {
        int complexityId = -1; // Инициализируем значение по умолчанию

        String select = "SELECT idcomplexity FROM complexity WHERE complexity_name=?";
        try (Connection connection = getDbConnection();
             PreparedStatement prSt = connection.prepareStatement(select)) {

            prSt.setString(1, complexityName);
            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                complexityId = resultSet.getInt("idcomplexity");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return complexityId;
    }

    public String getCategoryName(int categoryId) {
        String categoryName = null;

        String select = "SELECT category_name FROM category WHERE idcategory=?";
        try (Connection connection = getDbConnection();
             PreparedStatement prSt = connection.prepareStatement(select)) {

            prSt.setInt(1, categoryId);
            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                categoryName = resultSet.getString("category_name");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return categoryName;
    }

    public String getComplexityName(int complexityId) {
        String complexityName = null;

        String select = "SELECT complexity_name FROM complexity WHERE idcomplexity=?";
        try (Connection connection = getDbConnection();
             PreparedStatement prSt = connection.prepareStatement(select)) {

            prSt.setInt(1, complexityId);
            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                complexityName = resultSet.getString("complexity_name");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return complexityName;
    }

    // Получить всех пользователей
    public ResultSet getAllUsers() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.USER_TABLE;
        PreparedStatement prSt = getDbConnection().prepareStatement(query);
        return prSt.executeQuery();
    }

    // Удалить пользователя
    public void deleteUser(User selectedUser) {
        String deleteQuery = "DELETE FROM " + Const.USER_TABLE + " WHERE " +
                Const.USER_NAME + " = ? AND " + Const.USER_PASS + " = ?";

        try (Connection connection = getDbConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setString(1, selectedUser.getUsername());
            statement.setString(2, selectedUser.getPassword());
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(String category_name) {
        String deleteQuery = "DELETE FROM " + Const.CATEGORY_TABLE + " WHERE " +
                Const.CATEGORY_NAME + " = ? ";

        try (Connection connection = getDbConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            //String Category = Const.CATEGORY_NAME;
            statement.setString(1, category_name);

            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getCategoryByName(String categoryName) throws SQLException, ClassNotFoundException {
        String selectQuery = "SELECT * FROM " + Const.CATEGORY_TABLE + " WHERE " + Const.CATEGORY_NAME + "=?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(selectQuery);
        preparedStatement.setString(1, categoryName);
        return preparedStatement.executeQuery();
    }

    public void deleteComplexity(String selectedComplexity) {
        String deleteQuery = "DELETE FROM " + Const.COMPLEXITY_TABLE + " WHERE " +
                Const.COMPLEXITY_NAME + " = ? ";

        try (Connection connection = getDbConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            //String Category = Const.CATEGORY_NAME;
            statement.setString(1, selectedComplexity);

            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getRandomQuestion(String complexity) {
        int idComplexity = getComplexityId(complexity);

        String randomQuestionQuery = "SELECT " + Const.QUEST_TEXT + " FROM " + Const.QUEST_TABLE +
                " WHERE " + Const.QUEST_COMPLEXITY_ID + " = ? ORDER BY RAND() LIMIT 1";

        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(randomQuestionQuery)) {

            preparedStatement.setInt(1, idComplexity);
            boolean executeResult = preparedStatement.execute();

            if (executeResult) {
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    if (resultSet.next()) {
                        return resultSet.getString(Const.QUEST_TEXT);
                    }
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Обработка ошибок подключения к базе данных
        }

        return null; // В случае ошибки вернем null или другое значение по умолчанию
    }


    public int getLastScore(String nowUser, String player) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        // Предполагаем, что getDbConnection() - это ваш метод для получения соединения с базой данных
        Connection connection = getDbConnection();
        int nowUserId = databaseHandler.getUserIdByName(nowUser);
        String selectQuery = "SELECT " + Const.LAST_SCORE + " FROM " + Const.GAMERS_TABLE + " WHERE " + Const.USERS_ID + "=? AND " + Const.NICKNAME + "=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

        preparedStatement.setInt(1, nowUserId);
        preparedStatement.setString(2, player);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Возвращаем значение из колонки LAST_SCORE
            return resultSet.getInt(Const.LAST_SCORE);
        } else {
            // В случае отсутствия данных, можно вернуть значение по умолчанию
            return 0; // Или какое-то другое значение
        }
    }


    public void updateScore(String nowUser, String player, int additionalScore) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        int currentScore = databaseHandler.getLastScore(nowUser, player);
        int newScore = currentScore + additionalScore;
        String sql = "UPDATE " + Const.GAMERS_TABLE + " SET " + Const.LAST_SCORE + " = ? WHERE " + Const.USERS_ID + " = ? AND " + Const.NICKNAME + " = ?";
        int nowUserId = databaseHandler.getUserIdByName(nowUser);
        try (Connection connection = getDbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, newScore);
            statement.setInt(2, nowUserId);
            statement.setString(3, player);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void bankruptScore(String nowUser, String player) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        int newScore = 0;
        String sql = "UPDATE " + Const.GAMERS_TABLE + " SET " + Const.LAST_SCORE + " = ? WHERE " + Const.USERS_ID + " = ? AND " + Const.NICKNAME + " = ?";

        try (Connection connection = getDbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int nowUserId = databaseHandler.getUserIdByName(nowUser);
            statement.setInt(1, newScore);
            statement.setInt(2, nowUserId);
            statement.setString(3, player);  // Исправлено: установите значение для третьего параметра
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Обработайте ошибку в соответствии с вашими требованиями.
        }
    }

    public static void addGamersInOneAcc(String nowUser, String player) {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String sql = "INSERT INTO " + Const.GAMERS_TABLE + " (" + Const.USERS_ID + ", " + Const.NICKNAME + ") VALUES (?, ?)";

        try (Connection connection = databaseHandler.getDbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int nowUserId = databaseHandler.getUserIdByName(nowUser);

            statement.setInt(1, nowUserId);
            statement.setString(2, player);

            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public int getUserIdByName(String selectedUser) throws SQLException, ClassNotFoundException {

        String select = "SELECT " + Const.USERS_ID + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_NAME + "=?";
        try (PreparedStatement prSt = getDbConnection().prepareStatement(select)) {
            prSt.setString(1, selectedUser);
            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(Const.USERS_ID);
            } else {
                return 0; // Верните значение по умолчанию или бросьте исключение, в зависимости от вашей логики
            }
        }
    }
    public static int checkIncludeGamer(String nowUser, String player) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String sql = "SELECT COUNT(*) FROM " + Const.GAMERS_TABLE +
                " WHERE " + Const.USERS_ID + " = ? AND " + Const.NICKNAME + " = ?";

        try (Connection connection = databaseHandler.getDbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int nowUserId = databaseHandler.getUserIdByName(nowUser);

            statement.setInt(1, nowUserId);
            statement.setString(2, player);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0 ? 1 : 0;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0; // В случае ошибки или если не удалось выполнить проверку
    }

    public String getAnswerByQuest(String nowQuest) {
        String answerText = null;

        // Предполагаем, что getDbConnection() - это ваш метод для получения соединения с базой данных
        try (Connection connection = getDbConnection()) {
            String selectQuery = "SELECT " + Const.ANSWER_TEXT + " FROM " + Const.QUEST_TABLE + " WHERE " + Const.QUEST_TEXT + "=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, nowQuest);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Получаем значение из колонки ANSWER_TEXT
                        answerText = resultSet.getString(Const.ANSWER_TEXT);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Обработка ошибок, если необходимо
        }

        return answerText;
    }

    public Integer  getMaxScore(String nowUser, String currentPlayer) {
        Integer  maxScore = 0;

        DatabaseHandler databaseHandler = new DatabaseHandler();

        try (Connection connection = databaseHandler.getDbConnection()) {
            int nowUserId = databaseHandler.getUserIdByName(nowUser);
            String query = "SELECT "+ Const.MAX_SCORE +" FROM " + Const.GAMERS_TABLE + " WHERE " +
                    Const.NICKNAME + " = ? AND " + Const.USERS_ID + " = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, currentPlayer);
                preparedStatement.setInt(2, nowUserId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    maxScore = resultSet.getInt(Const.MAX_SCORE);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return maxScore;
    }

    public void updateMaxScore(String nowUser, String currentPlayer, int currentScore) {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        try {
            int nowUserId = databaseHandler.getUserIdByName(nowUser);

            try (Connection connection = databaseHandler.getDbConnection()) {
                // Проверяем текущий MAX_SCORE
                int maxScore = getMaxScore(nowUser, currentPlayer);

                // Если текущий счет больше текущего MAX_SCORE, обновляем MAX_SCORE
                if (currentScore > maxScore) {
                    String updateQuery = "UPDATE " + Const.GAMERS_TABLE + " SET " +
                            Const.MAX_SCORE + " = ? WHERE " +
                            Const.NICKNAME + " = ? AND " + Const.USERS_ID + " = ?";

                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        preparedStatement.setInt(1, currentScore);
                        preparedStatement.setString(2, currentPlayer);
                        preparedStatement.setInt(3, nowUserId);
                        preparedStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertMaxScore(String nowUser, String currentPlayer, int currentScore) {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        try (Connection connection = databaseHandler.getDbConnection()) {
            int nowUserId = databaseHandler.getUserIdByName(nowUser);

            String insertQuery = "INSERT INTO " + Const.GAMERS_TABLE + " (" +
                    Const.USERS_ID + ", " + Const.NICKNAME + ", " + Const.MAX_SCORE + ") " +
                    "VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, nowUserId);
                preparedStatement.setString(2, currentPlayer);
                preparedStatement.setInt(3, currentScore);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<PlayerScore> getPlayerScores(String nowUser) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getDbConnection();

        String selectQuery = "SELECT " + Const.NICKNAME + ", " + Const.LAST_SCORE + ", " + Const.MAX_SCORE +
                " FROM " + Const.GAMERS_TABLE + " WHERE " + Const.USERS_ID + "= ?";

        int nowUserId = databaseHandler.getUserIdByName(nowUser);

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, nowUserId);

            ResultSet resultSet = preparedStatement.executeQuery();

            ObservableList<PlayerScore> playerScores = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String playerName = resultSet.getString(Const.NICKNAME);
                int lastScore = resultSet.getInt(Const.LAST_SCORE);
                int bestScore = resultSet.getInt(Const.MAX_SCORE);

                PlayerScore playerScore = new PlayerScore(playerName, lastScore, bestScore);
                playerScores.add(playerScore);
            }

            return playerScores;
        }
    }


    public ResultSet getAllGamers() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM "+ Const.GAMERS_TABLE;
        PreparedStatement prSt = getDbConnection().prepareStatement(query);
        return prSt.executeQuery();
    }


    public void resetLastScore(String player, String nowUser) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getDbConnection();


        PreparedStatement preparedStatement = null;
        int nowUserId = databaseHandler.getUserIdByName(nowUser);
        try {
            // Обновление значения LAST_SCORE в таблице GAMERS_TABLE
            String updateQuery = "UPDATE " + Const.GAMERS_TABLE + " SET " +
                    Const.LAST_SCORE + " = 0 " +
                    "WHERE " + Const.NICKNAME + " = ? AND " +
                    Const.USERS_ID + " = ?";

            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, player);
            preparedStatement.setInt(2, nowUserId);

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


}