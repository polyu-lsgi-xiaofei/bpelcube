package gr.uoa.di.s3lab.bpelcube.prototype;

import gr.uoa.di.s3lab.p2p.P2PService;

public abstract class BPELcubeService implements P2PService {

	protected BPELcubeNode node;
	
	protected BPELcubeNodeDB nodeDB;
	
	protected BPELcubeService() {
		node = (BPELcubeNode) BPELcubeNode.sharedInstance;
		nodeDB = (BPELcubeNodeDB) node.getNodeDB();
	}

}
