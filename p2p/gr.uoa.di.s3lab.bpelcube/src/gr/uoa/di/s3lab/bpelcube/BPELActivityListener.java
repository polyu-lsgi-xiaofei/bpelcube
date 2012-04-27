package gr.uoa.di.s3lab.bpelcube;

import java.util.concurrent.SynchronousQueue;

/**
 * 
 * @author Michael Pantazoglou
 *
 */
public class BPELActivityListener<E> {
	
	private SynchronousQueue<E> queue;
	
	private String p2pSessionId;
	
	private String activityId;
	
	public BPELActivityListener(SynchronousQueue<E> queue) {
		this.queue = queue;
	}
	
	/**
	 * Listens for a notification from the activity notifier.
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public E listen() throws InterruptedException {
		return queue.take();
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
