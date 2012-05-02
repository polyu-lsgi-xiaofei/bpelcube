package gr.uoa.di.s3lab.bpelcube.services;

import gr.uoa.di.s3lab.bpelcube.BPELCubeService;
import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PResponse;

import java.io.File;

/**
 * This service undeploys the BPEL process bundle that is specified in the 
 * request.
 * 
 * @author Michael Pantazoglou
 *
 */
public class UndeployProcessBundleService extends BPELCubeService {
	
	/**
	 * The request that triggered this service.
	 */
	private UndeployProcessBundleRequest request;
	
	/**
	 * Constructor.
	 */
	public UndeployProcessBundleService() {
		super();
	}

	@Override
	public void setRequest(P2PRequest req) {
		request = (UndeployProcessBundleRequest) req;
	}
	
	private boolean removeDirectory(File directory) {

		if (directory == null)
			return false;
		if (!directory.exists())
			return true;
		if (!directory.isDirectory())
			return false;

		String[] list = directory.list();

		// Some JVMs return null for File.list() when the directory is empty.
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				File entry = new File(directory, list[i]);
				if (entry.isDirectory()) {
					if (!removeDirectory(entry))
						return false;
				}
				else {
					if (!entry.delete()) {
						return false;
					}
				}
			}
		}
		return directory.delete();
	}

	@Override
	public void execute() {
		
		me.getLog().info("Undeploying bundle folder with name: " + request.getBundleName());
		
		File bundleDir = new File(me.getBPELEngineDeployDirectory(), request.getBundleName());
		boolean deleted = this.removeDirectory(bundleDir);
		me.getLog().info("Undeploy bundle folder " + (deleted?"success":"failure"));
		
		me.getLog().info("Removing bundle zip file in case it exists");
		File bundleZip = new File(me.getBPELEngineDeployDirectory(), request.getBundleName() + ".zip");
		if (bundleZip.exists()) {
			boolean fileDeleted = bundleZip.delete();
			me.getLog().info("Bundle zip file remove " + (fileDeleted?"success":"failure"));
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
