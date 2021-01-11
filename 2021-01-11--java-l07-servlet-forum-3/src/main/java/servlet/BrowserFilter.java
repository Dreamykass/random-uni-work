package servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class BrowserFilter implements Filter {
//    // Filter param keys
//    public static final String KEY_BROWSER_IDS = "browserIds";
//    public static final String KEY_BAD_BROWSER_URL = "badBrowserUrl";
//    // Be default, support all GWT-capable browsers
//    // Assume any version recent enough except IE
//    private static final String[] DEFAULT_BROWSERS =
//            {"Chrome", "Firefox", "Safari", "Opera", "MSIE 8", "MSIE 7", "MSIE 6"};
//    // Configured params
//    private String[] browserIds;
//    private String badBrowserUrl;

    @Override
    public void init(FilterConfig cfg) throws ServletException {
//        String ids = cfg.getInitParameter(KEY_BROWSER_IDS);
//        this.browserIds = (ids != null) ? ids.split(",") : DEFAULT_BROWSERS;
//
//        badBrowserUrl = cfg.getInitParameter(KEY_BAD_BROWSER_URL);
//        if (badBrowserUrl == null) {
//            throw new IllegalArgumentException("BrowserFilter requires param badBrowserUrl");
//        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        String userAgent = ((HttpServletRequest) req).getHeader("User-Agent");
//        for (String browser_id : browserIds) {
//            if (userAgent.contains(browser_id)) {
//                chain.doFilter(req, resp);
//                return;
//            }
//        }
        // Unsupported browser
//        ((HttpServletResponse) resp).sendRedirect(this.badBrowserUrl);

        if (userAgent.contains("Firefox")) {
            ((HttpServletRequest) req).getSession().setAttribute("browser", "#ff0000");
        } else if (userAgent.contains("Chrome")) {
            ((HttpServletRequest) req).getSession().setAttribute("browser", "#aaaaaa");
        } else {
            ((HttpServletRequest) req).getSession().setAttribute("browser", "#ffffff");
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
//        this.browserIds = null;
    }
}