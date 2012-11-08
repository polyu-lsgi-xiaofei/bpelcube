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
public class EnvMPolygon extends BpelObject{

    public EnvMPolygon(Element el) {
        super(el);
    }
    
    public Collection<EnvPolygon> getPolygons(){
        Collection<EnvPolygon> results= new LinkedList(); 
        for(BpelObject obj: this.getChildren(EnvisionExtensionQNames.ENVISION_POLY_QNAME))
            results.add(new EnvPolygon(obj.getElement()));
        return results;
    }
    
    public String serialize(){
        String mPolygon="";
        for(EnvPolygon polygon:getPolygons())
            mPolygon+=polygon.serialize()+EnvPolygon.POLYGON_DELIMITER;
//        remove trailing delimiter
        mPolygon= mPolygon.substring(0, mPolygon.lastIndexOf(EnvPolygon.POLYGON_DELIMITER));

        mPolygon="'MULTIPOLYGON"+(mPolygon.isEmpty()?" EMPTY'":"("+mPolygon+")'");
        return mPolygon;
    }
}
