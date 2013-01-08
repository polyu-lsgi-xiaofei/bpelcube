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
package org.apache.ode.bpel.o;

import java.net.URI;
import java.util.*;
import org.apache.ode.bpel.o.OScope.Variable;


/**
 * Compiled representation of a BPEL <code>&lt;switch&gt;</code>. The
 * BPEL compiler generates instances of this class.
 */
public class OSwitch extends OActivity {
    static final long serialVersionUID = -1L  ;

    /**@since 18-09-2012
     * @author George Athanasopoulos
     * Extensions catering for DDA-SoP information
     */
    public boolean adaptationSwitch = false;
    /**Each element should be associated to a single variable. if/switch statement may use several variables thus multiple queries to 
     * the SCS Engine may be required to support the retrieval of information and writing to process instance variables.
     */
    public Map<String, Collection<URI>> _varMetamodelReferences;
    public Map<String, URI> _varLiftingScheme;
    public Map<String,URI> _varLoweringScheme;
    public Map<String, Collection<String>> _varTimeIntervals;
    public Map<String, Collection<String>> _varMPolygons;

    public Map<String, Variable> getVarMaps(){
        if(_varMetamodelReferences.isEmpty())
            return Collections.emptyMap();

        Map<String, Variable> _result = new HashMap<String, Variable>();
        OScope _scope = getParentScope(this.getParent());
      
        for(String _varName:_varMetamodelReferences.keySet()){
        	Variable _var = _scope.getVisibleVariable(_varName);
           _result.put(_varName, _var);
           }
                
        return _result;
    }

    private OScope getParentScope(OActivity _parent){
        if (_parent == null)
            return this.getOwner().procesScope;
        
        if(_parent instanceof OScope)
            return (OScope)_parent;
        else 
            if(_parent.getParent()==null)
                return _parent.getOwner().procesScope;
            else
                return getParentScope(_parent.getParent());
    }
    
    public Variable getColateralVar(String name){
        return getParentScope(this).getVisibleVariable(name+"Obs_bl");
    }
    
    /**
     * The cases declared within the <code>&lt;switch&gt;</code> actvity.
     */
    private final List<OCase> _cases = new ArrayList<OCase> ();

    
    public OSwitch(OProcess owner, OActivity parent) {
        super(owner, parent);
    }

    public void addCase(OCase acase) {
        _cases.add(acase);
    }

    public List<OCase> getCases() {
        return Collections.unmodifiableList(_cases);
    }

    public static class OCase extends OBase {
        private static final long serialVersionUID = 1L;
        public OExpression expression;
        public OActivity activity;

        public OCase(OProcess owner) {
            super(owner);
        }
    }
}
