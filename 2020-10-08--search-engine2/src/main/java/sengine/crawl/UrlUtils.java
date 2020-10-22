package sengine.crawl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

public class UrlUtils {
    private static final Logger logger = LogManager.getLogger(CrawlingTask.class);

    /**
     * @param _url
     * @return
     */
    public static String domainFromUrlOrNull(String _url) {
        URI uri = null;

        try {
            uri = new URI(_url);
        } catch (URISyntaxException e) {
            return null;
        }

        //        return domain.startsWith("www.") ? domain.substring(4) : domain;


        return uri.getHost();
    }

    /**
     * @param _url
     * @param _expectedDomain
     * @return
     */
    public static boolean domainOfUrlEndsWithDomain(String _url, String _expectedDomain) {
        assert _url != null;
        assert _expectedDomain != null;

        if (_url.equals(_expectedDomain))
            return true;

        var actualDomain = domainFromUrlOrNull(_url);

        // naive
//        if (!_url.contains(_expectedDomain))
//            return false;

        // actual check
//        if (actualDomain == null)
//            return false;
//        else
//            return actualDomain.equals(_expectedDomain);

        if (actualDomain == null) {
            return false;
        } else {
//            return actualDomain.contains(_expectedDomain);
            return actualDomain.endsWith(_expectedDomain);
        }
    }

}
