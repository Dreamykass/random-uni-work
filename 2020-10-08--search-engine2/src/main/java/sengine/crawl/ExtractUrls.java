package sengine.crawl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractUrls {

    public static List<String> fromString(String _str) {
        List<String> list = new ArrayList<>();

        String expr = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        // https://stackoverflow.com/questions/5120171/extract-links-from-a-web-page
        // https://stackoverflow.com/questions/6038061/regular-expression-to-find-urls-within-a-string

        Matcher m = Pattern.compile(expr)
                .matcher(_str);
        while (m.find()) {
            list.add(m.group());
        }

        return list;
    }
}
