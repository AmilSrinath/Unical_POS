/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Sanjuka
 */
public class Statement {
    private static PreparedStatement getStatement(String sql,Object...list) throws Exception{
        
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement prts=connection.prepareStatement(sql);
        
        for(int i=0;i<list.length;i++){
            prts.setObject(i+1,list[i]);
        }
        return prts;
    }
    public static int executeUpdate(String sql,Object...list) throws Exception{
        return getStatement(sql, list).executeUpdate();
    }
    public static ResultSet executeQuery(String sql,Object...list) throws Exception{
        return getStatement(sql, list).executeQuery();
    }
}
