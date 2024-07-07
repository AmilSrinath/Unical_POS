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
public class DBCon {
    
    
    public static Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
        ConfigPropertyReader configPropertyReader=new ConfigPropertyReader();
        Connection con = null;
        Class.forName("com.mysql.jdbc.Driver");
        String jdbc="jdbc:mysql://"
                + configPropertyReader.getPropertyConfigurations().getServerHost() + ":"
                + configPropertyReader.getPropertyConfigurations().getServerPort() + "/"
                + configPropertyReader.getPropertyConfigurations().getServerDb()
                + "?user="
                + configPropertyReader.getPropertyConfigurations().getServerUser()
                + "&password="
                + CommonFunctions.decodePW(configPropertyReader.getPropertyConfigurations().getServerPwd())
                //+ ConfigPropertyReader_.configPropertyObject.getServerPw()
                + "&zeroDateTimeBehavior=convertToNull";
        String jdbcutf8 = "&useUnicode=true&characterEncoding=UTF-8";
        con = DriverManager.getConnection(jdbc + jdbcutf8);
//        System.err.println(con);
        return con;
    }
    
    public Connection getLocalConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection c = DriverManager.getConnection("jdbc:sqlite:pos_local_cache.db");
        return c;
    }
}
