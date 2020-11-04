package sengine.drive;

import sengine.crawl.CrawlPagesFromDomain;
import sengine.index.PageIntoIndexedPage;
import sengine.index.Word;
import sengine.run.TaskManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("start of main");

        Map<String, List<Word>> indexedWords = new HashMap<>();
        TaskManager taskManager = new TaskManager();

        JFrame f = new JFrame("Search engine");
        DefaultListModel<String> jListModel = new DefaultListModel<>();
        JList<String> jList = new JList<>(jListModel);
        JButton button1 = new JButton("Index a domain");
        JButton button2 = new JButton("Search");

        // list
        {
            jList = new JList<>(jListModel);
            jList.setBounds(400, 200, 400, 600);
            f.add(jList);
        }
        // button 1
        {
//            button1 = new JButton("Index a domain");
            button1.setBounds(50, 100, 300, 30);

            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    var domain = InputDialog.get("Domain to index:", f);
                    var staringUrl = InputDialog.get("Starting from url:", f);

                    button1.setEnabled(false);
                    button2.setEnabled(false);

                    var crawled = CrawlPagesFromDomain.from(domain, staringUrl, taskManager);
                    System.out.println("crawled this many: " + crawled.size());

                    button1.setEnabled(true);
                    button2.setEnabled(true);

                    for (var page : crawled) {
                        var indexedPage = PageIntoIndexedPage.fromPage(page);
//                        for (var word : indexedPage.wordMap.entrySet()) {
//                            if (indexedWords.containsKey(word.getKey()))
//                                indexedWords.;
//                            else {
//
//                            }
//                        }
                        indexedWords.putAll(indexedPage.wordMap);
                    }
                    System.out.println("word map is now: " + indexedWords.size());
                }
            });

            f.add(button1);
        }

        // button 2
        {
//            button2 = new JButton("Search");
            button2.setBounds(50, 200, 300, 30);

            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    var wordToSearch = InputDialog.get("Search for word: ", f);

                    var list = indexedWords.get(wordToSearch);

                    jListModel.clear();

                    if (list != null) {
                        for (Word word : list) {
                            System.out.println("word: " + word.str + "... in: " + word.page.url);
                            jListModel.addElement("word: " + word.str + "... in: " + word.page.url);
                        }
                    }

                }
            });

            f.add(button2);
        }

        f.setSize(800, 800);
        f.setLayout(null);
        f.setVisible(true);
    }
}
