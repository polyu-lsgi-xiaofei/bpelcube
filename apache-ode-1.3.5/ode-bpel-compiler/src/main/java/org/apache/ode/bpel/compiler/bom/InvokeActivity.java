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
package org.apache.ode.bpel.compiler.bom;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.xml.namespace.QName;

import org.w3c.dom.Element;

/**
 * BOM representation of a BPEL <code>&lt;invoke&gt;</code> activity.
 */
public class InvokeActivity extends ScopeLikeActivity implements Communication {
    
    /** @since 17-09-2012, 
     * @author George Athanasopoulos
     * This private memeber is used for holding the specified meta-information used for DDA-SoP
     */
    private EnvOutcome _envisionExtensions;
    
    /** Mix-In for handling the {@link Communication} interface methods. */
    private CommunicationHelper _commHelper;
    
    @Override
    public Scope getScope() {
        return new BastardScope(getElement());
    }

    public InvokeActivity(Element el) {
        super(el);
        
        // One of the few times where multiple inheritence would come in handy.
        _commHelper = new CommunicationHelper(el);
        
//        @author George Athanasopoulos
        if (el.getElementsByTagNameNS(EnvisionExtensionQNames.ENVISION_OUTCOME_QNAME.getNamespaceURI(),
                EnvisionExtensionQNames.ENVISION_OUTCOME_QNAME.getLocalPart()) != null && el.
                getElementsByTagNameNS(EnvisionExtensionQNames.ENVISION_OUTCOME_QNAME.getNamespaceURI(),
                EnvisionExtensionQNames.ENVISION_OUTCOME_QNAME.getLocalPart()).getLength() > 0) {
            Element outcomeElement = this.getExtensibilityElement(EnvisionExtensionQNames.ENVISION_OUTCOME_QNAME);
            _envisionExtensions = new EnvOutcome(outcomeElement);
        }
    }

    public Collection<URI> getMetamodelReference(){
        return _envisionExtensions!=null?_envisionExtensions.getModelReference():null;
    }
    public URI getLiftingScheme(){
        return _envisionExtensions!=null?_envisionExtensions.getLiftingScheme():null;
    }
    public URI getLoweringScheme(){
        return _envisionExtensions!=null?_envisionExtensions.getLoweringScheme():null;
    }
    public Collection<EnvMPolygon> getMultiPolygons(){
        return (Collection<EnvMPolygon>) (_envisionExtensions!=null?_envisionExtensions.getMultiPolygons():Collections.emptyList());
    }
    public Collection<EnvTimeInterval> getTimeIntervals(){
        return (Collection<EnvTimeInterval>) (_envisionExtensions!=null?_envisionExtensions.getTimeIntervals():Collections.emptyList());
    }
    /**
     * Get the input variable.
     * 
     * @return name of input variable
     */
    public String getInputVar() {
        return getAttribute("inputVariable", null);
    }

    /**
     * The output variable.
     * 
     * @return output variable name
     */
    public String getOutputVar() {
        return getAttribute("outputVariable", null);

    }

    public String getOperation() {
        return _commHelper.getOperation();
    }

    public String getPartnerLink() {
        return _commHelper.getPartnerLink();
    }

    public QName getPortType() {
        return _commHelper.getPortType();
    }

    public List<Correlation> getCorrelations() {
        return _commHelper.getCorrelations();
    }

    
    /**
     * Bastardized scope for invokes. Only supports catches/compensation handlers.
     * @author mszefler
     *
     */
    static class BastardScope extends Scope {

        public BastardScope(Element el) {
            super(el);
        }

        @Override
        public FaultHandler getFaultHandler() {
            // Note, in <invoke> unlike <scope> the catches are NOT in an <faultHandler> element. 
            return new FaultHandler(getElement());
        }

        @Override
        public CorrelationSet getCorrelationSetDecl(String setName) {
            return null;
        }

        @Override
        public List<CorrelationSet> getCorrelationSetDecls() {
            return Collections.emptyList();
        }

        @Override
        public List<OnEvent> getEvents() {
            return Collections.emptyList();
        }

        @Override
        public PartnerLink getPartnerLink(String partnerLinkName) {
            return null;
        }

        @Override
        public List<PartnerLink> getPartnerLinks() {
            return Collections.emptyList();
        }

        @Override
        public Variable getVariableDecl(String varName) {
            return null; 
        }

        @Override
        public List<Variable> getVariables() {
            return Collections.emptyList();

        }

        
    }
}
