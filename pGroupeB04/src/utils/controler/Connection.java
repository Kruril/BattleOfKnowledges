package utils.controler;

import model.User;
import model.UserBuilder;
import utils.utility.Cryptage;
import view.admin.AdminLoginSP;
import utils.user.Player;
import view.user.UserLoginSP;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import exceptions.UserAlreadyExist;
import exceptions.UserUnknown;

import java.util.Objects;

public abstract class Connection {

    /**
     * connection to the remote database via a login and password.
     * The name of the class from which the method is called is used to find out which table we are going to use
     * @param login String containing the login
     * @param password String containing the password
     * @param className name of the class from which the method is called
     * @param <C> class generic
     * @return boolean true if connection succeed or false if failed
     */
    public static <C> boolean connectionGame(String login, String password, Class<C> className) throws UserUnknown{
        User user = User.fromJson(login);
        if (user != null) {
            if (className == UserLoginSP.class && !user.isAllowed()) {
                Player.setUser(user);
                return Objects.equals(Cryptage.decrypt(user.getPassword()), password);
            }
            else if (className == AdminLoginSP.class && user.isAllowed()) {
                return Objects.equals(Cryptage.decrypt(user.getPassword()), password);
            }
        } else {
        	throw new UserUnknown();
        }
        return false;
    }

    /**
     * Create a user if it does not already exist in the database.
     * You must encode the login, password and email of the new user.
     * An email verification will be done if given
     * @param login login given by the user
     * @param password password given by the user
     * @param email email given by the user
     * @return boolean true if user was added or false if user wasn't added
     */
    public static boolean createUser(String login, String password, String email) throws UserAlreadyExist{

        if (isValidEmailAddress(email) || email.equals("")) {
            if (checkUser(login)) {
                User user = new UserBuilder()
                        .login(login)
                        .password(Cryptage.encrypt(password))
                        .email(email)
                        .bank(0)
                        .pseudo(login)
                        .build();
                user.toJson();
                Player.setUser(user);
                return true;
            } else {
            	throw new UserAlreadyExist();
            }
        }
        return false;
    }


    /**
     * checks if the user does not already belong to the database.
     * A user already exists if his login if found
     * @param login login of user that we will check
     * @return boolean true if user isn't in database or false if he is
     */
    private static boolean checkUser(String login){
        return User.fromJson(login) == null;
    }

    /**
     * check that an email is correctly encoded
     * @param email email that we will check
     * @return boolean true if email valid or false if not
     */
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

}
