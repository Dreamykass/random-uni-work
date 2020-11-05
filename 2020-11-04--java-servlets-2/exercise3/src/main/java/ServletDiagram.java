import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletDiagram")
public class ServletDiagram extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head>");
            out.println("<title>diagram</title>");
            out.println("<style>rect {\n" +
                    "  fill: white; \n" +
                    "}\n" +
                    "text {\n" +
                    "  color: black;\n" +
                    "}</style>");
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

            out.println("<h3>diagram:</h3>");
            out.println("<svg class=\"chart\" width=\"800\" height=\"400\" aria-labelledby=\"title desc\" role=\"img\">");
            for (int i = 0; i < list.size(); i++) {
                out.println("<g class=\"bar\">");
                out.println("<rect width=\"100\" height=\"" + list.get(i) * 10 + "\" x=\"" + i * 100 + "\" y=\"200\"></rect>");
                out.println("<text x=\"" + (i * 100 + 50) + "\" y=\"190\" dy=\"2.35em\">" + list.get(i) + "</text>");
                out.println("</g>");
            }
            out.println("</svg>");
        }
    }
}
