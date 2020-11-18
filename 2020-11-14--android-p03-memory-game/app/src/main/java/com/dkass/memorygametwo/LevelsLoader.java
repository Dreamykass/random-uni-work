package com.dkass.memorygametwo;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class LevelsLoader {

    private static String loadRaw(Context ctx) {
        InputStream inputStream = ctx.getResources().openRawResource(R.raw.levels);

        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffReader = new BufferedReader(inputReader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while ((line = buffReader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public static List<Level> loadAllLevels(Context ctx) {
        List<Level> levels = new ArrayList<>();

        String raw = loadRaw(ctx);
        Log.e("syf", "raw:\n" + raw);

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            dBuilder = dbFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(raw));
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();

            Log.e("aaa", "Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("level");
            Log.e("aaa", "length: " + nodeList.getLength());

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Level level = new Level();
                    level.title = element.getAttribute("name");
                    Log.e("aaa", "level: " + level.title);
                    level.maxWrongGuesses = Integer.parseInt(element.getAttribute("wrong_guesses"));

                    NodeList pairs = element.getElementsByTagName("pair");
                    Log.e("aaa", "pairs len: " + pairs.getLength());

                    for (int o = 0; o < pairs.getLength(); o++) {
                        Node node2 = pairs.item(o);

                        String val = ((Element) node2).getAttribute("filename");
                        Log.e("aaa", "pair: " + val);

                        level.pairs.add(val);
                    }

                    Collections.shuffle(level.pairs);
                    levels.add(level);
                }
            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return levels;
    }
}
