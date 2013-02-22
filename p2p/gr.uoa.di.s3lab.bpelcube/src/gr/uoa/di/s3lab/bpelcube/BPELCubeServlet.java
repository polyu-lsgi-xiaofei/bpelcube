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
package gr.uoa.di.s3lab.bpelcube;

import gr.uoa.di.s3lab.p2p.hypercube.Hypercube;
import gr.uoa.di.s3lab.p2p.hypercube.Neighbor;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implements a simple servlet that displays information about the local 
 * BPELcube node.
 * 
 * @author Michael Pantazoglou
 *
 */
public class BPELCubeServlet extends HttpServlet {

	private static final long serialVersionUID = 201205282215L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		BPELCubeNode me = (BPELCubeNode) BPELCubeNode.sharedInstance;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html>");
		sb.append("<header>");
		sb.append("<title>BPELcube</title>");
		sb.append("</header>");
		sb.append("<body>");
		sb.append("<h1>BPELcube</h1>");
		
		// Print local node information
		sb.append("<h2>Local node</h2>");
		sb.append("<table cellpadding=4 cellspacing=4 border=1>");
		sb.append("<tr>");
		sb.append("<td>Position vector:</td><td>").append(Hypercube.vectorAsString(me.getPositionVector())).append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>Name:</td><td>").append(me.getName()).append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>Address:</td><td>").append(me.getAddress()).append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>Bootstrap URI:</td><td>").append(me.getBootstrapURI()).append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>Last use:</td><td>").append(new Date(me.getLastUsed())).append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		
		// Print hypercube neighbours
		sb.append("<h2>Hypercube Neighbours</h2>");
		sb.append("<table cellpadding=4 cellspacing=4 border=1>");
		sb.append("<tr>");
		sb.append("<th>Dimension</th>");
		sb.append("<th>Position Vector</th>");
		sb.append("<th>Address</th>");
		sb.append("<th>Last Use</th>");
		sb.append("</tr>");
		
		Set<Neighbor> neighborSet = me.getNeighborSet();
		for (Neighbor neighbor : neighborSet) {
			int dimension = Hypercube.getLinkDimensionality(me.getPositionVector(), neighbor.getPositionVector());
			sb.append("<tr>");
			sb.append("<td>").append(dimension).append("</td>");
			sb.append("<td>").append(Hypercube.vectorAsString(neighbor.getPositionVector())).append("</td>");
			sb.append("<td>").append(neighbor.getNetworkAddress()).append("</td>");
			sb.append("<td>").append(new Date(neighbor.getLastUsed())).append("</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		
		sb.append("</body>");
		sb.append("</html>");
		
		resp.getWriter().write(sb.toString());
	}

}
