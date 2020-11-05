import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletInput")
public class ServletInput extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head>");
            out.println("<title>input</title>");
            out.println("</head><body style=\"background-color:#17202A; color:white\">");

            out.println("<h3>numbers:</h3>");
            List<Integer> list;
            if (request.getSession().getAttribute("list") == null) {
                out.println("list is null, creating it");
                list = new ArrayList<>();
                request.getSession().setAttribute("list", list);
            } else {
                list = (List<Integer>) (request.getSession().getAttribute("list"));
            }
            out.println("<br>[");
            for (Integer i : list)
                out.println("" + i + ", ");
            out.println("]<br>/numbers<br>");

            out.println("<h3>input:</h3>");
            out.println("<form action=\"process.mp4\" method=\"post\">");
            {
                out.println("<label for=\"number\">number:</label>\n");
                out.println("<input type=\"text\" id=\"number\" name=\"number\"><br>");

                out.println("<input type=\"submit\" value=\"submit\"><br>");
            }
            out.println("</form>");

            out.println("<form action=\"diagram.mp4\" method=\"get\">");
            out.println("<input type=\"submit\" value=\"generate\"><br>");
            out.println("</form>");

        }
    }
}
