package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;
import gr.uoa.di.s3lab.p2p.hypercube.HypercubeService;
import gr.uoa.di.s3lab.p2p.hypercube.Neighbor;

/**
 * This service updates the timestamp of last use of the specified neighbor.
 * 
 * @author Michael Pantazoglou
 *
 */
public class UpdateNeighborLastUsedService extends HypercubeService {
	/**
	 * The request message that triggered this service.
	 */
	private UpdateNeighborLastUsedRequest request;
	
	/**
	 * Constructor.
	 */
	public UpdateNeighborLastUsedService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (UpdateNeighborLastUsedRequest) req;
	}

	@Override
	public void execute() {
		Neighbor n = me.getNeighbor(request.getNeighborPosition());
		n.setLastUsed(request.getLastUsed());
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
