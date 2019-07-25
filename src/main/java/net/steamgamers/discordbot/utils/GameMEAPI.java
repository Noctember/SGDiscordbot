package net.steamgamers.discordbot.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.utils
 *
 * @author SweetKebab_
 * Created the 2019-01-18 at 16:47.
 */
public class GameMEAPI {
    private String steamid;
    private String server;
    private HashMap<String, String> cache;

    public GameMEAPI(String steamid, String server) {
        this.steamid = steamid;
        this.server = server;
        cache = new HashMap<>();
    }

    public void prepare() throws ParserConfigurationException, MalformedURLException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc;
        NodeList nodes;
        switch (server) {
            case "ttt":
                doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo8/" + steamid).openStream());

                nodes = doc.getElementsByTagName("*");
                putInCache(nodes);
                break;
            case "scrim":
                doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo7/" + steamid).openStream());

                nodes = doc.getElementsByTagName("*");
                putInCache(nodes);
                break;
            case "mg":
                doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo3/" + steamid).openStream());

                nodes = doc.getElementsByTagName("*");
                putInCache(nodes);
                break;
            case "ze":
                doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo2/" + steamid).openStream());

                nodes = doc.getElementsByTagName("*");
                putInCache(nodes);
                break;
            case "jb":
                doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo4/" + steamid).openStream());

                nodes = doc.getElementsByTagName("*");
                putInCache(nodes);
                break;
        }
    }

    private void putInCache(NodeList nodes) {
        for (int in = 0; in < nodes.getLength(); in++) {
            Element element = (Element) nodes.item(in);
            if (cache.get("name") != null && element.getNodeName().equals("name"))
                cache.put("favgun", element.getTextContent());
            else
                cache.put(element.getNodeName(), element.getTextContent());
            System.out.println(element.getNodeName()+" : "+element.getTextContent());
        }
    }

    public String get(String key) {
        return cache.get(key);
    }
}
