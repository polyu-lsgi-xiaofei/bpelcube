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

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.ode.bpel.compiler.bom.Activity;
import org.apache.ode.bpel.compiler.bom.EnvMPolygon;
import org.apache.ode.bpel.compiler.bom.EnvTimeInterval;
import org.apache.ode.bpel.compiler.bom.SwitchActivity;
import org.apache.ode.bpel.o.OActivity;
import org.apache.ode.bpel.o.OSwitch;

/**
 * Generator for legacy BPEL 1.1 <code>&lt;switch&gt;</code> actiivty.
 * @author Maciej Szefler - m s z e f l e r @ g m a i l . c o m
 *
 */
public class SwitchGenerator extends DefaultActivityGenerator {
  public OActivity newInstance(Activity src) {
        return new OSwitch(_context.getOProcess(), _context.getCurrent());
  }

  public void compile(OActivity output, Activity src) {
    OSwitch oswitch = (OSwitch) output;
    SwitchActivity switchDef = (SwitchActivity)src;

    for (SwitchActivity.Case ccase : switchDef.getCases()) {
      OSwitch.OCase ocase = new OSwitch.OCase(_context.getOProcess());
      ocase.activity = _context.compile(ccase.getActivity());
      ocase.expression = (ccase.getCondition() == null ? _context.constantExpr(true) : _context.compileExpr(ccase.getCondition()));
      oswitch.addCase(ocase);
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