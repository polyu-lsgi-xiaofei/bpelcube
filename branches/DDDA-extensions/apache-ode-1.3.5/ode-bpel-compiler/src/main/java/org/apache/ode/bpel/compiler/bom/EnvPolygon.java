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

    public EnvPolygon(Element el) {
        super(el);
    }
    
    public Collection<EnvNode> getNodes(){
        return this.getChildren(EnvNode.class);
    }
}
