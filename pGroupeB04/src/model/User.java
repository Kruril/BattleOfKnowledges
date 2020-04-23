package model;

import enumeration.Path;
import serialisation.Serialization;

public class User{

    private String login, password, email;
    private int bank;
    private boolean allowed;

    public User(String login, String password, String email, int bank) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.bank = bank;
        this.allowed = false;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getBank() {
        return bank;
    }

    public boolean isAllowed() {
        return allowed;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static User fromJson(String login) {
        return Serialization.readJson(Path.FILE_CONNECTION.getPath() + login, User.class);
    }

    public void toJson() {
        Serialization.writeJson(Path.FILE_CONNECTION.getPath() + login, this);
    }
}
