package help;

import javax.servlet.GenericServlet;

public class QuestionsHelp {
    public static String mainPageView(GenericServlet servlet) {
        StringBuilder str = new StringBuilder();
        int visiblePosts = Integer.parseInt(servlet.getInitParameter("default_visible_posts"));

        str.append("<div class=\"card\">");
        str.append("<h2>TITLE HEADING</h2>");
        str.append("<h5>Title description, Sep 2, 2017</h5>");
        str.append("<p>Some text..</p>");

        return str.toString();
    }
}
