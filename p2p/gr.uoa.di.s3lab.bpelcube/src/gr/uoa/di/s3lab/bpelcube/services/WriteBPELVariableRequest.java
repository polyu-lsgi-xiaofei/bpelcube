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
package gr.uoa.di.s3lab.bpelcube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

/**
 * This class implements the request message of the WriteBPELVariable p2p service.
 * 
 * @author Michael Pantazoglou
 *
 */
public class WriteBPELVariableRequest extends P2PRequest {

	private static final long serialVersionUID = 201205051820L;
	
	/**
	 * The p2p session id.
	 */
	private String p2pSessionId;
	
	/**
	 * The variable id.
	 */
	private String variableId;
	
	/**
	 * The variable value.
	 */
	private String variableValue;
	
	/**
	 * Constructor.
	 */
	public WriteBPELVariableRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new WriteBPELVariableService();
	}

	public String getP2pSessionId() {
		return p2pSessionId;
	}

	public void setP2pSessionId(String p2pSessionId) {
		this.p2pSessionId = p2pSessionId;
	}

	public String getVariableId() {
		return variableId;
	}

	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}

	public String getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

}
