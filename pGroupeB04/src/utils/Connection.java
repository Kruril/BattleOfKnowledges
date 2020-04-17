package utils;

import application.Main;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.AdminChoiceSP;
import view.MainPageSP;
import view.UserLoginSP;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Connection {

    private static java.sql.Connection connection;

    public static <C> boolean connection(TextField txtLogin, PasswordField pwfPassword, Class<C> className) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        connection = DriverManager.getConnection("jdbc:mysql://sql7.freesqldatabase.com:3306/sql7333954" + "?useUnicode=true&characterEncoding=utf-8",
                "sql7333954", "ipCXxP8p8r");

        if (connection != null) System.out.println("Connected");
        else System.out.println("Connection fail");
        Statement state = connection.createStatement();

        String table = className == UserLoginSP.class ? "user" : "admin";
        Parent parent = className == UserLoginSP.class ? new MainPageSP(): new AdminChoiceSP();

        ResultSet result = state.executeQuery("select * from " + table + " where login = '" + txtLogin.getText()
                + "' and password = '" + pwfPassword.getText() + "'");

        if (!result.next()) {
            pwfPassword.setText("");
            txtLogin.setText("");
            txtLogin.setPromptText("Invalid");
            pwfPassword.setPromptText("Invalid");
            return false;
        }
        String loginSql = result.getString("login");
        String passwordSql = result.getString("password");

        if (loginSql != "" && passwordSql != "") {
            Main.switchScene(parent);
            return true;
        }
        return false;
    }

}
