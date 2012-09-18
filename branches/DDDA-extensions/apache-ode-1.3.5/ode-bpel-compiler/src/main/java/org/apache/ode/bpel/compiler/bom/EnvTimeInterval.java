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
public class EnvTimeInterval extends BpelObject{

    public EnvTimeInterval(Element el) {
        super(el);
    }
    
    public String serialize(){
        String interval=this.getEndTime()+" "+this.getStartTime();
        return interval;
    }
    
    //////////
    //  Private assistive methods
    
    private String getStartTime(){
        Element extensibilityElement = this.getExtensibilityElement(EnvisionExtensionQNames.ENVISION_STARTTIME_QNAME);
        if(extensibilityElement!=null)
            return extensibilityElement.getNodeValue();
        
        return null;
    }
    
    private String getEndTime(){
        Element extensibilityElement = this.getExtensibilityElement(EnvisionExtensionQNames.ENVISION_ENDTIME_QNAME);
        if(extensibilityElement!=null)
            return extensibilityElement.getNodeValue();
        
        return null;
    }
}
