package utils.controler;

import model.User;
import model.UserBuilder;
import view.UserLoginSP;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.*;

public abstract class SQLManager {

    private static Connection connection;



    /**
     * connection to the remote database via a login and password.
     * The name of the class from which the method is called is used to find out which table we are going to use
     * @param login String containing the login
     * @param password String containing the password
     * @param className name of the class from which the method is called
     * @param <C> class generic
     * @return boolean true if connection succeed or false if failed
     * @throws SQLException An exception that provides information on a database access error or other errors
     */
    public static <C> boolean connectionDB(String login, String password, Class<C> className) throws SQLException {

        Statement state = connection.createStatement();

        String table = className == UserLoginSP.class ? "user" : "admin";

        ResultSet result = state.executeQuery("select * from " + table + " where login = '" + login
                + "' and password = '" + password + "'");

        return result.next();

    }

    /**
     * Create a user if it does not already exist in the database.
     * You must encode the login, password and email of the new user.
     * An email verification will be done if given
     * @param login login given by the user
     * @param password password given by the user
     * @param email email given by the user
     * @return boolean true if user was added or false if user wasn't added
     * @throws SQLException An exception that provides information on a database access error or other errors
     */
    public static boolean createUser(String login, String password, String email) throws SQLException {

        if (isValidEmailAddress(email) || email.equals("")) {
            if (checkUser(login)) {
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
        return false;
    }

    /**
     * Insertion of the new user in the database
     * @param user user that we want to add
     * @throws SQLException An exception that provides information on a database access error or other errors
     */
    private static void insertNewUser(User user) throws SQLException {

        String query = "insert into user (login, password, email, bank) values (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword() );
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setInt(4, user.getBank());

        preparedStatement.execute();
    }

    /**
     * checks if the user does not already belong to the database.
     * A user already exists if his login if found
     * @param login login of user that we will check
     * @return boolean true if user isn't in database or false if he is
     * @throws SQLException An exception that provides information on a database access error or other errors
     */
    private static boolean checkUser(String login) throws SQLException {

        Statement state = connection.createStatement();
        ResultSet result = state.executeQuery("select * from user");

        while (result.next()) {
            String loginSQL = result.getString("login");
            if (loginSQL.equals(login)) {
                return false;
            }
        }
        return true;
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

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection("jdbc:mysql://sql7.freesqldatabase.com:3306/sql7333954"
                            + "?useUnicode=true&characterEncoding=utf-8",
                    "sql7333954", "ipCXxP8p8r");
            if (connection != null) System.out.println("Connected");
            else System.out.println("Connection fail");
        }
        return connection;
    }

}
