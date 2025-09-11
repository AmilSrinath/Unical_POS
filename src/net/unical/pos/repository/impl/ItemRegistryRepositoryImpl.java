/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.log.Log;
import net.unical.pos.model.ItemRegistry;
import net.unical.pos.model.PosMainItem;
import net.unical.pos.repository.custom.ItemRegistryRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class ItemRegistryRepositoryImpl implements ItemRegistryRepositoryCustom{

    @Override
    public boolean saveRegistry(PosMainItem item, Connection connection) {
        try {
            String sql = "INSERT INTO pos_main_item_registry_tb (main_category_id, sub_category_id, status, created_date, edited_date, visibility, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, item.getMainItemCategoryId());
            pstm.setInt(2, item.getSubItemCategoryId());
            pstm.setInt(3, 1);
            pstm.setString(4, String.valueOf(LocalDateTime.now()));
            pstm.setString(5, String.valueOf(LocalDateTime.now()));
            pstm.setInt(6, 1);
            pstm.setInt(7, LogInForm.userID);
            
            return pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ItemRegistryRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "Item Registry Save Failed");
        }
        return false;
    }     

    @Override
    public ItemRegistry getLatestRegistry() {
        String sql = "SELECT * FROM pos_main_item_registry_tb ORDER BY registry_id DESC LIMIT 1";
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
            if (rst.next()) {
                return new ItemRegistry(
                        rst.getInt("registry_id"),
                        rst.getInt("main_category_id"),
                        rst.getInt("sub_category_id"),
                        rst.getInt("status"),
                        rst.getDate("created_date"),
                        rst.getDate("edited_date"),
                        rst.getInt("visibility"),
                        rst.getInt("user_id")
                );
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemRegistryRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ItemRegistryRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "Latest Registry Fetched Error");
        }
        return null;
    }
}
