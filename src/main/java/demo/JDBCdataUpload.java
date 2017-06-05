package demo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.*;

public class JDBCdataUpload {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/world";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "kola";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement statement = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("db.xml"));
            doc.getDocumentElement().normalize();

            int size = doc.getDocumentElement().getChildNodes().getLength();

            NodeList listOfChapter = doc.getElementsByTagName("DataEntrys");
            NodeList listofVerse = doc.getDocumentElement().getElementsByTagName("DataEntrys").item(0).getChildNodes();
            int x =2;
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

                    String sql = "UPDATE atmospheric_data SET month = ?, day = ? WHERE id = ?";

                    statement = conn.prepareStatement(sql);
                    statement.setInt(1, (int) Double.parseDouble(f));
                    statement.setInt(2, (int) Double.parseDouble(g));
                    statement.setInt(3, x);
                    x++;
                    statement.executeUpdate();
                }
            }

            System.out.println("Inserted records into the table...");

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(statement!=null)
                    conn.close();
            }catch(SQLException se){}
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}