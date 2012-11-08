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
import java.util.HashMap;
import java.util.LinkedList;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Map;

/**
 * Representation of the BPEL
 * <code>&lt;switch&gt;</code> activity.
 */
public class IfActivity extends Activity {

    private EnvOutcomeQueries _queries;

    public IfActivity(Element el) {
        super(el);
        //        @author George Athanasopoulos
        if (el.getElementsByTagNameNS(EnvisionExtensionQNames.ENVISION_OUTCOME_QUERIES_QNAME.getNamespaceURI(),
                EnvisionExtensionQNames.ENVISION_OUTCOME_QUERIES_QNAME.getLocalPart()) != null && el.
                getElementsByTagNameNS(EnvisionExtensionQNames.ENVISION_OUTCOME_QUERIES_QNAME.getNamespaceURI(),
                EnvisionExtensionQNames.ENVISION_OUTCOME_QUERIES_QNAME.getLocalPart()).getLength() > 0) {
            Element outcomesElement = (Element) el.getElementsByTagNameNS(EnvisionExtensionQNames.ENVISION_OUTCOME_QUERIES_QNAME.getNamespaceURI(),
                EnvisionExtensionQNames.ENVISION_OUTCOME_QUERIES_QNAME.getLocalPart()).item(0);

            
        _queries =  new EnvOutcomeQueries(outcomesElement);
        }

    }

    public Collection<EnvOutcomeQuery> getQueries() {
        return _queries.getEnvQueries();
    }

    public Expression getCondition() {
        return getFirstChild(Expression.class);
    }

    /**
     * Get the activity for this if. BPEL 2.0 draft mandated the inclusion of the condition success activity in a <then>
     * element. In that case this will be null. For BPEL 2.0 final this should return the condition success activity.
     *
     * @return activity enabled when case is satisfied
     */
    public Activity getActivity() {
        return getFirstChild(Activity.class);
    }

    /**
     * Get the cases for this switch.
     *
     * @return the cases
     */
    public List<Case> getCases() {
        return getChildren(Case.class);
    }

    public Map<String, Collection<URI>> getMetamodelReference() {
        Map<String, Collection<URI>> referencesMap = new HashMap<String, Collection<URI>>();
        if(_queries!=null)
        for (EnvOutcomeQuery query : _queries.getEnvQueries())
            referencesMap.put(query.getVariable(), query.getModelReferences());

        return referencesMap;
    }

    public Map<String, URI> getLiftingScheme() {
        Map<String, URI> referencesMap = new HashMap<String, URI>();
        if(_queries!=null)
        for (EnvOutcomeQuery query : _queries.getEnvQueries())
            referencesMap.put(query.getVariable(), query.getLiftingScheme());

        return referencesMap;
    }

    public Map<String, URI> getLoweringScheme() {
        Map<String, URI> referencesMap = new HashMap<String, URI>();
        if(_queries!=null)
        for (EnvOutcomeQuery query : _queries.getEnvQueries())
            referencesMap.put(query.getVariable(), query.getLoweringScheme());

        return referencesMap;
    }

    public Map<String, Collection<EnvMPolygon>> getMultiPolygons() {
        Map<String, Collection<EnvMPolygon>> referencesMap = new HashMap<String, Collection<EnvMPolygon>>();
        if(_queries!=null)
        for (EnvOutcomeQuery query : _queries.getEnvQueries())
            referencesMap.put(query.getVariable(), query.getMPolygons());

        return referencesMap;
    }

    public Map<String, Collection<EnvTimeInterval>> getTimeIntervals() {
        Map<String, Collection<EnvTimeInterval>> referencesMap = new HashMap<String, Collection<EnvTimeInterval>>();
        if(_queries!=null)
        for (EnvOutcomeQuery query : _queries.getEnvQueries())
            referencesMap.put(query.getVariable(), query.getTimeIntervals());

        return referencesMap;
    }

    /**
     * BPEL object model representation of a
     * <code>&lt;case&gt;</code>.
     */
    public static class Case extends BpelObject {

        public Case(Element el) {
            super(el);
        }

        /**
         * Get the activity for this case.
         *
         * @return activity enabled when case is satisfied
         */
        public Activity getActivity() {
            return getFirstChild(Activity.class);
        }

        /**
         * Get the condition associated with this case.
         *
         * @return the condition
         */
        public Expression getCondition() {
            return getFirstChild(Expression.class);
        }
    }
}
