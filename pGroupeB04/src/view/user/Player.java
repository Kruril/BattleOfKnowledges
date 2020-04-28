package view.user;

import model.User;

public class Player {

    private static User user;

    public static void setUser(User user) {
        Player.user = user;
    }

    public static User getUser() {
        return user;
    }

    public static String getName() {
        return user.getPseudo();
    }

    public static void setName(String name) {
        user.setPseudo(name);
    }
}
