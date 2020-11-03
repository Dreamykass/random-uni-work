import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "OutputServlet")
public class OutputServlet extends HttpServlet {

    private Map<String, String> flattenMap(Map<String, String[]> map) {
        Map<String, String> flat = new HashMap<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            flat.put(entry.getKey(), entry.getValue()[0]);
        }
        return flat;
    }

    private String decoratedWarning(String str) {
        return "<h8 style=\"background-color:tomato;\">" + str + "</h8><br>";
    }

    private String decoratedSuccess(String str) {
        return "<h8 style=\"background-color:powderblue;\">" + str + "</h8><br>";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>\n" +
                    "<head>\n" +
                    "    <title>Output</title>\n" +
                    "</head>\n" +
                    "<body>");
            out.println("hello");

            Map<String, String> parameterMap = flattenMap(request.getParameterMap());
            Integer size = Integer.parseInt(parameterMap.get("size"));
            boolean parseError = false;
            out.println("<h1>size: " + size + "</h1>");

            int[][] square = new int[size][size];

            out.println("<table style=\"width:100%\">");

            for (int x = 0; x < size; x++) {
                out.println("<tr>");
                for (int y = 0; y < size; y++) {
                    out.println("<th>");
                    out.println("x: " + x + "; y:" + y + "<br>");
                    String name = "cell (" + x + "," + y + ")";
                    String value = parameterMap.get(name);
                    try {
                        square[x][y] = Integer.parseInt(value);
                    } catch (Exception ignored) {
                        out.println(decoratedWarning("not a number"));
                        parseError = true;
                    }
                    out.println(parameterMap.get(name));
                    out.println("</th>");
                }
                out.println("</tr>");
            }

            out.println("</table>");

            if (parseError) {
                out.println(decoratedWarning("parse error!"));
            } else if (!MatrixService.magicSquare(square)) {
                out.println(decoratedWarning("not magic!"));
            } else {
                out.println(decoratedSuccess("magic!"));
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
