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
import org.LogConfigurator;
import org.apache.ode.bpel.compiler.BpelCompiler;
import org.apache.ode.bpel.compiler.BpelCompiler11;
import org.apache.ode.bpel.compiler.BpelCompiler20;
import org.apache.ode.bpel.compiler.BpelCompiler20Draft;
import org.apache.ode.bpel.compiler.DefaultResourceFinder;
import org.apache.ode.bpel.compiler.ResourceFinder;
import org.apache.ode.bpel.compiler.bom.BpelObjectFactory;
import org.apache.ode.bpel.compiler.bom.Process;
import org.apache.ode.bpel.o.OProcess;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author gathanas
 */

public class EnvisionExtendedTest {
    
    private static final String EXTENDED_RESOURCE_BPEL="F:/My_Projects/Envision/GoogleCode/apache-ode-1.3.5/ode-bpel-compiler/target/test-classes/com/s3lab/bpel/extended/Landslide_Ex/LandSlideProbCalc_Ex.bpel";
    private File _processFile;
    private ResourceFinder _resourceFinder;
    public EnvisionExtendedTest() {
        new LogConfigurator(); 
    }
    
    @Before
    public void setup() throws URISyntaxException{
        URL resourceURL = this.getClass().getResource("/extended/Landslide_Ex/");
        _processFile = new File(EXTENDED_RESOURCE_BPEL);
//        setup the default resource finder
        _resourceFinder =  new DefaultResourceFinder(_processFile.getParentFile(), _processFile.getAbsoluteFile());
    }
    
    @Test
    public void compileTest() throws FileNotFoundException, IOException, SAXException, Exception{
        InputSource source = new InputSource(new FileInputStream(_processFile));
        Process processDetails = BpelObjectFactory.getInstance().parse(source, _processFile.toURI());
        BpelCompiler bpelCompiler = null;
        int version = 0;
        switch (processDetails.getBpelVersion()) {
            case BPEL20:
                bpelCompiler = new BpelCompiler20();
                bpelCompiler.setResourceFinder(_resourceFinder);
                version = 20;
                break;
            case BPEL20_DRAFT:
                bpelCompiler = new BpelCompiler20Draft();
                bpelCompiler.setResourceFinder(_resourceFinder);
                version = 21;
                break;
            case BPEL11:
                bpelCompiler = new BpelCompiler11();
                bpelCompiler.setResourceFinder(_resourceFinder);
                version = 11;
                break;
        }
        OProcess proc = bpelCompiler.compile(processDetails, _resourceFinder, version);
    }
}
