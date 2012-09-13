package gr.uoa.di.s3lab.envision.scsclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;

public class testClientMain {
	
	
	public static void main(String[] args) throws RemoteException, IOException, ClassNotFoundException, URISyntaxException, ParserConfigurationException, SAXException, SQLException, TransactionException {
		
		SCSClient client = SCSClient.AccessSCSClient();
		
		client.initSCCEngine();
		
		client.createProcessScope("processScope");
		client.createProcessInstanceScope("processScope", "processInstanceScope");
		client.addAffiliation("processScope", "processInstanceScope");
		
		DocTest DocT = new DocTest("C:\\Users\\Pigi\\Desktop\\envision\\SVN\\Papers\\SCS_Engine_2012\\evaluation\\hydroExecuteWPS3KB.xml");     
		String multiPol = "MULTIPOLYGON(((1 1, 1 -1, -1 -1, -1 1, 1 1)),((1 1, 3 1, 3 3, 1 3, 1 1)))";
        client.write(DocT, Lease.FOREVER, new URI("http://purl.org/ifgi/dul#Entity"), "testSyntType", "processScope", multiPol, null, null, null);
        client.write(DocT, Lease.FOREVER, new URI("http://purl.org/ifgi/dul#Entity"), "testSyntType", "processScope+processInstanceScope", multiPol, null, null, null);
		
        client.read(new URI("http://purl.org/ifgi/dul#Entity"), "testSyntType", "processScope", multiPol, null, null, null);
        client.read(new URI("http://purl.org/ifgi/dul#Entity"), "testSyntType", "processScope+processInstanceScope", multiPol, null, null, null);
		
	
	}

	
	
	
}
