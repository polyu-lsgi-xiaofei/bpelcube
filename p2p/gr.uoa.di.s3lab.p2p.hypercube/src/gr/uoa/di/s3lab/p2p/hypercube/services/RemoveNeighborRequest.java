package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

/**
 * This message is sent by a node to another one asking to remove the specified 
 * neighbor.
 * 
 * @author Michael Pantazoglou
 *
 */
public class RemoveNeighborRequest extends P2PRequest {

	private static final long serialVersionUID = 201204111809L;
	/**
	 * Position vector of the neighbor to be removed.
	 */
	private int[] neighborPositionVector;
	
	/**
	 * Constructor.
	 */
	public RemoveNeighborRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new RemoveNeighborService();
	}

	/**
	 * @return the neighborPositionVector
	 */
	public int[] getNeighborPositionVector() {
		return neighborPositionVector;
	}

	/**
	 * @param neighborPositionVector the neighborPositionVector to set
	 */
	public void setNeighborPositionVector(int[] neighborPositionVector) {
		this.neighborPositionVector = neighborPositionVector;
	}

}
