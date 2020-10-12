package sengine_tests.crawl_test;

import org.junit.jupiter.api.Test;
import sengine.crawl.CrawlPagesFromDomain;
import sengine.run.TaskManager;

public class CrawlPagesFromDomainTest {

    @Test
    public void test1() {
        TaskManager taskManager = new TaskManager();

//        CrawlPagesFromDomain.from("pwsz.nysa.pl", "http://pwsz.nysa.pl/", taskManager);
        CrawlPagesFromDomain.from("strzelec.pwsz.nysa.pl", "http://strzelec.pwsz.nysa.pl/", taskManager);

        taskManager.stopRunners();
    }
}
