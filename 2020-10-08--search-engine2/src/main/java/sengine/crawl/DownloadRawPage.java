package sengine.crawl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadRawPage {

    public static RawPage fromUrl(String _url) throws MalformedURLException {
        assert _url != null;

        RawPage rawPage = new RawPage();
        rawPage.url = _url;


        URL url;
        InputStream inputStream = null;
        BufferedReader bufferedReader;
        String line;

        try {
            url = new URL(rawPage.url);
            inputStream = url.openStream();  // throws an IOException
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine()) != null) {
                rawPage.rawContent = rawPage.rawContent + line;
            }
        } catch (MalformedURLException mue) {
//            mue.printStackTrace();
            throw mue;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            assert false;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ioe) {
                // nothing to see here
            }
        }


        return rawPage;
    }
}
