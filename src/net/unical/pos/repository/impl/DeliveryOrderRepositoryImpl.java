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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dto.DeliveryOrderDto;
import net.unical.pos.dto.OrderDetailsDto;
import net.unical.pos.model.DeliveryOrder;
import net.unical.pos.model.DeliveryOrderAmounts;
import net.unical.pos.model.WrapperOrder;
import net.unical.pos.repository.custom.DeliveryOrderRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Sanjuka
 */
public class DeliveryOrderRepositoryImpl implements DeliveryOrderRepositoryCustom {

    @Override
    public String getOrderIDByBillNo(String billNo) {
        System.out.println("IMPL delivery_ID : "+billNo);
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
            Log.error(ex, "get Order ID By Bill No error");
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
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("Error closing resources: " + e.getMessage());
                Log.error(e, "get Order ID By Bill No error");
            }
        }

        return orderId;
    }

    @Override
    public Integer save(DeliveryOrder deliveryOrder, boolean isOrder) throws Exception {
        System.out.println("Order Type 1 : "+deliveryOrder.getOrderType());
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
            System.out.println("Order Type 2 : "+deliveryOrder.getOrderType());
            if (deliveryOrder.getCustomerId() != null) {
                // Update Customer
                ps = con.prepareStatement("UPDATE pos_main_customer_tb SET customer_name = ?, address = ?, phone_one = ?, phone_two = ? WHERE customer_id = ?");
                ps.setString(1, deliveryOrder.getCustomerName());
                ps.setString(2, deliveryOrder.getAddress());
                ps.setString(3, deliveryOrder.getPhoneOne());
                ps.setString(4, deliveryOrder.getPhoneTwo());
                ps.setInt(5, deliveryOrder.getCustomerId());
                ps.executeUpdate();

                Log.info(DeliveryOrderRepositoryImpl.class, "UPDATE pos_main_customer_tb");

                System.out.println("Order Type 3 : "+deliveryOrder.getOrderType());
                
                // Add Delivery
                ps = con.prepareStatement("INSERT INTO pos_main_delivery_order_tb (customer_id, order_code, cod_amount, weight, remark, status, is_free_delivery, user_id, is_exchange, order_type, website_order_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, deliveryOrder.getCustomerId());
                ps.setString(2, deliveryOrder.getOrderCode());
                ps.setDouble(3, deliveryOrder.getCod());
                ps.setString(4, deliveryOrder.getWeight());
                ps.setString(5, deliveryOrder.getRemark());
                ps.setInt(6, 1);
                ps.setInt(7, deliveryOrder.getFreeShip());
                ps.setInt(8, LogInForm.userID);
                ps.setInt(9, deliveryOrder.getIsExchange());
                ps.setString(10, deliveryOrder.getOrderType());
                ps.setString(11, deliveryOrder.getWebsiteOrderId());
                ps.executeUpdate();
                rst = ps.getGeneratedKeys();
                
                if (rst.next()) {
                    deliveryId = rst.getInt(1);
                }

                Log.info(DeliveryOrderRepositoryImpl.class, "INSERT INTO pos_main_delivery_order_tb");

                // Add Order
                if (isOrder) {
                    ps = con.prepareStatement("INSERT INTO pos_main_order_tb (customer_id, delivery_order_id, bill_no, sub_total_price, delivery_fee, total_order_price, payment_type_id, remark, user_id, status, visible, paid_amount, total_discount_price, discount_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, deliveryOrder.getCustomerId());
                    ps.setInt(2, deliveryId);
                    ps.setString(3, deliveryOrder.getOrderCode());
                    ps.setDouble(4, deliveryOrder.getSubTotalPrice());
                    ps.setDouble(5, deliveryOrder.getDeliveryFee());
                    ps.setDouble(6, deliveryOrder.getGrandTotalPrice());
                    ps.setInt(7, deliveryOrder.getPaymentTypeId());
                    ps.setString(8, deliveryOrder.getRemark());
                    ps.setInt(9, LogInForm.userID);
                    ps.setInt(10, 1);
                    ps.setInt(11, 1);
                    ps.setDouble(12, deliveryOrder.getPaidAmount());

                    Double totalDiscount = 0.0;
                    for (OrderDetailsDto order : deliveryOrder.getOrderDetailsDtos()) {
                        totalDiscount += order.getTotalDiscountPrice();
                    }
                    ps.setDouble(13, totalDiscount);
                    ps.setInt(14, deliveryOrder.getDiscountId());
                    ps.executeUpdate();
                    rst = ps.getGeneratedKeys();
                    if (rst.next()) {
                        orderId = rst.getInt(1);
                    }
                } else {
                    ps = con.prepareStatement("INSERT INTO pos_main_order_tb (customer_id, delivery_order_id, bill_no, sub_total_price, delivery_fee, total_order_price, payment_type_id, remark, user_id, status, visible, paid_amount, total_discount_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, deliveryOrder.getCustomerId());
                    ps.setInt(2, deliveryId);
                    ps.setString(3, deliveryOrder.getOrderCode());
                    ps.setDouble(4, deliveryOrder.getSubTotalPrice());
                    ps.setDouble(5, deliveryOrder.getDeliveryFee());
                    ps.setDouble(6, deliveryOrder.getGrandTotalPrice());
                    ps.setInt(7, deliveryOrder.getPaymentTypeId());
                    ps.setString(8, deliveryOrder.getRemark());
                    ps.setInt(9, LogInForm.userID);
                    ps.setInt(10, 1);
                    ps.setInt(11, 1);
                    ps.setDouble(12, deliveryOrder.getPaidAmount());

                    Double totalDiscount = 0.0;
                    for (OrderDetailsDto order : deliveryOrder.getOrderDetailsDtos()) {
                        totalDiscount += order.getTotalDiscountPrice();
                    }
                    ps.setDouble(13, totalDiscount);
                    ps.executeUpdate();
                    rst = ps.getGeneratedKeys();
                    if (rst.next()) {
                        orderId = rst.getInt(1);
                    }

                }

                Log.info(DeliveryOrderRepositoryImpl.class, "INSERT INTO pos_main_order_tb");

                // Add Order Details
                ArrayList<OrderDetailsDto> orderDetailsDtos = deliveryOrder.getOrderDetailsDtos();
                for (OrderDetailsDto detailsDto : orderDetailsDtos) {
                    ps = con.prepareStatement("INSERT INTO pos_main_order_details_tb (order_id, item_id, quantity, per_item_price, total_discount_price, total_item_price, status, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, orderId);
                    ps.setInt(2, detailsDto.getItemId());
                    ps.setDouble(3, detailsDto.getQty());
                    ps.setDouble(4, detailsDto.getPerItemPrice());
                    ps.setDouble(5, detailsDto.getTotalDiscountPrice());
                    ps.setDouble(6, detailsDto.getTotalItemPrice());
                    ps.setInt(7, 1);
                    ps.setInt(8, LogInForm.userID);
                    ps.executeUpdate();
                }

                Log.info(DeliveryOrderRepositoryImpl.class, "INSERT INTO pos_main_order_details_tb");

            } else {
                // Add Customer
                ps = con.prepareStatement("INSERT INTO pos_main_customer_tb (customer_name, address, phone_one, phone_two, status, user_id, visible, customer_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, deliveryOrder.getCustomerName());
                ps.setString(2, deliveryOrder.getAddress());
                ps.setString(3, deliveryOrder.getPhoneOne());
                ps.setString(4, deliveryOrder.getPhoneTwo());
                ps.setInt(5, 1);
                ps.setInt(6, LogInForm.userID);
                ps.setInt(7, 1);
                ps.setString(8, deliveryOrder.getCustomerNumber());
                ps.executeUpdate();
                rst = ps.getGeneratedKeys();
                if (rst.next()) {
                    customerId = rst.getInt(1);
                }

                Log.info(DeliveryOrderRepositoryImpl.class, "INSERT INTO pos_main_customer_tb");

                // Add Delivery
                ps = con.prepareStatement("INSERT INTO pos_main_delivery_order_tb (customer_id, order_code, cod_amount, weight, remark, status, is_free_delivery, user_id, order_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, customerId);
                ps.setString(2, deliveryOrder.getOrderCode());
                ps.setDouble(3, deliveryOrder.getCod());
                ps.setString(4, deliveryOrder.getWeight());
                ps.setString(5, deliveryOrder.getRemark());
                ps.setInt(6, 1);
                ps.setInt(7, deliveryOrder.getFreeShip());
                ps.setInt(8, LogInForm.userID);
                ps.setString(9, deliveryOrder.getOrderType());
                ps.executeUpdate();
                rst = ps.getGeneratedKeys();
                if (rst.next()) {
                    deliveryId = rst.getInt(1);
                }

                Log.info(DeliveryOrderRepositoryImpl.class, "INSERT INTO pos_main_delivery_order_tb");

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
                ps.setInt(9, LogInForm.userID);
                ps.setInt(10, 1);
                ps.setInt(11, 1);
                ps.setDouble(12, deliveryOrder.getPaidAmount());
                ps.executeUpdate();
                rst = ps.getGeneratedKeys();
                if (rst.next()) {
                    orderId = rst.getInt(1);
                }

                Log.info(DeliveryOrderRepositoryImpl.class, "INSERT INTO pos_main_order_tb");

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
                    ps.setInt(7, LogInForm.userID);
                    ps.executeUpdate();
                }
            }

            System.out.println("deliveryOrder.getPaymentTypeId() : " + deliveryOrder.getPaymentTypeId()); // 1 -> Not Paid, 2 -> Paid
            // Add Payment
            ps = con.prepareStatement("INSERT INTO pos_payment_tb (order_id, customer_id, cod, total_amount, payment_status, created_Date, edited_Date, user_id, status_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, orderId);
            ps.setInt(2, deliveryOrder.getCustomerId() != null ? deliveryOrder.getCustomerId() : customerId);
            ps.setDouble(3, deliveryOrder.getCod());
            ps.setDouble(4, deliveryOrder.getGrandTotalPrice());
            ps.setInt(5, (deliveryOrder.getPaymentTypeId() - 1));
            ps.setTimestamp(6, deliveryOrder.getCreateDate());
            ps.setTimestamp(7, deliveryOrder.getEditedDate());
            ps.setInt(8, LogInForm.userID);
            ps.setInt(9, deliveryOrder.getPaymentTypeId() == 1 ? 9 : 8);
            ps.executeUpdate();

            con.commit();

            Log.info(DeliveryOrderRepositoryImpl.class, "INSERT INTO pos_main_order_tb");

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Order Save Faild");
            con.rollback();
            return null;
        } finally {
            if (isLocalConnection && con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    Log.error(e, "Order Save Faild");
                    return null;
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    Log.error(e, "Order Save Faild");
                    return null;
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception e) {
                    Log.error(e, "Order Save Faild");
                    return null;
                }
            }
        }
        return deliveryId;
    }

    @Override
    public ArrayList<DeliveryOrder> getAll(String date, Integer paymentType) throws Exception {
        PreparedStatement ps = null;
        ResultSet rst = null;
        boolean isLocalConnection = false;
        Connection con = null;

        ArrayList<DeliveryOrder> deliveryOrders = new ArrayList<>();

        try {

            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }

            String sql = "";

//            System.out.println("Payment Type : "+paymentType);
            if (paymentType == 1) {
                sql = "SELECT dot.delivery_id,dot.order_code,ct.customer_name,ct.address,dot.cod_amount,ct.phone_one,ct.phone_two,\n"
                        + "ot.sub_total_price,ot.delivery_fee,dot.status,dot.is_return,ot.total_order_price,dot.remark,pt.payment_type_id,ot.is_print \n"
                        + "FROM pos_main_delivery_order_tb dot\n"
                        + "INNER JOIN pos_main_customer_tb ct ON dot.customer_id=ct.customer_id\n"
                        + "INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n"
                        + "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id=pt.payment_type_id\n"
                        + "WHERE dot.status=1 AND DATE_FORMAT(dot.created_Date,'%Y-%m-%d') = '" + date + "' AND pt.payment_type_id='" + paymentType + "'";
            } else if (paymentType == 2) {
                sql = "SELECT dot.delivery_id,dot.order_code,ct.customer_name,ct.address,dot.cod_amount,ct.phone_one,ct.phone_two,\n"
                        + "ot.sub_total_price,ot.delivery_fee,dot.status,dot.is_return,ot.total_order_price,dot.remark,pt.payment_type_id,ot.is_print \n"
                        + "FROM pos_main_delivery_order_tb dot\n"
                        + "INNER JOIN pos_main_customer_tb ct ON dot.customer_id=ct.customer_id\n"
                        + "INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n"
                        + "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id=pt.payment_type_id\n"
                        + "WHERE dot.status=1 AND DATE_FORMAT(dot.created_Date,'%Y-%m-%d') = '" + date + "' AND pt.payment_type_id='" + paymentType + "'";
            } else {
                sql = "SELECT dot.delivery_id,dot.order_code,ct.customer_name,ct.address,dot.cod_amount,ct.phone_one,ct.phone_two,\n"
                        + "ot.sub_total_price,ot.delivery_fee,dot.status,dot.is_return,ot.total_order_price,dot.remark,pt.payment_type_id,ot.is_print \n"
                        + "FROM pos_main_delivery_order_tb dot\n"
                        + "INNER JOIN pos_main_customer_tb ct ON dot.customer_id=ct.customer_id\n"
                        + "INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n"
                        + "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id=pt.payment_type_id\n"
                        + "WHERE dot.status=1 AND DATE_FORMAT(dot.created_Date,'%Y-%m-%d') = '" + date + "'";
            }

//            System.out.println(sql);
            ps = con.prepareStatement(sql);

            rst = ps.executeQuery();

            while (rst.next()) {
                DeliveryOrder deliveryOrder = new DeliveryOrder();
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
            Log.error(e, "Get All faild");
        } finally {

            if (isLocalConnection && con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    Log.error(e, "Get All faild");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    Log.error(e, "Get All faild");
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception e) {
                    Log.error(e, "Get All faild");
                }
            }
        }
        return deliveryOrders;
    }

    @Override
    public ArrayList<DeliveryOrder> getAllDuration(String fromDate, String toDate, Integer paymentType, int status, String orderType) {
        ArrayList<DeliveryOrder> deliveryOrders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBCon.getDatabaseConnection();
            StringBuilder sql = new StringBuilder(
                    "SELECT dot.created_date, dot.delivery_id, dot.order_code, ct.customer_name, ct.address, dot.cod_amount, "
                    + "ct.phone_one, ct.phone_two, ot.order_id,  ot.sub_total_price, ot.delivery_fee, dot.status, dot.status_id, "
                    + "dot.status_id, dot.is_return, ot.total_order_price, dot.remark, pt.payment_type_id, ot.is_print, "
                    + "p.payment_id, p.cod AS cod_payment, p.total_amount, p.payment_status, dot.order_type "
                    + "FROM pos_main_delivery_order_tb dot "
                    + "INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id "
                    + "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id "
                    + "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id "
                    + "LEFT JOIN pos_payment_tb p ON ot.order_id = p.order_id "
                    + "WHERE dot.status = 1 AND DATE(dot.created_date) BETWEEN ? AND ?"
            );

            if (paymentType != 0) {
                sql.append(" AND pt.payment_type_id = ?");
            }
            if (status != 0) {
                sql.append(" AND dot.status_id = ?");
            }
            if (!orderType.equals("Any")) {
                sql.append(" AND dot.order_type = ?");
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
            if (!orderType.equals("Any")) {
                ps.setString(paramIndex++, orderType);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setCreateDate(rs.getTimestamp("created_date"));
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
                deliveryOrder.setStatusID(rs.getInt("status_id"));

                // Set the new fields from pos_payment_tb
                deliveryOrder.setPaymentId(rs.getInt("payment_id"));
                deliveryOrder.setCodPayment(rs.getDouble("cod_payment"));
                deliveryOrder.setTotalAmount(rs.getDouble("total_amount"));
                deliveryOrder.setPaymentStatus(rs.getInt("payment_status"));
                deliveryOrder.setOrderType(rs.getString("order_type"));

                deliveryOrders.add(deliveryOrder);
            }
        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Get all duration faild");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "Get all duration faild");
                e.printStackTrace();
            }
        }
        return deliveryOrders;
    }

    public ArrayList<DeliveryOrder> getAllOrdersByOrderID(String orderID) {
        System.out.println("Order Id: " + orderID);
        ArrayList<DeliveryOrder> deliveryOrders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBCon.getDatabaseConnection();
            StringBuilder sql = new StringBuilder(
                    "SELECT dot.created_date, dot.delivery_id, dot.order_code, ct.customer_name, ct.address, dot.cod_amount, "
                    + "ct.phone_one, ct.phone_two, ot.sub_total_price, ot.delivery_fee, dot.status, "
                    + "dot.status_id, dot.is_return, ot.total_order_price, dot.remark, pt.payment_type_id, ot.is_print, "
                    + "p.payment_id, p.cod AS cod_payment, p.total_amount, p.payment_status "
                    + "FROM pos_main_delivery_order_tb dot "
                    + "INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id "
                    + "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id "
                    + "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id "
                    + "LEFT JOIN pos_payment_tb p ON ot.order_id = p.order_id "
                    + "WHERE dot.status = 1 AND dot.order_code LIKE ?"
            );

            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, "%" + orderID + "%");
            int paramIndex = 3;

            rs = ps.executeQuery();

            while (rs.next()) {
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setCreateDate(rs.getTimestamp("created_date"));
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
                deliveryOrder.setStatusID(rs.getInt("status_id"));
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
            Log.error(e, "Get all duration faild");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "Get all duration faild");
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

        ArrayList<DeliveryOrder> deliveryOrders = new ArrayList<>();

        try {

            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }

            String sql = "SELECT mdo.order_code,mo.total_order_price,mdo.cod_amount,mdo.created_date,mdo.status_id FROM pos_main_delivery_order_tb AS mdo\n"
                    + "INNER JOIN pos_main_order_tb AS mo ON mo.delivery_order_id=mdo.delivery_id \n"
                    + "WHERE mdo.customer_id='" + customer_id + "' AND mdo.status=1";

            System.out.println(sql);
            ps = con.prepareStatement(sql);

            rst = ps.executeQuery();

            while (rst.next()) {
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setOrderCode(rst.getString("order_code"));
                deliveryOrder.setGrandTotalPrice(rst.getDouble("total_order_price"));
                deliveryOrder.setCod(rst.getDouble("cod_amount"));
                deliveryOrder.setDate(rst.getString("created_date"));
                deliveryOrder.setStatusType(rst.getInt("status_id"));

                deliveryOrders.add(deliveryOrder);
            }

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "get Delivery Orders By Customer faild");
        } finally {

            if (isLocalConnection && con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    Log.error(e, "get Delivery Orders By Customer faild");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    Log.error(e, "get Delivery Orders By Customer faild");
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception e) {
                    Log.error(e, "get Delivery Orders By Customer faild");
                }
            }
        }
        return deliveryOrders;
    }

    public void update(String delivery_id, int status_id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rst = null;
        boolean isLocalConnection = false;
        Connection con = null;

        try {

            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }

            System.out.println("delivery_id : " + delivery_id);

            System.err.println("Updated + " + delivery_id + " : " + status_id);
            ps = con.prepareStatement("UPDATE pos_main_delivery_order_tb SET status_id = '" + status_id + "' WHERE delivery_id='" + delivery_id + "'");
            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "update faild");
        } finally {

            if (isLocalConnection && con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    Log.error(e, "update faild");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    Log.error(e, "update faild");
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception e) {
                    Log.error(e, "update faild");
                }
            }

        }
    }

    public void updateWithOrderId(String orderId, int status_id) throws Exception {
        PreparedStatement ps = null;
        boolean isLocalConnection = false;
        Connection con = null;

        try {
            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }

            System.out.println("order_id : " + orderId);
            System.err.println("Updating status_id = " + status_id + " for order_id = " + orderId);

            String sql = "UPDATE pos_main_delivery_order_tb dot "
                    + "JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id "
                    + "SET dot.status_id = ? "
                    + "WHERE ot.order_id = ? ";

            ps = con.prepareStatement(sql);
            ps.setInt(1, status_id);
            ps.setString(2, orderId);
            ps.executeUpdate();
            System.out.println("updated :" + (ps.executeUpdate() > 0));

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Update failed");
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (Exception e) {
                Log.error(e, "Close failed");
            }
            if (isLocalConnection && con != null) try {
                con.close();
            } catch (Exception e) {
                Log.error(e, "Close failed");
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

            StringBuilder sql = new StringBuilder();
            if (paymentType == 1 || paymentType == 2) {
                sql.append("SELECT dot.delivery_id, dot.order_code, ct.customer_name, ct.address, dot.cod_amount, ")
                        .append("ct.phone_one, ct.phone_two, ot.sub_total_price, ot.delivery_fee, dot.status, dot.status_id, ")
                        .append("dot.is_return, ot.total_order_price, dot.remark, pt.payment_type_id, ot.is_print ")
                        .append("FROM pos_main_delivery_order_tb dot ")
                        .append("INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id ")
                        .append("INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id ")
                        .append("INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id ")
                        .append("WHERE dot.status = 1 AND DATE(dot.created_date) BETWEEN ? AND ? ")
                        .append("AND pt.payment_type_id = ?");
            } else {
                sql.append("SELECT SUM(dot.cod_amount) AS total_cod, SUM(ot.delivery_fee) AS total_delivery_fee, ")
                        .append("SUM(ot.total_order_price) AS total_amount ")
                        .append("FROM pos_main_delivery_order_tb dot ")
                        .append("INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id ")
                        .append("WHERE dot.status_id NOT IN (6, 7) AND DATE(dot.created_date) BETWEEN ? AND ?");
            }

            ps = con.prepareStatement(sql.toString());
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            if (paymentType == 1 || paymentType == 2) {
                ps.setInt(3, paymentType);
            }

            rst = ps.executeQuery();

            while (rst.next()) {
                DeliveryOrderAmounts deliveryOrder = new DeliveryOrderAmounts();
                if (paymentType == 1 || paymentType == 2) {
                    deliveryOrder.setTotalAmount(rst.getDouble("total_order_price"));
                    deliveryOrder.setTotalDeliveryCharge(rst.getDouble("delivery_fee"));
                    deliveryOrder.setTotalCod(rst.getDouble("cod_amount"));
                    // Add more fields as needed
                } else {
                    deliveryOrder.setTotalAmount(rst.getDouble("total_amount"));
                    deliveryOrder.setTotalDeliveryCharge(rst.getDouble("total_delivery_fee"));
                    deliveryOrder.setTotalCod(rst.getDouble("total_cod"));
                }
                deliveryOrdersAmounts.add(deliveryOrder);
            }

            rst.close();
            ps.close();

            // Calculate returns
            String sqlReturns = "SELECT SUM(ot.sub_total_price) AS total_returns "
                    + "FROM pos_main_delivery_order_tb dot "
                    + "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id "
                    + "WHERE dot.status_id = 6 AND DATE(dot.created_date) BETWEEN ? AND ?";
            ps = con.prepareStatement(sqlReturns);
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            rst = ps.executeQuery();
            if (rst.next()) {
                double totalReturns = rst.getDouble("total_returns");
                deliveryOrdersAmounts.forEach(order -> order.setTotalReturns(totalReturns));
            }

            String sqlDelivered = "SELECT SUM(ot.total_order_price) AS total_delivered "
                    + "FROM pos_main_delivery_order_tb dot "
                    + "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id "
                    + "WHERE dot.status_id = 5 AND DATE(dot.created_date) BETWEEN ? AND ?";
            ps = con.prepareStatement(sqlDelivered);
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            rst = ps.executeQuery();
            if (rst.next()) {
                double totalDelivered = rst.getDouble("total_delivered");
                deliveryOrdersAmounts.forEach(order -> order.setTotalDeliverds(totalDelivered));
            }

            rst.close();
            ps.close();

            // Calculate total COD (excluding Delivered and Returned)
            String sqlCOD = "SELECT SUM(cod_amount) AS total_cod "
                    + "FROM pos_main_delivery_order_tb "
                    + "WHERE status_id NOT IN (5, 6, 7) AND DATE(created_date) BETWEEN ? AND ?";
            ps = con.prepareStatement(sqlCOD);
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            rst = ps.executeQuery();
            if (rst.next()) {
                double totalCod = rst.getDouble("total_cod");
                deliveryOrdersAmounts.forEach(order -> order.setTotalCod(totalCod));
            }

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "get Calculation failed");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (isLocalConnection && con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Log.error(e, "Closing resources failed in getCalculation");
            }
        }

        return deliveryOrdersAmounts;
    }

     public boolean update(DeliveryOrder deliveryOrderDto, Integer orderId, String delivery_id, boolean isOrder) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        boolean success = false;

        try {
            connection = DBCon.getDatabaseConnection();

            if (!deliveryOrderDto.getOrderCode().isEmpty()) {
                if (isOrderCodeExists(connection, deliveryOrderDto.getOrderCode(), delivery_id)) {
                    return false;
                }
            }

            connection.setAutoCommit(false);

            if (changeItemStatusByOrderID(connection, deliveryOrderDto, orderId)
                    && updateDeliveryDetails(connection, deliveryOrderDto, delivery_id)
                    && updateOrder(connection, deliveryOrderDto, orderId, delivery_id, isOrder)
                    && updateCustomer(connection, deliveryOrderDto)
                    && updateOrderDetails(connection, deliveryOrderDto, orderId)) {

                connection.commit();
                success = true;
            } else {
                connection.rollback();
            }
        } catch (SQLException | ClassNotFoundException e) {
            if (connection != null) {
                connection.rollback();
                Log.info(e, "connection rollback");
            }
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "update failed");
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

        return success;
    }

    private boolean isOrderCodeExists(Connection connection, String orderCode, String currentDeliveryId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM pos_main_delivery_order_tb WHERE order_code = ? AND delivery_id != ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, orderCode);
            ps.setString(2, currentDeliveryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    private boolean changeItemStatusByOrderID(Connection connection, DeliveryOrder deliveryOrderDto, Integer orderId) throws SQLException, ClassNotFoundException {
        System.out.println("Start changeItemStatusByOrderID");
        String updateOrderDetailsStatusSQL = "UPDATE pos_main_order_details_tb SET status = 0 WHERE order_id = ?";

        PreparedStatement orderDetailsStatusStatement = null;

        orderDetailsStatusStatement = connection.prepareStatement(updateOrderDetailsStatusSQL);
        orderDetailsStatusStatement.setInt(1, orderId);

        return orderDetailsStatusStatement.executeUpdate() > 0;
    }

    private boolean updateDeliveryDetails(Connection connection, DeliveryOrder deliveryOrderDto, String delivery_id) throws SQLException, ClassNotFoundException {
        System.out.println("Start updateDeliveryDetails");

        System.out.println("deliveryOrderDto.getOrderCode() : " + deliveryOrderDto.getOrderCode());
        System.out.println("deliveryOrderDto.getOrderId() : " + deliveryOrderDto.getOrderId());
        System.out.println("delivery_id : " + delivery_id);

        String updateOrderSQL = "UPDATE pos_main_delivery_order_tb SET "
                + "customer_id = ?, cod_amount = ?, weight = ?, remark = ?, "
                + "status_id = ?, is_free_delivery = ?, is_return = ?, user_id = ?, order_code = ?, order_type = ? "
                + "WHERE delivery_id = ?";

        PreparedStatement orderStatement = null;

        orderStatement = connection.prepareStatement(updateOrderSQL);
        orderStatement.setObject(1, deliveryOrderDto.getCustomerId());
        orderStatement.setDouble(2, deliveryOrderDto.getCod());
        orderStatement.setString(3, deliveryOrderDto.getWeight());
        orderStatement.setString(4, deliveryOrderDto.getRemark());
        orderStatement.setInt(5, deliveryOrderDto.getStatus());
        orderStatement.setInt(6, deliveryOrderDto.getFreeShip());
        orderStatement.setInt(7, 0);
        orderStatement.setInt(8, 1);
        orderStatement.setString(9, deliveryOrderDto.getOrderCode());
        orderStatement.setString(10, deliveryOrderDto.getOrderType());
        orderStatement.setString(11, delivery_id);

        return orderStatement.executeUpdate() > 0;
    }

    private boolean updateOrder(Connection connection, DeliveryOrder deliveryOrderDto, Integer orderId, String delivery_id, boolean isOrder) throws ClassNotFoundException, SQLException {
        System.out.println("Start updateOrder");
        if (isOrder) {
            String updateMainOrderSQL = "UPDATE pos_main_order_tb SET "
                    + "customer_id = ?, sub_total_price = ?, "
                    + "delivery_fee = ?, total_order_price = ?, table_id = ?, "
                    + "remark = ?, edited_by = ?, status = ?, paid_amount = ?, bill_no = ?, edited_Date = ?, discount_id = ? "
                    + "WHERE delivery_order_id = ?";

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
            mainOrderStatement.setString(9, deliveryOrderDto.getPaidAmount() + "");
            mainOrderStatement.setString(10, deliveryOrderDto.getOrderCode());
            mainOrderStatement.setTimestamp(11, deliveryOrderDto.getEditedDate());
            mainOrderStatement.setInt(12, deliveryOrderDto.getDiscountId());
            mainOrderStatement.setString(13, delivery_id);

            return mainOrderStatement.executeUpdate() > 0;
        } else {
            String updateMainOrderSQL = "UPDATE pos_main_order_tb SET "
                    + "customer_id = ?, sub_total_price = ?, "
                    + "delivery_fee = ?, total_order_price = ?, table_id = ?, "
                    + "remark = ?, edited_by = ?, status = ?, paid_amount = ?, bill_no = ?, edited_Date = ?, discount_id = ? "
                    + "WHERE delivery_order_id = ?";

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
            mainOrderStatement.setString(9, deliveryOrderDto.getPaidAmount() + "");
            mainOrderStatement.setString(10, deliveryOrderDto.getOrderCode());
            mainOrderStatement.setTimestamp(11, deliveryOrderDto.getEditedDate());
            mainOrderStatement.setString(13, delivery_id);
            mainOrderStatement.setInt(12, 0);

            return mainOrderStatement.executeUpdate() > 0;
        }

    }

    private boolean updateCustomer(Connection connection, DeliveryOrder deliveryOrderDto) throws ClassNotFoundException, SQLException {
        System.out.println("Start updateCustomer");
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
        System.out.println("Start updateOrderDetails");
        String insertOrderDetailsSQL = "INSERT INTO pos_main_order_details_tb(order_id,item_id,quantity,per_item_price,total_item_price,status,user_id, total_discount_price) VALUES(?,?,?,?,?,?,?,?)";

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
            insertOrderDetailsStatement.setDouble(8, detail.getTotalDiscountPrice());

            if (insertOrderDetailsStatement.executeUpdate() < 0) {
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

            String sql = "SELECT dot.delivery_id, dot.order_code, ct.customer_name, ct.address, dot.cod_amount, ct.phone_one, ct.phone_two, "
                    + "ot.sub_total_price, ot.delivery_fee, dot.status, dot.status_id, dot.is_return, ot.total_order_price, dot.remark, "
                    + "pt.payment_type_id, ot.is_print "
                    + "FROM pos_main_delivery_order_tb dot "
                    + "INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id "
                    + "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id "
                    + "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id "
                    + "WHERE dot.order_code = ?";

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
            Log.error(e, "get Order By Id faild");
        } finally {
            if (isLocalConnection && con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                    Log.error(e, "get Order By Id faild");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                    Log.error(e, "get Order By Id faild");
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception e) {
                    Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                    Log.error(e, "get Order By Id faild");
                }
            }
        }
        return deliveryOrder;
    }

    public ArrayList<DeliveryOrder> getAllDuration(String fromDate, String toDate, int paymentType) {
        return null;
    }

    public ArrayList<WrapperOrder> getWrappingOrder(Date fromDate, Date toDate, int selectedIndex) throws ClassNotFoundException {
        ArrayList<WrapperOrder> list = new ArrayList<>();
        String sql = "SELECT dot.order_code, dot.delivery_id, ct.customer_number, ct.customer_name, ct.address, dot.cod_amount, "
                + "ct.phone_one, ct.phone_two, dot.weight, dot.created_date "
                + "FROM pos_main_delivery_order_tb dot "
                + "INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id "
                + "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id "
                + "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id "
                + "LEFT JOIN pos_payment_tb p ON ot.order_id = p.order_id "
                + "WHERE dot.status_id = ? AND Date(dot.created_date) BETWEEN ? AND ?";

        try (Connection con = DBCon.getDatabaseConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            if (selectedIndex == 3) {
                pst.setInt(1, selectedIndex);
            } else if (selectedIndex == 7) {
                pst.setInt(1, selectedIndex);
            }
            pst.setDate(2, new java.sql.Date(fromDate.getTime()));
            pst.setDate(3, new java.sql.Date(toDate.getTime()));

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                WrapperOrder order = new WrapperOrder();
                order.setOrderCode( rs.getString("order_code"));
                order.setDeliveryId(rs.getString("delivery_id"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setCustomerId( rs.getString("customer_number"));
                order.setAddress(rs.getString("address"));
                order.setCodAmount(rs.getDouble("cod_amount"));
                order.setPhoneOne( rs.getString("phone_one"));
                order.setPhoneTwo(rs.getString("phone_two"));
                order.setWeight(rs.getDouble("weight"));
                order.setCreatedDate(rs.getString("created_date"));
                list.add(order);
            }

        } catch (SQLException e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "get Wrapping Order faild");
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<WrapperOrder> getDaliyOutOfDeliveryOrder(Date fromDate, Date toDate) throws ClassNotFoundException {
        ArrayList<WrapperOrder> list = new ArrayList<>();
        String sql = "SELECT dot.order_code, dot.delivery_id, ct.customer_name, ct.address, dot.cod_amount, "
                + "ct.phone_one, ct.phone_two, dot.weight "
                + "FROM pos_main_delivery_order_tb dot "
                + "INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id "
                + "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id "
                + "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id "
                + "LEFT JOIN pos_payment_tb p ON ot.order_id = p.order_id "
                + "WHERE dot.status_id = 4 AND Date(dot.created_date) BETWEEN ? AND ?";

        try (Connection con = DBCon.getDatabaseConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

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
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "get Daliy Out Of Delivery Order faild");
            e.printStackTrace();
        }

        return list;
    }

    public String getOrderCount(String fromDate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rst = null;
        String totalOrders = "0";

        try {
            con = DBCon.getDatabaseConnection();
            String sql = "SELECT COUNT(*) AS total_orders FROM ( "
                    + "    SELECT d.order_code "
                    + "    FROM pos_main_delivery_order_tb d "
                    + "    JOIN pos_main_customer_tb c ON d.customer_id = c.customer_id "
                    + "    JOIN pos_main_order_tb o ON d.delivery_id = o.delivery_order_id "
                    + "    JOIN ( "
                    + "        SELECT od.order_id, i.item_id, i.item_name, SUM(od.quantity) AS total_quantity "
                    + "        FROM pos_main_order_details_tb od "
                    + "        JOIN pos_main_item_tb i ON od.item_id = i.item_id "
                    + "        GROUP BY od.order_id, i.item_id, i.item_name "
                    + "    ) AS item ON o.order_id = item.order_id "
                    + "    WHERE DATE(d.created_date) = ? "
                    + "    GROUP BY d.delivery_id "
                    + ") AS subquery;";

            ps = con.prepareStatement(sql);
            ps.setString(1, fromDate); // e.g., '2025-06-16'
            rst = ps.executeQuery();

            if (rst.next()) {
                totalOrders = rst.getString("total_orders");
            }

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "get Order Count faild");
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
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "get Order Count faild");
            }
        }

        return totalOrders;
    }

    public ArrayList<DeliveryOrder> getAllOrdersByCustomerCode(String customerCode) throws SQLException {
        ArrayList<DeliveryOrder> deliveryOrdersList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            System.out.println("Fetching orders for customerCode: " + customerCode);

            // Get connection
            conn = DBConnection.getInstance().getConnection();

            if (conn == null || conn.isClosed()) {
                throw new SQLException("Database connection is null or already closed.");
            }

            String sql = "SELECT dot.created_date, dot.delivery_id, dot.order_code, ct.customer_name, ct.address, dot.cod_amount, "
                    + "ct.phone_one, ct.phone_two, ot.sub_total_price, ot.delivery_fee, dot.status, "
                    + "dot.status_id, dot.is_return, ot.total_order_price, dot.remark, pt.payment_type_id, ot.is_print, "
                    + "p.payment_id, p.cod AS cod_payment, p.total_amount, p.payment_status "
                    + "FROM pos_main_delivery_order_tb dot "
                    + "INNER JOIN pos_main_customer_tb ct ON dot.customer_id = ct.customer_id "
                    + "INNER JOIN pos_main_order_tb ot ON dot.delivery_id = ot.delivery_order_id "
                    + "INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id = pt.payment_type_id "
                    + "LEFT JOIN pos_payment_tb p ON ot.order_id = p.order_id "
                    + "WHERE dot.status = 1 AND ct.customer_number LIKE ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + customerCode + "%");

            rs = ps.executeQuery();
            int rowCount = 0;

            while (rs.next()) {
                try {
                    DeliveryOrder deliveryOrder = new DeliveryOrder();
                    deliveryOrder.setCreateDate(rs.getTimestamp("created_date"));
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
                    deliveryOrder.setStatusID(rs.getInt("status_id"));
                    deliveryOrder.setIsReturn(rs.getInt("is_return"));
                    deliveryOrder.setGrandTotalPrice(rs.getDouble("total_order_price"));
                    deliveryOrder.setRemark(rs.getString("remark"));
                    deliveryOrder.setPaymentTypeId(rs.getInt("payment_type_id"));
                    deliveryOrder.setIsPrint(rs.getInt("is_print"));

                    // Payment table
                    deliveryOrder.setPaymentId(rs.getInt("payment_id"));
                    deliveryOrder.setCodPayment(rs.getDouble("cod_payment"));
                    deliveryOrder.setTotalAmount(rs.getDouble("total_amount"));
                    deliveryOrder.setPaymentStatus(rs.getInt("payment_status"));

                    deliveryOrdersList.add(deliveryOrder);
                    rowCount++;
                } catch (Exception rowEx) {
                    Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, "SQL Error", rowEx);
                    Log.error(rowEx, "SQL exception in resultset while getAllOrdersByCustomerCode");
                }
            }

            System.out.println("Total orders found: " + rowCount);

        } catch (SQLException e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, "SQL Error", e);
            Log.error(e, "SQL exception in getAllOrdersByCustomerCode");
            throw e; // Re-throw for upstream handling
        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, "General Error", e);
            Log.error(e, "Unexpected error in getAllOrdersByCustomerCode");
            e.printStackTrace();
        } finally {

        }

        return deliveryOrdersList;
    }

    public boolean isLastOrderDelivered(String phoneNumber) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBCon.getDatabaseConnection();

            // Check if customer exists
            String checkCustomerSQL = "SELECT customer_id FROM pos_main_customer_tb WHERE phone_one = ?";
            ps = con.prepareStatement(checkCustomerSQL);
            ps.setString(1, phoneNumber);
            rs = ps.executeQuery();

            if (!rs.next()) {
                return true;
            }

            int customerId = rs.getInt("customer_id");
            rs.close();
            ps.close();

            String checkDeliverySQL = "SELECT COUNT(*) AS undelivered_count "
                    + "FROM pos_main_delivery_order_tb "
                    + "WHERE customer_id = ? AND status_id != 5";
            ps = con.prepareStatement(checkDeliverySQL);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("undelivered_count");
                return count == 0;
            }

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Check last order delivered status failed");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "Resource closing failed");
            }
        }

        return true; // default to allow order in ambiguous cases (e.g., error or no data)
    }

    public void addDeliveredDate(String delivery_id, java.util.Date today) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBCon.getDatabaseConnection();

            String sql = "UPDATE pos_main_delivery_order_tb SET delivered_date = ? WHERE delivery_id = ?";
            ps = con.prepareStatement(sql);
            ps.setTimestamp(1, new java.sql.Timestamp(today.getTime())); // use Timestamp for precise datetime
            ps.setString(2, delivery_id);

            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Failed to update delivered_date");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "Resource closing failed in addDeliveredDate");
            }
        }
    }

    public void addDeliveredDateWithOrderID(String orderID, Date now) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBCon.getDatabaseConnection();

            String sql = "UPDATE pos_main_delivery_order_tb d "
                    + "JOIN pos_main_order_tb o ON d.delivery_id = o.delivery_order_id "
                    + "SET d.delivered_date = ? "
                    + "WHERE o.order_id = ?";

            ps = con.prepareStatement(sql);
            ps.setTimestamp(1, new java.sql.Timestamp(now.getTime())); // convert Date to Timestamp
            ps.setString(2, orderID);

            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Failed to update delivered_date using order_id");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "Resource closing failed in addDeliveredDateWithOrderID");
            }
        }
    }

    public List<String> getOrders(String fromDate, String toDate, String selectedItem) {
        List<String> subTotalPrices = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBCon.getDatabaseConnection();

            StringBuilder sql = new StringBuilder("SELECT ot.sub_total_price "
                    + "FROM pos_main_delivery_order_tb AS dot "
                    + "INNER JOIN pos_main_order_tb AS ot ON dot.delivery_id = ot.delivery_order_id "
                    + "INNER JOIN pos_main_customer_tb AS ct ON dot.customer_id = ct.customer_id "
                    + "WHERE dot.status = 1 "
                    + "AND dot.status_id = 5 "
                    + "AND DATE(dot.delivered_date) BETWEEN ? AND ?"
            );

            if (!selectedItem.equals("any")) {
                sql.append(" AND order_type = ?");

            }
            ps = con.prepareStatement(sql.toString());
            ps.setString(1, fromDate); // e.g., "2025-07-01"
            ps.setString(2, toDate);   // e.g., "2025-07-31"
            if (!selectedItem.equals("any")) {
               ps.setString(3, selectedItem);

            }
            rs = ps.executeQuery();

            while (rs.next()) {
                subTotalPrices.add(rs.getString("sub_total_price"));
            }

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "getOrders query failed");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "getOrders resource closing failed");
            }
        }

        return subTotalPrices;
    }

    public void updateOrderRemark(String orderID, String text) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBCon.getDatabaseConnection();

            String sql = "UPDATE pos_main_delivery_order_tb d "
                    + "JOIN pos_main_order_tb o ON d.delivery_id = o.delivery_order_id "
                    + "SET d.remark = ? "
                    + "WHERE o.order_id = ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, text);
            ps.setString(2, orderID);

            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Failed to update remark using order_id");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "Resource closing failed in updateOrderRemark");
            }
        }
    }

    public String getRemark(String orderID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String remark = null;

        try {
            con = DBCon.getDatabaseConnection();

            String sql = "SELECT d.remark "
                    + "FROM pos_main_delivery_order_tb d "
                    + "JOIN pos_main_order_tb o ON d.delivery_id = o.delivery_order_id "
                    + "WHERE o.order_id = ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, orderID);

            rs = ps.executeQuery();

            if (rs.next()) {
                remark = rs.getString("remark");
            }

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Failed to get remark using order_id");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "Resource closing failed in getRemark");
            }
        }

        return remark;
    }

    public String getRemarkWithDeliveryId(String delivery_id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String remark = null;

        try {
            con = DBCon.getDatabaseConnection();

            String sql = "SELECT remark FROM pos_main_delivery_order_tb WHERE delivery_id = ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, delivery_id);

            rs = ps.executeQuery();

            if (rs.next()) {
                remark = rs.getString("remark");
            }

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Failed to get remark using delivery_id");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "Resource closing failed in getRemarkWithDeliveryId");
            }
        }

        return remark;
    }

    public void updateOrderRemarkWithDeliveryId(String delivery_id, String text) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBCon.getDatabaseConnection();

            String sql = "UPDATE pos_main_delivery_order_tb SET remark = ? WHERE delivery_id = ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, text);
            ps.setString(2, delivery_id);

            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e, "Failed to update remark using delivery_id");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                Log.error(e, "Resource closing failed in updateOrderRemarkWithDeliveryId");
            }
        }
    }

    @Override
    public Double getSpecificWaight(Integer id) {
        String sql = "SELECT weight FROM pos_main_delivery_order_tb WHERE delivery_id = ?";

        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rst = pstm.executeQuery();
            if (rst.next()) {
                return rst.getDouble("weight");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }

    @Override
    public String getOrderType(String deliveryID) {
        String sql = "SELECT order_type FROM pos_main_delivery_order_tb WHERE delivery_id = ?";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(deliveryID));
            ResultSet rst = pstm.executeQuery();
            if (rst.next()) {
                return rst.getString("order_type");
            }
        } catch (RuntimeException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "Order Type retrieve failed!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getOrderByDeliveryId(String delivery_id) {
        String sql = "SELECT * FROM pos_main_delivery_order_tb WHERE delivery_id = ?";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(delivery_id));
            ResultSet rst = pstm.executeQuery();
            if (rst.next()) {
                return rst.getString("website_order_id");
            }
        } catch (RuntimeException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "Order Type retrieve failed!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public DeliveryOrder getOrderCodeByDelioveryId(String delivery_id) {
        String sql = "SELECT order_code,website_order_id FROM pos_main_delivery_order_tb WHERE delivery_id = ?";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(delivery_id));
            ResultSet rst = pstm.executeQuery();
            if (rst.next()) {
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setOrderCode(rst.getString("order_code"));
                deliveryOrder.setWebsiteOrderId(rst.getString("website_order_id"));
                return deliveryOrder;
            }
        } catch (RuntimeException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "Order Type retrieve failed!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer getLastDeliveryId() {
        String sql = "SELECT delivery_id FROM  pos_main_delivery_order_tb order by delivery_id desc LIMIT 1";
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
            if (rst.next()) {
                return rst.getInt("delivery_id");
            }
        } catch (Exception ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, "Delivery id retrieve failed!");
        }
        return 0;
    }

    @Override
    public String getOrderId(String order_ID) {
        try {
            String sql = "SELECT o.order_id "
                    + "FROM pos_main_delivery_order_tb d "
                    + "JOIN pos_main_order_tb o ON d.delivery_id = o.delivery_order_id "
                    + "WHERE d.delivery_id = ?";
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1, order_ID);
            ResultSet rst = pstm.executeQuery();
            if (rst.next()) {
                return String.valueOf(rst.getInt("order_id"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            Log.error(ex, ex.getMessage());
        }
        return null;
    }

}
