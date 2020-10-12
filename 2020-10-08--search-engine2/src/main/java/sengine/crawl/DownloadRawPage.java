package sengine.crawl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadRawPage {
    private static final Logger logger = LogManager.getLogger(DownloadRawPage.class);

    // or null at fail
    public static RawPage fromUrl(String _url) {
        assert _url != null;

        RawPage rawPage = new RawPage();
        rawPage.url = _url;
        URL url;

        // if url is invalid, return early
        try {
            url = new URL(rawPage.url);
        } catch (MalformedURLException e) {
            logger.warn("tried to download an invalid url, returned null instead: {}", _url);
            return null;
        }

        // if pdf, return early
        if (_url.endsWith(".pdf") || _url.endsWith(".png") || _url.endsWith(".jpg")) {
            logger.info("tried to download a pdf or png or jpg, returned null instead: {}", _url);
            return null;
        }

        // if Content-Type is not "text/html; charset=utf-8", return early
        {
            URLConnection u = null;
            try {
                u = new URL(_url).openConnection();
                String type = u.getHeaderField("Content-Type");

                if (type == null) {
                    logger.info("tried to download {}; Content-Type was null; aborted", _url);
                    return null;
                }

                if (!type.equalsIgnoreCase("text/html; charset=utf-8")) {
                    logger.info("tried to download Content-Type: {}; was not: {}; returned null instead: {}", type, "text/html; charset=utf-8", _url);
                    return null;
                }

            } catch (MalformedURLException e) {
                logger.info("tried to download Content-Type: {}; caught: {}; returned null instead: {}",
                        "MalformedURLException", "text/html; charset=utf-8", _url);
                return null;
            } catch (IOException e) {
                logger.info("tried to download Content-Type: {}; caught: {}; returned null instead: {}",
                        "IOException", "text/html; charset=utf-8", _url);
                return null;
            }
        }

        // actually try to download
        logger.info("attempting to download: {}", _url);

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
