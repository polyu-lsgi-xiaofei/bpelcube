package gr.uoa.di.s3lab.bpelcube;

/**
 * This class provides a number of P2P engine-related utilities.
 * 
 * @author Michael Pantazoglou
 *
 */
public class BPELCubeUtils {
	
	/**
	 * Counts the number of created process instances.
	 */
	private static long processInstanceCounter = 0L;
	
	/**
	 * Creates and returns a new P2P session ID. Uniqueness is attempted to be 
	 * ensured by using the current time millis and a static counter as the 
	 * ingredients of the generated ID.
	 * 
	 * @return
	 */
	public static String newP2PSessionID() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("p2p").append("-");
		sb.append(System.currentTimeMillis()).append("-");
		sb.append(processInstanceCounter++);
		
		return sb.toString();
	}

}
