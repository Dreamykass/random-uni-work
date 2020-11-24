package servlet;

import database.UserDatabase;
import datatype.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterProcessingServlet")
public class RegisterProcessingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

//        try (PrintWriter out = response.getWriter()) {
//            out.println("<html><head>");
//            out.println("<title>input</title>");
//            out.println("</head><body>");
//        }

        String login;
        String password;
        String email;

        try {
            login = request.getParameter("login");
            password = request.getParameter("password");
            email = request.getParameter("email");
        } catch (Exception ignored) {
            response.sendRedirect("error.jsp?error=exception");
            return;
        }

        User user = new User(login, password, email);

        if (UserDatabase.insertUserIfPossible(user)) {
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("error.jsp?error=fail");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
