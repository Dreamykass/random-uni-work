package help;

import database.UserDatabase;
import servlet.CountUserListener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Session {
    public static String getLoggedInUsernameOrGuest(HttpServletRequest request) {
        String s = (String) request.getSession().getAttribute("login");
        if (s == null)
            return "guest";
        else
            return s;
    }

    public static String getGreeting(HttpServletRequest request) {
        StringBuilder greeting = new StringBuilder();

        String loginOrGuest = help.Session.getLoggedInUsernameOrGuest(request);

        greeting.append("<h3>Hello, ");
        greeting.append(loginOrGuest);
        greeting.append(".</h3>");

        if (loginOrGuest.equals("guest")) {
            greeting.append("<a href=\"register.jsp\">Register.</a><br>");
            greeting.append("<a href=\"singin.jsp\">Singin.</a><br>");
        } else {
            greeting.append("<a href=\"logout.jsp\">Logout.</a><br>");
        }

        return greeting.toString();
    }

    public static String getFooter(HttpServletRequest request) {
        StringBuilder greeting = new StringBuilder();

        String loginOrGuest = help.Session.getLoggedInUsernameOrGuest(request);

        greeting.append("<h3>Hello, ");
        greeting.append(loginOrGuest);
        greeting.append(".</h3>");

        if (loginOrGuest.equals("guest")) {
            greeting.append("<a href=\"register.jsp\">Register.</a><br>");
            greeting.append("<a href=\"singin.jsp\">Singin.</a><br>");
        } else {
            greeting.append("<a href=\"user.jsp?user=").append(loginOrGuest).append("\">My account.</a><br>");
            greeting.append("<a href=\"user_questions.jsp?user=").append(loginOrGuest).append("\">My questions.</a><br>");
            greeting.append("<a href=\"user_answers.jsp?user=").append(loginOrGuest).append("\">My answers.</a><br>");
        }

        greeting.append("<br><br><br>");
        greeting.append(UserDatabase.errors);

        return greeting.toString();
    }

    public static String logoutAndRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("login");
        CountUserListener.decrement(request.getServletContext(), request);
        response.sendRedirect("index.jsp");
        return "";
    }


}

