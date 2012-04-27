package gr.uoa.di.s3lab.p2p;

import gr.uoa.di.s3lab.p2p.impl.P2PNetworkImpl;

/**
 * This factory is used to create implementation-specific instances of the 
 * P2PNetwork class.
 * 
 * @author Michael Pantazoglou
 *
 */
public class P2PNetworkFactory {
	
	public static P2PNetwork newInstance() {
		return new P2PNetworkImpl();
	}

}
