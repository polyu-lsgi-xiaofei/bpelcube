package gr.uoa.di.s3lab.p2p;

import java.io.Serializable;
import java.net.URI;

/**
 * Defines a convenient abstraction of p2p endpoints.
 * 
 * @author Michael Pantazoglou
 */
public class P2PEndpoint implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
     * The underlying address of this endpoint.
     */
    protected URI address;
    
    public P2PEndpoint() {
    	
    }
    
    public final void setAddress(URI a) {
    	address = a;
    }

    public final URI getAddress() {
        return address;
    }

    @Override
    public String toString() {
    	return address.toString();
    }
    
    @Override
    public boolean equals(Object o) {
    	if (o instanceof P2PEndpoint) {
    		P2PEndpoint oEndpoint = (P2PEndpoint) o;
    		URI oAddress = oEndpoint.getAddress();
    		if (oAddress == null) {
    			return address==null;
    		} else {
    			return oAddress.equals(address);
    		}
    	}
    	return false;
    }
    
    @Override
    public int hashCode() {
    	return address.hashCode();
    }
}
