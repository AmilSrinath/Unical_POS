/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.repository.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dto.OrderTypeDto;
import net.unical.pos.model.OrderTypeModel;
import net.unical.pos.repository.custom.OrderTypeRepositoryCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class OrderTypeRepositoryImpl implements OrderTypeRepositoryCustom{
    private String TABLE = "pos_main_config_order_type_tb"; 
    @Override
    public boolean saveOrderType(OrderTypeModel orderTypeModel) {
        String sql = "INSERT INTO " + TABLE + " (type, status, created_at, edited_date) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1, orderTypeModel.getType());
            pstm.setInt(2, orderTypeModel.getStatus());
            pstm.setDate(3, orderTypeModel.getCreatedDate());
            pstm.setDate(4, orderTypeModel.getEditedDate());
            return pstm.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<OrderTypeModel> getAllOrdrTypes() {
        String sql = "SELECT * FROM " + TABLE;
        ArrayList<OrderTypeModel> orderTypeModels = new ArrayList<>();
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
            while (rst.next()) {
                orderTypeModels.add(new OrderTypeModel(
                        rst.getInt("id"),
                        rst.getString("type"),
                        rst.getInt("status"),
                        rst.getDate("created_at"),
                        rst.getDate("edited_date"))
                );
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderTypeModels;
    }

    @Override
    public boolean updateOrderType(OrderTypeDto orderTypeDto) {
        String sql = "UPDATE " + TABLE + " SET type = ?, status = ?, edited_date = ? WHERE id = ?";
        try {
            PreparedStatement pstm =  DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1, orderTypeDto.getType());
            pstm.setInt(2,orderTypeDto.getStatus());
            pstm.setDate(3, Date.valueOf(LocalDate.now()));
            pstm.setInt(4, orderTypeDto.getId());
            return 0 < pstm.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderTypeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
