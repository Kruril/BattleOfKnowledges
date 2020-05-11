package model;

import enumeration.Path;
import serialisation.Serialization;

import java.io.Serializable;

public class User implements Serializable {

    private String login, password, email, pseudo;
    private int bank;
    private boolean allowed;

    public User(String login, String password, String email, int bank, String pseudo) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.bank = bank;
        this.allowed = false;
        this.pseudo = pseudo;
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

    public String getPseudo() {
        return pseudo;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Load a User from a json file.
     * The login must be specified
     * @param login login of user
     */
    public static User fromJson(String login) {
        return Serialization.readJson(Path.FILE_CONNECTION.getPath() + login, User.class);
    }

    /**
     * Save the desired deck to a json file.
     * The path to this file is defined via login of user
     */
    public void toJson() {
        Serialization.writeJson(Path.FILE_CONNECTION.getPath() + login, this);
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
