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
public class EnvPolygon extends BpelObject {
    public static final String POLYGON_DELIMITER=",";
    
    public EnvPolygon(Element el) {
        super(el);
    }
    
    public Collection<EnvNode> getNodes(){
        Collection<EnvNode> results= new LinkedList(); 
        for(BpelObject obj: this.getChildren(EnvisionExtensionQNames.ENVISION_NODE_QNAME))
            results.add(new EnvNode(obj.getElement()));
        return results;
    }
    
    public String serialize(){
        String poly="";
        for(EnvNode node:getNodes())
            poly+=node.serialize()+EnvNode.NODE_DELIMITER;
        
        return poly="("+poly+")";
    }
}
