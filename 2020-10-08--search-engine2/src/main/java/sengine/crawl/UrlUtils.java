package sengine.crawl;

import java.net.URI;
import java.net.URISyntaxException;

public class UrlUtils {

    public static String domainFromUrlOrNull(String _url) {
        URI uri = null;

        try {
            uri = new URI(_url);
        } catch (URISyntaxException ignore) {
            return null;
        }

        //        return domain.startsWith("www.") ? domain.substring(4) : domain;

        return uri.getHost();
    }

    public static boolean domainOfUrlEndsWithDomain(String _url, String _expectedDomain) {
        assert _url != null;
        assert _expectedDomain != null;

        var actualDomain = domainFromUrlOrNull(_url);

        // naive
//        if (!_url.contains(_expectedDomain))
//            return false;

        // actual check
//        if (actualDomain == null)
//            return false;
//        else
//            return actualDomain.equals(_expectedDomain);

        if (actualDomain == null)
            return false;
        else
//            return actualDomain.contains(_expectedDomain);
            return actualDomain.endsWith(_expectedDomain);
    }

}
