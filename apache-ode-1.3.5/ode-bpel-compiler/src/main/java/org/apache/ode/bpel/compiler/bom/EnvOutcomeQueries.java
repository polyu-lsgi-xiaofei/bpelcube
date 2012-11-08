/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.ode.bpel.compiler.bom;

import java.util.Collection;
import java.util.LinkedList;
import org.w3c.dom.Element;

/**
 *
 * @author gathanas
 */
public class EnvOutcomeQueries extends BpelObject{

    public EnvOutcomeQueries(Element el) {
        super(el);
    }
    
    
    public Collection<EnvOutcomeQuery> getEnvQueries(){
        Collection<EnvOutcomeQuery> results= new LinkedList(); 
        for(BpelObject obj: this.getChildren(EnvisionExtensionQNames.ENVISION_OUTCOME_QUERY_QNAME))
            results.add(new EnvOutcomeQuery(obj.getElement()));
        return results;
    }
}
