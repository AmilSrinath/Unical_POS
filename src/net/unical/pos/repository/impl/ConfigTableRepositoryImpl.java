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
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.ConfigTable;
import net.unical.pos.model.ConfigTableDetails;
import net.unical.pos.repository.custom.ConfigTableRepositoryCustom;

/**
 *
 * @author HP
 */
public class ConfigTableRepositoryImpl implements ConfigTableRepositoryCustom {

    @Override
    public Integer save(ConfigTable configTable) {
        try {
            return Statement.executeUpdate("Insert into pos_con_config_table_location_tb values(?,?,?,?,?,?,?,?,?,?,?)",
                    configTable.getConfigTablesId(),
                    configTable.getMainTableLocationId(),
                    configTable.getSubTableLocationId(),
                    configTable.getMainLocationName(),
                    configTable.getSubLocationName(),
                    configTable.getTableCodePrefix(),
                    configTable.getNumOfTables(),
                    configTable.getImagePath(),
                    configTable.getStatus(),
                    configTable.getUserId(),
                    configTable.getVisible());
        } catch (Exception ex) {
            Logger.getLogger(ConfigTableRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public ArrayList<ConfigTable> getAll() {
        try {
            ResultSet rst = Statement.executeQuery("Select * from pos_con_config_table_location_tb where status = 1");
            ArrayList<ConfigTable> configTables = new ArrayList<>();
            while (rst.next()) {
                configTables.add(new ConfigTable(
                        rst.getInt("config_table_location_id"),
                        rst.getInt("main_table_location_id"),
                        rst.getInt("sub_table_location_id"),
                        rst.getString("main_location_name"),
                        rst.getString("sub_location_name"),
                        rst.getString("table_prefix"),
                        rst.getInt("no_of_tables"),
                        rst.getString("image_path"),
                        rst.getInt("status"),
                        rst.getInt("user_id"),
                        rst.getInt("visible")));

            }
            return configTables;
        } catch (Exception ex) {
            Logger.getLogger(ConfigTableRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ArrayList<ConfigTableDetails> getAllDetails() {
        try {
            ResultSet rst = Statement.executeQuery("Select * from pos_con_config_table_details_tb where status = 1");
            ArrayList<ConfigTableDetails> configTables = new ArrayList<>();
            while (rst.next()) {
                configTables.add(new ConfigTableDetails(
                        rst.getInt("table_id"),
                        rst.getInt("config_table_id"),
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
            return configTables;
        } catch (Exception ex) {
            Logger.getLogger(ConfigTableRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
