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
package gr.uoa.di.s3lab.bpelcube.util;

import gr.uoa.di.s3lab.bpelcube.BPELCubeNode;
import gr.uoa.di.s3lab.bpelcube.BPELCubeNodeDB;
import gr.uoa.di.s3lab.bpelcube.services.WriteBPELVariableRequest;
import gr.uoa.di.s3lab.p2p.P2PEndpoint;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implements an in-memory caching mechanism for BPEL variables, which is 
 * intended to be used by the ASSIGN activity.
 * 
 * @author Michael Pantazoglou
 *
 */
public class BPELVariablesCache {
	
	/**
	 * The id of the p2p session where this cache instance is used.
	 */
	private String p2pSessionId;
	
	/**
	 * Contains the BPEL variables that have been read.
	 */
	private Map<String, String> readVariables;
	
	/**
	 * Contains the BPEL variables that have been written.
	 */
	private Map<String, String> writeVariables;
	
	/**
	 * Constructor.
	 * 
	 * @param p2pSessionId
	 */
	public BPELVariablesCache(String p2pSessionId) {
		this.p2pSessionId = p2pSessionId;
		readVariables = new HashMap<String, String>();
		writeVariables = new HashMap<String, String>();
	}
	
	/**
	 * Reads the value of the specified variable from the cache.
	 * 
	 * @param variableId the id of the variable
	 * @return the variable value, or null if the specified variable does not 
	 * exist in the cache
	 */
	public String read(String variableId) {
		BPELCubeNode.sharedInstance.getLog().info("Reading variable " + variableId + " from cache.");
		String variableValue = readVariables.get(variableId);
		if (variableValue == null) {
			variableValue = writeVariables.get(variableId);
		}
		BPELCubeNode.sharedInstance.getLog().debug("Value: " + variableValue);
		return variableValue;
	}
	
	/**
	 * Adds the specified variable to the list of read variables.
	 * 
	 * @param variableId
	 * @param variableValue
	 */
	public void addReadVariable(String variableId, String variableValue) {
		BPELCubeNode.sharedInstance.getLog().info("Adding read variable " + variableId + " in cache.");
		BPELCubeNode.sharedInstance.getLog().info("Read value:" + variableValue);
		if (readVariables.containsKey(variableId)) {
			readVariables.remove(variableId);
		}
		readVariables.put(variableId, variableValue);
	}
	
	/**
	 * Writes the specified variable value in the cache.
	 * 
	 * @param variableId the variable id
	 * @param variableValue the variable value
	 */
	public void write(String variableId, String variableValue) {
		BPELCubeNode.sharedInstance.getLog().info("Writing variable " + variableId + " in cache.");
		BPELCubeNode.sharedInstance.getLog().debug("New value: " + variableValue);
		if (readVariables.containsKey(variableId)) {
			readVariables.remove(variableId);
		}
		if (writeVariables.containsKey(variableId)) {
			writeVariables.remove(variableId);
		}
		writeVariables.put(variableId, variableValue);
	}
	
	/**
	 * Removes all cached contents.
	 */
	public void clear() {
		BPELCubeNode.sharedInstance.getLog().info("Clearing the BPEL variables cache.");
		readVariables.clear();
		writeVariables.clear();
	}
	
	/**
	 * Persists all updated BPEL variable values.
	 */
	public void persistUpdates() {
		BPELCubeNode me = (BPELCubeNode) BPELCubeNode.sharedInstance;
		BPELCubeNodeDB db = (BPELCubeNodeDB) me.getNodeDB();
		Set<String> keySet = writeVariables.keySet();
		for (String variableId : keySet) {
			me.getLog().info("Persisting variable " + variableId);
			String holder = db.getVariableHolder(p2pSessionId, variableId);
			if (holder == null) {
				me.getLog().info("Saving locally.");
				db.addVariable(p2pSessionId, variableId, me.getEndpoint().toString(), writeVariables.get(variableId));
			} else {
				try {
					me.getLog().info("Sending WriteBPELVariable request to " + holder);
					WriteBPELVariableRequest writeRequest = new WriteBPELVariableRequest();
					writeRequest.setP2PSessionId(p2pSessionId);
					writeRequest.setVariableId(variableId);
					writeRequest.setVariableValue(writeVariables.get(variableId));
					P2PEndpoint p2pEndpoint = new P2PEndpoint();
					p2pEndpoint.setAddress(new URI(holder));
					me.invokeTwoWayService(p2pEndpoint, writeRequest);
					me.getLog().info("Received WriteBPELVariable response");
				} catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
