package gr.uoa.di.s3lab.envision.scsclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.s3lab.space.common.ResultEntry;
import com.s3lab.space.common.ResultsList;

import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;

public class TestClientMain {
	
	TestClientMain(){
		
	}
	
	public void docTest() throws ParserConfigurationException, SAXException, IOException, URISyntaxException{
		SCSClient client = SCSClient.AccessSCSClient();
		
		client.initSCCEngine("pleiades.di.uoa.gr");
		
		client.createProcessScope("processScope");
		client.createProcessInstanceScope("processScope", "processInstanceScope");
		client.addAffiliation("processInstanceScope", "processScope");
		
		DocTest DocT = new DocTest("C:\\Users\\Pigi\\Desktop\\envision\\SVN\\Papers\\SCS_Engine_2012\\evaluation\\hydroExecuteWPS3KB.xml");     
		String multiPol = "MULTIPOLYGON(((1 1, 1 -1, -1 -1, -1 1, 1 1)),((1 1, 3 1, 3 3, 1 3, 1 1)))";
        client.write(DocT, Lease.FOREVER, new URI("http://purl.org/ifgi/dul#Entity"), "testSyntType", "processScope", null, multiPol, null, null, null);
        client.write(DocT, Lease.FOREVER, new URI("http://purl.org/ifgi/dul#Entity"), "testSyntType", "processScope", "processInstanceScope", multiPol, null, null, null);
		
        client.read(new URI("http://purl.org/ifgi/dul#Entity"), "testSyntType", "processScope", null, multiPol, null, null, null);
        client.read(new URI("http://purl.org/ifgi/dul#Entity"), "testSyntType", "processScope", "processInstanceScope", multiPol, null, null, null);
		
	}
	
	public void findElements() throws URISyntaxException{
		SCSClient client = SCSClient.AccessSCSClient();	
		client.initSCCEngine("pleiades.di.uoa.gr");
		
		ResultsList list = client.read(new URI("http://purl.org/ifgi/geoevents#precipitation"), null, "LandslideEx-13", null, null, null, null, null);
		System.out.println("Result list size is :"+list.getCollection().size());
		for(ResultEntry entry: list.getCollection()){
			System.out.println("Reading entry class name :"+entry.getEntry().getClass().getSimpleName());
		}
	}
	
	public static void main(String[] args) throws RemoteException, IOException, ClassNotFoundException, URISyntaxException, ParserConfigurationException, SAXException, SQLException, TransactionException {
		TestClientMain client = new TestClientMain();
		client.findElements();
	}

	
	
	
}
