package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

/**
 *  
 * @author Michael Pantazoglou
 *
 */
public class UpdateNeighborLastUsedRequest extends P2PRequest {
	
	private static final long serialVersionUID = 201204162002L;
	/**
	 * The position vector of the sender.
	 */
	private int[] neighborPosition;
	/**
	 * The timestamp of last use of the sender.
	 */
	private long lastUsed;

	/**
	 * Constructor.
	 */
	public UpdateNeighborLastUsedRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new UpdateNeighborLastUsedService();
	}

	/**
	 * @return the neighborPosition
	 */
	public int[] getNeighborPosition() {
		return neighborPosition;
	}

	/**
	 * @param neighborPosition the neighborPosition to set
	 */
	public void setNeighborPosition(int[] neighborPosition) {
		this.neighborPosition = neighborPosition;
	}

	/**
	 * @return the lastUsed
	 */
	public long getLastUsed() {
		return lastUsed;
	}

	/**
	 * @param lastUsed the lastUsed to set
	 */
	public void setLastUsed(long lastUsed) {
		this.lastUsed = lastUsed;
	}

}
