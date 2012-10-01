/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author gathanas
 */
public class LogConfigurator {
    static {
        System.out.println("Loading Log Configuration");
        LogManager defaultManger = LogManager.getLogManager();
        String propertiesFileName = System.getProperty("java.util.logging.config.file");
        try {
            if(propertiesFileName!=null)
                defaultManger.readConfiguration(new FileInputStream(propertiesFileName));
            else
                defaultManger.readConfiguration(ClassLoader.getSystemClassLoader().getResourceAsStream("log4j.properties"));
        } catch (Exception ex) {
            System.out.println("Configuration loading failed!!");
            }
        }
   
}
