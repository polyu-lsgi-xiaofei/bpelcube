package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PEndpoint;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;
import gr.uoa.di.s3lab.p2p.P2PService;
import gr.uoa.di.s3lab.p2p.hypercube.Hypercube;
import gr.uoa.di.s3lab.p2p.hypercube.HypercubeService;
import gr.uoa.di.s3lab.p2p.hypercube.Neighbor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This class implements the Hypercube broadcast service.
 * 
 * @author Michael Pantazoglou
 *
 */
public class BroadcastService extends HypercubeService {
	/**
	 * The request that triggered this service.
	 */
	private BroadcastRequest request;
	
	/**
	 * Constructor.
	 */
	public BroadcastService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (BroadcastRequest) req;
	}

	@Override
	public void execute() {
		
		// Process the actual service request in a separate thread
		new Thread(new Runnable() {
			
			public void run() {
				P2PRequest serviceRequest = request.getServiceRequest();
				P2PService p2pService = serviceRequest.createService();
				p2pService.setRequest(request.getServiceRequest());
				p2pService.execute();
			}
		}).start();		
		
		int minDimension = request.getMinDimension();
		me.getLog().debug("Broadcast received from neighbor in dimension " + 
				minDimension);
		if (minDimension == Hypercube.MAX_NUMBER_OF_DIMENSIONS - 1) {
			return;
		}
		
		// forward the broadcast request
		
		List<Integer> dimensionsCovered = new ArrayList<Integer>();
		Set<Neighbor> myNeighborSet = me.getNeighborSet();
		for (Neighbor n : myNeighborSet) {
			String p_s = Hypercube.vectorAsString(n.getPositionVector());
			
			int linkDimensionality = Hypercube.getLinkDimensionality(
					me.getPositionVector(), n.getPositionVector());
			if (linkDimensionality > minDimension) {
				if (dimensionsCovered.contains(linkDimensionality)) {
					continue;
				}
				dimensionsCovered.add(linkDimensionality);
				P2PEndpoint p2pEndpoint = new P2PEndpoint();
				p2pEndpoint.setAddress(n.getNetworkAddress());
				request.setMinDimension(linkDimensionality);
				try {
					me.getLog().debug("Forwarding broadcast message to " + p_s);
					me.invokeOneWayService(p2pEndpoint, request);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
