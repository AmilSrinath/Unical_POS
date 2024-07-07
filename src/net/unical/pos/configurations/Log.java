/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.configurations;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Sanjuka
 */
public class Log {
    
    static Logger errorLogger = Logger.getLogger("error");
    static Logger infoLogger = Logger.getLogger("info");

    static {
        init();
    }

    public static void init() {
        DOMConfigurator.configure("logconfig.xml");
    }

    public static void info(java.lang.Object oLogger, java.lang.Object oMsg) {
        infoLogger.info(oLogger + "\t" + oMsg);
    }

    public static void error(java.lang.Object oLogger, java.lang.Object oMsg) {
        errorLogger.error(oLogger + "\t" + oMsg);
    }
    
    public static void error(java.lang.Object oLogger, java.lang.Object oMsg, Throwable ex) {
        errorLogger.error(oLogger + "\t" + oMsg, ex);
    }

    public static void main(String argv[]) {
        //Log.init();
        Log.error(Log.class, "www.UNICAL.net");
        Log.info(Log.class, "www.UNICAL.net");

    }
}
