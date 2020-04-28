package model;

public class UserBuilder{

    private String login, password, email, pseudo;
    private int bank;

    public UserBuilder login(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder bank(int bank) {
        this.bank = bank;
        return this;
    }

    public UserBuilder pseudo(String pseudo) {
        this.pseudo = pseudo;
        return this;
    }

    public User build() {
        return new User(login, password, email, bank, pseudo);
    }
}
