package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;
import gr.uoa.di.s3lab.p2p.hypercube.HypercubeService;

/**
 * This service is used to update the cover map vector of the provider node.
 * 
 * @author Michael Pantazoglou
 *
 */
public class UpdateCoverMapVectorService extends HypercubeService {
	/**
	 * The request that triggered this service.
	 */
	private UpdateCoverMapVectorRequest request;
	
	/**
	 * Constructor.
	 */
	public UpdateCoverMapVectorService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (UpdateCoverMapVectorRequest) req;
	}

	@Override
	public void execute() {
		me.setCoverMapVector(request.getNewCoverMapVector());
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
