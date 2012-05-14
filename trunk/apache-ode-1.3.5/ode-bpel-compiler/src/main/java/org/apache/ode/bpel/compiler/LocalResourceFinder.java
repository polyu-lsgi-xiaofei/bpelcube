package org.apache.ode.bpel.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is used by the <code>DefaultResourceFinder</code>, in order to 
 * retrieve resources that are locally available.
 * 
 * @author Michael Pantazoglou
 *
 */
public class LocalResourceFinder {
	
	/**
	 * The log for this class.
	 */
	private static final Log LOG = LogFactory.getLog(
			LocalResourceFinder.class);
	
	/**
	 * The list of URIs, for which the corresponding resource is locally stored.
	 */
	private static List<URI> localResources;
	
	/**
	 * Loads the list of URIs, for which the corresponding resource is locally 
	 * stored.
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private static void loadLocalResources() throws IOException, 
			URISyntaxException {
		if (localResources == null) {
			LOG.debug("Loading list of local resources URI for the first time");
			localResources = new ArrayList<URI>();
			InputStream in = LocalResourceFinder.class.getResourceAsStream(
					"localResources");
			if (in == null) {
				LOG.debug("Could not find local resources file");
				return;
			}
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader r = new BufferedReader(reader);
			while (true) {
				String s = r.readLine();
				if (s == null) {
					break;
				}
				localResources.add(new URI(s));
			}
			LOG.debug("Loaded " + localResources.size() + " local resource URI(s)");
			r.close();
            reader.close();
            in.close();
		}
	}
	
	/**
	 * Attempts to open and return an input stream for the specified 
	 * <b>absolute</b> URI. This method is intended to prevent downloading 
	 * the resource from its original location, if a mapping exists and the 
	 * resource is locally stored.
	 * 
	 * @param uri
	 * @return
	 */
	public static InputStream openResource(URI uri) {
		try {
			loadLocalResources();
			LOG.debug("Looking up local resource for " + uri);
			if (localResources.contains(uri)) {
				LOG.debug("Found local resource for " + uri);
				String resourceLocation = uri.getHost() + uri.getPath();
				LOG.debug("Resource location: " + resourceLocation);
				InputStream in = 
					LocalResourceFinder.class.getClassLoader().getResourceAsStream(
						resourceLocation);
				if (in == null) {
					LOG.debug("Could not open input stream for " + resourceLocation);
					return null;
				} else {
					return in;
				}
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

}
