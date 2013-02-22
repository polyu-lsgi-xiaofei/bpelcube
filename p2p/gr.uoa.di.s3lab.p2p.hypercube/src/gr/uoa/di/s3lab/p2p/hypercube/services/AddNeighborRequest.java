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
package gr.uoa.di.s3lab.p2p.hypercube.services;

import gr.uoa.di.s3lab.p2p.P2PRequest;
import gr.uoa.di.s3lab.p2p.P2PService;
import gr.uoa.di.s3lab.p2p.hypercube.Neighbor;

/**
 * This class implements the request that is sent to a node in order to add 
 * a specific node to its list of neighbors.
 * 
 * @author Michael Pantazoglou
 *
 */
public class AddNeighborRequest extends P2PRequest {

	private static final long serialVersionUID = 201204092352L;
	/**
	 * The neighbor to be added.
	 */
	private Neighbor newNeighbor;

	/**
	 * Constructor.
	 */
	public AddNeighborRequest() {
		super();
	}

	@Override
	public P2PService createService() {
		return new AddNeighborService();
	}

	/**
	 * @return the newNeighbor
	 */
	public Neighbor getNewNeighbor() {
		return newNeighbor;
	}

	/**
	 * @param newNeighbor the newNeighbor to set
	 */
	public void setNewNeighbor(Neighbor newNeighbor) {
		this.newNeighbor = newNeighbor;
	}

}
