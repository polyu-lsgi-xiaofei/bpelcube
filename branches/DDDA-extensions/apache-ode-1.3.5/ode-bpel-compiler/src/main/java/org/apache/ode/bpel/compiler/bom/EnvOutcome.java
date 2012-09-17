/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.ode.bpel.compiler.bom;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Element;

/**
 *
 * @author gathanas
 */
public class EnvOutcome extends BpelObject{

    public EnvOutcome(Element el) {
        super(el);
    }

    public Collection<URI> getModelReference(){
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
    
    public Collection<EnvTimeInterval> getTimeIntervals(){
        return this.getChildren(EnvTimeInterval.class);
    }
    
    public Collection<EnvMPolygon> getMultiPolygons(){
        return this.getChildren(EnvMPolygon.class);
    }
}
