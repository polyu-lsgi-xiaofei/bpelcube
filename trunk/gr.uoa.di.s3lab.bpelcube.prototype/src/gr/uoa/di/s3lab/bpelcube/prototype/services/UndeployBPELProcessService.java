package gr.uoa.di.s3lab.bpelcube.prototype.services;

import gr.uoa.di.s3lab.bpelcube.prototype.BPELcubeService;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;

public class UndeployBPELProcessService extends BPELcubeService {
	
	private UndeployBPELProcessRequest request;

	public UndeployBPELProcessService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (UndeployBPELProcessRequest) req;
	}

	@Override
	public void execute() {
		nodeDB.removeDeployedProcess(request.getProcessId());
		if (!request.isBroadcastInitiated()) {
			request.setBroadcastInitiated(true);
			node.initiateBroadcast(request);
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