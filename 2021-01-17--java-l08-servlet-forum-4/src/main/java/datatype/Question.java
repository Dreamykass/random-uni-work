package datatype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question implements Serializable, Comparable<Question> {
    public long id = -1;
    public List<Answer> answers = new ArrayList<>();
    public List<Rating> ratings = new ArrayList<>();
    public String questionBody;
    public String authorLogin;
    public Date dateOfCreation;

    public Question() {
    }

    @Override
    public int hashCode() {
        return (int) dateOfCreation.getTime();
    }

    @Override
    public int compareTo(Question o) {
        return Long.compare(this.dateOfCreation.getTime(), o.dateOfCreation.getTime());
    }
}
