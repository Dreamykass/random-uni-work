package sengine.crawl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadRawPage {
    private static final Logger logger = LogManager.getLogger(DownloadRawPage.class);

    /**
     * @param _urlString
     * @return
     */
    public static URL strToUrlIfValidHtml(String _urlString) {
        URL url;

        // set url
        // if MalformedURLException (then return null)
        {
            try {
                url = new URL(_urlString);
            } catch (MalformedURLException e) {
                logger.info("strToUrlIfValid(), checking url: {}; invalid because: {}", _urlString, "MalformedURLException");
                return null;
            }
        }

        // if URL.toURI fails
        {
            try {
                var uri = url.toURI();
            } catch (URISyntaxException e) {
                logger.info("strToUrlIfValid(), checking url: {}; invalid because: {}", _urlString, "URISyntaxException on url.toURI()");
            }
        }

        // if pdf
        if (_urlString.endsWith(".pdf") || _urlString.endsWith(".png") || _urlString.endsWith(".jpg")) {
            logger.info("strToUrlIfValid(), checking url: {}; invalid because: {}", _urlString, "ends with filetype extension (.pdf, etc)");
            return null;
        }

        // if Content-Type is not "text/html; charset=utf-8"
        {
            URLConnection u = null;
            try {
                u = new URL(_urlString).openConnection();
                String type = u.getHeaderField("Content-Type");

                if (type == null) {
                    logger.info("strToUrlIfValid(), checking url: {}; invalid because: {}", _urlString, "Content-Type was null");
                    return null;
                }

                if (!type.equalsIgnoreCase("text/html; charset=utf-8")) {
                    logger.info("strToUrlIfValid(), checking url: {}; invalid because: {}{}", _urlString, "Content-Type was: ", type);
                    return null;
                }

            } catch (MalformedURLException e) {
                logger.info("strToUrlIfValid(), checking url: {}; invalid because: {}", _urlString, "MalformedURLException at Content-Type check");
                return null;
            } catch (IOException e) {
                logger.info("strToUrlIfValid(), checking url: {}; invalid because: {}", _urlString, "IOEXCEPTION at Content-Type check");
                return null;
            }
        }

        // ok
        logger.info("strToUrlIfValid(), checking url: {}; IS VALID", _urlString);
        return url;
    }

    // or null at fail
    public static RawPage fromUrl(String _urlString) {
        assert _urlString != null;

        RawPage rawPage = new RawPage();
        rawPage.url = _urlString;
        URL url;

        // if url is invalid, return early
        url = strToUrlIfValidHtml(_urlString);
        if (url == null)
            return null;

        // actually try to download
        logger.info("attempting to download: {}", _urlString);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignore) {
        }


        InputStream inputStream = null;
        BufferedReader bufferedReader;
        String line;

        try {
            inputStream = url.openStream();  // throws an IOException
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine()) != null) {
                rawPage.rawContent = rawPage.rawContent + line;
            }
        } catch (MalformedURLException ignore) {
//            mue.printStackTrace();
            return null;
        } catch (IOException ioe) {
            logger.warn("IOException while trying to download {}", _urlString);
            ioe.printStackTrace();
//            assert false;
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ioe) {
                // nothing to see here
                ioe.printStackTrace();
            }
        }


        return rawPage;
    }
}
