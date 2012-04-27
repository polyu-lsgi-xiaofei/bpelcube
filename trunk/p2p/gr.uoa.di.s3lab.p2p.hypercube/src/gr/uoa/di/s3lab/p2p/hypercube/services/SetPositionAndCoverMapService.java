package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;
import gr.uoa.di.s3lab.p2p.hypercube.HypercubeService;

/**
 * This service is used to set the provider node's position and cover map 
 * vectors.
 * 
 * @author Michael Pantazoglou
 *
 */
public class SetPositionAndCoverMapService extends HypercubeService {
	/**
	 * The request that triggered this service.
	 */
	private SetPositionAndCoverMapRequest request;
	
	/**
	 * Constructor.
	 */
	public SetPositionAndCoverMapService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (SetPositionAndCoverMapRequest) req;
	}

	@Override
	public void execute() {
		me.setPositionVector(request.getPositionVector());
		me.setCoverMapVector(request.getCoverMapVector());
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
