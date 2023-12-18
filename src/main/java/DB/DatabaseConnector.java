package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private String dbHost;
    private String dbPort;
    private String dbName;
    private String dbUser;
    private String dbPass;

    public DatabaseConnector(String dbHost, String dbPort, String dbName, String dbUser, String dbPass) {
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public boolean isDbConnected() {
        try {

            String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Попытка подключения
            try (Connection connection = DriverManager.getConnection(connectionString, dbUser, dbPass)) {
                return true; // Подключение успешно
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Обработка ошибок
            e.printStackTrace(); // или логирование ошибки
            return false; // Подключение не удалось
        }
    }
}