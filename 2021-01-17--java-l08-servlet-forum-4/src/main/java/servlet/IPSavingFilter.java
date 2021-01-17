package servlet;


import database.Constants;
import datatype.FailedLogin;

import javax.servlet.*;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class IPSavingFilter implements Filter {

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        try {
            FailedLogin failedLogin = new FailedLogin();
            failedLogin.address = req.getRemoteAddr().toString();
            failedLogin.date = new Date();

            FileOutputStream fos = new FileOutputStream(Constants.DATABASE_ROOT + "failed_logins.xml");
            XMLEncoder xe = new XMLEncoder(fos);

            xe.writeObject(failedLogin);
            xe.close();

        } catch (Exception ignored) {
        }

        chain.doFilter(req, resp);

    }

    public void destroy() {
    }
}
