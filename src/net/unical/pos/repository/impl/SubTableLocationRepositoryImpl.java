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
import net.unical.pos.model.SubTableLocation;
import net.unical.pos.repository.custom.SubTableLocationRepositoryCustom;

/**
 *
 * @author HP
 */
public class SubTableLocationRepositoryImpl implements SubTableLocationRepositoryCustom {

    @Override
    public boolean save(SubTableLocation subTableLocation) {
        try {
            return Statement.executeUpdate("Insert into pos_con_sub_table_location_tb values(?,?,?,?,?,?,?,?)",
                    subTableLocation.getSubTableLocationId(),
                    subTableLocation.getMainTableLocationId(),
                    subTableLocation.getMainName(),
                    subTableLocation.getSubName(),
                    subTableLocation.getImagePath(),
                    subTableLocation.getStatus(),
                    subTableLocation.getUserId(),
                    subTableLocation.getVisible()) > 0;
        } catch (Exception ex) {
            Logger.getLogger(SubTableLocationRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<SubTableLocation> getAllSubTables() {
        try {
            ResultSet rst = Statement.executeQuery("Select * from pos_con_sub_table_location_tb where status = 1");
            ArrayList<SubTableLocation> subTableLocations = new ArrayList<>();
            while (rst.next()) {
                subTableLocations.add(new SubTableLocation(
                        rst.getInt("sub_table_location_id"),
                        rst.getInt("main_table_location_id"),
                        rst.getString("main_location_name"),
                        rst.getString("sub_location_name"),
                        rst.getString("image_path"),
                        rst.getInt("status"),
                        rst.getInt("user_id"),
                        rst.getInt("visible")));

            }
            return subTableLocations;
        } catch (Exception ex) {
            Logger.getLogger(SubTableLocationRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
