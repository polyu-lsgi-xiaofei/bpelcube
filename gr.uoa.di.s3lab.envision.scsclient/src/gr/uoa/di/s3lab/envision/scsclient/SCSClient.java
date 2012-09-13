package gr.uoa.di.s3lab.envision.scsclient;

import java.io.IOException;
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
	private SemanticJavaSpace space;
	
	private SCSClient(){
		sharedInstance = this;
	}
	
	public static SCSClient AccessSCSClient(){
		if(sharedInstance == null){
           sharedInstance = new SCSClient();
        }
        return sharedInstance;
	}
	
	public void initSCCEngine() throws RemoteException, IOException, ClassNotFoundException{
		LookupLocator locator = new LookupLocator("jini://pleiades.di.uoa.gr/");
        //LookupLocator locator = new LookupLocator("jini://pigi");
        System.out.println("Locator details:" + locator.getHost() + ": " + locator.getPort());
        
        System.out.println("locatorName= " + locator.toString());

        space = (SemanticJavaSpace) locator.getRegistrar().lookup(
            new ServiceTemplate(null,
            new Class[]{SemanticJavaSpace.class},
            null));

        if (space != null) {
            System.out.println("JavaSpace Service discovered !!");
        } else {
            System.out.println("JavaSpace discovery failed!!");
        }
        
        space.initializeDBs();

	}
	
	
	public Lease write(Node node,  long desiredTTL, URI metaInformation, String syntacticType, String scopeName, String multipolygon, Timestamp startTime, Timestamp endTime, TimeZone tz) throws SQLException, RemoteException, TransactionException{
		
		//initialize the real information stored in the node element
		XMLEntryNode entryNode = new XMLEntryNode(node);
		
		//initialize the metaInformation along with the syntactic type
		RDFS_WSMLMetaInformation rdflInfo = new RDFS_WSMLMetaInformation();
		rdflInfo.setCategory(metaInformation);
        rdflInfo.setSyntType(syntacticType);
        
        //initialize the spatial characteristics
        MultiPolygon multipol = new MultiPolygon(multipolygon);
        
        //initialize the temporal characteristics
		TemporalFeature tmpFeature;
		if(startTime!=null && endTime!=null && tz!=null)
			tmpFeature = new TemporalFeature(startTime, endTime, tz);
		else
			tmpFeature = null;
		
		//call the write and return the TTL returned from the SCS Engine
		//template of the write function: Lease writeMetaInfoScopeSpatioTemp(Entry entry, Transaction tnx, long ls, MetaInformation sinfo, String scopeName, MultiPolygon multiPol, TemporalFeature tmpFeature)
		return space.writeMetaInfoScopeSpatioTemp(entryNode, null, desiredTTL, rdflInfo, scopeName, multipol, tmpFeature);
		
	}
	
public Lease write(DocTest node,  long desiredTTL, URI metaInformation, String syntacticType, String scopeName, String multipolygon, Timestamp startTime, Timestamp endTime, TimeZone tz) throws SQLException, RemoteException, TransactionException{
		
		//initialize the real information stored in the node element
		//XMLEntryNode entryNode = new XMLEntryNode(node);
		
		//initialize the metaInformation along with the syntactic type
		RDFS_WSMLMetaInformation rdflInfo = new RDFS_WSMLMetaInformation();
		rdflInfo.setCategory(metaInformation);
        rdflInfo.setSyntType(syntacticType);
        
        //initialize the spatial characteristics
        MultiPolygon multipol = new MultiPolygon(multipolygon);
        
        //initialize the temporal characteristics
		TemporalFeature tmpFeature;
		if(startTime!=null && endTime!=null && tz!=null)
			tmpFeature = new TemporalFeature(startTime, endTime, tz);
		else
			tmpFeature = null;
		
		//call the write and return the TTL returned from the SCS Engine
		//template of the write function: Lease writeMetaInfoScopeSpatioTemp(Entry entry, Transaction tnx, long ls, MetaInformation sinfo, String scopeName, MultiPolygon multiPol, TemporalFeature tmpFeature)
		return space.writeMetaInfoScopeSpatioTemp(node, null, desiredTTL, rdflInfo, scopeName, multipol, tmpFeature);
		
	}
	
	
	
	public ResultsList read(URI metaInformation, String syntacticType, String scopeName, String multipolygon, Timestamp startTime, Timestamp endTime, TimeZone tz) throws SQLException, RemoteException, TransactionException{
		
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
        MultiPolygon multipol = new MultiPolygon(multipolygon);
        
        //initialize the temporal characteristics
		TemporalFeature tmpFeature;
		if(startTime!=null && endTime!=null && tz!=null)
			tmpFeature = new TemporalFeature(startTime, endTime, tz);
		else
			tmpFeature = null;
		
		//call the write and return the matching results
		return space.readMetaInfoScopeSpatioTemp(null, null, query, scopeName, true, multipol, tmpFeature);
		
	}
	
	
	public void createProcessScope (String processIdScope) throws URISyntaxException, RemoteException{
		
		RDFS_WSMLMetaInformation rdflInfo = new RDFS_WSMLMetaInformation();
		String concept = "http://purl.org/ifgi/dul";	//the value of this variable does not affect as - the only restriction
														//is to be from a namespace belonging to an ontology that is parsed during the 
														//initialization process of the SCS Engine
		URI uri = new URI(concept);
        rdflInfo.setCategory(uri);
		space.createScope(processIdScope, rdflInfo, Long.MAX_VALUE);
		
	}
	
	public void createProcessInstanceScope (String processIdScope, String processInstanceScope) throws URISyntaxException, RemoteException{
		
		RDFS_WSMLMetaInformation rdflInfo = new RDFS_WSMLMetaInformation();
		String concept = "http://purl.org/ifgi/dul";	//the value of this variable does not affect as - the only restriction
														//is to be from a namespace belonging to an ontology that is parsed during the 
														//initialization process of the SCS Engine
		URI uri = new URI(concept);
        rdflInfo.setCategory(uri);
		space.createScope(processIdScope + "+" + processInstanceScope, rdflInfo, Long.MAX_VALUE);
		
	}
	
	public void addAffiliation(String processIdScope, String processInstanceScope) throws URISyntaxException, RemoteException{
		
		Role[] roles = new Role[1];
        roles[0] = new Role("ProcessInstanceBond");
        
        AffiliationType affType = new AffiliationType("AffType");
		affType.setRoles(roles);
		
        URI localURI1 = new URI ("jini://pleiades.di.uoa.gr/SemanticContextSpace" + "#" + processIdScope);
        Bond fromScope = new Bond(localURI1, new Role("BondProcessIdProcessInstance")); 
		
		URI localURI2 = new URI ("jini://localhost/SemanticContextSpace" + "#" + processIdScope + "+" + processInstanceScope);
		Bond toScope = new Bond(localURI2, new Role("BondProcessIdProcessInstance"));
		
		Collection<Bond> toScopes = new ArrayList<Bond>();
        toScopes.add(toScope);
        
        space.addAffiliation(fromScope, toScopes, affType);
        
	}
	

}
