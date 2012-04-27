package gr.uoa.di.s3lab.p2p.impl;

import gr.uoa.di.s3lab.p2p.P2PConnection;
import gr.uoa.di.s3lab.p2p.P2PConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket-based implementation of the {@link P2PConnectionListener} interface.
 * 
 * @author Michael Pantazoglou
 *
 */
public class P2PConnectionListenerImpl implements P2PConnectionListener {
	
	private ServerSocket serverSocket = null;
	
	/**
	 * Constructor.
	 * 
	 * @param port
	 */
	public P2PConnectionListenerImpl(int port) {
		super();
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.setSoTimeout(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public P2PConnection listen() throws Exception {
		
		Socket socket = serverSocket.accept();
		return new P2PConnectionImpl(socket);
	}

	@Override
	public void stop() throws Exception {
		serverSocket.close();
		serverSocket = null;
	}

}
