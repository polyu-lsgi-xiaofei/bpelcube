/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.ode.axis2.hooks;

import gr.uoa.di.s3lab.bpelcube.BPELCubeNode;
import gr.uoa.di.s3lab.bpelcube.BPELCubeUtils;
import gr.uoa.di.s3lab.bpelcube.services.ExecuteBPELProcessRequest;
import gr.uoa.di.s3lab.p2p.hypercube.Hypercube;
import gr.uoa.di.s3lab.p2p.hypercube.Neighbor;
import gr.uoa.di.s3lab.p2p.hypercube.services.ShortestPathRouteRequest;

import java.util.ArrayList;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPHeader;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.engine.AxisEngine;
import org.apache.axis2.receivers.AbstractMessageReceiver;
import org.apache.axis2.util.MessageContextBuilder;
import org.apache.axis2.wsdl.WSDLConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ode.axis2.ODEService;

/**
 * Receives messages forwarded by Axis.
 */
public class ODEMessageReceiver extends AbstractMessageReceiver {

    private static final Log __log = LogFactory.getLog(ODEMessageReceiver.class);

    private ODEService _service;
    
    /**
     * Michael Pantazoglou: Starts a random walk within the BPELcube in order 
     * to find the node that will actually execute the BPEL process.
     * 
     * @param msgContext contains the SOAP request for the BPEL process
     */
    private void doRandomWalk(MessageContext msgContext) {
    	BPELCubeNode me = (BPELCubeNode) BPELCubeNode.sharedInstance;
    	// Prepare ExecuteBPELProcessRequest
		ExecuteBPELProcessRequest executeBPELProcessRequest = new ExecuteBPELProcessRequest();
		executeBPELProcessRequest.setProcessEndpointAddress(msgContext.getTo().getAddress());
		executeBPELProcessRequest.setProcessSOAPRequest(msgContext.getEnvelope().toString());
		executeBPELProcessRequest.setReplyTo(msgContext.getFrom());
		// Start random walk
		__log.info("Starting random walk in BPELcube");
		int[] destination = Hypercube.getRandomPositionVector();
		__log.info("Random walk destination: " + Hypercube.vectorAsString(destination));
		Neighbor n = me.getNextNeighborInShortestPath(destination);
		if (n != null) {
			try {
				__log.info("Next position in shortest path routing: " + Hypercube.vectorAsString(n.getPositionVector()) + " (" + n.getNetworkAddress() + ")");
				ShortestPathRouteRequest shortestPathRouteRequest = new ShortestPathRouteRequest();
				shortestPathRouteRequest.setDestinationPositionVector(destination);
				shortestPathRouteRequest.setServiceRequest(executeBPELProcessRequest);
				me.invokeOneWayService(n.asP2PEndpoint(), shortestPathRouteRequest);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// The destination position is covered by me 
		}
    }

    @SuppressWarnings("rawtypes")
	public final void invokeBusinessLogic(final MessageContext msgContext) throws AxisFault {
    	
//    	/**********************************************************************/
//    	// Michael Pantazoglou: 
//    	// Check if shortest-path routing has been performed or not
//    	
//    	SOAPEnvelope soapEnvelope = msgContext.getEnvelope();
//    	SOAPHeader soapHeader = soapEnvelope.getHeader();
//    	if (soapHeader != null) { // the soap request contains a header
//    		ArrayList soapHeaderBlocks = soapHeader.getHeaderBlocksWithNSURI(
//    				BPELCubeUtils.BPELCUBE_NS);
//    		if (soapHeaderBlocks != null) { // the header contains bpelcube header blocks
//    			boolean isRouted = false;
//    			for (int i=0; i<soapHeaderBlocks.size(); i++) {
//    				SOAPHeaderBlock soapHeaderBlock = (SOAPHeaderBlock) soapHeaderBlocks.get(i);
//    				if (soapHeaderBlock.getLocalName().equals(BPELCubeUtils.SOAP_HEADER_ROUTED.getLocalPart())) {
//    					// the routed bpelcube header block was found.
//    					// this means that this node must proceed with the 
//    					// execution of the incoming soap request
//    					isRouted = true;
//    					break;
//    				}
//    			}
//    			if (!isRouted) {
//    				// after processing all bpelcube header blocks, we didn't 
//    				// find the routed header block. This means that this node
//    				// must start a random walk.
//    				doRandomWalk(msgContext);
//    				return;
//    			}
//    		} else { 
//    			// no bpelcube header blocks were found, which means that this 
//    			// node will start a random walk
//    			doRandomWalk(msgContext);
//    			return;
//    		}
//    	} else { 
//    		// no header was found, so this node will do a random walk
//    		doRandomWalk(msgContext);
//    		return;
//    	}
//    	// If this point is reached, it means that a random walk has already 
//    	// been performed and thus this soap request must be processed.
//    	/**********************************************************************/
    	
        if (hasResponse(msgContext.getAxisOperation())) {
            if (__log.isDebugEnabled())
                __log.debug("Received request message for " + msgContext.getAxisService().getName() + "."
                        + msgContext.getAxisOperation().getName());
            // Client is expecting a response, running in the same thread
            MessageContext outMsgContext = MessageContextBuilder.createOutMessageContext(msgContext);
            // pass on the endpoint properties for output context
            outMsgContext.getOptions().setParent(_service.getOptions());
            outMsgContext.getOperationContext().addMessageContext(outMsgContext);
            invokeBusinessLogic(msgContext, outMsgContext);
            if (__log.isDebugEnabled()) {
                __log.debug("Reply for " + msgContext.getAxisService().getName() + "."
                        + msgContext.getAxisOperation().getName());
                __log.debug("Reply message " + outMsgContext.getEnvelope());
            }
            AxisEngine.send(outMsgContext);
        } else {
            if (__log.isDebugEnabled())
                __log.debug("Received one-way message for " + msgContext.getAxisService().getName() + "."
                        + msgContext.getAxisOperation().getName());
            invokeBusinessLogic(msgContext, null);
        }
    }

    private void invokeBusinessLogic(MessageContext msgContext, MessageContext outMsgContext)
            throws AxisFault {
        _service.onAxisMessageExchange(msgContext, outMsgContext, getSOAPFactory(msgContext));

    }

    public void setService(ODEService service) {
        _service = service;
    }

    private boolean hasResponse(AxisOperation op) {
        switch (op.getAxisSpecificMEPConstant()) {
            case WSDLConstants.MEP_CONSTANT_IN_OUT:
                return true;
            case WSDLConstants.MEP_CONSTANT_OUT_ONLY:
                return true;
            case WSDLConstants.MEP_CONSTANT_OUT_OPTIONAL_IN:
                return true;
            case WSDLConstants.MEP_CONSTANT_ROBUST_OUT_ONLY:
                return true;
            default:
                return false;
        }
    }
}
