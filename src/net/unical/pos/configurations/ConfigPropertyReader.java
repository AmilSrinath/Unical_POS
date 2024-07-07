/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Sanjuka
 */
public class ConfigPropertyReader{
    
    public static ConfigPropertyObject configPropertyObject;
    public static Map<String, String> missingParamsMap;
    
    public ConfigPropertyObject getPropertyConfigurations() {
        configPropertyObject = new ConfigPropertyObject();
        
        missingParamsMap = new HashMap<>();
        FileInputStream fis = null;
        
        try {
            
            fis = new FileInputStream("config.txt");
            Properties props = new Properties();
            props.load(fis);
            
            if (props.getProperty("server_ip") != null) {
                configPropertyObject.setServerHost(props.getProperty("server_ip"));
            } else {
                missingParamsMap.put("ServerHost", "server_ip");
            }
            
            if (props.getProperty("server_port") != null) {
                configPropertyObject.setServerPort(props.getProperty("server_port"));
            } else {
                missingParamsMap.put("ServerPort", "server_port");
            }
            
            if (props.getProperty("server_db") != null) {
                configPropertyObject.setServerDb(props.getProperty("server_db"));
            } else {
                missingParamsMap.put("ServerDb", "server_db");
            }
            
            if (props.getProperty("server_user") != null) {
                configPropertyObject.setServerUser(props.getProperty("server_user"));
            } else {
                missingParamsMap.put("ServerUser", "server_user");
            }
            
            if (props.getProperty("server_pw") != null) {
                configPropertyObject.setServerPw(props.getProperty("server_pw"));
            } else {
                missingParamsMap.put("ServerPw", "server_pw");
            }
            
            if (props.getProperty("server_pwd") != null) {
                configPropertyObject.setServerPwd(props.getProperty("server_pwd"));
            } else {
                missingParamsMap.put("ServerPwd", "server_pwd");
            }
            
            if (props.getProperty("SPLASH_SCREEN") != null) {
                configPropertyObject.setSplash_screen(props.getProperty("SPLASH_SCREEN"));
            } else {
                missingParamsMap.put("Splash_screen", "SPLASH_SCREEN");
            }
            
        } catch (Exception ex) {
//            Logger.getLogger(ConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
            }
        }
        
        return configPropertyObject;
    }
}
