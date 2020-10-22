package sengine_tests.index_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import sengine.crawl.DownloadRawPage;
import sengine.crawl.RawPageIntoPage;
import sengine.index.PageIntoIndexedPage;

import static org.junit.jupiter.api.Assertions.*;

public class IndexingTests {
    private static final Logger logger = LogManager.getLogger(IndexingTests.class);

    @Test
    public void fromStringTest() {

        var raw = DownloadRawPage.fromUrl("http://www.rekrutacja.pwsz.nysa.pl/");
        assertNotNull(raw);

        var page = RawPageIntoPage.from(raw);
        var indexedPage = PageIntoIndexedPage.fromPage(page);

        assertNotEquals(0, indexedPage.wordMap.size());
        assertTrue(indexedPage.wordMap.containsKey("Stypendia"));
        assertEquals(2, indexedPage.wordMap.get("Stypendia").size());
    }
}
