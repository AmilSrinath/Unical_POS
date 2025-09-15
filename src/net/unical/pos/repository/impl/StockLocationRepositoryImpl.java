/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.model.StockLocation;
import net.unical.pos.repository.custom.StockLocationRepositoryCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class StockLocationRepositoryImpl implements StockLocationRepositoryCustom {

    @Override
    public ArrayList<StockLocation> getStockLocations() {
        ArrayList<StockLocation> stockLocations = new ArrayList<>();
    
        try {
            
            String sql = "SELECT * FROM pos_inv_stock_location_tb";

            ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
            while (rst.next()) {
                StockLocation stockLocation = new StockLocation(
                        rst.getString("stock_code"),
                        rst.getString("code_prefix"),
                        rst.getString("stock_name"),
                        rst.getInt("contact"),
                        rst.getString("address"),
                        rst.getInt("status"),
                        rst.getInt("user_id"),
                        rst.getTimestamp("created_at")
                );

                stockLocations.add(stockLocation);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockLocationRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StockLocationRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stockLocations;

    }
}
