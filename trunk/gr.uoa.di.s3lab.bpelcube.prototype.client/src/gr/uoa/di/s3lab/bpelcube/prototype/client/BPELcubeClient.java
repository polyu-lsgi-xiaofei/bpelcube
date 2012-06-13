package gr.uoa.di.s3lab.bpelcube.prototype.client;

import gr.uoa.di.s3lab.bpelcube.prototype.model.BPELProcess;
import gr.uoa.di.s3lab.bpelcube.prototype.services.DeployBPELProcessRequest;
import gr.uoa.di.s3lab.p2p.P2PEndpoint;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BPELcubeClient {
	
	private static final long MINUTE = 60 * 1000; 
	
	/**
	 * The full path of the file that contains the network addresses of the 
	 * BPELcube nodes.
	 */
	private String nodesFile;
	
	/**
	 * The number of requests per minute that this client will send to the 
	 * BPELcube engine.
	 */
	private int requestsPerMinute;
	
	/**
	 * The fixed delay (in ms) of the services being invoked.
	 */
	private long serviceDelay;
	
	/**
	 * The size (in kb) of the messages produced by the external services.
	 */
	private int messageSize;
	
	/**
	 * The duration in minutes that this client will be running.
	 */
	private int durationInMinutes;
	
	/**
	 * The P2P endpoints of the accessible BPELcube nodes.
	 */
	private P2PEndpoint[] nodes;
	
	/**
	 * The random generator that is used for the random selection of BPELcube
	 * nodes.
	 */
	private Random random = new Random();
	
	/**
	 * Constructor.
	 */
	public BPELcubeClient() {
		
	}
	
	/**
	 * Prints the command-line usage of this program.
	 */
	private static void printUsage() {
		StringBuilder sb = new StringBuilder();
		sb.append("BPELcubeClient 1.0.0. Usage: \r\n");
		sb.append("BPELcubeClient ");
		sb.append("[-f nodesfile] ");
		sb.append("[-r req/min] ");
		sb.append("[-s srv delay (ms)] ");
		sb.append("[-m msg size (kb)] ");
		sb.append("[-d duration (min)] ");
		sb.append("\r\n");
		System.out.println(sb.toString());
	}
	
	private void extractArguments(String[] args) {
		int i=0;
		if (!args[i++].equals("-f")) {
			printUsage();
			System.exit(-1);
		}
		nodesFile = args[i++];
		if (!args[i++].equals("-r")) {
			printUsage();
			System.exit(-1);
		}
		requestsPerMinute = Integer.valueOf(args[i++]);
		if (!args[i++].equals("-s")) {
			printUsage();
			System.exit(-1);
		}
		serviceDelay = Long.valueOf(args[i++]);
		if (!args[i++].equals("-m")) {
			printUsage();
			System.exit(-1);
		}
		messageSize = Integer.valueOf(args[i++]);
		if (!args[i++].equals("-d")) {
			printUsage();
			System.exit(-1);
		}
		durationInMinutes = Integer.valueOf(args[i++]);
	}
	
	private boolean loadNodes() {
		List<P2PEndpoint> nodesList = new ArrayList<P2PEndpoint>();
		try {
			FileInputStream fstream = new FileInputStream(nodesFile);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				P2PEndpoint p2pEndpoint = new P2PEndpoint();
				try {
					p2pEndpoint.setAddress(new URI(strLine));
				} catch (URISyntaxException e) {
					System.out.println(e.getMessage());
					continue;
				}
				nodesList.add(p2pEndpoint);
			}
			in.close();
			nodes = nodesList.toArray(new P2PEndpoint[nodesList.size()]);
			System.out.println("Number of nodes: " + nodes.length);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Randomly selects and returns a BPELcube node endpoint.
	 * 
	 * @return
	 */
	private P2PEndpoint getRandomNode() {
		int nodeIndex = random.nextInt(nodes.length);
		return nodes[nodeIndex];
	}
	
	private BPELProcess prepareBPELProcess() {
		// TODO Implement me
		return null;
	}
	
	/**
	 * Deploys the specified BPEL process to the BPELcube engine.
	 * 
	 * @param bpelProcess the BPEL process to be deployed
	 */
	private void deployBPELProcess(BPELProcess bpelProcess) {
		
		P2PEndpoint deployChampion = getRandomNode();
		System.out.println("Deploying BPEL process to node: " + deployChampion);
		
		DeployBPELProcessRequest deployRequest = new DeployBPELProcessRequest();
		deployRequest.setBPELProcess(bpelProcess);
		deployRequest.setBroadcastInitiated(false);
		
		
	}
	
	private void undeployBPELProcess(String processId) {
		// TODO Implement me
	}
	
	private void invokeBPELProcess(String processId) {
		// TODO Implement me
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 10) {
			printUsage();
			System.exit(-1);
		}
		final BPELcubeClient app = new BPELcubeClient();
		app.extractArguments(args);
		if (!app.loadNodes()) {
			System.out.println("Failed to load nodes file.");
			System.exit(-1);
		}
		BPELProcess bpelProcess = app.prepareBPELProcess();
		app.deployBPELProcess(bpelProcess);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// TODO Start process invocations
		app.undeployBPELProcess(bpelProcess.getId());
	}

}
