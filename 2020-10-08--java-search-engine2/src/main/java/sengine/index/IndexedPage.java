package sengine.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexedPage {
    public String url = "invalid";
    public String rawContent = "";
    public List<String> outgoingLinks = new ArrayList<String>();
    public List<Word> words = new ArrayList<Word>();
    public Map<String, List<Word>> wordMap = new HashMap<>();
}
