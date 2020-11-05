import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletMultiplication")
public class ServletMultiplication extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head>");
            out.println("<title>multiplication</title>");
            out.println("</head><body>");

            CookieService.htmlWriteCookies(out, request.getCookies());

            out.println("<h3>processing input from last page:</h3>");
            Integer input = null;
            {
                try {
                    input = Integer.parseInt(request.getParameter("input"));
                    out.println("input: " + input + "<br>");
                } catch (Exception ignored) {
                    out.println("parsing error<br>");
                }
            }

            out.println("<h3>setting/getting the cookie:</h3>");
            Integer value = null;
            {
                try {
                    Cookie[] cookies = request.getCookies();
                    for (Cookie cookie : cookies)
                        if (cookie.getName().equals("value"))
                            value = Integer.parseInt(cookie.getValue());
                    out.println("current value is: " + value + "<br>");
                } catch (Exception ignored) {
                    out.println("parse or cookie error<br>");
                }

                if (input == null) {
                    out.println("input == null; doing nothing<br>");
                } else if (value == null) {
                    value = input;
                    out.println("value == null; value = input<br>");
                    response.addCookie(new Cookie("value", value.toString()));
                } else {
                    value = value * input;
                    out.println("else; value = value * input<br>");
                    response.addCookie(new Cookie("value", value.toString()));
                }

            }

            out.println("<h3>multiplication form:</h3>");
            out.println("<form action=\"foo.bar\" method=\"get\">");
            {
                out.println("<label for=\"input\">input:</label>\n");
                out.println("<input type=\"input\" id=\"input\" name=\"input\"><br>");

                out.println("<label for=\"current_value\">current_value:</label>\n");
                out.println("<input type=\"current_value\" id=\"current_value\" name=\"current_value\" value=\"" + value + "\"><br>");

                out.println("<input type=\"submit\" value=\"submit\"><br>");
            }
            out.println("</form>");
        }
    }

}
