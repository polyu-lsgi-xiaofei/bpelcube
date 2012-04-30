package gr.uoa.di.s3lab.p2p.impl;

import gr.uoa.di.s3lab.p2p.P2PConnection;
import gr.uoa.di.s3lab.p2p.P2PConnectionListener;
import gr.uoa.di.s3lab.p2p.P2PEndpoint;
import gr.uoa.di.s3lab.p2p.P2PNetwork;

import java.net.Socket;
import java.net.URI;

/**
 * Socket-based implementation of the {@link P2PNetwork}.
 * 
 * @author Michael Pantazoglou
 *
 */
public class P2PNetworkImpl extends P2PNetwork {
	
	/**
	 * The network address of the local node.
	 */
	private URI nodeAddress;
	
	/**
	 * The connection listener of the local node.
	 */
	private P2PConnectionListener connectionListener;
	
	/**
	 * Constructor.
	 */
	public P2PNetworkImpl() {
		super();
	}

	@Override
	public URI startup(URI nodeHome, String nodeName, URI nodeAddress,
			int port, String networkName) throws Exception {
		
		this.nodeAddress = nodeAddress;
		return this.nodeAddress;
	}

	@Override
	public P2PConnectionListener getP2PConnectionListener() {
		if (connectionListener == null) {
			connectionListener = new P2PConnectionListenerImpl(nodeAddress.getPort());
		}
		return connectionListener;
	}

	@Override
	public P2PConnection connect(P2PEndpoint e) throws Exception {
		
		Socket socket = new Socket(e.getAddress().getHost(), e.getAddress().getPort());
		return new P2PConnectionImpl(socket);
	}

	@Override
	public void shutdown() throws Exception {
		connectionListener.stop();
	}

}
