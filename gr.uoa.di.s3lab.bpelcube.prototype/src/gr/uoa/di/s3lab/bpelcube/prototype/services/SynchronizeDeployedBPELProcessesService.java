package gr.uoa.di.s3lab.bpelcube.prototype.services;

import gr.uoa.di.s3lab.bpelcube.prototype.BPELcubeService;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;

import java.util.ArrayList;
import java.util.List;

public class SynchronizeDeployedBPELProcessesService extends BPELcubeService {
	
	private SynchronizeDeployedBPELProcessesRequest request;

	public SynchronizeDeployedBPELProcessesService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (SynchronizeDeployedBPELProcessesRequest) req;
	}

	@Override
	public void execute() {
		List<String> aList = request.getDeployedProcessIds();
		List<String> bList = nodeDB.getAllDeployedProcessIds();
		
		// find out which elements in aList are NOT included in bList
		List<String> toBeUndeployed = new ArrayList<String>();
		for (String s : aList) {
			if (!bList.contains(s)) {
				toBeUndeployed.add(s);
			}
		}
		
		// find out which elements in bList are NOT included in aList
		List<String> toBeDeployed = new ArrayList<String>();
		for (String s : bList) {
			if (!aList.contains(s)) {
				toBeDeployed.add(s);
			}
		}
		
		// send undeploy requests
		for (String processId : toBeUndeployed) {
			UndeployBPELProcessRequest undeployRequest = new UndeployBPELProcessRequest();
			undeployRequest.setProcessId(processId);
			try {
				node.invokeOneWayService(request.getRequester(), undeployRequest);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		
		// send deploy requests
		for (String processId : toBeDeployed) {
			DeployBPELProcessRequest deployRequest = new DeployBPELProcessRequest();
			deployRequest.setBPELProcess(nodeDB.getDeployedProcess(processId));
			deployRequest.setBroadcastInitiated(true); // to avoid broadcasting
			try {
				node.invokeOneWayService(request.getRequester(), deployRequest);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
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
