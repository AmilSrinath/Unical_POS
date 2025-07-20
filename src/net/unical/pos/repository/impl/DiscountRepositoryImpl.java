/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dto.DiscountDto;
import net.unical.pos.model.DiscountModel;
import net.unical.pos.repository.custom.DiscountRepositoryCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class DiscountRepositoryImpl implements DiscountRepositoryCustom{
    private String TABLE = "pos_main_discount_tb";

    @Override
    public ArrayList<DiscountModel> getAllDiscounts() {
        String sql = "SELECT * FROM " + TABLE;
        ArrayList<DiscountModel> discounts = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
            if(rs.next()){
                while (rs.next()) {
                    discounts.add(
                        new DiscountModel(
                            rs.getInt("discount_id"),
                            rs.getString("discount_name"),
                            rs.getDouble("percentage"),
                            rs.getDouble("amount"),
                            rs.getInt("status")
                        )
                     );
                }
            } else {
               throw new RuntimeException("SQL ERROR");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DiscountRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DiscountRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return discounts;
    }

    @Override
    public boolean addDiscount(DiscountDto discountDto) {
        String sql = "INSERT INTO " + TABLE + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, discountDto.getDiscountId());
            ps.setString(2, discountDto.getDiscountName());
            ps.setDouble(3, discountDto.getPercentage());
            ps.setDouble(4, discountDto.getAmount());
            ps.setInt(4, discountDto.getStatus());
            ps.setInt(6, 1);
            ps.setInt(7, 1);
            return 0 < ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DiscountRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DiscountRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
