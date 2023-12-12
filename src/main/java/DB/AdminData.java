package DB;

public class AdminData {
    private static final String adminLogin = "admin";
    private static final String adminPassword = "admin";

    public static boolean checkCredentials(String enteredLogin, String enteredPassword) {
        return adminLogin.equals(enteredLogin) && adminPassword.equals(enteredPassword);
    }
}