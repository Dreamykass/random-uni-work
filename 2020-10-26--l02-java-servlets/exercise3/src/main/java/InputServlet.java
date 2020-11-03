import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InputServlet")
public class InputServlet extends HttpServlet {
    public Integer size;

    @Override
    public void init(ServletConfig config) {
        size = Integer.parseInt(config.getInitParameter("size"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>\n" +
                    "<head>\n" +
                    "    <title>Input</title>\n" +
                    "</head>\n" +
                    "<body>");

            out.println("<h1>Magic Square!</h1>");

            out.println("<form action=\"output.do\" method=\"post\">");
            out.println("<input type=\"hidden\" id=\"size\" name=\"size\" value=\"" + size + "\">\n");

            out.println("<table style=\"width:100%\">");

            for (int x = 0; x < size; x++) {
                out.println("<tr>");
                for (int y = 0; y < size; y++) {
                    out.println("<th>");
                    out.println("x: " + x + "; y:" + y + "<br>");
                    String name = "cell (" + x + "," + y + ")";
                    out.println("<input type=\"text\" id=\"" + name + "\" name=\"" + name + "\">");
                    out.println("</th>");
                }
                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<input type=\"submit\" value=\"submit\">");
            out.println("</form>");
        }
    }


}
