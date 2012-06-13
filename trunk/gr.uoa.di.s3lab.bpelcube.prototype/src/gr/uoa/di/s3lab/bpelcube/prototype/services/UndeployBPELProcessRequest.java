package gr.uoa.di.s3lab.bpelcube.prototype.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

public class UndeployBPELProcessRequest extends P2PRequest {

	private static final long serialVersionUID = 201206131952L;
	
	private String processId;
	
	private boolean broadcastInitiated;

	public UndeployBPELProcessRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return null;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public boolean isBroadcastInitiated() {
		return broadcastInitiated;
	}

	public void setBroadcastInitiated(boolean broadcastInitiated) {
		this.broadcastInitiated = broadcastInitiated;
	}

}
