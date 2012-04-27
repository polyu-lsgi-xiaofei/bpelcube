package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;
import gr.uoa.di.s3lab.p2p.hypercube.Hypercube;
import gr.uoa.di.s3lab.p2p.hypercube.HypercubeService;

/**
 * This service is executed each time the node receives a request for adding 
 * a new neighbor to its neighbor set.
 * 
 * @author Michael Pantazoglou
 *
 */
public class AddNeighborService extends HypercubeService {
	/**
	 * The request that triggered this service.
	 */
	private AddNeighborRequest request;
	
	/**
	 * Constructor.
	 */
	public AddNeighborService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (AddNeighborRequest) req;
	}

	@Override
	public void execute() {
		
		me.getLog().info("Adding neighbor: " + 
				Hypercube.vectorAsString(
						request.getNewNeighbor().getPositionVector()));
		
		if (me.getNeighbor(request.getNewNeighbor().getPositionVector()) 
				== null) {
			me.getNeighborSet().add(request.getNewNeighbor());
		}
	}

	@Override
	public boolean isRequestResponse() {
		return false;
	}

	@Override
	public P2PResponse getResponse() {
		return null;
	}

}
