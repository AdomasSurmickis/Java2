/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.pamokas0715;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Dedelis
 */
public class Run {
    private static final Log log = LogFactory.getLog(Run.class);
    public static void main(String[] args) {
//        try {
//            new FileInputStream("");
//        } catch (FileNotFoundException ex) {
//            log.error("nepavyko atidaryti failo", ex);
//        }
        log.fatal("fatalTest");
        log.error("errorTest");
        log.warn("warnTest");
        log.info("infoTest");
        log.debug("debugTest");
        log.trace("traceTest");
    }
    
}
