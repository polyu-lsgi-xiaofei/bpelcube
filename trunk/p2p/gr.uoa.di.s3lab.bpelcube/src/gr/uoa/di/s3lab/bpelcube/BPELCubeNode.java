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

import gr.uoa.di.s3lab.bpelcube.services.GetDeployedProcessBundlesUpdateRequest;
import gr.uoa.di.s3lab.bpelcube.services.RecruitRequest;
import gr.uoa.di.s3lab.p2p.Log.Level;
import gr.uoa.di.s3lab.p2p.P2PEndpoint;
import gr.uoa.di.s3lab.p2p.P2PNodeDB;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.hypercube.Hypercube;
import gr.uoa.di.s3lab.p2p.hypercube.HypercubeNode;
import gr.uoa.di.s3lab.p2p.hypercube.Neighbor;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

/**
 * Hypercube-based implementation of the P2P engine node.
 * 
 * @author Michael Pantazoglou
 *
 */
public class BPELCubeNode extends HypercubeNode {
	
	public static enum Role {
		MANAGER,
		WORKER
	};
	
	/**
	 * The embedded database of this node.
	 */
	private BPELCubeNodeDB db;
	
	/**
	 * The absolute path of the directory that is used by the BPEL engine in 
	 * order to store the deployed BPEL bundles.
	 */
	private String bpelEngineDeployDirectory; 
	
	private Hashtable<String, BPELActivityListener<P2PRequest>> activityListeners = 
			new Hashtable<String, BPELActivityListener<P2PRequest>>();
	
	private Hashtable<String, BPELProcessExecutionListener<P2PRequest>> processExecutionListeners = 
			new Hashtable<String, BPELProcessExecutionListener<P2PRequest>>();
	
	/**
	 * Constructor.
	 * 
	 * @param home
	 * @param name
	 * @param domain
	 * @param port
	 * @param logLevel
	 */
	public BPELCubeNode(URI home, String name, String domain, int port,
			Level logLevel) {
		super(home, name, domain, port, logLevel);
		db = new BPELCubeNodeDB(home.toString());
	}
	
	/**
	 * Constructor.
	 * 
	 * @param home
	 * @param name
	 * @param domain
	 * @param address
	 * @param port
	 * @param logLevel
	 */
	public BPELCubeNode(URI home, String name, String domain, URI address,
			int port, Level logLevel) {
		super(home, name, domain, address, port, logLevel);
		db = new BPELCubeNodeDB(home.toString());
	}
	
	@Override
	protected void join() throws Exception {
		
		super.join();
		
		if (getBootstrapURI() == null) {
			return;
		}
		
		// get updates wrt the currently deployed processes
		GetDeployedProcessBundlesUpdateRequest getDeployedProcessBundlesUpdateRequest = 
				new GetDeployedProcessBundlesUpdateRequest();
		getDeployedProcessBundlesUpdateRequest.setRequester(getEndpoint());
		getDeployedProcessBundlesUpdateRequest.setDeployedProcessBundles(getCurrentlyDeployedProcessBundles());
		
		P2PEndpoint bootstrapEndpoint = new P2PEndpoint();
		bootstrapEndpoint.setAddress(getBootstrapURI());
		invokeOneWayService(bootstrapEndpoint, getDeployedProcessBundlesUpdateRequest);
	}
	
	@Override
	public P2PNodeDB getNodeDB() {
		return db;
	}
	
	/**
	 * Gets the BPEL engine deploy directory's absolute path.
	 * 
	 * @return
	 */
	public String getBPELEngineDeployDirectory() {
		return this.bpelEngineDeployDirectory;
	}
	
	/**
	 * Sets the BPEL engine deploy directory's absolute path.
	 * 
	 * @param directoryPath
	 */
	public void setBPELEngineDeployDirectory(String directoryPath) {
		this.bpelEngineDeployDirectory = directoryPath;
	}
	
	/**
	 * Initializes the distributed execution of the specified activities by 
	 * assigning them to a series of selected worker nodes.
	 * 
	 * @param p2pSessionId
	 * @param activityIds ids of the activities to be distributed
	 */
	public void recruitWorkers(String p2pSessionId, List<String> activityIds) {
		
		Long p2pSessionCreationTime = System.currentTimeMillis();
		db.addP2PSession(p2pSessionId, Role.MANAGER.toString(), p2pSessionCreationTime, null);
		
		setLastUsed(System.currentTimeMillis());
		
		RecruitRequest recruitRequest = new RecruitRequest();
		recruitRequest.setP2PSessionId(p2pSessionId);
		recruitRequest.setP2PSessionCreationTime(p2pSessionCreationTime);
		recruitRequest.setManagerEndpoint(getEndpoint());
		recruitRequest.setRequesterEndpoint(getEndpoint());
		recruitRequest.setActivityIds(activityIds);
		
		Neighbor LRU = getLRUNeighbor();
		if (LRU == null) {
			// I have no neighbors so I will do everything by myself
			for (String aId : activityIds) {
				db.addP2PSessionActivity(p2pSessionId, aId);
			}
		} else {
			// Recruit LRU
			getLog().debug("LRU neighbor: " + Hypercube.vectorAsString(LRU.getPositionVector()));
			db.addP2PSessionNeighor(p2pSessionId, LRU.asP2PEndpoint().toString());
			try {
				invokeOneWayService(LRU.asP2PEndpoint(), recruitRequest);
				
				// Now wait until the recruitment process is complete
				SynchronousQueue<P2PRequest> queue = new SynchronousQueue<P2PRequest>();
				BPELProcessExecutionListener<P2PRequest> processExecutionListener = 
						new BPELProcessExecutionListener<P2PRequest>(queue);
				processExecutionListener.setP2PSessionId(p2pSessionId);
				this.addProcessExecutionListener(processExecutionListener);
				processExecutionListener.listen();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Finalizes the specified P2P session.
	 * 
	 * @param p2pSessionId
	 * @param completionTime
	 */
	public void finalizeP2PSession(String p2pSessionId, Long completionTime) {
		
		db.setCompletionTime(p2pSessionId, completionTime);
	}
	
	/**
	 * Adds the specified activity listener.
	 * 
	 * @param activityListener
	 */
	public void addActivityListener(BPELActivityListener<P2PRequest> activityListener) {
		
		String p2pSessionId = activityListener.getP2PSessionId();
		String activityId = activityListener.getActivityId();
		
		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(p2pSessionId).append("-").append(activityId);
		
		String key = keyBuilder.toString();
		activityListeners.put(key, activityListener);
	}
	
	/**
	 * Gets the listener of the specified activity.
	 * 
	 * @param p2pSessionId
	 * @param activityId
	 * @return
	 */
	public BPELActivityListener<P2PRequest> getActivityListener(String p2pSessionId, String activityId) {
		
		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(p2pSessionId).append("-").append(activityId);
		
		String key = keyBuilder.toString();
		
		return activityListeners.get(key);
	}
	
	public BPELActivityListener<P2PRequest> removeActivityListener(String p2pSessionId, 
			String activityId) {
		
		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(p2pSessionId).append("-").append(activityId);
		
		String key = keyBuilder.toString();
		
		return activityListeners.remove(key);
	}
	
	/**
	 * Adds the specified BPEL process execution listener.
	 * 
	 * @param processExecutionListener
	 */
	public void addProcessExecutionListener(
			BPELProcessExecutionListener<P2PRequest> processExecutionListener) {
		processExecutionListeners.put(processExecutionListener.getP2PSessionId(), 
				processExecutionListener);
	}
	
	/**
	 * Gets the BPEL process execution listener that corresponds to the 
	 * specified P2P session.
	 * 
	 * @param p2pSessionId
	 * @return
	 */
	public BPELProcessExecutionListener<P2PRequest> getProcessExecutionListener(
			String p2pSessionId) {
		return processExecutionListeners.get(p2pSessionId);
	}
	
	/**
	 * Removes the BPEL process execution listener that corresponds to the 
	 * specified P2P session.
	 * 
	 * @param p2pSessionId
	 * @return
	 */
	public BPELProcessExecutionListener<P2PRequest> removeProcessExecutionListener(
			String p2pSessionId) {
		return processExecutionListeners.remove(p2pSessionId);
	}
	
	/**
	 * Gets a list with the file names of the currently deployed BPEL process 
	 * bundles.
	 * 
	 * @return
	 */
	public List<String> getCurrentlyDeployedProcessBundles() {
		this.getLog().debug("Getting bundle folders from deploy directory: " + this.bpelEngineDeployDirectory);
		List<String> deployedBundles = new ArrayList<String>();
		File dir = new File(this.bpelEngineDeployDirectory);
		String[] dirContents = dir.list();
		if (dirContents.length == 0) {
			this.getLog().debug("No bundles were found.");
		}
		for (String s : dirContents) {
			File f = new File(dir, s);
			if (f.isDirectory()) {
				this.getLog().debug("Found deployed bundle: " + s);
				deployedBundles.add(s);
			}
		}
		return deployedBundles;
	}

}
