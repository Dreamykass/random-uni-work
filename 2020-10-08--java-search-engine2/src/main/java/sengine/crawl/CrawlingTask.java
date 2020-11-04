package sengine.crawl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sengine.run.NamedTask;
import sengine.run.Task;
import sengine.run.TaskManager;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Damian Gruner
 * Crawling Task
 * To be used with the task system of TaskManager
 * @version 1.0
 * @implNote
 */
public class CrawlingTask implements Task, NamedTask {

    private static final Logger logger = LogManager.getLogger(CrawlingTask.class);

    String currentUrl;
    String domainUrl;
    ConcurrentMap<String, Page> urlToPageMap;
    AtomicLong activeTasksN;
    Set<String> checkedUrlsSet;

    /**
     * @param _currentUrl     current url to be crawled
     * @param _domainUrl      domain to which the url belongs
     * @param _urlToPageMap   concurrent map
     * @param _activeTasksN   atomic
     * @param _checkedUrlsSet set of checked urls
     */
    public CrawlingTask(String _currentUrl, String _domainUrl,
                        ConcurrentMap<String, Page> _urlToPageMap, AtomicLong _activeTasksN,
                        Set<String> _checkedUrlsSet) {
        assert _currentUrl != null;
        assert _domainUrl != null;
        assert _urlToPageMap != null;
        assert _activeTasksN != null;
        assert _checkedUrlsSet != null;

        currentUrl = _currentUrl;
        domainUrl = _domainUrl;
        urlToPageMap = _urlToPageMap;
        activeTasksN = _activeTasksN;
        checkedUrlsSet = _checkedUrlsSet;

        activeTasksN.incrementAndGet();
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return String.format("crawling domain: %s; -------- on site: %s", domainUrl, currentUrl);
    }

    /**
     * @param _taskManager
     */
    @Override
    public void run(TaskManager _taskManager) {
        assert _taskManager != null;
        logger.debug("started running; currentUrl: {}, domainUrl: {}", currentUrl, domainUrl);

        // ------------------------------------------------------ early returns:
        {
            if (urlToPageMap.containsKey(currentUrl)) {
                logger.debug("aborted; currentUrl: {}; urlToPageMap.containsKey", currentUrl);
                activeTasksN.decrementAndGet();
                return;
            } else if (checkedUrlsSet.contains(currentUrl)) {
                logger.debug("aborted; currentUrl: {}; checkedUrlsSet.contains", currentUrl);
                activeTasksN.decrementAndGet();
                return;
            } else if (!UrlUtils.domainOfUrlEndsWithDomain(currentUrl, domainUrl)) {
                logger.debug("aborted; currentUrl: {}; !UrlUtils.domainOfUrlEndsWithDomain", currentUrl);
                activeTasksN.decrementAndGet();
                return;
            }
        }

        var rawPage = DownloadRawPage.fromUrl(currentUrl);
        checkedUrlsSet.add(currentUrl);

        // ------------------------------------------------------ good path:
        if (rawPage != null) {
            var page = RawPageIntoPage.from(rawPage);

            logger.debug("on page {} found this many outgoing links: {}", page.url, page.outgoingLinks.size());

            for (var outUrl : page.outgoingLinks) {
                if (!checkedUrlsSet.contains(outUrl)) {
                    CrawlingTask crawlingTask = new CrawlingTask(outUrl, domainUrl, urlToPageMap, activeTasksN, checkedUrlsSet);
                    _taskManager.pushTaskToQueue(crawlingTask);
                }
            }

            urlToPageMap.put(page.url, page);
            logger.info("finished; page {} nicely put to map", page.url);

        } else {
            logger.debug("aborted; page {} could not be downloaded", currentUrl);
        }

        activeTasksN.decrementAndGet();
    }
}
