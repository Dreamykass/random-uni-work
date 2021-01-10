package datatype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question implements Serializable, Comparable<Question> {
    public List<Answer> answers = new ArrayList<>();
    public String questionBody;
    public String authorLogin;
    public Date dateOfCreation;
    public List<Rating> ratings = new ArrayList<>();

    public Question() {
    }

    @Override
    public int hashCode() {
        return (int) dateOfCreation.getTime();
    }

    @Override
    public int compareTo(Question o) {
//        return Integer.compare(this.hashCode(), o.hashCode());
        return Long.compare(this.dateOfCreation.getTime(), o.dateOfCreation.getTime());
    }
}
