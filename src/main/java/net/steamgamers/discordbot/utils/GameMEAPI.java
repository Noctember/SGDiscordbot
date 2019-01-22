package net.steamgamers.discordbot.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;


/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.utils
 *
 * @author SweetKebab_
 * Created the 2019-01-18 at 16:47.
 */
public class GameMEAPI {

    public static String getScrimStats(String name, String steamdID) throws Exception {
        String res = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo7/"+steamdID).openStream());

        NodeList nodes = doc.getElementsByTagName("*");
        for (int in = 0; in < nodes.getLength(); in++) {
            Element element = (Element) nodes.item(in);
            if(element.getNodeName().equals(name)) {
                res = element.getTextContent();
                return res;
            }
        }
        return res != null ? res : "N/A";
    }
    public static String getFavgunScrim(String name, String steamdID) throws Exception {
        String res = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo7/"+steamdID).openStream());

        NodeList nodes = doc.getElementsByTagName("*");
        for (int in = 0; in < nodes.getLength(); in++) {
            Element element = (Element) nodes.item(in);
            if(element.getNodeName().equals(name)) {
                res = element.getTextContent();
            }
        }
        return res != null ? res : "N/A";
    }
    public static String getTTTStats(String name, String steamdID) throws Exception {
        String res = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo8/"+steamdID).openStream());

        NodeList nodes = doc.getElementsByTagName("*");
        for (int in = 0; in < nodes.getLength(); in++) {
            Element element = (Element) nodes.item(in);
            if(element.getNodeName().equals(name)) {
                res = element.getTextContent();
                return res;
            }
        }
        return res != null ? res : "N/A";
    }
    public static String getFavgunTTT(String name, String steamdID) throws Exception {
        String res = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo8/"+steamdID).openStream());

        NodeList nodes = doc.getElementsByTagName("*");
        for (int in = 0; in < nodes.getLength(); in++) {
            Element element = (Element) nodes.item(in);
            if(element.getNodeName().equals(name)) {
                res = element.getTextContent();
            }
        }
        return res != null ? res : "N/A";
    }

    public static String getZEStats(String name, String steamdID) throws Exception {
        String res = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo2/"+steamdID).openStream());

        NodeList nodes = doc.getElementsByTagName("*");
        for (int in = 0; in < nodes.getLength(); in++) {
            Element element = (Element) nodes.item(in);
            if(element.getNodeName().equals(name)) {
                res = element.getTextContent();
                return res;
            }
        }
        return res != null ? res : "N/A";
    }
    public static String getFavgunZE(String name, String steamdID) throws Exception {
        String res = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo2/"+steamdID).openStream());

        NodeList nodes = doc.getElementsByTagName("*");
        for (int in = 0; in < nodes.getLength(); in++) {
            Element element = (Element) nodes.item(in);
            if(element.getNodeName().equals(name)) {
                res = element.getTextContent();
            }
        }
        return res != null ? res : "N/A";
    }
    public static String getMGStats(String name, String steamdID) throws Exception {
        String res = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo3/"+steamdID).openStream());

        NodeList nodes = doc.getElementsByTagName("*");
        for (int in = 0; in < nodes.getLength(); in++) {
            Element element = (Element) nodes.item(in);
            if(element.getNodeName().equals(name)) {
                res = element.getTextContent();
                return res;
            }
        }
        return res != null ? res : "N/A";
    }
    public static String getFavgunMG(String name, String steamdID) throws Exception {
        String res = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo3/"+steamdID).openStream());

        NodeList nodes = doc.getElementsByTagName("*");
        for (int in = 0; in < nodes.getLength(); in++) {
            Element element = (Element) nodes.item(in);
            if(element.getNodeName().equals(name)) {
                res = element.getTextContent();
            }
        }
        return res != null ? res : "N/A";
    }
    public static String getJBStats(String name, String steamdID) throws Exception {
        String res = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo4/"+steamdID).openStream());

        NodeList nodes = doc.getElementsByTagName("*");
        for (int in = 0; in < nodes.getLength(); in++) {
            Element element = (Element) nodes.item(in);
            if(element.getNodeName().equals(name)) {
                res = element.getTextContent();
                return res;
            }
        }
        return res != null ? res : "N/A";
    }
    public static String getFavgunJB(String name, String steamdID) throws Exception {
        String res = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL("http://steamgamers.gameme.com/api/playerinfo/csgo4/"+steamdID).openStream());

        NodeList nodes = doc.getElementsByTagName("*");
        for (int in = 0; in < nodes.getLength(); in++) {
            Element element = (Element) nodes.item(in);
            if(element.getNodeName().equals(name)) {
                res = element.getTextContent();
            }
        }
        return res != null ? res : "N/A";
    }
}
