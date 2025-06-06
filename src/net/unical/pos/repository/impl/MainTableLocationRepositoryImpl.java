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
import net.unical.pos.model.MainTableLocation;
import net.unical.pos.repository.custom.MainTableLocationRepositoryCustom;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author HP
 */
public class MainTableLocationRepositoryImpl implements MainTableLocationRepositoryCustom {

    @Override
    public boolean saveMainTableLocation(MainTableLocation mainTableLocation) {
        try {
            return Statement.executeUpdate("Insert into pos_con_main_table_location_tb values(?,?,?,?,?,?)", mainTableLocation.getMainTableLocationId(),
                    mainTableLocation.getLocationName(),
                    mainTableLocation.getImagePath(),
                    mainTableLocation.getStatus(),
                    LogInForm.userID,
                    mainTableLocation.getVisible()) > 0;
        } catch (Exception ex) {
            Logger.getLogger(MainTableLocationRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<MainTableLocation> getAllMainTableLocations() {
        try {
            ResultSet rst = Statement.executeQuery("Select * from pos_con_main_table_location_tb where visible = 1");
            ArrayList<MainTableLocation> mainTableLocations = new ArrayList<>();
            while (rst.next()) {
                mainTableLocations.add(new MainTableLocation(rst.getInt("main_table_location_id"),
                        rst.getString("main_location_name"), rst.getString("image_path"), rst.getInt("status"),
                        rst.getInt("user_id"), rst.getInt("visible")));
            }
            return mainTableLocations;
        } catch (Exception ex) {
            Logger.getLogger(MainTableLocationRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
