package gr.uoa.di.s3lab.bpelcube.prototype.services;

import gr.uoa.di.s3lab.bpelcube.prototype.model.BPELProcess;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;

public class DeployBPELProcessRequest extends P2PRequest {

	private static final long serialVersionUID = 201206131948L;
	
	private BPELProcess bpelProcess;
	
	private boolean broadcastInitiated;

	public DeployBPELProcessRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new DeployBPELProcessService();
	}

	public BPELProcess getBPELProcess() {
		return bpelProcess;
	}

	public void setBPELProcess(BPELProcess bpelProcess) {
		this.bpelProcess = bpelProcess;
	}

	public boolean isBroadcastInitiated() {
		return broadcastInitiated;
	}

	public void setBroadcastInitiated(boolean broadcastInitiated) {
		this.broadcastInitiated = broadcastInitiated;
	}

}
