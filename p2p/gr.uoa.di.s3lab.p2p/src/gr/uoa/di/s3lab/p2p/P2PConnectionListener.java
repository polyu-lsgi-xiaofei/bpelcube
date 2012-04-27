package gr.uoa.di.s3lab.p2p;

/**
 * The interface of p2p connection listeners.
 * 
 * @author Michael Pantazoglou
 */
public interface P2PConnectionListener {

    /**
     * Waits until a connection request is received, upon which a new p2p
     * connection is created and returned.
     * 
     * @return
     * @throws Exception
     */
    public P2PConnection listen() throws Exception;
    
    /**
     * Stops the connection listener.
     * 
     * @throws Exception
     */
    public void stop() throws Exception;

}
