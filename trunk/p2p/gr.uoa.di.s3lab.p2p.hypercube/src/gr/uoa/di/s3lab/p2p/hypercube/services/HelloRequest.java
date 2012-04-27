package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

/**
 * 
 * @author Michael Pantazoglou
 *
 */
public class HelloRequest extends P2PRequest {

	private static final long serialVersionUID = 201204101713L;
	
	private String message;

	public HelloRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new HelloService();
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
