package gr.uoa.di.s3lab.bpelcube.services;

import gr.uoa.di.s3lab.bpelcube.BPELCubeService;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;

/**
 * 
 * @author Michael Pantazoglou
 *
 */
public class RecruitCompleteService extends BPELCubeService {
	/**
	 * The request that triggered this service.
	 */
	private RecruitCompleteRequest request;
	
	/**
	 * Constructor.
	 */
	public RecruitCompleteService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (RecruitCompleteRequest) req;
	}

	@Override
	public void execute() {
		
		// TODO Invoke the process of the P2P session
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
