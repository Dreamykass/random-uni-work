import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String data = request.getParameter("data");

            if (login.equals(getInitParameter("login"))
                    && password.equals(getInitParameter("password"))) {
                response.sendRedirect("result.jsp?result=ok");
                PrintWriter writer = new PrintWriter(new FileOutputStream(new File("data.txt"), true));
                writer.println(data);
                writer.close();
            } else {
                response.sendRedirect("result.jsp?result=bad");
            }

        } catch (Exception ignored) {
            response.sendRedirect("result.jsp?result=bad");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
