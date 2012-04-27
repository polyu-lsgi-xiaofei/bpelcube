package gr.uoa.di.s3lab.bpelcube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

/**
 * 
 * @author Michael Pantazoglou
 *
 */
public class RecruitCompleteRequest extends P2PRequest {

	private static final long serialVersionUID = 201204231724L;
	/**
	 * Id of the P2P session for which recruitment is complete.
	 */
	private String p2pSessionId;
	
	/**
	 * Constructor.
	 */
	public RecruitCompleteRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new RecruitCompleteService();
	}

	public String getP2PSessionId() {
		return p2pSessionId;
	}
	
	public void setP2PSessionId(String p2pSessionId) {
		this.p2pSessionId = p2pSessionId;
	}

}
