package gr.uoa.di.s3lab.p2p;

import java.io.EOFException;


/**
 * This class is used to handle incoming p2p connections, i.e. connections
 * created by a p2p connection listener, by means of the appropriate p2p
 * service.
 * 
 * @author Michael Pantazoglou
 */
public class P2PConnectionHandler implements Runnable {
	
	protected final Log log = P2PNode.sharedInstance.getLog();

	protected P2PConnection connection;

	/**
	 * Constructor.
	 *
	 * @param conn the p2p connection to handle
	 */
	public P2PConnectionHandler(P2PConnection conn) {
		connection = conn;
	}

	/**
	 * Handles the connection in the following way: First, the request is
	 * received, and is used to create an instance of the corresponding service.
	 * Next, the service is executed based on the request, and, if a response
	 * is required, it is sent back through the connection. 
	 */
	protected void handle() {
		try {
			P2PMessage request = connection.receive();
			if (!(request instanceof P2PRequest)) {
				log.error("Invalid request");
				return;
			}
			P2PService service = ((P2PRequest)request).createService();
			if (service == null) {
				log.error("Unknown request");
				return;
			}
			log.debug("Executing service " + service.getClass().getSimpleName());
			service.setRequest((P2PRequest)request);
			service.execute();
			if (service.isRequestResponse()) {
				P2PResponse response = service.getResponse();
				connection.send(response);
			}
		} catch (InterruptedException e) {
			return;
		} catch (Exception e) {
			if (!(e instanceof EOFException)) {
				e.printStackTrace();
			}
		} finally {
			try {
//				log.debug("Closing the P2P connection.");
				connection.close();
				connection = null;
//				log.debug("P2P Connection closed.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Runs the p2p connection handler.
	 */
	public void run() {
		handle();
	}

}
