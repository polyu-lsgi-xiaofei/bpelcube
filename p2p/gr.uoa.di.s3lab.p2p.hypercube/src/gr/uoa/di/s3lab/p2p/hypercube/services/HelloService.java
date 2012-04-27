package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;
import gr.uoa.di.s3lab.p2p.hypercube.HypercubeService;

/**
 * 
 * @author Michael Pantazoglou
 *
 */
public class HelloService extends HypercubeService {
	
	private HelloRequest request;

	public HelloService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (HelloRequest) req;
	}

	@Override
	public void execute() {
		
		me.getLog().info("Broadcast message: " + request.getMessage());
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
