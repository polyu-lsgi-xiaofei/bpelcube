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

import gr.uoa.di.s3lab.bpelcube.BPELCubeService;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;

/**
 * This class implements the WriteBPELVariable p2p service, which is used to 
 * update the value of a specified variable in the context of a specified p2p
 * session.
 * 
 * @author Michael Pantazoglou
 *
 */
public class WriteBPELVariableService extends BPELCubeService {
	
	/**
	 * The request that triggered this service.
	 */
	private WriteBPELVariableRequest request;
	
	/**
	 * The response produced by this service.
	 */
	private WriteBPELVariableResponse response;
	
	/**
	 * Constructor.
	 */
	public WriteBPELVariableService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (WriteBPELVariableRequest) req;
	}

	@Override
	public void execute() {
		response = new WriteBPELVariableResponse();
		
		String p2pSessionId = request.getP2pSessionId();
		String variableId = request.getVariableId();
		String variableValue = request.getVariableValue();
		
		me.getLog().debug("Updating value of variable: " + variableId);
		
		db.updateVariableValue(p2pSessionId, variableId, variableValue);
	}

	@Override
	public boolean isRequestResponse() {
		return true;
	}

	@Override
	public P2PResponse getResponse() {
		return response;
	}

}
