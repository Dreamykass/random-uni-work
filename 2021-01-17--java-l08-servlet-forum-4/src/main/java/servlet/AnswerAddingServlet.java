package servlet;

import database.QuestionDatabase;
import datatype.Answer;
import datatype.Question;
import help.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AnswerAddingServlet")
public class AnswerAddingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int hashcode = Integer.parseInt(request.getParameter("hashcode"));

        List<Question> questions = QuestionDatabase.getAllQuestions();
        Question qq = null;


        Answer aa = new Answer();
        aa.answerBody = request.getParameter("answer_body");
        aa.dateOfCreation = new Date();
        aa.authorLogin = Session.getLoggedInUsernameOrGuest(request);


        for (Question qqq : questions) {
            if (qqq.hashCode() == hashcode) {
                qq = qqq;
            }
        }

        qq.answers.add(aa);

        QuestionDatabase.updateQuestion(qq);
        response.sendRedirect("details.jsp?hashcode=" + qq.hashCode());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
