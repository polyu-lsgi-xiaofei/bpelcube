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

import gr.uoa.di.s3lab.p2p.P2PEndpoint;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.context.MessageContext;

/**
 * 
 * @author Michael Pantazoglou
 *
 */
public class RecruitRequest extends P2PRequest {
	
	private static final long serialVersionUID = 201204231439L;
	/**
	 * The P2P session id.
	 */
	private String p2pSessionId;
	/**
	 * Creation time of the P2P session.
	 */
	private long p2pSessionCreationTime;
	
	/**
	 * Ids of the activities to be distributed.
	 */
	private List<String> activityIds;
	/**
	 * The P2P endpoint of the manager node for the particular P2P session.
	 */
	private P2PEndpoint managerEndpoint;
	/**
	 * The P2P endpoint of the requester node.
	 */
	private P2PEndpoint requesterEndpoint;
	
	/**
	 * The SOAP request message that will be used to invoke the BPEL process.
	 */
	private MessageContext messageContext;
	
	/**
	 * Constructor.
	 */
	public RecruitRequest() {
		super();
		activityIds = new ArrayList<String>();
	}
	
	@Override
	public P2PService createService() {
		return new RecruitService();
	}

	public String getP2PSessionId() {
		return p2pSessionId;
	}

	public void setP2PSessionId(String p2pSessionId) {
		this.p2pSessionId = p2pSessionId;
	}

	public long getP2PSessionCreationTime() {
		return p2pSessionCreationTime;
	}

	public void setP2PSessionCreationTime(long p2pSessionCreationTime) {
		this.p2pSessionCreationTime = p2pSessionCreationTime;
	}

	public List<String> getActivityIds() {
		return activityIds;
	}

	public void setActivityIds(List<String> activityIds) {
		this.activityIds = activityIds;
	}

	public P2PEndpoint getManagerEndpoint() {
		return managerEndpoint;
	}

	public void setManagerEndpoint(P2PEndpoint managerEndpoint) {
		this.managerEndpoint = managerEndpoint;
	}

	public P2PEndpoint getRequesterEndpoint() {
		return requesterEndpoint;
	}

	public void setRequesterEndpoint(P2PEndpoint requesterEndpoint) {
		this.requesterEndpoint = requesterEndpoint;
	}

	public MessageContext getMessageContext() {
		return messageContext;
	}

	public void setMessageContext(MessageContext messageContext) {
		this.messageContext = messageContext;
	}

}
