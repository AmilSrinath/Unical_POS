/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class ApiClient {
    private static String BASE_URL = "http://localhost:4000/api";
    private static HttpURLConnection uRLConnection;
    
    public static HttpURLConnection getURLConnection(String url){
        try {
            URL connectionUrl = new URL(BASE_URL+url);
            uRLConnection = (HttpURLConnection) connectionUrl.openConnection();
            return uRLConnection;
        } catch (IOException ex) {
            Logger.getLogger(ApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
