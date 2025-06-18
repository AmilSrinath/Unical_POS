/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.ConfigTableDetails;
import net.unical.pos.repository.custom.ConfigTableDetailsRepositoryCustom;

/**
 *
 * @author HP
 */
public class ConfigTableDetailsRepositoryImpl implements ConfigTableDetailsRepositoryCustom{

    @Override
    public Integer save(ConfigTableDetails configTableDetails) {
        System.out.println("Config Table Id : "+configTableDetails.getConfigTablesId());
        System.out.println("Main Table Id : "+configTableDetails.getMainTableLocationId());
        System.out.println("Sub Table Id : "+configTableDetails.getSubTableLocationId());
        try {
            return Statement.executeUpdate("Insert into pos_con_config_table_details_tb values(?,?,?,?,?,?,?,?,?,?,?)",
                    0,
                    configTableDetails.getConfigTablesId(),
                    configTableDetails.getMainTableLocationId(),
                    configTableDetails.getSubTableLocationId(),
                    configTableDetails.getTableName(),
                    configTableDetails.getWidth(),
                    configTableDetails.getHeight(),
                    configTableDetails.getImagePath(),
                    configTableDetails.getStatus(),
                    configTableDetails.getUserId(),
                    configTableDetails.getVisible());
        } catch (Exception ex) {
            Logger.getLogger(ConfigTableRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex,"Config not save");
            return 0;
        }
    }

    @Override
    public ArrayList<ConfigTableDetails> getAll() {
       try {
            ResultSet rst = Statement.executeQuery("Select * from pos_con_config_table_details_tb where status = 1 and visible=1");
            ArrayList<ConfigTableDetails> configTableDetailses = new ArrayList<>();
            while (rst.next()) {
                configTableDetailses.add(new ConfigTableDetails(
                        rst.getInt("config_detail_tables_id"),
                        rst.getInt("config_table_location_id"),
                        rst.getInt("main_table_location_id"),
                        rst.getInt("sub_table_location_id"),
                        rst.getString("table_name"),
                        rst.getInt("width"),
                        rst.getInt("height"),
                        rst.getString("image_path"),
                        rst.getInt("status"),
                        rst.getInt("user_id"),
                        rst.getInt("visible")));

            }
            return configTableDetailses;
        } catch (Exception ex) {
            Logger.getLogger(ConfigTableRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex,"Config get all error");
            return null;
        }
    }
    
}
