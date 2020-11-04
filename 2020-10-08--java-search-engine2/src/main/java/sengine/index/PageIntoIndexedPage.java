package sengine.index;

import sengine.crawl.Page;

import java.util.ArrayList;

public class PageIntoIndexedPage {
    /**
     * @param _page
     * @return
     */
    public static IndexedPage fromPage(Page _page) {
        IndexedPage indexedPage = new IndexedPage();

        indexedPage.outgoingLinks = _page.outgoingLinks;
        indexedPage.rawContent = _page.rawContent;
        indexedPage.url = _page.url;

        // extract words
        {
            var words = ExtractWords.fromString(_page.rawContent);
            for (var word : words)
                word.page = indexedPage;
            indexedPage.words = words;
        }

        // index words
        for (var word : indexedPage.words) {
            var wordMap = indexedPage.wordMap;

            if (!wordMap.containsKey(word.str)) {
                wordMap.put(word.str, new ArrayList<>());
            }
            wordMap.get(word.str).add(word);
        }

        return indexedPage;
    }
}
