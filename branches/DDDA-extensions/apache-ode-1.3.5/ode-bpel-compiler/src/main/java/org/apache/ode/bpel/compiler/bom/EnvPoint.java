/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.ode.bpel.compiler.bom;

import org.w3c.dom.Element;

/**
 *
 * @author gathanas
 */
public class EnvPoint extends BpelObject {
    public static final String POINT_DELIMITER=" ";
    
    public EnvPoint(Element el) {
        super(el);
    }

    public float getValue(){
        return Float.parseFloat(this.getElement().getNodeValue());
    }
    
    String serialize() {
        return this.getElement().getNodeValue();
    }
    
    
}
