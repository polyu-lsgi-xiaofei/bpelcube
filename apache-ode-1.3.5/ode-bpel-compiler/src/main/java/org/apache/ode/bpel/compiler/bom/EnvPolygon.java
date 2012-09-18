/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.ode.bpel.compiler.bom;

import java.util.Collection;
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
        return this.getChildren(EnvNode.class);
    }
    
    public String serialize(){
        String poly="";
        for(EnvNode node:getNodes())
            poly+=node.serialize()+EnvNode.NODE_DELIMITER;
        
        return poly="("+poly+")";
    }
}
