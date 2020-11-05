import javax.servlet.http.Cookie;
import java.io.PrintWriter;

public class CookieService {
    static public void htmlWriteCookies(PrintWriter out, Cookie[] cookies) {
        out.println("<h3>all cookies:</h3>");
        if (cookies == null) {
            out.println("cookies == null");
            return;
        }
        for (Cookie cookie : cookies) {
            out.println("---name: " + cookie.getName() + "; value: " + cookie.getValue() + "<br>");
        }
        out.println("/cookies<br>");
    }
}
