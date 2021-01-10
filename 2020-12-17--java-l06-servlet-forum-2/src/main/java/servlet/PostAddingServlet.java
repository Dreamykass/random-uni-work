package servlet;

import database.QuestionDatabase;
import datatype.Question;
import help.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "PostAddingServlet")
public class PostAddingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question qq = new Question();
        qq.questionBody = request.getParameter("question_body");
        qq.dateOfCreation = new Date();
        qq.authorLogin = Session.getLoggedInUsernameOrGuest(request);
        qq.answers = new ArrayList<>();
        QuestionDatabase.insertQuestion(qq);
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
