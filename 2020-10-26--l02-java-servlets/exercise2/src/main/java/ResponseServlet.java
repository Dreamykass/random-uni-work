import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@WebServlet(name = "ResponseServlet")
public class ResponseServlet extends HttpServlet {

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
                    "    <title>Response</title>\n" +
                    "</head>\n" +
                    "<body>");

            Map<String, String[]> parameterMap = request.getParameterMap();
            out.println("<h3>post params:</h3>");
            {
                for (Map.Entry<String, String[]> pair : parameterMap.entrySet()) {
                    out.println("(" + pair.getKey() + ", [");
                    for (String k : pair.getValue()) {
                        out.println(k + ", ");
                    }
                    out.println("])<br>");
                }
            }
            out.println("<h3>check if every param has exactly one value:</h3>");
            {
                for (Map.Entry<String, String[]> pair : parameterMap.entrySet()) {
                    if (pair.getValue().length != 1)
                        out.println(decoratedWarning("key (" + pair.getKey() + ") has more than one value!"));
                }
            }

            Map<String, String> paramRegexMap = new HashMap<>();
            {
                paramRegexMap.put("name", "^[A-Z][a-z]*$");
                paramRegexMap.put("pesel", "^[0-9]{11}$");
                paramRegexMap.put("birth", "^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
                paramRegexMap.put("sex", "^(m|f)$");
                paramRegexMap.put("occupation", "^.*$");
                paramRegexMap.put("email", "^([a-z0-9_.-]+@[a-z0-9_.-]+\\.\\w{2,4})$");
                paramRegexMap.put("height", "^([0-9]){1,3}$");
                paramRegexMap.put("hobby", "^.*$");
            }
            Map<String, Optional<String>> expectedValuesMap = new HashMap<>();
            {
                for (String key : paramRegexMap.keySet())
                    expectedValuesMap.put(key, Optional.empty());
            }


            out.println("<h3>check for unexpected parameters:</h3>");
            {
                for (Map.Entry<String, String[]> pair : parameterMap.entrySet()) {
                    String key = pair.getKey();
                    String value = pair.getValue()[0];

                    if (!expectedValuesMap.containsKey(key))
                        out.println(decoratedWarning("unexpected key: " + key));
                    else if (expectedValuesMap.get(key).isPresent())
                        out.println(decoratedWarning("duplicate key: " + key));
                    else
                        expectedValuesMap.put(key, Optional.of(value));
                }
            }
            out.println("<h3>check for missing parameters:</h3>");
            {
                for (Map.Entry<String, Optional<String>> pair : expectedValuesMap.entrySet())
                    if (!pair.getValue().isPresent())
                        out.println(decoratedWarning("missing key: " + pair.getKey()));
            }


            out.println("<h3>match regex:</h3>");
            {
                for (Map.Entry<String, Optional<String>> pair : expectedValuesMap.entrySet()) {
                    String key = pair.getKey();
                    String value = pair.getValue().orElse(null);
                    assert value != null;

                    Pattern pattern = Pattern.compile(paramRegexMap.get(key));
                    boolean matched = pattern.matcher(value).matches();

                    if (!matched)
                        out.println(decoratedWarning("pair (" + key + ", " + value + ") did not match against " +
                                "(" + key + ", " + paramRegexMap.get(key) + ")"));
                    else
                        out.println(decoratedSuccess("pair (" + key + ", " + value + ") did match against " +
                                "(" + key + ", " + paramRegexMap.get(key) + ") and is ok"));
                }
            }


            out.println("</body>\n" +
                    "</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

    }
}
