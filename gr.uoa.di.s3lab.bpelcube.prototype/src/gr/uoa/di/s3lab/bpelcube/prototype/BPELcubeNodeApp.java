package gr.uoa.di.s3lab.bpelcube.prototype;

import gr.uoa.di.s3lab.p2p.Log.Level;

import java.net.URI;
import java.net.URISyntaxException;

public class BPELcubeNodeApp {
	
	private BPELcubeNode node;
	
	private String nodeHome;
	
	private String nodeName;
	
	private String nodeAddress;
	
	private String bootstrapURI;
	
	public BPELcubeNodeApp() {
		
	}
	
	private static void printUsage() {
		StringBuilder sb = new StringBuilder();
		sb.append("BPELcubeNodeApp 1.0.0. Usage: \r\n");
		sb.append("BPELcubeNodeApp ");
		sb.append("[-h home URI] ");
		sb.append("[-n node name] ");
		sb.append("[-a node address] ");
		sb.append("[-b bootstrap URI] ");
		sb.append("\r\n");
		System.out.println(sb.toString());
	}
	
	private void extractArguments(String[] args) {
		int i=0;
		if (!args[i++].equals("-h")) {
			printUsage();
			System.exit(-1);
		}
		nodeHome = args[i++];
		if (!args[i++].equals("-n")) {
			printUsage();
			System.exit(-1);
		}
		nodeName = args[i++];
		if (!args[i++].equals("-a")) {
			printUsage();
			System.exit(-1);
		}
		nodeAddress = args[i++];
		if (!args[i++].equals("-b")) {
			printUsage();
			System.exit(-1);
		}
		bootstrapURI = args[i++];
	}
	
	private void run() {
		try {
			URI home = new URI(nodeHome);
			URI address = new URI(nodeAddress);
			int port = address.getPort();
			String domain = "BPELcube";
			node = new BPELcubeNode(home, nodeName, domain, address, port, Level.DEBUG);
			if (!bootstrapURI.equals("null")) {
				node.setBootstrapURI(new URI(bootstrapURI));
			}
			node.start();
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					if (node.isStarted()) {
						try {
							node.stop();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
			while (true) {
				Thread.sleep(1000);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		} 
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 8) {
			printUsage();
			System.exit(-1);
		}
		BPELcubeNodeApp app = new BPELcubeNodeApp();
		app.extractArguments(args);
		app.run();
	}

}
