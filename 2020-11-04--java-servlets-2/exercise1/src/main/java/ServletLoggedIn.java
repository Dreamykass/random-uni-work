import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ServletLoggedIn")
public class ServletLoggedIn extends HttpServlet {

    private Map<String, String> flattenMap(Map<String, String[]> map) {
        Map<String, String> flat = new HashMap<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            flat.put(entry.getKey(), entry.getValue()[0]);
        }
        return flat;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head>");
            out.println("<title>logged</title>");
            out.println("</head><body>");

            Map<String, String> params = flattenMap(request.getParameterMap());

            out.println("<h3>processing</h3>");
            if (params.containsKey("register")) {
                out.println("registering<br>");
                User user = new User();
                user.email = params.get("email");
                user.login = params.get("login");
                user.password = params.get("password");
                UserStorage.users.put(user.login, user);
                out.println("registered<br>");
            } else {
                out.println("logging in<br>");
                if (UserStorage.users.containsKey(params.get("login"))) {
                    if (UserStorage.users.get(params.get("login")).password.equals(params.get("password"))) {
                        out.println("logged in; adding login and password to cookies<br>");
                        response.addCookie(new Cookie("last_login", params.get("login")));
                        out.println("added<br>");
                    } else {
                        out.println("password wrong<br>");
                    }
                } else {
                    out.println("login wrong<br>");
                }
            }

            out.println("<br><br>");
            UserStorage.htmlWriteAllUsers(out);
            CookieService.htmlWriteCookies(out, request.getCookies());
            out.println("<br><br>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
