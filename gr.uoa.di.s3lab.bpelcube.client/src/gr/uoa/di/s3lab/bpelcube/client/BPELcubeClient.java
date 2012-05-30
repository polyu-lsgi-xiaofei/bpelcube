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
package gr.uoa.di.s3lab.bpelcube.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.impl.builder.StAXSOAPModelBuilder;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.MessageContext;

/**
 * Implements a client for testing with the BPELcube engine.
 * 
 * @author Michael Pantazoglou
 *
 */
public class BPELcubeClient {
	
	/**
	 * The name of the BPEL process that this client will execute.
	 */
	private String processName;
	
	/**
	 * The full path of the file that contains the network addresses of the 
	 * BPELcube nodes.
	 */
	private String nodesFile;
	
	/**
	 * The network addresses of the BPELcube nodes.
	 */
	private String[] nodes;
	
	/**
	 * The full path of the file with the SOAP request that will be used for the 
	 * invocation of the BPEL process.
	 */
	private String requestFile;
	
	/**
	 * The SOAP request that will be used for the invocation of the BPEL 
	 * process.
	 */
	private String request;
	
	/**
	 * The number of requests per minute that this client will send to the 
	 * BPELcube engine.
	 */
	private int requestsPerMinute;
	
	/**
	 * The duration in minutes that this client will be running.
	 */
	private int durationInMinutes;
	
	/**
	 * The list of process execution times that are persisted by the client.
	 */
	private List<Long> executionTimes;
	
	/**
	 * The list of throughput measurements that are taken by the client while 
	 * running.
	 */
	private List<Integer> throughputs;
	
	/**
	 * The random generator that is used for the random selection of BPELcube
	 * nodes.
	 */
	private Random random = new Random();
	
	/**
	 * Constructor.
	 */
	public BPELcubeClient() {
		executionTimes = new ArrayList<Long>();
		throughputs = new ArrayList<Integer>();
	}
	
	/**
	 * Prints the command-line usage of this program.
	 */
	private static void printUsage() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("BPELcubeClient 1.0.0. Usage: \r\n");
		sb.append("BPELcubeClient [-p processName] [-f nodesfile] [-r requestfile] [-rpm requests/minute] [-d duration]\r\n");
		
		System.out.println(sb.toString());
	}
	
	/**
	 * Extracts the user input from the command-line.
	 * 
	 * @param args the command-line arguments
	 */
	private void extractArguments(String[] args) {
		int i=0;
		if (!args[i++].equals("-p")) {
			printUsage();
			System.exit(-1);
		}
		processName = args[i++];
		if (!args[i++].equals("-f")) {
			printUsage();
			System.exit(-1);
		}
		nodesFile = args[i++];
		if (!args[i++].equals("-r")) {
			printUsage();
			System.exit(-1);
		}
		requestFile = args[i++];
		if (!args[i++].equals("-rpm")) {
			printUsage();
			System.exit(-1);
		}
		requestsPerMinute = Integer.valueOf(args[i++]);
		if (!args[i++].equals("-d")) {
			printUsage();
			System.exit(-1);
		}
		if (requestsPerMinute < 1) {
			System.out.println("Requests per minute cannot be less than 1.");
			System.exit(-1);
		}
		durationInMinutes = Integer.valueOf(args[i++]);
		if (durationInMinutes < 1) {
			System.out.println("Duration in minutes cannot be less than 1.");
			System.exit(-1);
		}
	}
	
	/**
	 * Loads from file to memory the network addresses of the BPELcube nodes.
	 * 
	 * @return
	 */
	private boolean loadNodes() {
		List<String> nodesList = new ArrayList<String>();
		try {
			FileInputStream fstream = new FileInputStream(nodesFile);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				nodesList.add(strLine);
			}
			in.close();
			nodes = nodesList.toArray(new String[nodesList.size()]);
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
	 * Loads from file to memory the SOAP request.
	 * 
	 * @return
	 */
	private boolean loadRequest() {
		request = "";
		try {
			FileInputStream fstream = new FileInputStream(requestFile);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				request += strLine;
			}
			in.close();
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
	 * Creates and returns an endpoint reference for the specified node 
	 * network address.
	 * 
	 * @param node
	 * @return
	 */
	private EndpointReference getBPELProcessEndpoint(String node) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		sb.append(node).append(":");
		sb.append(8080).append("/ode/processes/").append(processName);
		return new EndpointReference(sb.toString());
	}
	
	/**
	 * Performs a single invocation of the BPEL process.
	 */
	private void invoke() {
		new Thread(new Runnable() {
			public void run() {
				try {
					
					// Randomly select the BPELcube node that will become the 
					// manager of this invocation
					int nodeIndex = random.nextInt(nodes.length);
					System.out.println("Invoking BPEL process in node: " + nodes[nodeIndex]);
					
					ServiceClient serviceClient = new ServiceClient();
					Options options = new Options();
					options.setTo(getBPELProcessEndpoint(nodes[nodeIndex]));
					serviceClient.setOptions(options);
					
					OMElement omElement = AXIOMUtil.stringToOM(request);
					StAXSOAPModelBuilder builder = new StAXSOAPModelBuilder(omElement.getXMLStreamReader());
					SOAPEnvelope soapRequest = builder.getSOAPEnvelope();
					MessageContext newMessageContext = new MessageContext();
					newMessageContext.setEnvelope(soapRequest);
					
					OperationClient operationClient = serviceClient.createClient(ServiceClient.ANON_OUT_IN_OP);
					operationClient.addMessageContext(newMessageContext);
					
					long start = System.currentTimeMillis();
					operationClient.execute(true);
					operationClient.complete(newMessageContext);
					long end = System.currentTimeMillis();
					addExecutionTime(end - start);
				} catch (AxisFault e) {
					e.printStackTrace();
				} catch (XMLStreamException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	/**
	 * Persists the specified process execution time.
	 *  
	 * @param executionTime
	 */
	private synchronized void addExecutionTime(long executionTime) {
		System.out.println("Execution time: " + executionTime + " ms");
		executionTimes.add(executionTime);
	}
	
	/**
	 * Persists the specified throughput measurement.
	 * 
	 * @param throughput
	 */
	private synchronized void addThroughput(int throughput) {
		System.out.println("Throughput: " + throughput + " processes/min");
		throughputs.add(throughput);
	}
	
	/**
	 * Calculates and returns the average process execution time.
	 * 
	 * @return
	 */
	private long getAvgExecutionTime() {
		long avg = 0;
		for (long e : executionTimes) {
			avg += e;
		}
		return avg / executionTimes.size();
	}
	
	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length < 8) {
			printUsage();
			System.exit(-1);
		}
		
		final BPELcubeClient app = new BPELcubeClient();
		app.extractArguments(args);
		if (!app.loadNodes()) {
			System.out.println("Failed to load nodes file.");
			System.exit(-1);
		}
		if (!app.loadRequest()) {
			System.out.println("Failed to load request file.");
			System.exit(-1);
		}
		
		final long minute = 60 * 1000; 
		
		Thread t = new Thread() {
			public void run() {
				
				long sleepPeriod = minute / app.requestsPerMinute;
				while (true) {
					app.invoke();
					try {
						Thread.sleep(sleepPeriod);
					} catch (InterruptedException e) {
						break;
					}
				}
			}
		};
		
		synchronized (t) {
			t.start();
			try {
				int minutes = 0;
				while (true) {
					t.join((long)(minute));
					minutes += 1;
					app.addThroughput(app.executionTimes.size() / minutes);
					if (minutes == app.durationInMinutes) {
						break;
					}
				}
				t.interrupt();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Allow active invocation threads to gracefully complete
		try {
			System.out.println("Waiting for active invocations to complete...");
			Thread.sleep(minute);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Experiment finished.");
		System.out.println("Avg execution time: " + app.getAvgExecutionTime() + " ms");
	}

}
