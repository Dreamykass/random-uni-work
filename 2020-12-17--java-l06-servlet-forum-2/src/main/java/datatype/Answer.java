package datatype;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable {
    public String answerBody;
    public String authorLogin;
    public Date dateOfCreation;

    public Answer() {
    }

}
