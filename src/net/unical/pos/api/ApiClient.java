/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class ApiClient {
    private static HttpURLConnection uRLConnection;
    
    public static HttpURLConnection getURLConnection(String url){
        try {
            FileInputStream fis = new FileInputStream("config.txt");
            Properties props = new Properties();
            props.load(fis);
            URL connectionUrl = new URL(props.getProperty("BASE_URL")+url);
            uRLConnection = (HttpURLConnection) connectionUrl.openConnection();
            return uRLConnection;
        } catch (IOException ex) {
            Logger.getLogger(ApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
