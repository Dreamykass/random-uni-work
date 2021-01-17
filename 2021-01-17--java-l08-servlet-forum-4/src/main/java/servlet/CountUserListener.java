package servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CountUserListener implements HttpSessionListener {
    static int total = 0, current = 0;
    ServletContext ctx = null;

    static public void increment(ServletContext servletContext, HttpServletRequest httpServletRequest) {
        total++;
        current++;

        ServletContext ctx = httpServletRequest.getSession().getServletContext();
        ctx.setAttribute("totalusers", total);
        ctx.setAttribute("currentusers", current);
    }

    static public void decrement(ServletContext servletContext, HttpServletRequest httpServletRequest) {
        current--;
        httpServletRequest.getSession().getServletContext().setAttribute("currentusers", current);
    }

    public void sessionCreated(HttpSessionEvent e) {
        total++;
        current++;

        ctx = e.getSession().getServletContext();
        ctx.setAttribute("totalusers", total);
        ctx.setAttribute("currentusers", current);

    }

    public void sessionDestroyed(HttpSessionEvent e) {
        current--;
        ctx.setAttribute("currentusers", current);
    }

}  