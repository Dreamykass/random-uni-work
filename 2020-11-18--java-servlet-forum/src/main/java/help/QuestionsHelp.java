package help;

import database.QuestionDatabase;
import datatype.Question;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class QuestionsHelp {
    public static String mainPageView(ServletConfig config) {
        StringBuilder str = new StringBuilder();
        int visiblePosts = Integer.parseInt(config.getServletContext().getInitParameter("default_visible_posts"));

        List<Question> questions = QuestionDatabase.getAllQuestions();

        if (questions != null) {
            for (Question qq : questions) {

                str.append("<div class=\"card\">");

                str.append("<h2>").append(qq.questionBody).append("</h2>");

                str.append("<h5>Date of creation: ").append(qq.dateOfCreation).append("</h5>");

                str.append("<h5>Number of answers: ").append(qq.answers.size()).append("</h5>");

                str.append("</div>");


                if (visiblePosts <= 0)
                    break;
                visiblePosts--;
            }
        }

        return str.toString();
    }

    public static String newQuestionForm(HttpServletRequest request) {
        StringBuilder str = new StringBuilder();

        if (!Session.getLoggedInUsernameOrGuest(request).equals("guest")) {
            str.append("<form action='post_adding.jsp' method='post'>");

            str.append("<div class=\"card\">");
            str.append("<h2>Add new question:</h2><br>");
            str.append("<label for='question_body'>Question body</label><br>");
//            str.append("<input type='text' id='question_body' name='question_body'><br>");
            str.append("<textarea name=\"question_body\" id='question_body' cols=\"40\" rows=\"5\"></textarea>\n");
            str.append("<input type='submit' value='Submit'>");
            str.append("</div>");

            str.append("</form>");
        }

        return str.toString();
    }
}
