package gr.uoa.di.s3lab.envision.scsclient;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Pigi
 */
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import net.jini.core.entry.Entry;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Pigi
 */
public class DocTest implements Entry {

    Document doc;

    public DocTest(){
    }

    public DocTest(String fileName) throws ParserConfigurationException, SAXException, IOException{

        //Runtime runtime = Runtime.getRuntime();
        //long memUsedBefore = 0;
        //long memUsedAfter = 0;
        File file = new File(fileName);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        //for(int index =0;index<100;index++)
        //System.gc();
        //memUsedBefore = runtime.totalMemory()-runtime.freeMemory();
        doc = db.parse(file);
        //System.gc();
        //memUsedAfter = runtime.totalMemory()-runtime.freeMemory();
        //System.out.println("Memory Used On First Use: "+(memUsedAfter-memUsedBefore));
    }




}
