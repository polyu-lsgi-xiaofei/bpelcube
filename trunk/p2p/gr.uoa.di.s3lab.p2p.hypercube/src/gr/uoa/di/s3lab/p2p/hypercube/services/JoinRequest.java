package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

import java.net.URI;

/**
 * This class implements the request message that is sent by a joining node 
 * to another node that is already integrated into the hypercube topology. 
 * The receiver node then will execute the {@link JoinService} in order to 
 * integrate the requester node into the hypercube.
 * 
 * @author Michael Pantazoglou
 *
 */
public class JoinRequest extends P2PRequest {

	private static final long serialVersionUID = 201204061547L;
	/**
	 * Network address of the joining node.
	 */
	private URI joiningNodeNetworkAddress;

	/**
	 * Constructor.
	 */
	public JoinRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new JoinService();
	}

	/**
	 * @return the joiningNodeNetworkAddress
	 */
	public URI getJoiningNodeNetworkAddress() {
		return joiningNodeNetworkAddress;
	}

	/**
	 * @param joiningNodeNetworkAddress the joiningNodeNetworkAddress to set
	 */
	public void setJoiningNodeNetworkAddress(URI joiningNodeNetworkAddress) {
		this.joiningNodeNetworkAddress = joiningNodeNetworkAddress;
	}

}
