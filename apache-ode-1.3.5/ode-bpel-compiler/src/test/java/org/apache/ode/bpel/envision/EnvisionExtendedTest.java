/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.ode.bpel.envision;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.ode.bpel.compiler.DefaultResourceFinder;
import org.apache.ode.bpel.compiler.ResourceFinder;
import org.apache.ode.bpel.compiler.bom.BpelObjectFactory;
import org.apache.ode.bpel.compiler.bom.Process;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author gathanas
 */

public class EnvisionExtendedTest {
    
    private static final String EXTENDED_RESOURCE_BPEL="som/s3lab/bpel/extended/xxxx.bpel";
    private File _processFile;
    private ResourceFinder _resourceFinder;
    public EnvisionExtendedTest() {
    }
    
    @Before
    public void setup() throws URISyntaxException{
        URL resourceURL = ClassLoader.getSystemResource(EXTENDED_RESOURCE_BPEL);
        _processFile = new File(resourceURL.toURI());
//        setup the default resource finder
        _resourceFinder =  new DefaultResourceFinder(_processFile.getParentFile(), _processFile.getAbsoluteFile());
    }
    
    @Test
    public void compileTest() throws FileNotFoundException, IOException, SAXException{
        InputSource source = new InputSource(new FileInputStream(_processFile));
        Process parse = BpelObjectFactory.getInstance().parse(source, _processFile.toURI());
        
        
    }
}
