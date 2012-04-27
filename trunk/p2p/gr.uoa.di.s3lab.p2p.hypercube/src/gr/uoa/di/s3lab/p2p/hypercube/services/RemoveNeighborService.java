package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;
import gr.uoa.di.s3lab.p2p.hypercube.Hypercube;
import gr.uoa.di.s3lab.p2p.hypercube.HypercubeService;

/**
 * Removes the specified neighbor in the service request from this node's 
 * neighbor set.
 * 
 * @author Michael Pantazoglou
 *
 */
public class RemoveNeighborService extends HypercubeService {
	/**
	 * The request that triggered this service.
	 */
	private RemoveNeighborRequest request;
	
	/**
	 * Constructor.
	 */
	public RemoveNeighborService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (RemoveNeighborRequest) req;
	}

	@Override
	public void execute() {
		int n = me.removeNeighbor(request.getNeighborPositionVector());
		if (n == 0) {
			me.getLog().debug("Neighbor not found: " + Hypercube.vectorAsString(
					request.getNeighborPositionVector()));
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
