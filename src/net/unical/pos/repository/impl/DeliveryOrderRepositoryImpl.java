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
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.dto.OrderDetailsDto;
import net.unical.pos.model.DeliveryOrder;
import net.unical.pos.model.DeliveryOrderAmounts;
import net.unical.pos.model.WrapperOrder;
import net.unical.pos.repository.custom.DeliveryOrderRepositoryCustom;

/**
 *
 * @author Sanjuka
 */
public class DeliveryOrderRepositoryImpl implements DeliveryOrderRepositoryCustom{
    
    @Override
    public String getOrderIDByBillNo(String billNo) {
        String orderId = null;
        PreparedStatement ps = null;
        ResultSet rst = null;
        Connection con = null;

        try {
            con = DBCon.getDatabaseConnection();
            String query = "SELECT order_id FROM pos_main_order_tb WHERE delivery_order_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, billNo);
            rst = ps.executeQuery();

            if (rst.next()) {
                orderId = rst.getString("order_id");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connection Error (: " + ex.getMessage());
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
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }

        return orderId;
    }

    @Override
    public Integer save(DeliveryOrder deliveryOrder) throws Exception {
        System.err.println(deliveryOrder.getPaidAmount());
        PreparedStatement ps = null;
        ResultSet rst = null;
        boolean isLocalConnection = false;
        Connection con = null;

        Integer customerId = null;
        Integer deliveryId = null;
        Integer orderId = null;

        try {
            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }

            con.setAutoCommit(false);

            System.out.println("Customer Id : " + deliveryOrder.getCustomerId());
            if (deliveryOrder.getCustomerId() != null) {
                System.out.println("Customer Updated");
                // Update Customer
                ps = con.prepareStatement("UPDATE pos_main_customer_tb SET customer_name = ?, address = ?, phone_one = ?, phone_two = ? WHERE customer_id = ?");
                ps.setString(1, deliveryOrder.getCustomerName());
                ps.setString(2, deliveryOrder.getAddress());
                ps.setString(3, deliveryOrder.getPhoneOne());
                ps.setString(4, deliveryOrder.getPhoneTwo());
                ps.setInt(5, deliveryOrder.getCustomerId());
                ps.executeUpdate();

                // Add Delivery
                ps = con.prepareStatement("INSERT INTO pos_main_delivery_order_tb (customer_id, order_code, cod_amount, weight, remark, status, is_free_delivery, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, deliveryOrder.getCustomerId());
                ps.setString(2, deliveryOrder.getOrderCode());
                ps.setDouble(3, deliveryOrder.getCod());
                ps.setString(4, deliveryOrder.getWeight());
                ps.setString(5, deliveryOrder.getRemark());
                ps.setInt(6, 1);
                ps.setInt(7, deliveryOrder.getFreeShip());
                ps.setInt(8, 1);
                ps.executeUpdate();
                rst = ps.getGeneratedKeys();
                if (rst.next()) {
                    deliveryId = rst.getInt(1);
                }

                // Add Order
                ps = con.prepareStatement("INSERT INTO pos_main_order_tb (customer_id, delivery_order_id, bill_no, sub_total_price, delivery_fee, total_order_price, payment_type_id, remark, user_id, status, visible, paid_amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, deliveryOrder.getCustomerId());
                ps.setInt(2, deliveryId);
                ps.setString(3, deliveryOrder.getOrderCode());
                ps.setDouble(4, deliveryOrder.getSubTotalPrice());
                ps.setDouble(5, deliveryOrder.getDeliveryFee());
                ps.setDouble(6, deliveryOrder.getGrandTotalPrice());
                ps.setInt(7, deliveryOrder.getPaymentTypeId());
                ps.setString(8, deliveryOrder.getRemark());
                ps.setInt(9, 1);
                ps.setInt(10, 1);
                ps.setInt(11, 1);
                ps.setDouble(12, deliveryOrder.getPaidAmount());
                ps.executeUpdate();
                rst = ps.getGeneratedKeys();
                if (rst.next()) {
                    orderId = rst.getInt(1);
                }

                // Add Order Details
                ArrayList<OrderDetailsDto> orderDetailsDtos = deliveryOrder.getOrderDetailsDtos();
                for (OrderDetailsDto detailsDto : orderDetailsDtos) {
                    ps = con.prepareStatement("INSERT INTO pos_main_order_details_tb (order_id, item_id, quantity, per_item_price, total_item_price, status, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, orderId);
                    ps.setInt(2, detailsDto.getItemId());
                    ps.setDouble(3, detailsDto.getQty());
                    ps.setDouble(4, detailsDto.getPerItemPrice());
                    ps.setDouble(5, detailsDto.getTotalItemPrice());
                    ps.setInt(6, 1);
                    ps.setInt(7, 1);
                    ps.executeUpdate();
                }

            } else {
                // Add Customer
                ps = con.prepareStatement("INSERT INTO pos_main_customer_tb (customer_name, address, phone_one, phone_two, status, user_id, visible, customer_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, deliveryOrder.getCustomerName());
                ps.setString(2, deliveryOrder.getAddress());
                ps.setString(3, deliveryOrder.getPhoneOne());
                ps.setString(4, deliveryOrder.getPhoneTwo());
                ps.setInt(5, 1);
                ps.setInt(6, 1);
                ps.setInt(7, 1);
                ps.setString(8, deliveryOrder.getCustomerNumber());
                ps.executeUpdate();
                rst = ps.getGeneratedKeys();
                if (rst.next()) {
                    customerId = rst.getInt(1);
                }

                // Add Delivery
                ps = con.prepareStatement("INSERT INTO pos_main_delivery_order_tb (customer_id, order_code, cod_amount, weight, remark, status, is_free_delivery, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, customerId);
                ps.setString(2, deliveryOrder.getOrderCode());
                ps.setDouble(3, deliveryOrder.getCod());
                ps.setString(4, deliveryOrder.getWeight());
                ps.setString(5, deliveryOrder.getRemark());
                ps.setInt(6, 1);
                ps.setInt(7, deliveryOrder.getFreeShip());
                ps.setInt(8, 1);
                ps.executeUpdate();
                rst = ps.getGeneratedKeys();
                if (rst.next()) {
                    deliveryId = rst.getInt(1);
                }

                // Add Order
                ps = con.prepareStatement("INSERT INTO pos_main_order_tb (customer_id, delivery_order_id, bill_no, sub_total_price, delivery_fee, total_order_price, payment_type_id, remark, user_id, status, visible, paid_amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, customerId);
                ps.setInt(2, deliveryId);
                ps.setString(3, deliveryOrder.getOrderCode());
                ps.setDouble(4, deliveryOrder.getSubTotalPrice());
                ps.setDouble(5, deliveryOrder.getDeliveryFee());
                ps.setDouble(6, deliveryOrder.getGrandTotalPrice());
                ps.setInt(7, deliveryOrder.getPaymentTypeId());
                ps.setString(8, deliveryOrder.getRemark());
                ps.setInt(9, 1);
                ps.setInt(10, 1);
                ps.setInt(11, 1);
                ps.setDouble(12, deliveryOrder.getPaidAmount());
                ps.executeUpdate();
                rst = ps.getGeneratedKeys();
                if (rst.next()) {
                    orderId = rst.getInt(1);
                }

                // Add Order Details
                ArrayList<OrderDetailsDto> orderDetailsDtos = deliveryOrder.getOrderDetailsDtos();
                for (OrderDetailsDto detailsDto : orderDetailsDtos) {
                    ps = con.prepareStatement("INSERT INTO pos_main_order_details_tb (order_id, item_id, quantity, per_item_price, total_item_price, status, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, orderId);
                    ps.setInt(2, detailsDto.getItemId());
                    ps.setDouble(3, detailsDto.getQty());
                    ps.setDouble(4, detailsDto.getPerItemPrice());
                    ps.setDouble(5, detailsDto.getTotalItemPrice());
                    ps.setInt(6, 1);
                    ps.setInt(7, 1);
                    ps.executeUpdate();
                }
            }

            // Add Payment
            ps = con.prepareStatement("INSERT INTO pos_payment_tb (order_id, customer_id, cod, total_amount, payment_status, created_Date, edited_Date, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, orderId);
            ps.setInt(2, deliveryOrder.getCustomerId() != null ? deliveryOrder.getCustomerId() : customerId);
            ps.setDouble(3, deliveryOrder.getCod());
            ps.setDouble(4, deliveryOrder.getGrandTotalPrice());
            ps.setInt(5, 0);
            ps.setDate(6, deliveryOrder.getCreateDate());
            ps.setDate(7, deliveryOrder.getEditedDate());
            ps.setInt(8, deliveryOrder.getUserID());
            ps.executeUpdate();

            con.commit();

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            con.rollback();
            return null;
        } finally {
            if (isLocalConnection && con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    return null;
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    return null;
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return deliveryId;
    }
    
    
    
    @Override
    public ArrayList<DeliveryOrder> getAll(String date,Integer paymentType) throws Exception {
        PreparedStatement ps = null;
        ResultSet rst = null;
        boolean isLocalConnection = false;
        Connection con = null;
        
        ArrayList<DeliveryOrder> deliveryOrders=new ArrayList<>();
        
        try {
            
            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }
            
            String sql="";
            
//            System.out.println("Payment Type : "+paymentType);
            if(paymentType==1){
                sql="SELECT dot.delivery_id,dot.order_code,ct.customer_name,ct.address,dot.cod_amount,ct.phone_one,ct.phone_two,\n" +
"ot.sub_total_price,ot.delivery_fee,dot.status,dot.is_return,ot.total_order_price,dot.remark,pt.payment_type_id,ot.is_print \n" +
"FROM pos_main_delivery_order_tb dot\n" +
"INNER JOIN pos_main_customer_tb ct ON dot.customer_id=ct.customer_id\n" +
"INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n" +
"INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id=pt.payment_type_id\n" +
"WHERE dot.status=1 AND DATE_FORMAT(dot.created_Date,'%Y-%m-%d') = '"+date+"' AND pt.payment_type_id='"+paymentType+"'";
            }else if(paymentType==2){
                sql="SELECT dot.delivery_id,dot.order_code,ct.customer_name,ct.address,dot.cod_amount,ct.phone_one,ct.phone_two,\n" +
"ot.sub_total_price,ot.delivery_fee,dot.status,dot.is_return,ot.total_order_price,dot.remark,pt.payment_type_id,ot.is_print \n" +
"FROM pos_main_delivery_order_tb dot\n" +
"INNER JOIN pos_main_customer_tb ct ON dot.customer_id=ct.customer_id\n" +
"INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n" +
"INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id=pt.payment_type_id\n" +
"WHERE dot.status=1 AND DATE_FORMAT(dot.created_Date,'%Y-%m-%d') = '"+date+"' AND pt.payment_type_id='"+paymentType+"'";
            }else{
                sql="SELECT dot.delivery_id,dot.order_code,ct.customer_name,ct.address,dot.cod_amount,ct.phone_one,ct.phone_two,\n" +
"ot.sub_total_price,ot.delivery_fee,dot.status,dot.is_return,ot.total_order_price,dot.remark,pt.payment_type_id,ot.is_print \n" +
"FROM pos_main_delivery_order_tb dot\n" +
"INNER JOIN pos_main_customer_tb ct ON dot.customer_id=ct.customer_id\n" +
"INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n" +
"INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id=pt.payment_type_id\n" +
"WHERE dot.status=1 AND DATE_FORMAT(dot.created_Date,'%Y-%m-%d') = '"+date+"'";
            }
            
//            System.out.println(sql);
            ps=con.prepareStatement(sql);
            
            rst=ps.executeQuery();
            
            
            while(rst.next()){
                DeliveryOrder deliveryOrder=new DeliveryOrder();
                        deliveryOrder.setOrderId(rst.getInt("delivery_id"));
                        deliveryOrder.setOrderCode(rst.getString("order_code"));
                        deliveryOrder.setCustomerName(rst.getString("customer_name"));
                        deliveryOrder.setAddress(rst.getString("address"));
                        deliveryOrder.setCod(rst.getDouble("cod_amount"));
                        deliveryOrder.setPhoneOne(rst.getString("phone_one"));
                        deliveryOrder.setPhoneTwo(rst.getString("phone_two"));
                        deliveryOrder.setSubTotalPrice(rst.getDouble("sub_total_price"));
                        deliveryOrder.setDeliveryFee(rst.getDouble("delivery_fee"));
                        deliveryOrder.setStatus(rst.getInt("status"));
                        deliveryOrder.setIsReturn(rst.getInt("is_return"));
                        deliveryOrder.setGrandTotalPrice(rst.getDouble("total_order_price"));
                        deliveryOrder.setRemark(rst.getString("remark"));
                        deliveryOrder.setPaymentTypeId(rst.getInt("payment_type_id"));
                        deliveryOrder.setIsPrint(rst.getInt("is_print"));
                        
                        deliveryOrders.add(deliveryOrder);
            }
            
        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
        }finally {

            if (isLocalConnection && con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception e) {
                }
            }
        }
       return deliveryOrders; 
    }

    @Override
    public ArrayList<DeliveryOrder> getAllDuration(String fromDate, String toDate, Integer paymentType, int status) {
        ArrayList<DeliveryOrder> deliveryOrders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBCon.getDatabaseConnection();
            StringBuilder sql = new StringBuilder(
                "SELECT dot.delivery_id, dot.order_code, ct.customer_name, ct.address, dot.cod_amount, " +
                "ct.phone_one, ct.phone_two, ot.sub_total_price, ot.delivery_fee, dot.status, " +
                "dot.status_id, dot.is_return, ot.total_order_price, dot.remark, pt.payment_type_id, ot.is_print, " +
                "p.payment_id, p.cod AS cod_payment, p.total_amount, p.payment_status " +
                "FROM pos_main_delivery_order_tb dot " +
                "INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id " +
                "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id " +
                "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id " +
                "LEFT JOIN pos_payment_tb p ON ot.order_id = p.order_id " +
                "WHERE dot.status = 1 AND DATE(dot.created_date) BETWEEN ? AND ?"
            );

            if (paymentType != 0) {
                sql.append(" AND pt.payment_type_id = ?");
            }
            if (status != 0) {
                sql.append(" AND dot.status_id = ?");
            }

            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            int paramIndex = 3;
            if (paymentType != 0) {
                ps.setInt(paramIndex++, paymentType);
            }
            if (status != 0) {
                ps.setInt(paramIndex++, status);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setOrderId(rs.getInt("delivery_id"));
                deliveryOrder.setOrderCode(rs.getString("order_code"));
                deliveryOrder.setCustomerName(rs.getString("customer_name"));
                deliveryOrder.setAddress(rs.getString("address"));
                deliveryOrder.setCod(rs.getDouble("cod_amount"));
                deliveryOrder.setPhoneOne(rs.getString("phone_one"));
                deliveryOrder.setPhoneTwo(rs.getString("phone_two"));
                deliveryOrder.setSubTotalPrice(rs.getDouble("sub_total_price"));
                deliveryOrder.setDeliveryFee(rs.getDouble("delivery_fee"));
                deliveryOrder.setStatus(rs.getInt("status"));
                deliveryOrder.setStatusType(rs.getInt("status_id"));
                deliveryOrder.setIsReturn(rs.getInt("is_return"));
                deliveryOrder.setGrandTotalPrice(rs.getDouble("total_order_price"));
                deliveryOrder.setRemark(rs.getString("remark"));
                deliveryOrder.setPaymentTypeId(rs.getInt("payment_type_id"));
                deliveryOrder.setIsPrint(rs.getInt("is_print"));

                // Set the new fields from pos_payment_tb
                deliveryOrder.setPaymentId(rs.getInt("payment_id"));
                deliveryOrder.setCodPayment(rs.getDouble("cod_payment"));
                deliveryOrder.setTotalAmount(rs.getDouble("total_amount"));
                deliveryOrder.setPaymentStatus(rs.getInt("payment_status"));

                deliveryOrders.add(deliveryOrder);
            }
        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deliveryOrders;
    }



    public ArrayList<DeliveryOrder> getDeliveryOrdersByCustomer(Integer customer_id) {
        PreparedStatement ps = null;
        ResultSet rst = null;
        boolean isLocalConnection = false;
        Connection con = null;
        
        ArrayList<DeliveryOrder> deliveryOrders=new ArrayList<>();
        
        try {
            
            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }
            
            String sql="SELECT mdo.order_code,mo.total_order_price,mdo.cod_amount,mdo.created_date,mdo.status_id FROM pos_main_delivery_order_tb AS mdo\n" +
"INNER JOIN pos_main_order_tb AS mo ON mo.delivery_order_id=mdo.delivery_id \n" +
"WHERE mdo.customer_id='"+customer_id+"' AND mdo.status=1";
            
            
            System.out.println(sql);
            ps=con.prepareStatement(sql);
            
            rst=ps.executeQuery();
            
            
            while(rst.next()){
                DeliveryOrder deliveryOrder=new DeliveryOrder();
                        deliveryOrder.setOrderCode(rst.getString("order_code"));
                        deliveryOrder.setGrandTotalPrice(rst.getDouble("total_order_price"));
                        deliveryOrder.setCod(rst.getDouble("cod_amount"));
                        deliveryOrder.setDate(rst.getString("created_date"));
                        deliveryOrder.setStatusType(rst.getInt("status_id"));
                        
                        deliveryOrders.add(deliveryOrder);
            }
            
        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
        }finally {

            if (isLocalConnection && con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception e) {
                }
            }
        }
       return deliveryOrders;
    }

    public void update(String orderCode, int status_id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rst = null;
        boolean isLocalConnection = false;
        Connection con = null;
        
        try {
            
            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }
            System.err.println("Updated + "+orderCode+" : "+status_id);
            ps=con.prepareStatement("UPDATE pos_main_delivery_order_tb SET status_id = '"+status_id+"' WHERE order_code='"+orderCode+"'");
            ps.executeUpdate();
            
            
        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
        }finally {

            if (isLocalConnection && con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception e) {
                }
            }

        }
    }

    public ArrayList<DeliveryOrderAmounts> getCalculation(String fromDate, String toDate, Integer paymentType) {
        PreparedStatement ps = null;
        ResultSet rst = null;
        boolean isLocalConnection = false;
        Connection con = null;

        ArrayList<DeliveryOrderAmounts> deliveryOrdersAmounts = new ArrayList<>();

        try {
            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }

            String sql = "";

            if (paymentType == 1 || paymentType == 2) {
                sql = "SELECT dot.delivery_id, dot.order_code, ct.customer_name, ct.address, dot.cod_amount, ct.phone_one, ct.phone_two, " +
                      "ot.sub_total_price, ot.delivery_fee, dot.status, dot.status_id, dot.is_return, ot.total_order_price, dot.remark, " +
                      "pt.payment_type_id, ot.is_print " +
                      "FROM pos_main_delivery_order_tb dot " +
                      "INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id " +
                      "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id " +
                      "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id " +
                      "WHERE dot.status = 1 AND DATE(dot.created_date) BETWEEN ? AND ? AND pt.payment_type_id = ?";
            } else {
                sql = "SELECT SUM(dot.cod_amount) AS total_cod, SUM(ot.delivery_fee) AS total_delivery_fee, SUM(ot.total_order_price) AS total_amount " +
                      "FROM pos_main_delivery_order_tb dot " +
                      "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id " +
                      "WHERE dot.status_id NOT IN (6) AND DATE(dot.created_date) BETWEEN ? AND ?";
            }

            ps = con.prepareStatement(sql);
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            if (paymentType == 1 || paymentType == 2) {
                ps.setInt(3, paymentType);
            }

            rst = ps.executeQuery();

            while (rst.next()) {
                DeliveryOrderAmounts deliveryOrder = new DeliveryOrderAmounts();
                if (paymentType == 1 || paymentType == 2) {
                    // populate the deliveryOrder object with the necessary data
                    deliveryOrder.setTotalAmount(rst.getDouble("total_order_price"));
                    deliveryOrder.setTotalDeliveryCharge(rst.getDouble("delivery_fee"));
                    // set other fields as required
                } else {
                    deliveryOrder.setTotalAmount(rst.getDouble("total_amount"));
                    deliveryOrder.setTotalDeliveryCharge(rst.getDouble("total_delivery_fee"));
                    deliveryOrder.setTotalCod(rst.getDouble("total_cod"));
                }
                deliveryOrdersAmounts.add(deliveryOrder);
            }

            rst.close();
            ps.close();

            String sql2 = "SELECT SUM(ot.sub_total_price) AS total_returns FROM pos_main_delivery_order_tb dot " +
                          "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id " +
                          "WHERE dot.status_id = 5 AND DATE(dot.created_date) BETWEEN ? AND ?";

            ps = con.prepareStatement(sql2);
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            rst = ps.executeQuery();
            if (rst.next()) {
                double totalReturns = rst.getDouble("total_returns");
                deliveryOrdersAmounts.forEach(deliveryOrder -> deliveryOrder.setTotalReturns(totalReturns));
            }

            rst.close();
            ps.close();

            String sql3 = "SELECT SUM(cod_amount) AS total_cod FROM pos_main_delivery_order_tb " +
                          "WHERE status_id NOT IN (5, 6) AND DATE(created_date) BETWEEN ? AND ?";

            ps = con.prepareStatement(sql3);
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            rst = ps.executeQuery();
            if (rst.next()) {
                double totalCod = rst.getDouble("total_cod");
                deliveryOrdersAmounts.forEach(deliveryOrder -> deliveryOrder.setTotalCod(totalCod));
            }
        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rst != null) rst.close();
                if (ps != null) ps.close();
                if (isLocalConnection && con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deliveryOrdersAmounts;
    }


    
    public boolean update(DeliveryOrder deliveryOrderDto, Integer orderId) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        boolean success = false;

        try {
            connection = DBCon.getDatabaseConnection();
            connection.setAutoCommit(false);

            if (changeItemStatusByOrderID(connection, deliveryOrderDto, orderId) &&
                updateDeliveryDetails(connection, deliveryOrderDto) &&
                updateOrder(connection, deliveryOrderDto, orderId) &&
                updateCustomer(connection, deliveryOrderDto) &&
                updateOrderDetails(connection, deliveryOrderDto, orderId)) {

                connection.commit();
                success = true;
            } else {
                connection.rollback();
            }
        } catch (SQLException | ClassNotFoundException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

        return success;
    }

    private boolean changeItemStatusByOrderID(Connection connection, DeliveryOrder deliveryOrderDto, Integer orderId) throws SQLException, ClassNotFoundException {
        String updateOrderDetailsStatusSQL = "UPDATE pos_main_order_details_tb SET status = 0 WHERE order_id = ?";
        
        PreparedStatement orderDetailsStatusStatement = null;
        
        orderDetailsStatusStatement = connection.prepareStatement(updateOrderDetailsStatusSQL);
        orderDetailsStatusStatement.setInt(1, orderId);
        
        return orderDetailsStatusStatement.executeUpdate() > 0;
    }

    private boolean updateDeliveryDetails(Connection connection, DeliveryOrder deliveryOrderDto) throws SQLException, ClassNotFoundException {
        String updateOrderSQL = "UPDATE pos_main_delivery_order_tb SET "
                + "customer_id = ?, cod_amount = ?, weight = ?, remark = ?, "
                + "status_id = ?, is_free_delivery = ?, is_return = ?, user_id = ? "
                + "WHERE order_code = ?";
        
        PreparedStatement orderStatement = null;
        
        orderStatement = connection.prepareStatement(updateOrderSQL);
        orderStatement.setObject(1, deliveryOrderDto.getCustomerId());
        orderStatement.setDouble(2, deliveryOrderDto.getCod());
        orderStatement.setString(3, deliveryOrderDto.getWeight());
        orderStatement.setString(4, deliveryOrderDto.getRemark());
        orderStatement.setInt(5, 2);
        orderStatement.setInt(6, deliveryOrderDto.getFreeShip());
        orderStatement.setInt(7, 0);
        orderStatement.setInt(8, 1);
        orderStatement.setString(9, deliveryOrderDto.getOrderCode());

        return orderStatement.executeUpdate() > 0;
    }

    private boolean updateOrder(Connection connection, DeliveryOrder deliveryOrderDto, Integer orderId) throws ClassNotFoundException, SQLException {
        String updateMainOrderSQL = "UPDATE pos_main_order_tb SET "
                + "customer_id = ?, sub_total_price = ?, "
                + "delivery_fee = ?, total_order_price = ?, table_id = ?, "
                + "remark = ?, edited_by = ?, status = ?, paid_amount = ? "
                + "WHERE delivery_order_id = (SELECT delivery_id FROM pos_main_delivery_order_tb WHERE order_code = ?)";
        
        PreparedStatement mainOrderStatement = null;
        
        mainOrderStatement = connection.prepareStatement(updateMainOrderSQL);
        mainOrderStatement.setInt(1, deliveryOrderDto.getCustomerId());
        mainOrderStatement.setDouble(2, deliveryOrderDto.getSubTotalPrice());
        mainOrderStatement.setDouble(3, deliveryOrderDto.getDeliveryFee());
        mainOrderStatement.setDouble(4, deliveryOrderDto.getGrandTotalPrice());
        mainOrderStatement.setInt(5, deliveryOrderDto.getPaymentTypeId());
        mainOrderStatement.setString(6, deliveryOrderDto.getRemark());
        mainOrderStatement.setInt(7, 1);
        mainOrderStatement.setInt(8, 1);
        mainOrderStatement.setString(9, deliveryOrderDto.getPaidAmount()+"");
        mainOrderStatement.setString(10, deliveryOrderDto.getOrderCode());

        return mainOrderStatement.executeUpdate() > 0;
    }

    private boolean updateCustomer(Connection connection, DeliveryOrder deliveryOrderDto) throws ClassNotFoundException, SQLException {
        String updateCustomerSQL = "UPDATE pos_main_customer_tb SET "
                + "customer_name = ?, address = ?, phone_one = ?, phone_two = ?, customer_number = ? "
                + "WHERE customer_id = ?";
        
        PreparedStatement customerStatement = null;
        
        customerStatement = connection.prepareStatement(updateCustomerSQL);
        customerStatement.setString(1, deliveryOrderDto.getCustomerName());
        customerStatement.setString(2, deliveryOrderDto.getAddress());
        customerStatement.setString(3, deliveryOrderDto.getPhoneOne());
        customerStatement.setString(4, deliveryOrderDto.getPhoneTwo());
        customerStatement.setString(5, deliveryOrderDto.getCustomerNumber());
        customerStatement.setInt(6, deliveryOrderDto.getCustomerId());
        
        return customerStatement.executeUpdate() > 0;
    }

    private boolean updateOrderDetails(Connection connection, DeliveryOrder deliveryOrderDto, Integer orderId) throws SQLException, ClassNotFoundException {
        String insertOrderDetailsSQL = "INSERT INTO pos_main_order_details_tb(order_id,item_id,quantity,per_item_price,total_item_price,status,user_id) VALUES(?,?,?,?,?,?,?)";
        
        PreparedStatement insertOrderDetailsStatement = null;
        
        for (OrderDetailsDto detail : deliveryOrderDto.getOrderDetailsDtos()) {
            insertOrderDetailsStatement = connection.prepareStatement(insertOrderDetailsSQL);
            
            insertOrderDetailsStatement.setInt(1, orderId);
            insertOrderDetailsStatement.setInt(2, detail.getItemId());
            insertOrderDetailsStatement.setInt(3, detail.getQty());
            insertOrderDetailsStatement.setDouble(4, detail.getPerItemPrice());
            insertOrderDetailsStatement.setDouble(5, detail.getTotalItemPrice());
            insertOrderDetailsStatement.setInt(6, 1);
            insertOrderDetailsStatement.setInt(7, 1);
            
            if(insertOrderDetailsStatement.executeUpdate() < 0){
                return false;
            }
        }
        
        return true;
    }

    public DeliveryOrder getOrderById(String orderId) {
        PreparedStatement ps = null;
        ResultSet rst = null;
        boolean isLocalConnection = false;
        Connection con = null;

        DeliveryOrder deliveryOrder = null;

        try {

            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }

            String sql = "SELECT dot.delivery_id, dot.order_code, ct.customer_name, ct.address, dot.cod_amount, ct.phone_one, ct.phone_two, " +
                         "ot.sub_total_price, ot.delivery_fee, dot.status, dot.status_id, dot.is_return, ot.total_order_price, dot.remark, " +
                         "pt.payment_type_id, ot.is_print " +
                         "FROM pos_main_delivery_order_tb dot " +
                         "INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id " +
                         "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id " +
                         "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id " +
                         "WHERE dot.order_code = ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, orderId);

            rst = ps.executeQuery();

            if (rst.next()) {
                deliveryOrder = new DeliveryOrder();
                deliveryOrder.setOrderId(rst.getInt("delivery_id"));
                deliveryOrder.setOrderCode(rst.getString("order_code"));
                deliveryOrder.setCustomerName(rst.getString("customer_name"));
                deliveryOrder.setAddress(rst.getString("address"));
                deliveryOrder.setCod(rst.getDouble("cod_amount"));
                deliveryOrder.setPhoneOne(rst.getString("phone_one"));
                deliveryOrder.setPhoneTwo(rst.getString("phone_two"));
                deliveryOrder.setSubTotalPrice(rst.getDouble("sub_total_price"));
                deliveryOrder.setDeliveryFee(rst.getDouble("delivery_fee"));
                deliveryOrder.setStatus(rst.getInt("status"));
                deliveryOrder.setStatusType(rst.getInt("status_id"));
                deliveryOrder.setIsReturn(rst.getInt("is_return"));
                deliveryOrder.setGrandTotalPrice(rst.getDouble("total_order_price"));
                deliveryOrder.setRemark(rst.getString("remark"));
                deliveryOrder.setPaymentTypeId(rst.getInt("payment_type_id"));
                deliveryOrder.setIsPrint(rst.getInt("is_print"));
            }

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (isLocalConnection && con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception e) {
                }
            }
        }
        return deliveryOrder;
    }

    public ArrayList<DeliveryOrder> getAllDuration(String fromDate, String toDate, int paymentType) {
        return null;
    }

    public ArrayList<WrapperOrder> getWrappingOrder(Date fromDate, Date toDate) throws ClassNotFoundException {
        ArrayList<WrapperOrder> list = new ArrayList<>();
        String sql = "SELECT dot.order_code, dot.delivery_id, ct.customer_name, ct.address, dot.cod_amount, " +
                     "ct.phone_one, ct.phone_two, dot.weight " +
                     "FROM pos_main_delivery_order_tb dot " +
                     "INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id " +
                     "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id " +
                     "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id " +
                     "LEFT JOIN pos_payment_tb p ON ot.order_id = p.order_id " +
                     "WHERE dot.status_id = 3 AND Date(dot.created_date) BETWEEN ? AND ?";

        try (Connection con = DBCon.getDatabaseConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setDate(1, new java.sql.Date(fromDate.getTime()));
            pst.setDate(2, new java.sql.Date(toDate.getTime()));

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                WrapperOrder order = new WrapperOrder(
                    rs.getString("order_code"),
                    rs.getString("delivery_id"),
                    rs.getString("customer_name"),
                    rs.getString("address"),
                    rs.getDouble("cod_amount"),
                    rs.getString("phone_one"),
                    rs.getString("phone_two"),
                    rs.getDouble("weight")
                );
                list.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<WrapperOrder> getDaliyOutOfDeliveryOrder(Date fromDate, Date toDate) throws ClassNotFoundException {
        ArrayList<WrapperOrder> list = new ArrayList<>();
        String sql = "SELECT dot.order_code, dot.delivery_id, ct.customer_name, ct.address, dot.cod_amount, " +
                     "ct.phone_one, ct.phone_two, dot.weight " +
                     "FROM pos_main_delivery_order_tb dot " +
                     "INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id " +
                     "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id " +
                     "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id " +
                     "LEFT JOIN pos_payment_tb p ON ot.order_id = p.order_id " +
                     "WHERE dot.status_id = 4 AND Date(dot.created_date) BETWEEN ? AND ?";

        try (Connection con = DBCon.getDatabaseConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setDate(1, new java.sql.Date(fromDate.getTime()));
            pst.setDate(2, new java.sql.Date(toDate.getTime()));

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                WrapperOrder order = new WrapperOrder(
                    rs.getString("order_code"),
                    rs.getString("delivery_id"),
                    rs.getString("customer_name"),
                    rs.getString("address"),
                    rs.getDouble("cod_amount"),
                    rs.getString("phone_one"),
                    rs.getString("phone_two"),
                    rs.getDouble("weight")
                );
                list.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
}