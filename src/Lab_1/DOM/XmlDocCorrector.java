package Lab_1.DOM;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XmlDocCorrector {
     private File targetFile, sourceFile;
     private String src, target;
     private FileWriter fw = null;
     private FileReader fr = null;
     Document correctedDoc;

     public XmlDocCorrector(String src, String target)
     {
         this.src = src;
         this.target = target;

         initFiles();
         initTrgtWriter();
         initSrcReader();
         fillTrgtFile();
     }

     private void initFiles()
     {
         targetFile = new File(target);
         sourceFile = new File(src);
     }

     private void initTrgtWriter()
     {
         try {
             fw = new FileWriter(targetFile);
         }
         catch (IOException e) {
             e.printStackTrace();
         }
     }
    private void initSrcReader()
    {
        try {
            fr = new FileReader(sourceFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

     private void fillTrgtFile()
     {
         try {
             while (fr.ready())
             {
                 fw.write(fr.read());
             }
             fr.close();
             fw.close();
         }
         catch (IOException e)
         {
             e.printStackTrace();
         }
     }


     public void correct()
     {
         AvgCorrector avgCorrector = new AvgCorrector(target);
         correctedDoc = avgCorrector.correctAvg();
     }

     public void saveChangesToTargetFile()
     {
         try {
             Transformer trf = TransformerFactory.newInstance().newTransformer();

             DOMSource src = new DOMSource(correctedDoc);
             FileOutputStream fos = new FileOutputStream(targetFile);
             StreamResult result = new StreamResult(fos);

             trf.setOutputProperty(OutputKeys.INDENT, "yes");
             //trf.setOutputProperty(OutputKeys.STANDALONE, "yes");
             trf.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "groups.dtd");

             trf.transform(src,result);
         } catch (TransformerException | IOException e) {
             e.printStackTrace();
         }
     }

}
