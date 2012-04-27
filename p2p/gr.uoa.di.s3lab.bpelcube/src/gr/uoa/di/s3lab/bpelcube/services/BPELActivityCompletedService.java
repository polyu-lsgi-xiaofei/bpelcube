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
 * 
 * @author Michael Pantazoglou
 *
 */
public class BPELActivityCompletedService extends BPELCubeService {
	
	private BPELActivityCompletedRequest request;
	
	/**
	 * Constructor.
	 */
	public BPELActivityCompletedService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (BPELActivityCompletedRequest) req;
	}

	@Override
	public void execute() {
		// TODO Implement this method
	}

	@Override
	public boolean isRequestResponse() {
		return false;
	}

	@Override
	public P2PResponse getResponse() {
		return null;
	}

}
