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



package org.apache.ode.bpel.compiler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.ode.bpel.compiler.api.CompilationException;
import org.apache.ode.bpel.compiler.bom.Activity;
import org.apache.ode.bpel.compiler.bom.EnvMPolygon;
import org.apache.ode.bpel.compiler.bom.EnvTimeInterval;
import org.apache.ode.bpel.compiler.bom.IfActivity;
import org.apache.ode.bpel.o.OActivity;
import org.apache.ode.bpel.o.OSwitch;
import org.apache.ode.utils.msg.MessageBundle;


/**
 * Generates code for the <code>&lt;switch&gt;</code> activities.
 */
class IfGenerator extends DefaultActivityGenerator {
    private static final IfGeneratorMessages __cmsgs = MessageBundle.getMessages(IfGeneratorMessages.class);

    public OActivity newInstance(Activity src) {
        return new OSwitch(_context.getOProcess(), _context.getCurrent());
    }

    public void compile(OActivity output, Activity src) {
        OSwitch oswitch = (OSwitch) output;
        IfActivity switchDef = (IfActivity)src;
               
        if (switchDef.getCondition() == null)
            throw new CompilationException(__cmsgs.errIfWithNoCondition());
             
        boolean first = true;
        if (switchDef.getActivity() != null) {
            OSwitch.OCase ocase = new OSwitch.OCase(_context.getOProcess());
            ocase.activity = _context.compile(switchDef.getActivity());
            ocase.expression = _context.compileExpr(switchDef.getCondition());
            oswitch.addCase(ocase);
            first = false;
        }

        for (IfActivity.Case ccase : switchDef.getCases()) {
            OSwitch.OCase ocase = new OSwitch.OCase(_context.getOProcess());
            ocase.activity = _context.compile(ccase.getActivity());
            ocase.expression = first ? _context.compileExpr(switchDef.getCondition())
                    : (ccase.getCondition() == null ? _context.constantExpr(true) : _context.compileExpr(ccase.getCondition()));
            oswitch.addCase(ocase);
            first = false;
        }
        
        
        /**
         * @since 18-09-2012
         * @author George Athanasopoulos transferring envision outcome related information to OInvoke activity
         */
        {
            oswitch._varMetamodelReferences = switchDef.getMetamodelReference();
            oswitch._varLiftingScheme = switchDef.getLiftingScheme();
            oswitch._varLoweringScheme = switchDef.getLoweringScheme();
//         setting mutli-polygons
            if (oswitch._varMPolygons == null && switchDef.getMultiPolygons()!=null)
                oswitch._varMPolygons = new HashMap();
            for (String varKey : switchDef.getMultiPolygons().keySet()) {
                Collection<String> mPolygons = new LinkedList();
                for(EnvMPolygon mPoly:switchDef.getMultiPolygons().get(varKey))
                    mPolygons.add(mPoly.serialize());
                oswitch._varMPolygons.put(varKey,mPolygons);
            }
//       setting time intervals
            if (oswitch._varTimeIntervals == null && switchDef.getTimeIntervals()!=null)
                oswitch._varTimeIntervals = new HashMap();
            for (String varKey : switchDef.getTimeIntervals().keySet()) {
                Collection<String> mIntervals = new LinkedList();
                for(EnvTimeInterval mPoly:switchDef.getTimeIntervals().get(varKey))
                    mIntervals.add(mPoly.serialize());
                oswitch._varTimeIntervals.put(varKey,mIntervals);
            }
        }// end of Envision extensions


    }
}
