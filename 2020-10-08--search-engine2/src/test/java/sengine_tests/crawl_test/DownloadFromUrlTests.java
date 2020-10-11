package sengine_tests.crawl_test;

import org.junit.jupiter.api.Test;
import sengine.crawl.DownloadRawPage;
import sengine.crawl.RawPage;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class DownloadFromUrlTests {

    @Test
    public void test1() {
        AtomicReference<RawPage> rawPageR = new AtomicReference<RawPage>();
        assertDoesNotThrow(() -> {
            rawPageR.set(DownloadRawPage.fromUrl("http://pwsz.nysa.pl/"));
        });
        var rawPage = rawPageR.get();

        assertNotNull(rawPage.rawContent);
        assertNotNull(rawPage.url);

        assertEquals("http://pwsz.nysa.pl/", rawPage.url);
        assertTrue(rawPage.rawContent.contains("PWSZ w Nysie architektura, dietetyka, filologia angielska"));
        assertTrue(rawPage.rawContent.contains("<ol class=\"carousel-indicators\">"));
        assertTrue(rawPage.rawContent.contains("<a href=\"https://www.rekrutacja.pwsz.nysa.pl/\" target=\"_blank\" style"));
        assertTrue(rawPage.rawContent.contains("Państwowa Wyższa Szkoła Zawodowa w Nysie © 2019. Kontakt z webmasterem"));
    }
}
