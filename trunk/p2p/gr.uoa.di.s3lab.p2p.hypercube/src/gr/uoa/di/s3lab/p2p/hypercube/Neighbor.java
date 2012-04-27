package gr.uoa.di.s3lab.p2p.hypercube;

import gr.uoa.di.s3lab.p2p.P2PEndpoint;

import java.io.Serializable;
import java.net.URI;

/**
 * This class implements the neighbor of a hypercube node.
 * 
 * @author Michael Pantazoglou
 *
 */
public class Neighbor implements Serializable {
	
	private static final long serialVersionUID = 201204041225L;
	
	/**
	 * The position vector of this neighbor.
	 */
	private int[] positionVector;
	/**
	 * The network address of this neighbor.
	 */
	private URI networkAddress;
	/**
	 * The timestamp (in milliseconds) of the last time this neighbor was used.
	 */
	private long lastUsed;
	
	/**
	 * Constructor.
	 */
	public Neighbor() {
		super();
	}
	
	/**
	 * @return the positionVector
	 */
	public int[] getPositionVector() {
		return positionVector;
	}

	/**
	 * @param positionVector the positionVector to set
	 */
	public void setPositionVector(int[] positionVector) {
		this.positionVector = positionVector;
	}

	/**
	 * @return the networkAddress
	 */
	public URI getNetworkAddress() {
		return networkAddress;
	}

	/**
	 * @param networkAddress the networkAddress to set
	 */
	public void setNetworkAddress(URI networkAddress) {
		this.networkAddress = networkAddress;
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
	
	/**
	 * Returns a P2PEndpoint that corresponds to this neighbor.
	 * 
	 * @return
	 */
	public P2PEndpoint asP2PEndpoint() {
		P2PEndpoint p2pEndpoint = new P2PEndpoint();
		p2pEndpoint.setAddress(networkAddress);
		return p2pEndpoint;
	}

}
