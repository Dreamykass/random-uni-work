package servlet;

import datatype.Question;
import datatype.Rating;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class RatingTag extends SimpleTagSupport {
    public String hash;

    public void setQuestion(String hash2) {
        this.hash = hash2;
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("Rating tag!");
        out.print("<br>");
        out.print("<br>");

        out.print("Current rating: ");
        Question q = database.QuestionDatabase.getQuestionFromHashcode(Integer.parseInt(hash));
        int sum = 0;
        if (q != null) {
            for (Rating r : q.ratings)
                sum += r.value;
            if (q.ratings.size() != 0)
                out.print(sum / q.ratings.size());
        }
        out.print("<br>");
        out.print("<br>");


        out.print("<form action=\"rating.jsp\" method=\"post\">\n" +

                "  <label for=\"rating\">Rating:</label><br>\n" +
                "  <input type=\"number\" id=\"rating\" name=\"rating\" value=\"5\"><br>\n" +

                "  <input type='hidden' id='hashcode' name='hashcode' value='" + hash + "'>" +

                "  <input type=\"submit\" value=\"Submit\">\n" +
                "</form> ");
        out.print(hash);
    }
}
