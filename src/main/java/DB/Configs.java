package DB;

public class Configs {

    protected static String dbHost = "localhost";
    protected static String dbPort = "3306";
    protected static String dbName = "test";
    protected static String dbUser;
    protected static String dbPass;

    public static void setDbUser(String dbUser) {
        Configs.dbUser = dbUser;
    }

    public static void setDbPass(String dbPass) {
        Configs.dbPass = dbPass;
    }

    public static void setDBUserAndPass(String loginText, String loginPassword) {
        setDbUser(loginText);
        setDbPass(loginPassword);

    }

    public static String getDbUser() {

        return dbUser;
    }

    public static String getDbPass() {

        return dbPass;
    }
}
