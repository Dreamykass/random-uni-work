package sengine_tests.crawl_test;

import org.junit.jupiter.api.Test;
import sengine.crawl.DownloadRawPage;
import sengine.crawl.ExtractUrls;
import sengine.crawl.RawPage;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrlExtractionTests {

    @Test
    public void test1() {
        assertEquals(0, ExtractUrls.fromString("").size());
        assertEquals(1, ExtractUrls.fromString("http://pwsz.nysa.pl/").size());
        assertEquals(2, ExtractUrls.fromString("http://pwsz.nysa.pl/ http://pwsz.nysa.pl/").size());
        assertEquals(2, ExtractUrls.fromString("http://pwsz.nysa.pl/, http://pwsz.nysa.pl/").size());
        assertEquals(1, ExtractUrls.fromString("<a href=\"https://www.w3schools.com\">Visit W3Schools</a>").size());
    }

    @Test
    public void test2() {
        AtomicReference<RawPage> rawPageR = new AtomicReference<RawPage>();
        assertDoesNotThrow(() -> {
            rawPageR.set(DownloadRawPage.fromUrl("http://pwsz.nysa.pl/"));
        });
        var rawPage = rawPageR.get();

        assertEquals(120, ExtractUrls.fromString(rawPage.rawContent).size());
    }
}
