package sengine.crawl;

import java.util.ArrayList;
import java.util.List;

public class Page {
    public String url = "invalid";
    public String rawContent = "";
    public List<String> outgoingLinks = new ArrayList<String>();
}
