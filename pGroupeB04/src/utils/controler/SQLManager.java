package utils.controler;

import application.Main;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import model.UserBuilder;
import view.AdminChoiceSP;
import view.MainPageSP;
import view.UserLoginSP;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.*;

public abstract class SQLManager {

    private static Connection connection;

    public static <C> void connectionDB(TextField txtLogin, PasswordField pwfPassword, Class<C> className) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        getConnection();

        if (connection != null) System.out.println("Connected");
        else System.out.println("Connection fail");
        Statement state = connection.createStatement();

        String table = className == UserLoginSP.class ? "user" : "admin";
        Parent parent = className == UserLoginSP.class ? new MainPageSP() : new AdminChoiceSP();

        ResultSet result = state.executeQuery("select * from " + table + " where login = '" + txtLogin.getText()
                + "' and password = '" + pwfPassword.getText() + "'");

        if (!result.next()) {
            pwfPassword.setText("");
            txtLogin.setText("");
            txtLogin.setPromptText("Invalid");
            pwfPassword.setPromptText("Invalid");
            return;
        }

        Main.switchScene(parent);
        connection.close();

    }

    public static boolean createUser(String login, String password, String email) throws SQLException {

        if (isValidEmailAddress(email) || email.equals("")) {
            getConnection();
            if (checkUser(login)) {
                Main.switchScene(new MainPageSP());
                User user = new UserBuilder()
                        .login(login)
                        .password(password)
                        .email(email)
                        .bank(0)
                        .build();
                insertNewUser(user);
                return true;
            }
        }
        connection.close();
        return false;
    }

    private static void insertNewUser(User user) throws SQLException {
        getConnection();

        String query = "insert into user (login, password, email, bank) values (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setInt(4, user.getBank());

        preparedStatement.execute();
    }

    private static boolean checkUser(String login) throws SQLException {
        getConnection();

        Statement state = connection.createStatement();
        ResultSet result = state.executeQuery("select * from user");

        while (result.next()) {
            String loginSQL = result.getString("login");
            if (loginSQL.equals(login)) {
                connection.close();
                return false;
            }
        }
        return true;
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static void getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://sql7.freesqldatabase.com:3306/sql7333954" + "?useUnicode=true&characterEncoding=utf-8",
                    "sql7333954", "ipCXxP8p8r");
        }
    }

}
