/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.ode.bpel.compiler.bom;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Element;

/**
 *
 * @author gathanas
 */
public class EnvNode extends BpelObject {
    public static final String NODE_DELIMITER=",";
    
    public EnvNode(Element el) {
        super(el);
    }
    
    public float[] getPointsArray(){
        List<EnvPoint> children= new LinkedList(); 
        for(BpelObject obj: this.getChildren(EnvisionExtensionQNames.ENVISION_POINT_QNAME))
            children.add(new EnvPoint(obj.getElement()));

        float[] nodeArray = new float[children.size()];
        int i=0;
        for(EnvPoint point:children)
            nodeArray[i++]=point.getValue();
        
        return nodeArray;
    }
    public String serialize(){
        
        List<EnvPoint> children = this.getChildren(EnvPoint.class);
        String nodeArray = "";
        for(EnvPoint point:children)
            nodeArray+=point.serialize()+EnvPoint.POINT_DELIMITER;
        
        return nodeArray.trim();
    }
}
