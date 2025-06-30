/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.CustomerModel;
import net.unical.pos.model.OrderDetails;
import net.unical.pos.model.PosMainOrderDetails;
import net.unical.pos.repository.custom.MainOrderDetailRepositoryCustom;

/**
 *
 * @author HP
 */
public class MainOrderDetailRepositoryImpl implements MainOrderDetailRepositoryCustom {

    @Override
    public ArrayList<PosMainOrderDetails> getOrderDetails(Integer orderId) {
        try {
            ResultSet resultSet = Statement.executeQuery("SELECT * FROM pos_main_order_details_tb WHERE status = " + 1);
            ArrayList<PosMainOrderDetails> mainOrderDetailses = new ArrayList<>();

            while (resultSet.next()) {
                PosMainOrderDetails mainOrderDetails = new PosMainOrderDetails(
                        resultSet.getInt("order_detail_id"),
                        resultSet.getInt("order_id"),
                        resultSet.getInt("item_id"),
                        resultSet.getInt("item_bar_code"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("per_item_price"),
                        resultSet.getDouble("per_item_discount_price"),
                        resultSet.getDouble("total_discount_price"),
                        resultSet.getDouble("total_price"),
                        resultSet.getInt("status"));
                mainOrderDetailses.add(mainOrderDetails);
            }
            return mainOrderDetailses;
        } catch (Exception ex) {
            Logger.getLogger(MainOrderDetailRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex,"get Order Details error");
            return null;
        }
    }

    @Override
    public ArrayList<PosMainOrderDetails> getOrderDetailsByOrderId(Integer orderId) {
        ArrayList<PosMainOrderDetails> mainOrderDetailses = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rst = null;
        Connection con = null;
        
        

        try {
            con = DBCon.getDatabaseConnection();
            String query = "SELECT * FROM pos_main_order_details_tb WHERE order_id = ? AND status = 1";
            ps = con.prepareStatement(query);
            ps.setInt(1, orderId);
            rst = ps.executeQuery();

            while (rst.next()) {
                PosMainOrderDetails mainOrderDetails = new PosMainOrderDetails(
                    rst.getInt("order_detail_id"),
                    rst.getInt("order_id"),
                    rst.getInt("item_id"),
                    rst.getInt("item_bar_code"),
                    rst.getInt("quantity"),
                    rst.getDouble("per_item_price"),
                    rst.getDouble("per_item_price"),
                    rst.getDouble("total_discount_price"),
                    rst.getDouble("total_item_price"),
                    rst.getInt("status")
                );
                mainOrderDetailses.add(mainOrderDetails);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainOrderDetailRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex,"get Order Details By Order Id error");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(MainOrderDetailRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e,"get Order Details By Order Id error");
            }
        }

        return mainOrderDetailses;
    }

    @Override
    public ArrayList<OrderDetails[]> getOrderDetailsByCustomerId(Integer customerId) {
        ArrayList<OrderDetails[]> orderDetailsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBCon.getDatabaseConnection();
            String query = "SELECT o.order_id, o.bill_no, i.item_name, d.quantity, d.per_item_price, d.total_item_price, " +
               "o.total_order_price, o.delivery_fee, o.created_Date, dly.status_id " +
               "FROM pos_main_order_tb o " +
               "JOIN pos_main_order_details_tb d ON o.order_id = d.order_id " +
               "JOIN pos_main_item_tb i ON d.item_id = i.item_id " +
               "LEFT JOIN pos_main_delivery_order_tb dly ON o.order_id = dly.delivery_id " +
               "WHERE o.customer_id = ? AND d.status = 1";

            ps = conn.prepareStatement(query);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();

            Map<Integer, List<OrderDetails>> orderDetailsMap = new HashMap<>();

            while (rs.next()) {
                OrderDetails rowData = new OrderDetails(
                    rs.getInt("order_id"),
                    rs.getString("bill_no"),
                    rs.getString("item_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("per_item_price"),
                    rs.getDouble("total_item_price"),
                    rs.getDouble("delivery_fee"),
                    rs.getDouble("total_order_price"),
                    rs.getString("created_Date"),
                        rs.getInt("status_id")
                );

                int orderId = rs.getInt("order_id");
                orderDetailsMap.computeIfAbsent(orderId, k -> new ArrayList<>()).add(rowData);
            }

            for (List<OrderDetails> details : orderDetailsMap.values()) {
                orderDetailsList.add(details.toArray(new OrderDetails[0]));
            }

        } catch (SQLException e) {
            Logger.getLogger(MainOrderDetailRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e,"get Order Details By Customer Id error");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainOrderDetailRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex,"get Order Details By Customer Id error");
        } finally {
            // Close the resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(MainOrderDetailRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e,"get Order Details By Customer Id error");
                e.printStackTrace();
            }
        }

        return orderDetailsList;
    }

}
