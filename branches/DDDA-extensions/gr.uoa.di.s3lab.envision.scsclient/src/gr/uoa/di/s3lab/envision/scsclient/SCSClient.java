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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.jini.core.discovery.LookupLocator;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;
import org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope;

public class SCSClient {
    private static final Logger _logger = Logger.getLogger(SCSClient.class.getCanonicalName());
    private static SCSClient sharedInstance = null;
    private SemanticJavaSpace space = null;
    private String hostname;
    private Map<String, Collection<Scope>> scopeProcessMappings = new LinkedHashMap<String, Collection<Scope>>();
    
    private SCSClient() {
        sharedInstance = this;
    }
    
    public static SCSClient AccessSCSClient() {
        if (sharedInstance == null)
            sharedInstance = new SCSClient();
        return sharedInstance;
    }

//	////////////////////////////////////////////////////////////////////
//                      initialization/management operations
    public void addScopeAssignment(String processId, String processInstanceId, Collection<Scope> assignedScopes) {
        _logger.log(Level.INFO,"Adding scopes for process "+processId+" & instance id "+processInstanceId);
        this.scopeProcessMappings.put(getScopeName(processId, processInstanceId), assignedScopes);
    }
    
    private String getScopeName(String processId, String processInstanceId) {
        return (processId != null ? processId : "") + (processInstanceId != null ? "_"+processInstanceId : "");
    }

    public boolean spaceHasBeenInitialized() {
        if (space != null)
            return true;
        else
            return false;
    }
    
    public void initSCCEngine(String hostnameString) /*throws RemoteException, IOException, ClassNotFoundException*/ {
        
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
        } catch (Exception e) {
            // TODO Auto-generated catch block
            _logger.log(Level.SEVERE, "Exception while trying to connect to SCS Engine", e);
            e.printStackTrace();
        }
        
        if (space != null)
            _logger.log(Level.INFO,"JavaSpace Service discovered !!");
        else
            _logger.log(Level.INFO,"JavaSpace discovery failed!!");

        //delete from here!!!!!!!!!!!!!!!!!!!!!!!!
        /*try {
         space.initializeDBs();
         } catch (RemoteException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }	*/
        //delete until here!!!!!!!!!!!!!!!!!!!!!!!!
        
        
    }

//	/////////////////////////////////////////////////////////////////////////////////////
//                      SCS Engine related operations
//      /////////////////////////////////////////////////////////////////////////////////////        
    public Lease write(Node node, long desiredTTL, URI metaInformation, String syntacticType, String processIdScope,
            String processInstanceScope, String multipolygon, Timestamp startTime, Timestamp endTime, TimeZone tz) /*throws SQLException, RemoteException, TransactionException*/ {

        //initialize the real information stored in the node element
        XMLEntryNode entryNode = new XMLEntryNode(node);

        //initialize the metaInformation along with the syntactic type
        RDFS_WSMLMetaInformation rdflInfo = new RDFS_WSMLMetaInformation();
        rdflInfo.setCategory(metaInformation);
        rdflInfo.setSyntType(syntacticType);
        
        MultiPolygon multipol = null;
        //initialize the spatial characteristics
        //if(!multipolygon.equals("EMPTY"))
        if (multipolygon != null)
            try {
                multipol = new MultiPolygon(multipolygon);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        //initialize the temporal characteristics
        TemporalFeature tmpFeature;
        if (startTime != null && endTime != null && tz != null)
            tmpFeature = new TemporalFeature(startTime, endTime, tz);
        else
            tmpFeature = null;
        
        if (desiredTTL == 0)
            desiredTTL = Lease.FOREVER;

        //call the write and return the TTL returned from the SCS Engine
        //template of the write function: Lease writeMetaInfoScopeSpatioTemp(Entry entry, Transaction tnx, long ls, MetaInformation sinfo, String scopeName, MultiPolygon multiPol, TemporalFeature tmpFeature)
        Lease finalLease = null;
     if (scopeProcessMappings.containsKey(this.getScopeName(processIdScope, processInstanceScope)))
            for (Scope _scope : scopeProcessMappings.get(this.getScopeName(processIdScope, processInstanceScope)))
                try {
                    if (multipol == null && tmpFeature == null)
                        finalLease= space.writeMetaInfoScope(entryNode, null, desiredTTL, rdflInfo, URI.create(_scope.getStringValue()).getFragment());
                    else
                        finalLease= space.writeMetaInfoScopeSpatioTemp(entryNode, null, desiredTTL, rdflInfo, URI.create(_scope.getStringValue()).getFragment(), multipol, tmpFeature);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
        else            
            try {
                if (multipol == null && tmpFeature == null)
                    finalLease= space.writeMetaInfoScope(entryNode, null, desiredTTL, rdflInfo, getScopeName(processIdScope,
                            processInstanceScope));
                else
                    finalLease= space.writeMetaInfoScopeSpatioTemp(entryNode, null, desiredTTL, rdflInfo, this.getScopeName(processIdScope,
                            processInstanceScope), multipol, tmpFeature);
            } catch (Exception e) {
                e.printStackTrace();
            }

        return finalLease;
    }
    
    @Deprecated
    public Lease write(DocTest node, long desiredTTL, URI metaInformation, String syntacticType, String processIdScope,
            String processInstanceScope, String multipolygon, Timestamp startTime, Timestamp endTime, TimeZone tz) /*throws SQLException, RemoteException, TransactionException*/ {

        //initialize the real information stored in the node element
        //XMLEntryNode entryNode = new XMLEntryNode(node);

        //initialize the metaInformation along with the syntactic type
        RDFS_WSMLMetaInformation rdflInfo = new RDFS_WSMLMetaInformation();
        rdflInfo.setCategory(metaInformation);
        rdflInfo.setSyntType(syntacticType);

        //initialize the spatial characteristics
        MultiPolygon multipol = null;
        if (multipolygon != null)
            try {
                multipol = new MultiPolygon(multipolygon);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        //initialize the temporal characteristics
        TemporalFeature tmpFeature;
        if (startTime != null && endTime != null && tz != null)
            tmpFeature = new TemporalFeature(startTime, endTime, tz);
        else
            tmpFeature = null;
        
        if (desiredTTL == 0)
            desiredTTL = Lease.FOREVER;

        //call the write and return the TTL returned from the SCS Engine
        //template of the write function: Lease writeMetaInfoScopeSpatioTemp(Entry entry, Transaction tnx, long ls, MetaInformation sinfo, String scopeName, MultiPolygon multiPol, TemporalFeature tmpFeature)
        if (processInstanceScope == null)
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
                return space.writeMetaInfoScopeSpatioTemp(node, null, desiredTTL, rdflInfo, processIdScope + "_" + processInstanceScope,
                        multipol, tmpFeature);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (TransactionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }        
        return null;
    }
    
    public ResultsList read(URI metaInformation, String syntacticType, String processIdScope, String processInstanceScope,
            String multipolygon, Timestamp startTime, Timestamp endTime, TimeZone tz) {


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
        if (multipolygon != null)
            try {
                multipol = new MultiPolygon(multipolygon);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        //initialize the temporal characteristics
        TemporalFeature tmpFeature;
        if (startTime != null && endTime != null && tz != null)
            tmpFeature = new TemporalFeature(startTime, endTime, tz);
        else
            tmpFeature = null;
        
        ResultsList results = null;
        //call the read and return the matching results
        if (this.scopeProcessMappings.containsKey(getScopeName(processIdScope, processInstanceScope)))
            for(Scope _scope: scopeProcessMappings.get(getScopeName(processIdScope, processInstanceScope)))
                try {
                    if(results ==null)
                        results = new ResultsList();
    
                    String scopeUri=(URI.create(_scope.getStringValue()).getFragment()!=null)?URI.create(_scope.getStringValue()).getFragment():_scope.getStringValue();

                    if (multipol == null && tmpFeature == null) {
                        _logger.log(Level.FINER, "[SCSClient] readMetaInfoScope");
                        results.addResultList(space.readMetaInfoScope(null, null, query, scopeUri, true));
                    } 
                    else {
                        _logger.log(Level.FINER, "[SCSClient] readMetaInfoScopeSpatioTemp ["+multipolygon.toString()+", "+tmpFeature.toString()+"]");
                        results.addResultList(space.readMetaInfoScopeSpatioTemp(null, null, query, scopeUri,true, multipol, tmpFeature));
                    }
                    
                    if (results != null)
                        _logger.log(Level.INFO, "[SCSClient]" + results.getCollection().size() + " results returned");

                } catch (Exception e) {
                    _logger.log(Level.SEVERE, "Exception while querying the SCS engine", e);
                    continue;
                }
        else
            try {
                if (multipol == null && tmpFeature == null) {
                    _logger.log(Level.FINER, "[SCSClient] readMetaInfoScope");
                    results = space.readMetaInfoScope(null, null, query, this.getScopeName(processIdScope, processInstanceScope), true);
                } else {
                    _logger.log(Level.FINER, "[SCSClient] readMetaInfoScopeSpatioTemp");
                    results = space.readMetaInfoScopeSpatioTemp(null, null, query, this.getScopeName(processIdScope, processInstanceScope),
                            true,
                            multipol, tmpFeature);
                }
                if (results != null)
                    _logger.log(Level.INFO, "[SCSClient]" + results.getCollection().size() + " results returned");

            } catch (Exception e) {
                _logger.log(Level.SEVERE, "Exception while querying the SCS engine", e);
            }

        return results;
    }

    /*	
     *  @since 15-11-2012	 
     *  @todo Pigi should update code at removeProcessScope function so as to check if the scope is already removed and 
     *  to remove all related process instance scopes
     *  DONE!
     * */
    public void removeProcessScope(String processIdScope) {
        Collection<String> matchingScopes = null;
        try {
            matchingScopes = space.findScope(processIdScope);
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (!matchingScopes.isEmpty())
            try {
                space.removeAffiliatedScopes(processIdScope);
                space.removeScope(processIdScope);
            } catch (RemoteException rex) {
                rex.printStackTrace();
            }
    }
    
    public void createProcessScope(String processIdScope) /*throws URISyntaxException, RemoteException*/ {
        
        Collection<String> matchingScopes = null;
        try {
            matchingScopes = space.findScope(processIdScope);
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        if (!matchingScopes.isEmpty()) {
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
    
    public void createProcessInstanceScope(String processIdScope, String processInstanceScope) /*throws URISyntaxException, RemoteException*/ {
        
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
    
    public void addAffiliation(String processInstanceScope, String processIdScope) /*throws URISyntaxException, RemoteException*/ {
        
        Role[] roles = new Role[1];
        roles[0] = new Role("ProcessInstanceBond");
        
        AffiliationType affType = new AffiliationType("AffType");
        affType.setRoles(roles);
        
        URI localURI1 = null;
        try {
            localURI1 = new URI("jini://pleiades.di.uoa.gr/SemanticContextSpace" + "#" + processIdScope + "_" + processInstanceScope);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Bond fromScope = new Bond(localURI1, new Role("BondProcessIdProcessInstance"));        
        
        URI localURI2 = null;
        try {
            localURI2 = new URI("jini://pleiades.di.uoa.gr/SemanticContextSpace" + "#" + processIdScope);
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
    
    public void removeAffiliation(String processInstanceScope, String processIdScope) /*throws URISyntaxException, RemoteException*/ {
        
        Role[] roles = new Role[1];
        roles[0] = new Role("ProcessInstanceBond");
        
        AffiliationType affType = new AffiliationType("AffType");
        affType.setRoles(roles);
        
        URI localURI1 = null;
        try {
            localURI1 = new URI("jini://pleiades.di.uoa.gr/SemanticContextSpace" + "#" + processIdScope + "_" + processInstanceScope);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Bond fromScope = new Bond(localURI1, new Role("BondProcessIdProcessInstance"));        
        
        URI localURI2 = null;
        try {
            localURI2 = new URI("jini://pleiades.di.uoa.gr/SemanticContextSpace" + "#" + processIdScope);
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
