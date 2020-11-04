import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletWelcome")
public class ServletWelcome extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head>");
            out.println("<title>welcome</title>");
            {
                out.println("<script>function fun()\n" +
                        "{\n" +
                        "  if (document.getElementById('register').checked) \n" +
                        "  {\n" +
                        "      document.getElementById('email').disabled = false;\n" +
                        "  } else {\n" +
                        "      document.getElementById('email').disabled = true;\n" +
                        "  }\n" +
                        "}</script>");
            }
            out.println("</head><body>");

            UserStorage.htmlWriteAllUsers(out);
            CookieService.htmlWriteCookies(out, request.getCookies());
            out.println("<br><br>");

            out.println("<h3>login/register form:</h3>");
            out.println("<form action=\"logged.jpg\" method=\"post\">");
            {
                out.println("<label for=\"email\">email:</label>\n");
                out.println("<input type=\"email\" id=\"email\" name=\"email\" disabled=true><br>");

                out.println("<label for=\"login\">login:</label>\n");
                out.println("<input type=\"login\" id=\"login\" name=\"login\"><br>");

                out.println("<label for=\"password\">password:</label>\n");
                out.println("<input type=\"password\" id=\"password\" name=\"password\"><br>");

                out.println("<label for=\"register\">register:</label>\n");
                out.println("<input type=\"checkbox\" id=\"register\" name=\"register\" onclick=\"fun();\"><br>");

                out.println("<input type=\"submit\" value=\"submit\"><br>");
            }
            out.println("</form>");

        }
    }
}
