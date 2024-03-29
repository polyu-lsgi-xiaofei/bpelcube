/* 
 * Copyright 2012 Michael Pantazoglou
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
