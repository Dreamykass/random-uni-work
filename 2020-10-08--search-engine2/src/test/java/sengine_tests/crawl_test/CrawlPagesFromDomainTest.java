package sengine_tests.crawl_test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import sengine.crawl.CrawlPagesFromDomain;
import sengine.run.TaskManager;

public class CrawlPagesFromDomainTest {
    private static final Logger logger = LogManager.getLogger(CrawlPagesFromDomainTest.class);

    @Test
    public void test1() {
        TaskManager taskManager = new TaskManager(128);

        CrawlPagesFromDomain.from("rekrutacja.pwsz.nysa.pl", "http://www.rekrutacja.pwsz.nysa.pl/", taskManager);

        taskManager.stopRunners();

    }
}
