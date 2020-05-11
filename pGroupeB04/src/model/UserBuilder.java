package model;

public class UserBuilder{

    private String login, password, email, pseudo;
    private int bank;

    /**
     * set the login's user. Return a UserBuilder
     * @param login login's user
     * @return a object of UserBuilder
     */
    public UserBuilder login(String login) {
        this.login = login;
        return this;
    }

    /**
     * set the password's user. Return a UserBuilder
     * @param password password's user
     * @return a object of UserBuilder
     */
    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    /**
     * set the email's user. Return a UserBuilder
     * @param email email's user
     * @return a object of UserBuilder
     */
    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    /**
     * set the bank's user. Return a UserBuilder
     * @param bank bank's user
     * @return a object of UserBuilder
     */
    public UserBuilder bank(int bank) {
        this.bank = bank;
        return this;
    }

    /**
     * set the pseudo's user. Return a UserBuilder
     * @param pseudo pseudo's user
     * @return a object of UserBuilder
     */
    public UserBuilder pseudo(String pseudo) {
        this.pseudo = pseudo;
        return this;
    }

    /**
     * Build the UserBuilder. Return a user with parameter that
     * given before
     * @return a user
     */
    public User build() {
        return new User(login, password, email, bank, pseudo);
    }
}
