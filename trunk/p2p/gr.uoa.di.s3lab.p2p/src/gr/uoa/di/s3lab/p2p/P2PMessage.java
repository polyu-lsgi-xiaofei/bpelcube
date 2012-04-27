package gr.uoa.di.s3lab.p2p;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Defines a convenient abstraction for all p2p messages.
 * 
 * @author Michael Pantazoglou
 */
public class P2PMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The message's header may contain an arbitrary number of serializable 
     * objects distinguished by their name.
     */
    private HashMap<String,Serializable> header;
    
    public P2PMessage() {
    	header = new HashMap<String,Serializable>();
    }

	public HashMap<String,Serializable> getHeader() {
		return header;
	}

	public void setHeader(HashMap<String,Serializable> header) {
		this.header = header;
	}
	
	public void addHeaderElement(String name, Serializable headerElement) {
		this.header.put(name, headerElement);
	}

}
