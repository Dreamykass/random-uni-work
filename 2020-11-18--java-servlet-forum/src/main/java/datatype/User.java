package datatype;

import java.io.Serializable;

public class User implements Serializable {
    public String login;
    public String password;
    public String email;

    public User() {

    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

}
