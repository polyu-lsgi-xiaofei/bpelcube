package gr.uoa.di.s3lab.p2p.hypercube;

import gr.uoa.di.s3lab.p2p.P2PService;

/**
 * Abstract base of all Hypercube P2P service implementations.
 * 
 * @author Michael Pantazoglou
 *
 */
public abstract class HypercubeService implements P2PService {
	
	/**
	 * The node that executes this service.
	 */
	protected HypercubeNode me;

	/**
	 * Constructor.
	 */
	protected HypercubeService() {
		me = (HypercubeNode) HypercubeNode.sharedInstance;
	}
}
