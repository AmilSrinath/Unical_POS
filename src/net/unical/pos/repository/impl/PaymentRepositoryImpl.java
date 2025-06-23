/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.model.DeliveryOrder;
import net.unical.pos.repository.custom.PaymentRepository;

/**
 *
 * @author apple
 */
public class PaymentRepositoryImpl implements PaymentRepository{

    @Override
    public ArrayList<DeliveryOrder> getAllPaymentDuration(String fromDate, String toDate, Integer paymentType, int paidStatus) {
        ArrayList<DeliveryOrder> deliveryOrders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBCon.getDatabaseConnection();
            StringBuilder sql = new StringBuilder(
                "SELECT p.payment_id, p.cod, p.total_amount, p.payment_status, " +
                "o.order_id, o.customer_id, o.delivery_order_id, o.bill_no, o.sub_total_price, " +
                "o.delivery_fee, o.total_order_price, o.payment_type_id, " +
                "o.created_Date, o.remark, o.is_print, p.status_id, d.order_code, " +
                "d.status_id, c.customer_number " +  // <-- Added customer_number
                "FROM pos_payment_tb p " +
                "INNER JOIN pos_main_order_tb o ON p.order_id = o.order_id " +
                "INNER JOIN pos_main_delivery_order_tb d ON d.delivery_id = o.delivery_order_id " +
                "INNER JOIN pos_main_customer_tb c ON o.customer_id = c.customer_id " + // <-- New join
                "WHERE DATE(o.created_Date) BETWEEN ? AND ? " +
                "AND d.status_id IN (4, 5)"
            );


            if (paymentType != null && paymentType != 0) {
                sql.append(" AND o.payment_type_id = ?");
            }
            if (paidStatus != 0) {
                sql.append(" AND p.payment_status = ?");
            }

            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            int paramIndex = 3;
            if (paymentType != null && paymentType != 0) {
                ps.setInt(paramIndex++, paymentType);
            }
            if (paidStatus != 0) {
                ps.setInt(paramIndex++, paidStatus == 1 ? 1 : 0);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setPaymentId(rs.getInt("payment_id"));
                deliveryOrder.setCod(rs.getDouble("cod"));
                deliveryOrder.setTotalAmount(rs.getDouble("total_amount"));
                deliveryOrder.setPaymentStatus(rs.getInt("payment_status"));
                deliveryOrder.setOrderCode(rs.getString("order_code"));
                deliveryOrder.setOrderId(rs.getInt("order_id"));
                deliveryOrder.setCustomerId(rs.getInt("customer_number"));
                deliveryOrder.setSubTotalPrice(rs.getDouble("sub_total_price"));
                deliveryOrder.setDeliveryFee(rs.getDouble("delivery_fee"));
                deliveryOrder.setGrandTotalPrice(rs.getDouble("total_order_price"));
                deliveryOrder.setPaymentTypeId(rs.getInt("payment_type_id"));
                deliveryOrder.setDate(rs.getString("created_Date"));
                deliveryOrder.setRemark(rs.getString("remark"));
                deliveryOrder.setIsPrint(rs.getInt("is_print"));
                deliveryOrder.setPayment_Type(rs.getString("payment_status"));

                deliveryOrders.add(deliveryOrder);
            }
        } catch (Exception e) {
            Logger.getLogger(PaymentRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e,"get All Payment Duration error");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                Logger.getLogger(PaymentRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e,"get All Payment Duration error");
            }
        }
        return deliveryOrders;
    }

    public void update(String orderID, int paymentStatus) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBCon.getDatabaseConnection();
            String sql = "UPDATE pos_payment_tb SET payment_status = ?, status_id = ? WHERE order_id = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, paymentStatus);
            ps.setInt(2, paymentStatus==0 ? 9:8);
            ps.setString(3, orderID);
            ps.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(PaymentRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e,"get All Payment update error");
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                Logger.getLogger(PaymentRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e,"get All Payment update error");
                e.printStackTrace();
            }
        }
    }

    public ArrayList<DeliveryOrder> getPaymentByOrderId(String order_code) {
        ArrayList<DeliveryOrder> deliveryOrders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBCon.getDatabaseConnection();
            String sql = 
                "SELECT p.payment_id, p.cod, p.total_amount, p.payment_status, " +
                "o.order_id, o.customer_id, o.delivery_order_id, o.bill_no, o.sub_total_price, " +
                "o.delivery_fee, o.total_order_price, o.payment_type_id, " +
                "o.created_Date, o.remark, o.is_print, " +
                "d.status_id " +
                "FROM pos_payment_tb p " +
                "INNER JOIN pos_main_order_tb o ON p.order_id = o.order_id " +
                "INNER JOIN pos_main_delivery_order_tb d ON d.delivery_id = o.delivery_order_id " +
                "WHERE d.order_code LIKE ? " +
                "AND d.status_id IN (4, 5)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+order_code+"%");
            rs = ps.executeQuery();

            while (rs.next()) {
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setPaymentId(rs.getInt("payment_id"));
                deliveryOrder.setCod(rs.getDouble("cod"));
                deliveryOrder.setTotalAmount(rs.getDouble("total_amount"));
                deliveryOrder.setPaymentStatus(rs.getInt("payment_status"));
                deliveryOrder.setOrderCode(rs.getString("bill_no"));
                deliveryOrder.setOrderId(rs.getInt("order_id"));
                deliveryOrder.setCustomerId(rs.getInt("customer_id"));
                deliveryOrder.setSubTotalPrice(rs.getDouble("sub_total_price"));
                deliveryOrder.setDeliveryFee(rs.getDouble("delivery_fee"));
                deliveryOrder.setGrandTotalPrice(rs.getDouble("total_order_price"));
                deliveryOrder.setPaymentTypeId(rs.getInt("payment_type_id"));
                deliveryOrder.setDate(rs.getString("created_Date"));
                deliveryOrder.setRemark(rs.getString("remark"));
                deliveryOrder.setIsPrint(rs.getInt("is_print"));

                deliveryOrders.add(deliveryOrder);
            }
        } catch (Exception e) {
            Logger.getLogger(PaymentRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Get Payment by Order ID error");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                Logger.getLogger(PaymentRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "Get Payment by Order ID error (closing)");
            }
        }

        return deliveryOrders;
    }

}
