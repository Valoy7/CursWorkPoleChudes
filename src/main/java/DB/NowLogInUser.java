package DB;

public class NowLogInUser {
    private static String loggedInUsername;

    public static String getLoggedInUsername() {
        return loggedInUsername;
    }

    public static void setLoggedInUsername(String username) {
        loggedInUsername = username;
    }
}