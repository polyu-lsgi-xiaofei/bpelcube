/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.ode.bpel.compiler.bom;

import java.util.List;
import org.w3c.dom.Element;

/**
 *
 * @author gathanas
 */
public class EnvNode extends BpelObject {

    public EnvNode(Element el) {
        super(el);
    }
    
    public float[] getPointsArray(){
        List<EnvPoint> children = this.getChildren(EnvPoint.class);
        float[] nodeArray = new float[children.size()];
        int i=0;
        for(EnvPoint point:children)
            nodeArray[i++]=point.getValue();
        
        return nodeArray;
    }
    public String getPointsConcated(){
        
        List<EnvPoint> children = this.getChildren(EnvPoint.class);
        String nodeArray = "";
        for(EnvPoint point:children)
            nodeArray+=point.serialize()+" ";
        
        return nodeArray.trim();
    }
}
