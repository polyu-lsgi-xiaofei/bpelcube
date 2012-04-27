package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

/**
 * This request is sent by the first elected integration champion node to the 
 * new node in order to set its position vector and cover map vector.
 * 
 * @author Michael Pantazoglou
 *
 */
public class SetPositionAndCoverMapRequest extends P2PRequest {

	private static final long serialVersionUID = 1L;
	
	private int[] positionVector;
	
	private int[] coverMapVector;

	public SetPositionAndCoverMapRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new SetPositionAndCoverMapService();
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
	 * @return the coverMapVector
	 */
	public int[] getCoverMapVector() {
		return coverMapVector;
	}

	/**
	 * @param coverMapVector the coverMapVector to set
	 */
	public void setCoverMapVector(int[] coverMapVector) {
		this.coverMapVector = coverMapVector;
	}

}
