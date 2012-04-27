package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

/**
 * Request message of the {@link UpdateCoverMapVectorService}.
 * 
 * @author Michael Pantazoglou
 *
 */
public class UpdateCoverMapVectorRequest extends P2PRequest {
	
	private static final long serialVersionUID = 201204132347L;
	/**
	 * The new cover map vector.
	 */
	private int[] newCoverMapVector;

	/**
	 * Constructor.
	 */
	public UpdateCoverMapVectorRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new UpdateCoverMapVectorService();
	}

	/**
	 * @return the newCoverMapVector
	 */
	public int[] getNewCoverMapVector() {
		return newCoverMapVector;
	}

	/**
	 * @param newCoverMapVector the newCoverMapVector to set
	 */
	public void setNewCoverMapVector(int[] newCoverMapVector) {
		this.newCoverMapVector = newCoverMapVector;
	}

}
