package datatype;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable {
    public String answerBody;
    public String authorLogin;
    public Date dateOfCreation;

    public Answer() {
    }

    public Answer(String answerBody, String authorLogin, Date dateOfCreation) {
        this.answerBody = answerBody;
        this.authorLogin = authorLogin;
        this.dateOfCreation = dateOfCreation;
    }
}
