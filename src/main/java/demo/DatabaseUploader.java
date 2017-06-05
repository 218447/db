package demo;

import demo.model.AtmosphericData;
import demo.model.WeatherDAO;
import demo.model.WeatherDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DatabaseUploader {

    //@Autowired
    WeatherDAO weatherDAO = new WeatherDAOImpl();

    public void parse(String filename) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(filename));
            doc.getDocumentElement().normalize();

            int size = doc.getDocumentElement().getChildNodes().getLength();

            NodeList listOfChapter = doc.getElementsByTagName("DataEntrys");
            NodeList listofVerse = doc.getDocumentElement().getElementsByTagName("DataEntrys").item(0).getChildNodes();
            for (int j = 0; j < listOfChapter.getLength(); j++) {
                for (int i = 0; i < listofVerse.getLength() / listOfChapter.getLength(); i++) {
                    Element chapter = (Element) listOfChapter.item(j);
                    String a = ((Element) doc.getDocumentElement().getElementsByTagName("DataEntrys").item(j)).getElementsByTagName("DataEntry").item(i).getAttributes().getNamedItem("city").getNodeValue();
                    String b = ((Element) doc.getDocumentElement().getElementsByTagName("DataEntrys").item(j)).getElementsByTagName("DataEntry").item(i).getAttributes().getNamedItem("Temerature").getNodeValue();
                    String c =  ((Element) doc.getDocumentElement().getElementsByTagName("DataEntrys").item(j)).getElementsByTagName("DataEntry").item(i).getAttributes().getNamedItem("WilgotnoscWzgledna").getNodeValue();
                    String d =  ((Element) doc.getDocumentElement().getElementsByTagName("DataEntrys").item(j)).getElementsByTagName("DataEntry").item(i).getAttributes().getNamedItem("PredkoscWiatru").getNodeValue();
                    String e =  ((Element) doc.getDocumentElement().getElementsByTagName("DataEntrys").item(j)).getElementsByTagName("DataEntry").item(i).getAttributes().getNamedItem("Hour").getNodeValue();
                    String f =  ((Element) doc.getDocumentElement().getElementsByTagName("DataEntrys").item(j)).getElementsByTagName("DataEntry").item(i).getAttributes().getNamedItem("Month").getNodeValue();
                    String  g= ((Element) doc.getDocumentElement().getElementsByTagName("DataEntrys").item(j)).getElementsByTagName("DataEntry").item(i).getAttributes().getNamedItem("Day").getNodeValue();

                  // AtmosphericData atmosphericData = new AtmosphericData(a, Integer.parseInt(b),Integer.parseInt(c),Integer.parseInt(d), e, f + g);
                   //weatherDAO.insertAtmosphericData(atmosphericData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] Args) {
        System.out.println("start uploadu");

        DatabaseUploader databaseUploader = new DatabaseUploader();
        databaseUploader.parse("db.xml");

        System.out.println("koniec uploadu");
    }
}