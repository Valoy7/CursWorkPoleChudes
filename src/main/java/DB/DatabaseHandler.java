package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
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

}