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

import gr.uoa.di.s3lab.bpelcube.BPELActivityListener;
import gr.uoa.di.s3lab.bpelcube.BPELActivityNotifier;
import gr.uoa.di.s3lab.bpelcube.BPELCubeService;
import gr.uoa.di.s3lab.p2p.P2PEndpoint;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;

import java.util.Hashtable;
import java.util.Set;

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
		
		// persist all new variables
		Hashtable<String, P2PEndpoint> newVariableHolders = request.getNewVariableHolders();
		Set<String> variableIds = newVariableHolders.keySet();
		for (String vid : variableIds) {
			P2PEndpoint holderEndpoint = newVariableHolders.get(vid);
			// the variable value is set to null because the variable is held remotely
			db.addVariable(request.getP2PSessionId(), vid, holderEndpoint.toString(), null);
		}
		
		// notify the respective activity listener
		BPELActivityListener<P2PRequest> activityListener = null;
		while (true) {
			activityListener = me.getActivityListener(
					request.getP2PSessionId(), request.getActivityId());
			if (activityListener != null) {
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		BPELActivityNotifier<P2PRequest> activityNotifier = 
				new BPELActivityNotifier<P2PRequest>(activityListener.getQueue());
		try {
			activityNotifier.notify(request);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
