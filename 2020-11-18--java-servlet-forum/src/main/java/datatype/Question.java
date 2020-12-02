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

    @Override
    public int hashCode() {
        return (int) dateOfCreation.getTime();
    }


}
