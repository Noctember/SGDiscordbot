package net.steamgamers.discordbot.utils;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.*;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

/**
 * This file is a part of [SG] DiscordBot, located on net.steamgamers.discordbot.utils
 *
 * @author SweetKebab_
 * Created the 2019-01-18 at 16:58.
 */
public class test {
    public static void main(String[] args) throws Exception {
//        XMLReader myReader = XMLReaderFactory.createXMLReader();
//        myReader.setContentHandler(handler);
//        myReader.parse(new InputSource(new URL("http://steamgamers.gameme.com/api/playerinfo/STEAM_0:1:151420642").openStream()));
        try {
            DOMParser parser = new DOMParser();
            parser.parse("http://steamgamers.gameme.com/api/playerinfo/csgo7/STEAM_0:1:151420642");
            Document doc = parser.getDocument();
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //pretty printing
            transformer.transform(domSource, result);
            System.out.println("XML IN String format is: \n" + writer.toString());
            NodeList nodes = doc.getElementsByTagName("*");
            System.out.println("There are " + nodes.getLength() +
                    "  elements.");
            for (int in = 0; in < nodes.getLength(); in++) {
                listAllAttributes((Element) nodes.item(in));

            }

//            listAllAttributes((Element) nodes.item());
//            listAllAttributes((Element) nodes.item(0));
//            listAllAttributes((Element) nodes.item(0));
        } catch (Exception ex) {
            System.out.println(ex);
        }
//        System.out.println(doc.getDocumentElement().getAttributeNode("playerinfo"));
//        System.out.println(myReader.get);
        /*NodeList nodeList = doc.getElementsByTagName("*");
        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);
            NodeList nodeList1 = node.getChildNodes();
            for (int in = 0; i < nodeList1.getLength(); i++) {
                if (node.getNodeType() == Node.ELEMENT_NODE)
                    listAllAttributes((Element) node);
            }

                Document document = node.getOwnerDocument();
            DOMImplementationLS domImplLS = (DOMImplementationLS) document
                    .getImplementation();
            LSSerializer serializer = domImplLS.createLSSerializer();
            String str = serializer.writeToString(node);
//            System.out.println(str);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                listAllAttributes((Element) node);
                // do something with the current element
//                System.out.println(node.getNodeName());

            }
        }
*//*        Element nList = ;
        System.out.println(nList.getAttribute("player"));
        System.out.println(nList.getTagName());*/

    }
    public static void listAllAttributes(Element element) {

        System.out.println("List attributes for node: " + element.getNodeName()+"\n"+element.getTextContent());

        // get a map containing the attributes of this node
        NamedNodeMap attributes = element.getAttributes();

        // get the number of nodes in this map

        int numAttrs = attributes.getLength();


        for (int i = 0; i < numAttrs; i++) {
            Attr attr = (Attr) attributes.item(i);
            String attrName = attr.getNodeName();

            String attrValue = attr.getNodeValue();
            System.out.println("Found attribute: " + attrName + " with value: " + attrValue);

        }
    }
}
