package gr.uoa.di.s3lab.p2p;

import java.net.URI;

/**
 * Defines an abstraction of the P2P network.
 * 
 * @author Michael Pantazoglou
 *
 */
public abstract class P2PNetwork {
	
	/**
	 * Starts up this P2P network with the specified properties.
	 * 
	 * @param nodeHome the node's home directory
	 * @param nodeName the node's name
	 * @param nodeAddress the node's endpoint address (or null if it is not known a priori)
	 * @param port the node's listening port
	 * @param networkName the name of this network
	 * @return the node's established endpoint address
	 * @throws Exception
	 */
	public abstract URI startup(URI nodeHome, String nodeName, URI nodeAddress, 
			int port, String networkName) throws Exception;
	
	/**
	 * Returns the P2P connection listener that is associated with this network.
	 * 
	 * @return
	 */
	public abstract P2PConnectionListener getP2PConnectionListener();
	
	/**
	 * Establishes and returns a P2P connection to the specified P2P endpoint.
	 * 
	 * @param e
	 * @return
	 * @throws Exception
	 */
	public abstract P2PConnection connect(P2PEndpoint e) throws Exception;
	
	/**
	 * Shuts down this network.
	 * 
	 * @throws Exception
	 */
	public abstract void shutdown() throws Exception;

}
