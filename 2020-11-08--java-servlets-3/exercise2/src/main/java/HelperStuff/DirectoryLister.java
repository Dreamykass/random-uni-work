package HelperStuff;

import java.util.concurrent.ConcurrentSkipListSet;

public class DirectoryLister {
    public static ConcurrentSkipListSet<String> directoriesList = directoriesListInitializer();

    private static ConcurrentSkipListSet<String> directoriesListInitializer() {
        ConcurrentSkipListSet<String> list = new ConcurrentSkipListSet<>();
        list.add("directory_1");
        list.add("directory_2");
        list.add("directory_3");
        list.add("directory_4");
        list.add("directory_5");
        return list;
    }

    private static void directoriesCreator(ConcurrentSkipListSet<String> list) {

    }

    public static String directoriesToHtmlSelectOptionTags() {
        StringBuilder str = new StringBuilder();
        for (String dir : directoriesList) {
            str.append("<option value=\"")
                    .append(dir)
                    .append("\">")
                    .append(dir)
                    .append("</option>");
        }

        return str.toString();


    }
}
