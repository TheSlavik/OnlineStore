package org.example.store.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {

    public static Map<String, String> getConfig(String path) {
        Map<String, String> config = new LinkedHashMap<>();
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path);
            NodeList list = document.getDocumentElement().getChildNodes();
            for (int i = 1; i < list.getLength(); i += 2) {
                config.put(list.item(i).getNodeName(), list.item(i).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }
}
