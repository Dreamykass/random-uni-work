package sengine.index;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractWords {

    /**
     * @param _str
     * @return
     */
    public static List<Word> fromString(String _str) {
        List<Word> list = new ArrayList<>();

        String expr = "\\p{L}+";
        // https://stackoverflow.com/questions/3015401/how-to-deal-with-polish-characters-while-using-regex

        Matcher m = Pattern.compile(expr)
                .matcher(_str);
        while (m.find()) {
            Word word = new Word();
            word.str = m.group();
            word.indexInWordList = list.size();

            list.add(word);
        }

        return list;
    }
}
