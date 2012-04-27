package gr.uoa.di.s3lab.p2p;

/**
 * Defines an abstraction of all p2p services. A p2p service is used by a node
 * to respond to an incoming message. Depending on the kind of message, the 
 * appropriate service is loaded and executed by means of this interface.
 * 
 * @author Michael Pantazoglou
 * @see p2p.P2PConnectionHandler#handle()
 */
public interface P2PService {

    public void setRequest(P2PRequest req);

    public void execute();

    public boolean isRequestResponse();

    public P2PResponse getResponse();

}
