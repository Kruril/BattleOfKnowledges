package model;

public class User{

    private String login, password, email;
    private int bank;

    public User(String login, String password, String email, int bank) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
