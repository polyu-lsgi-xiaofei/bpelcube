package gr.uoa.di.s3lab.bpelcube.prototype;

import gr.uoa.di.s3lab.bpelcube.prototype.services.SynchronizeDeployedBPELProcessesRequest;
import gr.uoa.di.s3lab.p2p.Log.Level;
import gr.uoa.di.s3lab.p2p.P2PEndpoint;
import gr.uoa.di.s3lab.p2p.P2PNodeDB;
import gr.uoa.di.s3lab.p2p.hypercube.HypercubeNode;

import java.net.URI;

public class BPELcubeNode extends HypercubeNode {
	
	private BPELcubeNodeDB db;

	public BPELcubeNode(URI home, String name, String domain, int port,
			Level logLevel) {
		super(home, name, domain, port, logLevel);
		db = new BPELcubeNodeDB(home.toString());
	}

	public BPELcubeNode(URI home, String name, String domain, URI address,
			int port, Level logLevel) {
		super(home, name, domain, address, port, logLevel);
		db = new BPELcubeNodeDB(home.toString());
	}
	
	@Override
	protected void join() throws Exception {
		
		super.join();
		
		if (getBootstrapURI() == null) {
			return;
		}
		
		// send request to synchronize deployed BPEL processes
		SynchronizeDeployedBPELProcessesRequest syncRequest = new SynchronizeDeployedBPELProcessesRequest();
		syncRequest.setRequester(getEndpoint());
		syncRequest.setDeployedProcessIds(db.getAllDeployedProcessIds());
		P2PEndpoint bootstrapEndpoint = new P2PEndpoint();
		bootstrapEndpoint.setAddress(getBootstrapURI());
		invokeOneWayService(bootstrapEndpoint, syncRequest);
	}

	@Override
	public P2PNodeDB getNodeDB() {
		return db;
	}
	
	

}
