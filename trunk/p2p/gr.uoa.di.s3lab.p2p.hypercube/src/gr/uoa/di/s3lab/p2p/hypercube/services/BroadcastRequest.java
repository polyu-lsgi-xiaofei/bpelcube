package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

/**
 * Implements the request message that is sent by one node to another upon 
 * broadcasting.
 * 
 * @author Michael Pantazoglou
 *
 */
public class BroadcastRequest extends P2PRequest {
	
	private static final long serialVersionUID = 201204091622L;
	/**
	 * The minimum dimension of the requested broadcast. Its value determines 
	 * the follow-up nodes of this broadcast.
	 */
	private int minDimension = 0;
	/**
	 * The service request that each recipient node must process. It is assumed
	 * that the corresponding service is one-way, i.e. request-only.
	 */
	private P2PRequest serviceRequest;
	/**
	 * Constructor.
	 */
	public BroadcastRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new BroadcastService();
	}

	/**
	 * @return the minDimension
	 */
	public int getMinDimension() {
		return minDimension;
	}

	/**
	 * @param minDimension the minDimension to set
	 */
	public void setMinDimension(int minDimension) {
		this.minDimension = minDimension;
	}

	/**
	 * @return the serviceRequest
	 */
	public P2PRequest getServiceRequest() {
		return serviceRequest;
	}

	/**
	 * @param serviceRequest the serviceRequest to set
	 */
	public void setServiceRequest(P2PRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

}
