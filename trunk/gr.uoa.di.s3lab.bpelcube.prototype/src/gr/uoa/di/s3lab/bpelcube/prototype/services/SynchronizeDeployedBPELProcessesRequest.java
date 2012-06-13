package gr.uoa.di.s3lab.bpelcube.prototype.services;

import gr.uoa.di.s3lab.p2p.P2PEndpoint;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

import java.util.List;

public class SynchronizeDeployedBPELProcessesRequest extends P2PRequest {

	private static final long serialVersionUID = 201206132008L;
	
	/**
	 * The p2p endpoint of the requester node.
	 */
	private P2PEndpoint requester;
	
	private List<String> deployedProcessIds;

	public SynchronizeDeployedBPELProcessesRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new SynchronizeDeployedBPELProcessesService();
	}

	public List<String> getDeployedProcessIds() {
		return deployedProcessIds;
	}

	public void setDeployedProcessIds(List<String> deployedProcessIds) {
		this.deployedProcessIds = deployedProcessIds;
	}

	public P2PEndpoint getRequester() {
		return requester;
	}

	public void setRequester(P2PEndpoint requester) {
		this.requester = requester;
	}

}
