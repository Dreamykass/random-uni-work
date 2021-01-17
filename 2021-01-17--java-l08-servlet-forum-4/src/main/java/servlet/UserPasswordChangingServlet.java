package servlet;

import database.UserDatabase;
import datatype.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserPasswordChangingServlet")
public class UserPasswordChangingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = database.UserDatabase.getAllUsers();
        User user = null;
        int id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("password");

        for (User u : users)
            if (u.id == id)
                user = u;

        assert user != null;
        user.password = password;

        UserDatabase.deleteUserById(user.id);
        UserDatabase.insertUserIfPossible(user);
        response.sendRedirect("admin_panel.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
