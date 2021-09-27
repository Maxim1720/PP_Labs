package Lab_1.DOM;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class AvgCorrector {
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;
    NodeList students;
    String path;

    public AvgCorrector(String sourcePath)
    {
        this.path = sourcePath;
        initDocument();
    }
    private void initDocument()
    {
        dbf = DocumentBuilderFactory.newInstance();
        initDb();
        initDoc();
    }

    private void initDb()
    {
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    private void initDoc()
    {
        initDb();
        try {
            doc = db.parse(new File(path));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public Document correctAvg()
    {
        students = doc.getElementsByTagName("student");
        for(int i=0;i<students.getLength();i++)
        {
            Element student = (Element) students.item(i);
            NodeList subjects = student.getElementsByTagName("subject");

            double avg = 0;
            Element currAvgEl = (Element) student.getElementsByTagName("average").item(0);

            for(int j=0;j<subjects.getLength();j++) {
                Element currSubj = (Element) subjects.item(j);
                avg += Double.parseDouble(currSubj.getAttributes().getNamedItem("score").getNodeValue());
            }
            avg/=subjects.getLength();
            currAvgEl.setTextContent(String.valueOf(avg));
        }
        System.out.println();
        return doc;

    }


}
