package help;

import database.QuestionDatabase;
import datatype.Answer;
import datatype.Question;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class QuestionsHelp {
    public static String mainPageView(ServletConfig config, HttpServletRequest request) {
        StringBuilder str = new StringBuilder();
        int visiblePosts = Integer.parseInt(config.getServletContext().getInitParameter("default_visible_posts"));
        String query = request.getParameter("query_body");

        str.append("<form action='index.jsp' method='post'>");
        str.append("<div class=\"card\">");
        str.append("<h2>Search for question:</h2><br>");
        str.append("<label for='query_body'>Query body</label><br>");
        str.append("<textarea name=\"query_body\" id='query_body' cols=\"40\" rows=\"5\"></textarea>\n");
        str.append("<input type='submit' value='Submit'>");
        str.append("</div>");
        str.append("</form>");

        List<Question> questions = QuestionDatabase.getAllQuestions();

        if (questions != null) {
            for (Question qq : questions) {
                if (query != null && !query.isEmpty() && qq.questionBody.contains(query)) {
                    str.append("<div class=\"card\">");
                    str.append("<h2>").append(qq.questionBody).append("</h2>");
                    str.append("<h5>Date of creation: ").append(qq.dateOfCreation).append("</h5>");
                    str.append("<h5>Number of answers: ").append(qq.answers.size()).append("</h5>");
                    if (!help.Session.getLoggedInUsernameOrGuest(request).equals("guest")) {
                        str.append("<a href=\"details.jsp?hashcode=").append(qq.hashCode()).append("\">Details/Answer</a>");
                        str.append("Asked by ").append(qq.authorLogin);
                    }
                    str.append("</div>");

                    if (visiblePosts <= 0)
                        break;
                    visiblePosts--;
                } else if (query == null || query.isEmpty()) {
                    str.append("<div class=\"card\">");
                    str.append("<h2>").append(qq.questionBody).append("</h2>");
                    str.append("<h5>Date of creation: ").append(qq.dateOfCreation).append("</h5>");
                    str.append("<h5>Number of answers: ").append(qq.answers.size()).append("</h5>");
                    if (!help.Session.getLoggedInUsernameOrGuest(request).equals("guest")) {
                        str.append("<a href=\"details.jsp?hashcode=").append(qq.hashCode()).append("\">Details/Answer</a><br>");
                        str.append("Asked by ").append(qq.authorLogin);
                    }
                    str.append("</div>");


                    if (visiblePosts <= 0)
                        break;
                    visiblePosts--;
                }
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
            str.append("<textarea name=\"question_body\" id='question_body' cols=\"40\" rows=\"5\"></textarea>\n");
            str.append("<input type='submit' value='Submit'>");
            str.append("</div>");

            str.append("</form>");
        }

        return str.toString();
    }

    public static String questionDetailsView(HttpServletRequest request) {
        StringBuilder str = new StringBuilder();
        int hashcode = Integer.parseInt(request.getParameter("hashcode"));

        List<Question> questions = QuestionDatabase.getAllQuestions();
        Question question = null;

        for (Question qq : questions) {
            if (qq.hashCode() == hashcode) {
                question = qq;
            }
        }

        if (question == null) {
            str.append("question is null");

        } else {
            str.append("<div class=\"card\">");
            str.append("<h2>").append(question.questionBody).append("</h2>");
            str.append("<h5>Date of creation: ").append(question.dateOfCreation).append("</h5>");
            str.append("<h5>Number of answers: ").append(question.answers.size()).append("</h5>");
            str.append("Asked by ").append(question.authorLogin);

            {
                str.append("<hr>");
                str.append("<hr>");
                str.append("<form action='answer_adding.jsp' method='post'>");
                str.append("<div class=\"card\">");
                str.append("<h2>Add new answer:</h2><br>");
                str.append("<label for='answer_body'>Answer body</label><br>");
                str.append("<textarea name=\"answer_body\" id='answer_body' cols=\"40\" rows=\"5\"></textarea>\n");
                str.append("<input type=\"hidden\" id=\"hashcode\" name=\"hashcode\" value=\"").append(hashcode).append("\">");
                str.append("<input type='submit' value='Submit'>");
                str.append("</div>");
                str.append("</form>");
            }

            for (Answer answer : question.answers) {
                str.append("<hr>");
                str.append("<div class=\"card\">");
                str.append("<h2>").append(answer.answerBody).append("</h2>");
                str.append("<h5>Date of creation: ").append(answer.dateOfCreation).append("</h5>");
                str.append("Answered by ").append(answer.authorLogin);
                str.append("</div>");
            }

            str.append("</div>");

        }


        return str.toString();
    }
}
