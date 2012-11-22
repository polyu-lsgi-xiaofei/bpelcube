package gr.uoa.di.s3lab.envision.scsclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TimeZone;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.postgis.MultiPolygon;
import org.w3c.dom.Node;

import com.s3lab.space.actions.query.semantic.RDFS_WSMLQuery;
import com.s3lab.space.common.ResultsList;
import com.s3lab.space.common.SemanticJavaSpace;
import com.s3lab.space.common.XMLEntryNode;
import com.s3lab.space.spatial_database.TemporalFeature;
import com.s3lab.space.struct.core.AffiliationType;
import com.s3lab.space.struct.core.Bond;
import com.s3lab.space.struct.core.Role;
import com.s3lab.space.struct.wsml.RDFS_WSMLMetaInformation;

import net.jini.core.discovery.LookupLocator;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;

public class SCSClient {
	
	private static SCSClient sharedInstance = null;
	private SemanticJavaSpace space = null;
	private String hostname;
	
	private SCSClient(){
		sharedInstance = this;
	}
	
	public static SCSClient AccessSCSClient(){
		if(sharedInstance == null){
           sharedInstance = new SCSClient();
        }
        return sharedInstance;
	}
	
	public boolean spaceHasBeenInitialized(){
		if(space!=null)
			return true;
		else
			return false;
	}
	
	public void initSCCEngine(String hostnameString) /*throws RemoteException, IOException, ClassNotFoundException*/{
		
		System.out.println("initSCCEngine start");
		hostname = "jini://" + hostnameString + "/";
		LookupLocator locator = null;
		try {
			locator = new LookupLocator(hostname);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//LookupLocator locator = new LookupLocator("jini://pleiades.di.uoa.gr/");
		//LookupLocator locator = new LookupLocator("jini://pigi");
        System.out.println("Locator details:" + locator.getHost() + ": " + locator.getPort());
        
        System.out.println("locatorName= " + locator.toString());

        try {
			space = (SemanticJavaSpace) locator.getRegistrar().lookup(
			    new ServiceTemplate(null,
			    new Class[]{SemanticJavaSpace.class},
			    null));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (space != null) {
            System.out.println("JavaSpace Service discovered !!");
        } else {
            System.out.println("JavaSpace discovery failed!!");
        }
        
        //delete from here!!!!!!!!!!!!!!!!!!!!!!!!
        /*try {
			space.initializeDBs();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	*/
        //delete until here!!!!!!!!!!!!!!!!!!!!!!!!
		

	}
	
	
	public Lease write(Node node,  long desiredTTL, URI metaInformation, String syntacticType, String processIdScope, String processInstanceScope, String multipolygon, Timestamp startTime, Timestamp endTime, TimeZone tz) /*throws SQLException, RemoteException, TransactionException*/{
		
		//initialize the real information stored in the node element
		XMLEntryNode entryNode = new XMLEntryNode(node);
		
		//initialize the metaInformation along with the syntactic type
		RDFS_WSMLMetaInformation rdflInfo = new RDFS_WSMLMetaInformation();
		rdflInfo.setCategory(metaInformation);
        rdflInfo.setSyntType(syntacticType);
        MultiPolygon multipol = null;
        
        //initialize the spatial characteristics
        if(!multipolygon.equals("EMPTY"))
			try {
				multipol = new MultiPolygon(multipolygon);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        //initialize the temporal characteristics
		TemporalFeature tmpFeature;
		if(startTime!=null && endTime!=null && tz!=null)
			tmpFeature = new TemporalFeature(startTime, endTime, tz);
		else
			tmpFeature = null;
		
		if(desiredTTL==0)
			desiredTTL = Lease.FOREVER;
			
		//call the write and return the TTL returned from the SCS Engine
		//template of the write function: Lease writeMetaInfoScopeSpatioTemp(Entry entry, Transaction tnx, long ls, MetaInformation sinfo, String scopeName, MultiPolygon multiPol, TemporalFeature tmpFeature)
		if(processInstanceScope == null)
			try {
				return space.writeMetaInfoScopeSpatioTemp(entryNode, null, desiredTTL, rdflInfo, processIdScope, multipol, tmpFeature);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			try {
				return space.writeMetaInfoScopeSpatioTemp(entryNode, null, desiredTTL, rdflInfo, processIdScope + "_" + processInstanceScope, multipol, tmpFeature);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		return null;
	}
	
public Lease write(DocTest node,  long desiredTTL, URI metaInformation, String syntacticType, String processIdScope, String processInstanceScope, String multipolygon, Timestamp startTime, Timestamp endTime, TimeZone tz) /*throws SQLException, RemoteException, TransactionException*/{
		
		//initialize the real information stored in the node element
		//XMLEntryNode entryNode = new XMLEntryNode(node);
		
		//initialize the metaInformation along with the syntactic type
		RDFS_WSMLMetaInformation rdflInfo = new RDFS_WSMLMetaInformation();
		rdflInfo.setCategory(metaInformation);
        rdflInfo.setSyntType(syntacticType);
        
        //initialize the spatial characteristics
        MultiPolygon multipol = null;
		try {
			multipol = new MultiPolygon(multipolygon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //initialize the temporal characteristics
		TemporalFeature tmpFeature;
		if(startTime!=null && endTime!=null && tz!=null)
			tmpFeature = new TemporalFeature(startTime, endTime, tz);
		else
			tmpFeature = null;
		
		if(desiredTTL==0)
			desiredTTL = Lease.FOREVER;
		
		//call the write and return the TTL returned from the SCS Engine
		//template of the write function: Lease writeMetaInfoScopeSpatioTemp(Entry entry, Transaction tnx, long ls, MetaInformation sinfo, String scopeName, MultiPolygon multiPol, TemporalFeature tmpFeature)
		if(processInstanceScope == null)
			try {
				return space.writeMetaInfoScopeSpatioTemp(node, null, desiredTTL, rdflInfo, processIdScope, multipol, tmpFeature);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			try {
				return space.writeMetaInfoScopeSpatioTemp(node, null, desiredTTL, rdflInfo, processIdScope + "_" + processInstanceScope, multipol, tmpFeature);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		return null;
	}
	
	
	
	public ResultsList read(URI metaInformation, String syntacticType, String processIdScope, String processInstanceScope, String multipolygon, Timestamp startTime, Timestamp endTime, TimeZone tz) {
	
		
		//ResultsList readMetaInfoScopeSpatioTemp(Entry entry, Transaction tnx, com.s3lab.space.actions.SpaceQuery query, String scope, boolean propagateQuery, MultiPolygon multiPol, TemporalFeature tmpFeature)
        //readMetaInfoScopeSpatioTemp(null, null, query, scopeName, propagateQuery, multiPol, tmpFeature);
		//ResultsList results1 = semspace.readMetaInfoScopeSpatioTemp(query1, scopeName, true, multiPol, tmpFeature);

		//initialize the query attributes, namely the MetaInformation, the SynatcticType, 
		//and the deegre of match which is by default 1.0 in this application
		RDFS_WSMLQuery query = new RDFS_WSMLQuery("formula");
        query.setSyntType(syntacticType);
        query.setCategory(metaInformation);
        query.setSemMatchFloor(1.0f);
		
		//initialize the spatial characteristics
        MultiPolygon multipol = null;
		try {
			multipol = new MultiPolygon(multipolygon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //initialize the temporal characteristics
		TemporalFeature tmpFeature;
		if(startTime!=null && endTime!=null && tz!=null)
			tmpFeature = new TemporalFeature(startTime, endTime, tz);
		else
			tmpFeature = null;
		
		//call the write and return the matching results
		if(processInstanceScope == null)
			try {
				return space.readMetaInfoScopeSpatioTemp(null, null, query, processIdScope, true, multipol, tmpFeature);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			try {
				return space.readMetaInfoScopeSpatioTemp(null, null, query, processIdScope + "_" + processInstanceScope, true, multipol, tmpFeature);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
}
	
	/*	
	 *  @since 15-11-2012	 
	 *  @todo Pigi should update code at removeProcessScope function so as to check if the scope is already removed and 
	 *  to remove all related process instance scopes
	 *  DONE!
	 * */
	public void removeProcessScope(String processIdScope){
		Collection<String> matchingScopes = null;
		try {
			matchingScopes = space.findScope(processIdScope);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!matchingScopes.isEmpty()){
			try{
				space.removeAffiliatedScopes(processIdScope);
				space.removeScope(processIdScope);
			}
			catch(RemoteException rex){
				rex.printStackTrace();
			}	
		}
	}
	
	
	
	public void createProcessScope (String processIdScope) /*throws URISyntaxException, RemoteException*/{
		
		Collection<String> matchingScopes = null;
		try {
			matchingScopes = space.findScope(processIdScope);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(!matchingScopes.isEmpty()){
			RDFS_WSMLMetaInformation rdflInfo = new RDFS_WSMLMetaInformation();
			String concept = "http://purl.org/ifgi/dul";	//the value of this variable does not affect as - the only restriction
															//is to be from a namespace belonging to an ontology that is parsed during the 
															//initialization process of the SCS Engine
			URI uri = null;
			try {
				uri = new URI(concept);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        rdflInfo.setCategory(uri);
			try {
				space.createScope(processIdScope, rdflInfo, Long.MAX_VALUE);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void createProcessInstanceScope (String processIdScope, String processInstanceScope) /*throws URISyntaxException, RemoteException*/{
		
		RDFS_WSMLMetaInformation rdflInfo = new RDFS_WSMLMetaInformation();
		String concept = "http://purl.org/ifgi/dul";	//the value of this variable does not affect as - the only restriction
														//is to be from a namespace belonging to an ontology that is parsed during the 
														//initialization process of the SCS Engine
		URI uri = null;
		try {
			uri = new URI(concept);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        rdflInfo.setCategory(uri);
		try {
			space.createScope(processIdScope + "_" + processInstanceScope, rdflInfo, Long.MAX_VALUE);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addAffiliation(String processInstanceScope, String processIdScope) /*throws URISyntaxException, RemoteException*/{
		
		Role[] roles = new Role[1];
        roles[0] = new Role("ProcessInstanceBond");
        
        AffiliationType affType = new AffiliationType("AffType");
		affType.setRoles(roles);
		
        URI localURI1 = null;
		try {
			localURI1 = new URI ("jini://pleiades.di.uoa.gr/SemanticContextSpace" + "#" + processIdScope + "_" + processInstanceScope);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Bond fromScope = new Bond(localURI1, new Role("BondProcessIdProcessInstance")); 
		
		URI localURI2 = null;
		try {
			localURI2 = new URI ("jini://pleiades.di.uoa.gr/SemanticContextSpace" + "#" + processIdScope);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bond toScope = new Bond(localURI2, new Role("BondProcessIdProcessInstance"));
		
		Collection<Bond> toScopes = new ArrayList<Bond>();
        toScopes.add(toScope);
        
        try {
			space.addAffiliation(fromScope, toScopes, affType);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
public void removeAffiliation(String processInstanceScope, String processIdScope) /*throws URISyntaxException, RemoteException*/{
		
		Role[] roles = new Role[1];
        roles[0] = new Role("ProcessInstanceBond");
        
        AffiliationType affType = new AffiliationType("AffType");
		affType.setRoles(roles);
		
        URI localURI1 = null;
		try {
			localURI1 = new URI ("jini://pleiades.di.uoa.gr/SemanticContextSpace" + "#" + processIdScope + "_" + processInstanceScope);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Bond fromScope = new Bond(localURI1, new Role("BondProcessIdProcessInstance")); 
		
		URI localURI2 = null;
		try {
			localURI2 = new URI ("jini://pleiades.di.uoa.gr/SemanticContextSpace" + "#" + processIdScope);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bond toScope = new Bond(localURI2, new Role("BondProcessIdProcessInstance"));
        
        try {
			space.removeAffiliation(fromScope, toScope, affType);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	

}
