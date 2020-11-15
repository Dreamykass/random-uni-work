package FilesystemHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Stream;

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

    public static List<String> getAllFilesInDirectories() {
        List<String> list = new ArrayList<>();

        for (String dir : directoriesList) {
            try (Stream<Path> paths = Files.walk(Paths.get(dir))) {
                paths.filter(Files::isRegularFile).forEach(foo -> list.add(foo.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public static String filesInDirectoriesToHtmlSelectOptionTags() {
        StringBuilder str = new StringBuilder();
        for (String file : getAllFilesInDirectories()) {
            str.append("<option value=\"")
                    .append(file)
                    .append("\">")
                    .append(file)
                    .append("</option>");
        }
        return str.toString();
    }
}
