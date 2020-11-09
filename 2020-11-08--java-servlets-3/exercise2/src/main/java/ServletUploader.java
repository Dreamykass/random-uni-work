import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "ServletUploader")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5,
        location = "D:\\Code\\java\\glassfish-5.1.0\\glassfish5\\glassfish\\domains\\domain1\\config")
public class ServletUploader extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String uploadDir = request.getParameter("directory");

            for (Part part : request.getParts()) {
                String fileName = part.getSubmittedFileName();
                part.write(uploadDir + "/" + fileName);
            }

            response.sendRedirect("result.jsp?result=all good");

        } catch (Exception ignored) {
            response.sendRedirect("result.jsp?result=exception");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
