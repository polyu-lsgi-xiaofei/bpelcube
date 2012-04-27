package gr.uoa.di.s3lab.p2p;

/**
 * This interface is used for p2p message exchange.
 * 
 * @author Michael Pantazoglou
 */
public interface P2PConnection {

    /**
     * Sends the specified message to the node at the other end of this
     * connection.
     *
     * @param msg
     * @throws Exception
     */
    public void send(P2PMessage msg) throws Exception;

    /**
     * Receives a message from the node at the other end of this connection.
     *
     * @return the received message
     * @throws Exception
     */
    public P2PMessage receive() throws Exception;

    /**
     * Closes this connection.
     * 
     * @throws Exception
     */
    public void close() throws Exception;

}
