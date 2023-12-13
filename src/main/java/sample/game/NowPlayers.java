package sample.game;



public class NowPlayers {
    private static String firstPlayer;
    private static String secondPlayer;
    private static String thirdPlayer;
    private static String fourthPlayer;
    private static String fifthPlayer;

    // Конструктор по умолчанию
    public NowPlayers() {

    }

    // Геттеры и сеттеры для полей класса

    public static String getFirstPlayer() {
        return firstPlayer;
    }

    public static void setFirstPlayer(String first_Player) {
        firstPlayer = first_Player;
    }

    public static String getSecondPlayer() {
        return secondPlayer;
    }

    public static void setSecondPlayer(String second_Player) {
        secondPlayer = second_Player;
    }

    public static String getThirdPlayer() {
        return thirdPlayer;
    }

    public static void setThirdPlayer(String third_Player) {
        thirdPlayer = third_Player;
    }

    public static String getFourthPlayer() {
        return fourthPlayer;
    }

    public static void setFourthPlayer(String fourth_Player) {
        fourthPlayer = fourth_Player;
    }

    public static String getFifthPlayer() {
        return fifthPlayer;
    }

    public static void setFifthPlayer(String fifth_Player) {
        fifthPlayer = fifth_Player;
    }
}
