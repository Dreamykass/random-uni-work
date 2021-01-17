package datatype;

import java.io.Serializable;

public class Rating implements Serializable {
    public String authorLogin;
    public int value;

    public Rating() {
    }

    public Rating(String aut, int va) {
        authorLogin = aut;
        value = va;
    }
}
