package help;

import database.QuestionDatabase;
import datatype.Question;

import javax.servlet.ServletConfig;
import java.util.List;

public class QuestionsHelp {
    public static String mainPageView(ServletConfig config) throws NoSuchFieldException {
        StringBuilder str = new StringBuilder();
        int visiblePosts = Integer.parseInt(config.getServletContext().getInitParameter("default_visible_posts"));

//        str.append("<div class=\"card\">");
//        str.append("<h2>TITLE HEADING</h2>");
//        str.append("<h5>Title description, Sep 2, 2017</h5>");
//        str.append("<p>Some text..</p>");
//        str.append("</div>");

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

//        for (int i = 0; i < 12; i++) {
//            Random rr = new Random();
//            Question qq = new Question();
//            qq.authorLogin = "test";
//            qq.dateOfCreation = new Date();
//            qq.questionBody = "test";
//            qq.answers = new ArrayList<>();
//            if (!QuestionDatabase.insertQuestion(qq))
//                throw new NoSuchFieldException("");
//            str.append("a");
//        }

        str.append("wtf");

        return str.toString();
    }
}
