package model;

public class UserBuilder{
    private String login, password, email;

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

    public User build() {
        return new User(login, password, email);
    }
}
