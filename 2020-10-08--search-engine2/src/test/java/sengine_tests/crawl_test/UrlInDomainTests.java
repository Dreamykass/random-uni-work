package sengine_tests.crawl_test;

import org.junit.jupiter.api.Test;
import sengine.crawl.UrlUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrlInDomainTests {

    @Test
    public void testTrues() {
//        assertTrue(UrlUtils.isUrlInDomain("", ""));
        String expectedDomain = "pwsz.nysa.pl";

        assertTrue(UrlUtils.domainOfUrlEndsWithDomain("http://pwsz.nysa.pl/", expectedDomain));
        assertTrue(UrlUtils.domainOfUrlEndsWithDomain("https://www.pwsz.nysa.pl/plany/plany.php", expectedDomain));
        assertTrue(UrlUtils.domainOfUrlEndsWithDomain("http://pwsz.nysa.pl/#", expectedDomain));
        assertTrue(UrlUtils.domainOfUrlEndsWithDomain("http://www.pwsz.nysa.pl/index.php?p=15", expectedDomain));
        assertTrue(UrlUtils.domainOfUrlEndsWithDomain("http://pwsz.nysa.pl/?p=1&ak=1,8122,0", expectedDomain));
        assertTrue(UrlUtils.domainOfUrlEndsWithDomain("http://www.pwsz.nysa.pl/?p=1&ak=1,7448,0", expectedDomain));
        assertTrue(UrlUtils.domainOfUrlEndsWithDomain("https://biblioteka.pwsz.nysa.pl/", expectedDomain));

    }

    @Test
    public void testFalsies() {
        String expectedDomain = "pwsz.nysa.pl";

        assertFalse(UrlUtils.domainOfUrlEndsWithDomain("google.com", expectedDomain));
        assertFalse(UrlUtils.domainOfUrlEndsWithDomain("http://pwsz.pl/", expectedDomain));
        assertFalse(UrlUtils.domainOfUrlEndsWithDomain("http://nysa.pl/", expectedDomain));
        assertFalse(UrlUtils.domainOfUrlEndsWithDomain("http://pwsz.nysa.com/", expectedDomain));
        assertFalse(UrlUtils.domainOfUrlEndsWithDomain("http://pwsz.nysa.pz/", expectedDomain));
//        assertFalse(UrlUtils.domainOfUrlEndsWithDomain("", expectedDomain));
//        assertFalse(UrlUtils.domainOfUrlEndsWithDomain("", expectedDomain));
//        assertFalse(UrlUtils.domainOfUrlEndsWithDomain("", expectedDomain));

    }
}
