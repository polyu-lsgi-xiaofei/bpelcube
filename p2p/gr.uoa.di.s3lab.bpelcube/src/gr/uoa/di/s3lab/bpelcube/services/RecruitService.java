package gr.uoa.di.s3lab.bpelcube.services;

import gr.uoa.di.s3lab.bpelcube.BPELCubeService;
import gr.uoa.di.s3lab.bpelcube.BPELCubeNode.Role;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;
import gr.uoa.di.s3lab.p2p.hypercube.Hypercube;
import gr.uoa.di.s3lab.p2p.hypercube.Neighbor;
import gr.uoa.di.s3lab.p2p.hypercube.services.UpdateNeighborLastUsedRequest;

/**
 * 
 * @author Michael Pantazoglou
 *
 */
public class RecruitService extends BPELCubeService {
	/**
	 * The request that triggered this service.
	 */
	private RecruitRequest request;
	
	/**
	 * Constructor.
	 */
	public RecruitService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (RecruitRequest) req;
	}

	@Override
	public void execute() {
		
		if (!db.p2pSessionExists(request.getP2PSessionId())) {
			db.addP2PSession(request.getP2PSessionId(), Role.WORKER.toString(),
					request.getP2PSessionCreationTime(), null);
			// TODO invoke the BPEL process locally
		}
		
		String activityId = request.getActivityIds().remove(0);
		db.addP2PSessionActivity(request.getP2PSessionId(), activityId);
		db.addP2PSessionNeighor(request.getP2PSessionId(), 
				request.getRequesterEndpoint().toString());
		
		me.setLastUsed(System.currentTimeMillis());
		for (Neighbor n : me.getNeighborSet()) {
			UpdateNeighborLastUsedRequest updateNeighborLastUsedRequest = 
					new UpdateNeighborLastUsedRequest();
			updateNeighborLastUsedRequest.setNeighborPosition(me.getPositionVector());
			updateNeighborLastUsedRequest.setLastUsed(me.getLastUsed());
			try {
				me.invokeOneWayService(n.asP2PEndpoint(), updateNeighborLastUsedRequest);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!request.getActivityIds().isEmpty()) {
			// forward the recruit request to the LRU node
			request.setRequesterEndpoint(me.getEndpoint());
			try {
				Neighbor LRU = me.getLRUNeighbor();
				me.getLog().debug("LRU neighbor: " + Hypercube.vectorAsString(LRU.getPositionVector()));
				db.addP2PSessionNeighor(request.getP2PSessionId(), LRU.toString());
				me.invokeOneWayService(LRU.asP2PEndpoint(), request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// notify the manager of the P2P session that recruitment is complete
			RecruitCompleteRequest recruitCompleteRequest = new RecruitCompleteRequest();
			recruitCompleteRequest.setP2PSessionId(request.getP2PSessionId());
			try {
				me.invokeOneWayService(request.getManagerEndpoint(), recruitCompleteRequest);
			} catch (Exception e) {
				e.printStackTrace();
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
