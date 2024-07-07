/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.unical.pos.configurations.ConfigPropertyReader;

/**
 *
 * @author Sanjuka
 */
public class DBConnection {
    
    private Connection connection;
    private static DBConnection dbConnection;
    
    public DBConnection()throws ClassNotFoundException, SQLException{
        
        ConfigPropertyReader configPropertyReader=new ConfigPropertyReader();
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection("jdbc:mysql://"
                + configPropertyReader.getPropertyConfigurations().getServerHost()+":"
                + configPropertyReader.getPropertyConfigurations().getServerPort()+"/"
                + configPropertyReader.getPropertyConfigurations().getServerDb(),
                configPropertyReader.getPropertyConfigurations().getServerUser(),
                configPropertyReader.getPropertyConfigurations().getServerPw());
    }
    
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException{
        if(dbConnection==null){
            dbConnection=new DBConnection();
        }
        return dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }
    

}
