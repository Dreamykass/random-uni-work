package servlet;

import database.UserBase;
import datatype.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginProcessingServlet")
public class LoginProcessingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String login;
        String password;

        try {
            login = request.getParameter("login");
            password = request.getParameter("password");
        } catch (Exception ignored) {
            response.sendRedirect("error.jsp?error=exception");
            return;
        }

        List<User> users = UserBase.getAllUsers();

        for (User user : users) {
            if (user.login.equals(login)) {
                if (user.password.equals(password)) {
                    request.getSession().setAttribute("login", login);
                    response.sendRedirect("index.jsp");
                    return;
                } else {
                    response.sendRedirect("error.jsp?error=wrongpassword");
                    return;
                }
            }
        }

        response.sendRedirect("error.jsp?error=nologin");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
