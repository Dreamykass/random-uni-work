import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet")
public class MultiplicationServlet extends HttpServlet {
    public int multiplicationParameter;

    @Override
    public void init(ServletConfig config) {
        try {
            multiplicationParameter = Integer.parseInt(config.getInitParameter("multiplicationParameter"));
        } catch (Exception ignored) {
            multiplicationParameter = 9;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("Multiplication");
            out.println("<html>\n" +
                    "<head>\n" +
                    "<style>\n" +
                    "table, th, td {\n" +
                    "  border: 1px solid black;\n" +
                    "}\n" +
                    "</style>" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>");
            {
                out.println("<table style='width:100%; border=1px solid black'>");

                out.println("<tr>");
                out.println("<th>X</th>");
                for (int x = 1; x <= multiplicationParameter; x++)
                    out.println("<th>" + x + "</th>");
                out.println("</tr>");

                for (int x = 1; x <= multiplicationParameter; x++) {
                    out.println("<tr>");
                    out.println("<th>" + x + "</th>");

                    for (int y = 1; y <= multiplicationParameter; y++) {
                        out.println("<th>" + x * y + "</th>");

                    }

                    out.println("</tr>");
                }

                out.println("</table>");
            }

            out.println("</body>\n" +
                    "</html>");
        }
    }
}
