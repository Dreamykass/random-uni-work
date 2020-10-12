package sengine.crawl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sengine.run.NamedTask;
import sengine.run.TaskManager;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class CrawlingTask implements NamedTask {
    private static final Logger logger = LogManager.getLogger(CrawlingTask.class);

    String currentUrl;
    String domainUrl;
    ConcurrentMap<String, Page> urlToPageMap;
    AtomicLong activeTasksN;

    public CrawlingTask(String _currentUrl, String _domainUrl,
                        ConcurrentMap<String, Page> _urlToPageMap, AtomicLong _activeTasksN) {
        assert _currentUrl != null;
        assert _domainUrl != null;
        assert _urlToPageMap != null;
        assert _activeTasksN != null;

        currentUrl = _currentUrl;
        domainUrl = _domainUrl;
        urlToPageMap = _urlToPageMap;
        activeTasksN = _activeTasksN;

        activeTasksN.incrementAndGet();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void run(TaskManager _taskManager) {
        assert _taskManager != null;
        logger.debug("started running; currentUrl: {}, domainUrl: {}", currentUrl, domainUrl);

        // ------------------ early returns:
        if (!UrlUtils.domainOfUrlEndsWithDomain(currentUrl, domainUrl)) {
            logger.debug("aborted; currentUrl: {}; !UrlUtils.domainOfUrlEndsWithDomain", currentUrl);
            activeTasksN.decrementAndGet();
            return;
        } else if (urlToPageMap.containsKey(currentUrl)) {
            logger.debug("aborted; currentUrl: {}; urlToPageMap.containsKey", currentUrl);
            activeTasksN.decrementAndGet();
            return;
        }

        var rawPage = DownloadRawPage.fromUrl(currentUrl);

        // ------------------ good path:
        if (rawPage != null) {
            var page = RawPageIntoPage.from(rawPage);

            logger.debug("on page {} found this many outgoing links: {}", page.url, page.outgoingLinks.size());

            for (var outUrl : page.outgoingLinks) {
                CrawlingTask crawlingTask = new CrawlingTask(outUrl, domainUrl, urlToPageMap, activeTasksN);
                _taskManager.pushTaskToQueue(crawlingTask);
            }

            urlToPageMap.put(page.url, page);
            logger.info("finished; page {} nicely put to map", page.url);

        } else {
            logger.debug("aborted; page {} could not be downloaded", currentUrl);
        }

        activeTasksN.decrementAndGet();
    }
}
