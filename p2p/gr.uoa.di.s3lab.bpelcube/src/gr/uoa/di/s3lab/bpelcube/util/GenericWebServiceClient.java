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
package gr.uoa.di.s3lab.bpelcube.util;

import gr.uoa.di.s3lab.bpelcube.BPELCubeUtils;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMException;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPHeader;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axiom.soap.impl.builder.StAXSOAPModelBuilder;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.MessageContext;

/**
 * Stripped-down basic implementation of a generic web service client.
 * 
 * @author Michael Pantazoglou
 *
 */
public class GenericWebServiceClient {
	
	/**
	 * Constructor.
	 */
	public GenericWebServiceClient() {
		
	}
	
	public void invoke(String processEndpointAddress, String processSOAPRequest, String p2pSessionId, int bpelEnginePort)  {
		
		try {
			String toAddressAsString = processEndpointAddress;
			URL toAddress = new URL(toAddressAsString);
			
			StringBuilder sb = new StringBuilder();
			sb.append(toAddress.getProtocol()).append("://");
			sb.append("localhost").append(":");
			sb.append(bpelEnginePort).append(toAddress.getPath());
			
			String newToAddressAsString = sb.toString();
			
			ServiceClient serviceClient = new ServiceClient();
			Options options = new Options();
			options.setTo(new EndpointReference(newToAddressAsString));
			serviceClient.setOptions(options);
			
			String soapRequestAsString = processSOAPRequest;
			OMElement omElement = AXIOMUtil.stringToOM(soapRequestAsString);
			StAXSOAPModelBuilder builder = new StAXSOAPModelBuilder(omElement.getXMLStreamReader());
			SOAPEnvelope soapRequest = builder.getSOAPEnvelope();
			
			soapRequest.getHeader();
			SOAPFactory soapFactory = (SOAPFactory)soapRequest.getOMFactory();
			OMNamespace omNS = soapFactory.createOMNamespace(BPELCubeUtils.SOAP_HEADER_P2P_SESSION_ID.getNamespaceURI(), "bpelcube");
			SOAPHeader header = soapFactory.createSOAPHeader(soapRequest);
			SOAPHeaderBlock headerBlock = header.addHeaderBlock(BPELCubeUtils.SOAP_HEADER_P2P_SESSION_ID.getLocalPart(), omNS);
			headerBlock.setText(p2pSessionId);
			
			MessageContext newMessageContext = new MessageContext();
			newMessageContext.setEnvelope(soapRequest);
			
			OperationClient operationClient = serviceClient.createClient(ServiceClient.ANON_OUT_IN_OP);
			operationClient.addMessageContext(newMessageContext);
			operationClient.execute(false);
			operationClient.complete(newMessageContext);
			
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (OMException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

}
