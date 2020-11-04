package sengine.crawl;

public class RawPageIntoPage {
    /**
     * @param _rawPage
     * @return
     */
    public static Page from(RawPage _rawPage) {
        assert _rawPage != null;

        Page page = new Page();

        page.url = _rawPage.url;
        page.rawContent = _rawPage.rawContent;
        page.outgoingLinks = ExtractUrls.fromString(_rawPage.rawContent);

        return page;
    }
}
