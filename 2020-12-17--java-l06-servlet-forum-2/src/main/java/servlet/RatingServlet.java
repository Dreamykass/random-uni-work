package servlet;

import database.QuestionDatabase;
import datatype.Question;
import datatype.Rating;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RatingServlet")
public class RatingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int hashcode = Integer.parseInt(request.getParameter("hashcode"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        if (rating < 1)
            rating = 1;
        if (rating > 10)
            rating = 10;

        List<Question> questions = QuestionDatabase.getAllQuestions();
        Question qq = QuestionDatabase.getQuestionFromHashcode(hashcode);

        boolean rated = false;
        for (Rating r : qq.ratings) {
            if (r.authorLogin.equals(help.Session.getLoggedInUsernameOrGuest(request))) {
                rated = true;
                r.value = rating;
            }
        }

        if (!rated)
            qq.ratings.add(new Rating(help.Session.getLoggedInUsernameOrGuest(request), rating));

        QuestionDatabase.updateQuestion(qq);

        response.sendRedirect("details.jsp?hashcode=" + qq.hashCode());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
