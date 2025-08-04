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
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dto.DiscountDto;
import net.unical.pos.model.DiscountModel;
import net.unical.pos.repository.custom.DiscountRepositoryCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class DiscountRepositoryImpl implements DiscountRepositoryCustom {

    private String TABLE = "pos_main_discount_tb";

    @Override
    public ArrayList<DiscountModel> getAllDiscounts() {
        String sql = "SELECT * FROM " + TABLE;
        ArrayList<DiscountModel> discounts = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
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

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DiscountRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "Discounts fetch failde!");
        }
        return discounts;
    }

    @Override
    public boolean addDiscount(DiscountDto discountDto) {
        String sql = "INSERT INTO " + TABLE + " (discount_name, percentage, status, user_id, visible) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, discountDto.getDiscountName());
            ps.setDouble(2, discountDto.getPercentage());
            ps.setInt(3, discountDto.getStatus());
            ps.setInt(4, 1);
            ps.setInt(5, 1);
            return 0 < ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DiscountRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "Failed to add discount to the database");
        }
        return false;
    }

    @Override
    public Integer getDiscountId(double percentage) {
        System.out.println("percentage " + percentage);
        String sql = "SELECT discount_id FROM " + TABLE + " WHERE percentage = ?";
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setDouble(1, percentage);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("come to this");
                return rs.getInt("discount_id");
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(DiscountRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Discount id fetch failed");
        }
        return null;
    }

    @Override
    public boolean updateDiscount(DiscountModel discountModel) {
        String sql = "UPDATE " + TABLE + " SET discount_name = ?, percentage = ?, status =  ? WHERE discount_id = ?";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1, discountModel.getDiscountName());
            pstm.setDouble(2, discountModel.getPercentage());
            pstm.setInt(3, 1);
            pstm.setInt(4, discountModel.getDiscountId());
            return pstm.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DiscountRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "Discount Not Update!");
        }
        return false;
    }
}
