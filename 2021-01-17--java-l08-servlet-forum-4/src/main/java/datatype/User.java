package datatype;

import java.io.Serializable;

public class User implements Serializable {
    public int id;
    public String login;
    public String password;
    public String email;
    public String type = "user";
    public boolean blocked;
    public boolean passwordChange;

    public User() {

    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

}
