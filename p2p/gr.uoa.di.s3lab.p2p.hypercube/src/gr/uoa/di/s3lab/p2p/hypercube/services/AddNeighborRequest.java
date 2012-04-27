package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;
import gr.uoa.di.s3lab.p2p.hypercube.Neighbor;

/**
 * This class implements the request that is sent to a node in order to add 
 * a specific node to its list of neighbors.
 * 
 * @author Michael Pantazoglou
 *
 */
public class AddNeighborRequest extends P2PRequest {

	private static final long serialVersionUID = 201204092352L;
	/**
	 * The neighbor to be added.
	 */
	private Neighbor newNeighbor;

	/**
	 * Constructor.
	 */
	public AddNeighborRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new AddNeighborService();
	}

	/**
	 * @return the newNeighbor
	 */
	public Neighbor getNewNeighbor() {
		return newNeighbor;
	}

	/**
	 * @param newNeighbor the newNeighbor to set
	 */
	public void setNewNeighbor(Neighbor newNeighbor) {
		this.newNeighbor = newNeighbor;
	}

}
