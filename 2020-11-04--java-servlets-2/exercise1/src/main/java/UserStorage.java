import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserStorage {
    static public ConcurrentMap<String, User> users = new ConcurrentHashMap<>();

    static public void htmlWriteAllUsers(PrintWriter out) {
        out.println("<h3>all registered users:</h3>");
        for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
            out.println("---login: " + user.login + "; password: " + user.password + "; email:" + user.email + "<br>");
        }
        out.println("/users<br>");
    }
}
