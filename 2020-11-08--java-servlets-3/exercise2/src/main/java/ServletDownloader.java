import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "ServletDownloader")
public class ServletDownloader extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String filename = req.getParameter("directory_file");

        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=" + filename);
        resp.setBufferSize(8192);

//        try (InputStream in = req.getServletContext().getResourceAsStream(filename);
//        try (InputStream in = req.getServletContext().getResourceAsStream("/directory_1/foo.txt");
//        try (InputStream in = new FileInputStream("directory_1/foo.txt");
        try (InputStream in = new FileInputStream(filename);
             OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[8192];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }

        }
    }
}
