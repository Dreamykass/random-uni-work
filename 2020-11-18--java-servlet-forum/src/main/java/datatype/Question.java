package datatype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question implements Serializable {
    public List<Answer> answers = new ArrayList<>();
    public String questionBody;
    public String authorLogin;
    public Date dateOfCreation;

    public Question() {

    }

    public Question(List<Answer> answers, String questionBody, String authorLogin, Date dateOfCreation) {
        this.answers = answers;
        this.questionBody = questionBody;
        this.authorLogin = authorLogin;
        this.dateOfCreation = dateOfCreation;
    }


}
