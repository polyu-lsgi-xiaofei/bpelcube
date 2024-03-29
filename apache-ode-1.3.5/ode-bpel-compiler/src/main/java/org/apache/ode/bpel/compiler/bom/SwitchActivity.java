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

import java.util.List;

import org.w3c.dom.Element;

/**
 * Legacy (BPEL 1.1) representation of a <code>&lt;switch&gt;</code> activity.
 * @author Maciej Szefler - m s z e f l e r @ g m a i l . c o m
 *
 */
public class SwitchActivity extends Activity {

    public SwitchActivity(Element el) {
        super(el);
    }

    /**
     * Get the cases for this switch.
     * 
     * @return the cases
     */
    public List<Case> getCases() {
        return getChildren(Case.class);
    }

    /**
     * BPEL object model representation of <code>&lt;case&gt;</code> and 
     * <code>&lt;otherwise&gt;</code> elements. Note that the 
     * <code>&lt;otherwise&gt;</code> elements simply return null for
     * the {@link #getCondition()}.
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
            return isAttributeSet("condition") 
                ? new Expression11(getElement(),getElement().getAttributeNode("condition")) 
                    : null;
        }

    }
    
}
