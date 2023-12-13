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

    public void insertQuest(String quest, String answer, String complexity, String category) {
        String sql = "INSERT INTO quest_answercurs (quest, answer, complexity, category) VALUES (?, ?, ?, ?)";

        try (Connection connection = getDbConnection();  // вызываем не через класс, а через текущий объект
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, quest);
            statement.setString(2, answer);
            statement.setString(3, complexity);
            statement.setString(4, category);
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

}