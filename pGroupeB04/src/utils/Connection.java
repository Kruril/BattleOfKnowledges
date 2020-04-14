package utils;

import application.Main;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.UserLoginSP;

public abstract class Connection {

    public static <C> void connection(TextField txtLogin, PasswordField pwfPassword, Class<C> className, Parent parent) {

        String login = className == UserLoginSP.class ? "user": "admin";
        String password = "helha";
        if(pwfPassword.getText().equals(password) && txtLogin.getText().equals(login)) {
            Main.switchScene(parent);
        }
        else{
            pwfPassword.setText("");
            txtLogin.setText("");
            txtLogin.setPromptText("Invalid");
            pwfPassword.setPromptText("Invalid");
        }
    }
}
