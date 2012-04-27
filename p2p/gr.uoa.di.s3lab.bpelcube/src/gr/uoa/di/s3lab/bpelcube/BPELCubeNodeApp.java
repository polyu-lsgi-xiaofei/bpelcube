package gr.uoa.di.s3lab.bpelcube;

import gr.uoa.di.s3lab.p2p.Log;
import gr.uoa.di.s3lab.p2p.Log.Level;
import gr.uoa.di.s3lab.p2p.hypercube.Hypercube;
import gr.uoa.di.s3lab.p2p.hypercube.HypercubeNodeApp;
import gr.uoa.di.s3lab.p2p.hypercube.Neighbor;
import gr.uoa.di.s3lab.p2p.hypercube.services.HelloRequest;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

public class BPELCubeNodeApp extends BPELCubeNode {
	
	private static final String PROP_HOME_URI = 
			"hypercube.node.home.uri";
	
	private static final String PROP_NAME = 
			"hypercube.node.name";
	
	private static final String PROP_DOMAIN = 
			"hypercube.node.domain";
	
	private static final String PROP_PORT = 
			"hypercube.node.port";
	
	private static final String PROP_LOG_LEVEL = 
			"hypercube.node.log.level";
	
	private static final String PROP_BOOTSTRAP_URI = 
			"hypercube.node.bootstrap.uri";
	
	private static final String PROP_ADDRESS = 
			"hypercube.node.address";
	
	// GUI-related attributes
	
	/**
	 * The main frame.
	 */
	JFrame mainFrame;
	/**
	 * The main tabbed pane.
	 */
	JTabbedPane tabbedPane;
	/**
	 * The start button.
	 */
	JButton startButton;
	/**
	 * The stop button.
	 */
	JButton stopButton;
	/**
	 * The refresh button.
	 */
	JButton refreshButton;
	/**
	 * The console text pane.
	 */
	JTextPane console;
	
	/**
	 * 
	 * @param home
	 * @param name
	 * @param domain
	 * @param port
	 * @param logLevel
	 */
	public BPELCubeNodeApp(URI home, String name, String domain, int port,
			Level logLevel) {
		super(home, name, domain, port, logLevel);
	}
	
	/**
	 * 
	 * @param home
	 * @param name
	 * @param domain
	 * @param address
	 * @param port
	 * @param logLevel
	 */
	public BPELCubeNodeApp(URI home, String name, String domain, URI address,
			int port, Level logLevel) {
		super(home, name, domain, address, port, logLevel);
	}
	
	/**
	 * Initializes the GUI.
	 */
	protected void prepareGUI() {
		
		mainFrame = new JFrame();
		mainFrame.setTitle("P2PEngine:node " + this.getName());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		tabbedPane = new JTabbedPane();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		mainPanel.add(tabbedPane, gbc);
		
		// Add console tab
		console = new JTextPane();
		console.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(console);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabbedPane.addTab("Console", scrollPane);
		
		// Add broadcast tab
		JPanel broadcastPanel = new JPanel();
		broadcastPanel.setLayout(new GridBagLayout());
		JTextPane broadcastLog = new JTextPane();
		broadcastLog.setEditable(false);
		scrollPane = new JScrollPane(broadcastLog);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		broadcastPanel.add(scrollPane, gbc);
		JButton broadcastButton = new JButton("Broadcast message");
		broadcastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				HelloRequest helloRequest = new HelloRequest();
				helloRequest.setMessage("Hello!");
				initiateBroadcast(helloRequest);
			}
		});
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		broadcastPanel.add(broadcastButton, gbc);
		tabbedPane.addTab("Broadcast", broadcastPanel);
		
		// Add deploy test tab
		JPanel deployPanel = new JPanel();
		deployPanel.setLayout(new GridBagLayout());
		JButton deployButton = new JButton("Deploy Test");
		deployButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				List<String> activityIds = new ArrayList<String>();
				activityIds.add("Assign1");
				activityIds.add("Invoke1");
				activityIds.add("Assign2");
				activityIds.add("Invoke2");
				activityIds.add("Assign3");
				activityIds.add("Invoke3");
				activityIds.add("Assign4");
				initP2PExecution(activityIds);
			}
		});
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0;
		deployPanel.add(deployButton, gbc);
		tabbedPane.addTab("Deploy", deployPanel);
		
		// Add buttons
		JPanel buttonsPanel = new JPanel();
		
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					startButton.setEnabled(false);
					start();
					stopButton.setEnabled(true);
					refreshButton.setEnabled(true);
				} catch (Exception e) {
					e.printStackTrace();
					startButton.setEnabled(true);
				}
			}
		});
		buttonsPanel.add(startButton);
		
		stopButton = new JButton("Stop");
		stopButton.setEnabled(false);
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					stopButton.setEnabled(false);
					refreshButton.setEnabled(false);
					stop();
					startButton.setEnabled(true);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
			}
		});
		buttonsPanel.add(stopButton);
		
		refreshButton = new JButton("Refresh");
		refreshButton.setEnabled(false);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				StringBuilder sb = new StringBuilder();
				sb.append("Position: ");
				sb.append(Hypercube.vectorAsString(getPositionVector()));
				sb.append("\r\n");
				sb.append("CoverMap: ");
				sb.append(Hypercube.vectorAsString(getCoverMapVector()));
				sb.append("\r\n");
				sb.append("Neighbors: ").append("\r\n");
				Set<Neighbor> myNeighbors = getNeighborSet();
				for (Neighbor n : myNeighbors) {
					sb.append("\t");
					sb.append(Hypercube.vectorAsString(n.getPositionVector()));
					sb.append(" (dimension ");
					sb.append(Hypercube.getLinkDimensionality(
							getPositionVector(),n.getPositionVector()));
					sb.append(", last used: ");
					sb.append(n.getLastUsed());
					sb.append(")\r\n");
				}
				console.setText(sb.toString());
			}
		});
		buttonsPanel.add(refreshButton);
		
		// Put everything together
		mainFrame.getContentPane().setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		mainFrame.getContentPane().add(mainPanel, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		mainFrame.getContentPane().add(buttonsPanel, gbc);
	}
	
	void showGUI() {
		mainFrame.setSize(500, 300);
		mainFrame.setMinimumSize(new Dimension(500, 300));
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	/**
	 * Loads the properties of this node.
	 * 
	 * @param nodeName
	 * @throws Exception
	 */
	static Properties loadProperties(String nodeName) throws Exception {
		Properties nodeProperties = new Properties();
		
		InputStream in = 
				HypercubeNodeApp.class.getClassLoader().getResourceAsStream(
						nodeName + ".properties");
		nodeProperties.load(in);
		in.close();
		
		return nodeProperties;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		// Load properties
		Properties nodeProperties = loadProperties(args[0]);
		
		URI homeURI = new URI(nodeProperties.getProperty(PROP_HOME_URI));
		String name = nodeProperties.getProperty(PROP_NAME);
		String domain = nodeProperties.getProperty(PROP_DOMAIN);
		URI address = new URI(nodeProperties.getProperty(PROP_ADDRESS));
		int port = Integer.valueOf(nodeProperties.getProperty(PROP_PORT));
		Log.Level logLevel = Log.Level.valueOf(nodeProperties.getProperty(PROP_LOG_LEVEL));
		String bootstrapURIAsString = nodeProperties.getProperty(PROP_BOOTSTRAP_URI);
		URI bootstrapURI = null;
		if (bootstrapURIAsString != null && bootstrapURIAsString.length() > 0) {
			bootstrapURI = new URI(bootstrapURIAsString);
		}
		
		// Instantiate the hypercube node app
		BPELCubeNodeApp app = new BPELCubeNodeApp(homeURI, name, domain, 
				address, port, logLevel);
		app.setBootstrapURI(bootstrapURI);
		
		// Show the app gui
		app.prepareGUI();
		app.showGUI();
	}

}
