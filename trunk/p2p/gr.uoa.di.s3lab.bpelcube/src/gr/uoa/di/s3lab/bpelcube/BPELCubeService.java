package gr.uoa.di.s3lab.bpelcube;

import gr.uoa.di.s3lab.p2p.P2PService;

/**
 * Abstract base of all P2P Engine service implementations.
 * 
 * @author Michael Pantazoglou
 *
 */
public abstract class BPELCubeService implements P2PService {
	
	/**
	 * The node that executes this service.
	 */
	protected BPELCubeNode me;
	/**
	 * The embedded database of the node that executes this service.
	 */
	protected BPELCubeNodeDB db;
	
	/**
	 * Constructor.
	 */
	protected BPELCubeService() {
		super();
		me = (BPELCubeNode) BPELCubeNode.sharedInstance;
		db = (BPELCubeNodeDB) me.getNodeDB();
	}

}
