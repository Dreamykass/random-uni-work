import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletInputProcessing")
public class ServletInputProcessing extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String str = request.getParameter("number");
            Integer i = Integer.parseInt(str);

            List<Integer> list;
            if (request.getSession().getAttribute("list") == null) {
                list = new ArrayList<>();
                request.getSession().setAttribute("list", list);
            } else {
                list = (List<Integer>) (request.getSession().getAttribute("list"));
            }

            if (i >= 0 && i <= 10)
                list.add(i);
        } catch (Exception ignored) {

        }
        response.sendRedirect("input.mp4");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
