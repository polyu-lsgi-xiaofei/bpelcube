/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.ode.bpel.compiler.bom;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import org.w3c.dom.Element;

/**
 *
 * @author gathanas
 */
public class EnvOutcomeQuery extends BpelObject{

    public EnvOutcomeQuery(Element el) {
        super(el);
    }

    public Collection<EnvMPolygon> getMPolygons(){
        Collection<EnvMPolygon> results= new LinkedList(); 
        for(BpelObject obj: this.getChildren(EnvisionExtensionQNames.ENVISION_MULTIPOLY_QNAME))
            results.add(new EnvMPolygon(obj.getElement()));
        return results;
    }
    
    public Collection<EnvTimeInterval> getTimeIntervals() {
        Collection<EnvTimeInterval> results= new LinkedList(); 
        for(BpelObject obj: this.getChildren(EnvisionExtensionQNames.ENVISION_TIMEINTERVAL_QNAME))
            results.add(new EnvTimeInterval(obj.getElement()));
        return results;
    }

    public Collection<URI> getModelReferences(){
        Collection<URI> conceptsList = new LinkedList();
        String modelReferences = this.getAttribute(EnvisionExtensionQNames.SAWSDL_MODELREFERENCE_QNAME, "");
        String[] referenceArray = modelReferences.split(" ");
        for(String conceptRef:referenceArray)
            conceptsList.add(URI.create(conceptRef));
        
        return conceptsList;
    }

    public URI getLiftingScheme(){
        String liftingReferences = this.getAttribute(EnvisionExtensionQNames.SAWSDL_LIFTING_QNAME, "");
        return URI.create(liftingReferences);
    }
    

    public URI getLoweringScheme(){
        String liftingReferences = this.getAttribute(EnvisionExtensionQNames.SAWSDL_LOWERING_QNAME, "");
        return URI.create(liftingReferences);
    }
    
    public String getVariable(){
        return this.getAttribute(EnvisionExtensionQNames.ENVISION_OUTCOME_QUERY_VARIABLE_QNAME, null);
        
    }
}
