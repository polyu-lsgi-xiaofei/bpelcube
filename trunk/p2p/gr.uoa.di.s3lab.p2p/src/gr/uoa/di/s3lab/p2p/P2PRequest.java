package gr.uoa.di.s3lab.p2p;

/**
 * Defines a convenient abstraction for all p2p <b>request</b> messages.
 * 
 * @author Michael Pantazoglou
 *
 */
public abstract class P2PRequest extends P2PMessage {

	private static final long serialVersionUID = 3863562841558745016L;

	protected P2PRequest() {
		super();
	}
	
	/**
     * Creates and returns the appropriate p2p service that will handle this p2p request.
     * 
     * @return
     */
    public abstract P2PService createService();

}
