package gr.uoa.di.s3lab.bpelcube.services;

import gr.uoa.di.s3lab.bpelcube.BPELCubeService;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;

/**
 * 
 * @author Michael Pantazoglou
 *
 */
public class BPELActivityCompletedService extends BPELCubeService {
	
	private BPELActivityCompletedRequest request;
	
	/**
	 * Constructor.
	 */
	public BPELActivityCompletedService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (BPELActivityCompletedRequest) req;
	}

	@Override
	public void execute() {
		// TODO Implement this method
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
