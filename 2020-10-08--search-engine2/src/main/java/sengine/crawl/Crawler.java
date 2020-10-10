package sengine.crawl;

public class Crawler {

    public static RawPage readFromUrl(String _url) {
        RawPage rawPage = new RawPage();
        rawPage.url = _url;

        return rawPage;
    }
}
