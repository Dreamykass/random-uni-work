package sengine.crawl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sengine.run.TaskManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class CrawlPagesFromDomain {
    private static final Logger logger = LogManager.getLogger(CrawlPagesFromDomain.class);

    /**
     * @param _domainUrl
     * @param _startingUrl
     * @param _taskManager
     * @return
     */
    public static List<Page> from(String _domainUrl, String _startingUrl, TaskManager _taskManager) {
        logger.warn("started crawling url: {}", _startingUrl);

        ConcurrentMap<String, Page> urlToPageMap = new ConcurrentHashMap<String, Page>();
        Set<String> checkedUrlsSet = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
        AtomicLong activeTasksN = new AtomicLong(0);


        CrawlingTask crawlingTask = new CrawlingTask(_startingUrl, _domainUrl, urlToPageMap, activeTasksN, checkedUrlsSet);
        _taskManager.pushTaskToQueue(crawlingTask);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (activeTasksN.get() != 0) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logger.warn("still waiting until crawling ends...  url: {}; queue length: {}; map size: {}",
                    _startingUrl, _taskManager.getQueueLength(), urlToPageMap.size());
        }

        List<Page> outputList = new ArrayList<Page>();
        for (var kvp : urlToPageMap.entrySet()) {
            if (kvp.getValue() != null)
                outputList.add(kvp.getValue());
        }

        logger.warn("finished crawling domain: {}; starting at url: {}", _domainUrl, _startingUrl);
        logger.warn("crawled this many pages: {}; all links:", outputList.size());
        for (var page : outputList)
            logger.debug(" --- {}", page.url);
        logger.warn("returning the list of pages");
        return outputList;
    }
}
