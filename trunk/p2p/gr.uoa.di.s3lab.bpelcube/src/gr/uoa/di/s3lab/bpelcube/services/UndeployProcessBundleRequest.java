package gr.uoa.di.s3lab.bpelcube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

/**
 * This request triggers the undeployment of the specified BPEL process bundle 
 * from the local node.
 * 
 * @author Michael Pantazoglou
 *
 */
public class UndeployProcessBundleRequest extends P2PRequest {

	private static final long serialVersionUID = 201205022319L;
	
	/**
	 * The name of the bundle to be undeployed.
	 */
	private String bundleName;

	/**
	 * Constructor.
	 */
	public UndeployProcessBundleRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new UndeployProcessBundleService();
	}
	
	public String getBundleName() {
		return bundleName;
	}

	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}

}
