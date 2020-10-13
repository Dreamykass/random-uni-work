package sengine.index;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractWords {

    public static List<String> fromString(String _str) {
        List<String> list = new ArrayList<>();

        String expr = "\\p{L}+";
        // https://stackoverflow.com/questions/3015401/how-to-deal-with-polish-characters-while-using-regex

        Matcher m = Pattern.compile(expr)
                .matcher(_str);
        while (m.find()) {
            list.add(m.group());
        }

        return list;
    }
}
