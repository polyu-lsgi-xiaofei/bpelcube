package gr.uoa.di.s3lab.bpelcube;

import java.util.concurrent.SynchronousQueue;

/**
 * 
 * @author Michael Pantazoglou
 *
 */
public class BPELActivityNotifier<E> {
	
	private SynchronousQueue<E> queue;
	
	private String p2pSessionId;
	
	private String activityId;
	
	public BPELActivityNotifier(SynchronousQueue<E> queue) {
		this.queue = queue;
	}
	
	/**
	 * Notifies the activity listener.
	 * 
	 * @param e
	 * @throws InterruptedException
	 */
	public void notify(E e) throws InterruptedException {
		this.queue.put(e);
	}

	public String getP2PSessionId() {
		return p2pSessionId;
	}

	public void setP2PSessionId(String p2pSessionId) {
		this.p2pSessionId = p2pSessionId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

}
