/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import net.unical.pos.repository.custom.DeliveryOrderRepositoryCustom;

/**
 *
 * @author Sanjuka
 */
public class DeliveryOrderRepositoryImpl implements DeliveryOrderRepositoryCustom{

    @Override
    public Integer save(DeliveryOrder deliveryOrder) throws Exception {
        PreparedStatement ps = null;
        ResultSet rst = null;
        boolean isLocalConnection = false;
        Connection con = null;
        
        Integer customerId=null;
        Integer deliveryId=null;
        Integer orderId=null;
        
        try {
            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }
            
            con.setAutoCommit(false);
            
            System.out.println("Customer Id : "+deliveryOrder.getCustomerId());
            if(deliveryOrder.getCustomerId()!=null){
                
                System.out.println("Customer Updated");
//              Update Customer
                ps=con.prepareStatement("UPDATE pos_main_customer_tb SET customer_name = '"+deliveryOrder.getCustomerName()+"', address='"+deliveryOrder.getAddress()+"', phone_one='"+deliveryOrder.getPhoneOne()+"',"
                        + "phone_two='"+deliveryOrder.getPhoneTwo()+"' WHERE customer_id='"+deliveryOrder.getCustomerId()+"'");
                ps.executeUpdate();
                
//              Add Delivery
                ps=con.prepareStatement("INSERT INTO pos_main_delivery_order_tb(customer_id,order_code,cod_amount,weight,remark,status,is_free_delivery,user_id) VALUES(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, deliveryOrder.getCustomerId());
                ps.setString(2, deliveryOrder.getOrderCode());
                ps.setDouble(3, deliveryOrder.getCod());
                ps.setString(4, deliveryOrder.getWeight());
                ps.setString(5, deliveryOrder.getRemark());
                ps.setInt(6, 1);
                ps.setInt(7, deliveryOrder.getFreeShip());
                ps.setInt(8, 1);
                ps.executeUpdate();
                rst=ps.getGeneratedKeys();
                if (rst.next()) {
                    // returen value from previous action
                    deliveryId = rst.getInt(1);
                }
                
//              Add Order
                ps=con.prepareStatement("INSERT INTO pos_main_order_tb(customer_id,delivery_order_id,bill_no,sub_total_price,delivery_fee,total_order_price,payment_type_id,remark,user_id,status,visible) VALUES(?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
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
                ps.executeUpdate();
                rst=ps.getGeneratedKeys();
                if (rst.next()) {
                    // returen value from previous action
                    orderId = rst.getInt(1);
                }
                
//                Add Order Details
                ArrayList<OrderDetailsDto> orderDetailsDtos=deliveryOrder.getOrderDetailsDtos();
                for(OrderDetailsDto detailsDto:orderDetailsDtos){
                    ps=con.prepareStatement("INSERT INTO pos_main_order_details_tb(order_id,item_id,quantity,per_item_price,total_item_price,status,user_id) VALUES(?,?,?,?,?,?,?)");
                    ps.setInt(1, orderId);
                    ps.setInt(2, detailsDto.getItemId());
                    ps.setDouble(3, detailsDto.getQty());
                    ps.setDouble(4, detailsDto.getPerItemPrice());
                    ps.setDouble(5, detailsDto.getTotalItemPrice());
                    ps.setInt(6, 1);
                    ps.setInt(7, 1);
                    ps.executeUpdate();

//                    ps=con.prepareStatement("UPDATE pos_inv_stock_tb SET quantity = quantity-'"+detailsDto.getQty()+"'WHERE item_id='"+detailsDto.getItemId()+"'");
    //                System.out.println("UPDATE pos_inv_stock_tb SET quantity = quantity-'"+detailsDto.getQty()+"'WHERE item_id='"+detailsDto.getItemId()+"'");
//                    ps.executeUpdate();
                }
            }else{
                
//                Add Customer
                ps=con.prepareStatement("INSERT INTO pos_main_customer_tb(customer_name,address,phone_one,phone_two,status,user_id,visible,customer_number) VALUES(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, deliveryOrder.getCustomerName());
                ps.setString(2, deliveryOrder.getAddress());
                ps.setString(3, deliveryOrder.getPhoneOne());
                ps.setString(4, deliveryOrder.getPhoneTwo());
                ps.setInt(5, 1);
                ps.setInt(6, 1);
                ps.setInt(7, 1);
                ps.setString(8, deliveryOrder.getCustomerNumber());
                ps.executeUpdate();
                rst=ps.getGeneratedKeys();
                if (rst.next()) {
                    // returen value from previous action
                    customerId = rst.getInt(1);
                }
                
                
    //            Add Delivery
                ps=con.prepareStatement("INSERT INTO pos_main_delivery_order_tb(customer_id,order_code,cod_amount,weight,remark,status,is_free_delivery,user_id) VALUES(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, customerId);
                ps.setString(2, deliveryOrder.getOrderCode());
                ps.setDouble(3, deliveryOrder.getCod());
                ps.setString(4, deliveryOrder.getWeight());
                ps.setString(5, deliveryOrder.getRemark());
                ps.setInt(6, 1);
                ps.setInt(7, deliveryOrder.getFreeShip());
                ps.setInt(8, 1);
                ps.executeUpdate();
                rst=ps.getGeneratedKeys();
                if (rst.next()) {
                    // returen value from previous action
                    deliveryId = rst.getInt(1);
                }

    //            Add Order
                ps=con.prepareStatement("INSERT INTO pos_main_order_tb(customer_id,delivery_order_id,bill_no,sub_total_price,delivery_fee,total_order_price,payment_type_id,remark,user_id,status,visible) VALUES(?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
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
                ps.executeUpdate();
                rst=ps.getGeneratedKeys();
                if (rst.next()) {
                    // returen value from previous action
                    orderId = rst.getInt(1);
                }

    //            Add Order Details
                ArrayList<OrderDetailsDto> orderDetailsDtos=deliveryOrder.getOrderDetailsDtos();
                for(OrderDetailsDto detailsDto:orderDetailsDtos){
                    ps=con.prepareStatement("INSERT INTO pos_main_order_details_tb(order_id,item_id,quantity,per_item_price,total_item_price,status,user_id) VALUES(?,?,?,?,?,?,?)");
                    ps.setInt(1, orderId);
                    ps.setInt(2, detailsDto.getItemId());
                    ps.setDouble(3, detailsDto.getQty());
                    ps.setDouble(4, detailsDto.getPerItemPrice());
                    ps.setDouble(5, detailsDto.getTotalItemPrice());
                    ps.setInt(6, 1);
                    ps.setInt(7, 1);
                    ps.executeUpdate();

//                    ps=con.prepareStatement("UPDATE pos_inv_stock_tb SET quantity = quantity-'"+detailsDto.getQty()+"'WHERE item_id='"+detailsDto.getItemId()+"'");
    //                System.out.println("UPDATE pos_inv_stock_tb SET quantity = quantity-'"+detailsDto.getQty()+"'WHERE item_id='"+detailsDto.getItemId()+"'");
//                    ps.executeUpdate();
                }
            }
            
            con.commit();
            
        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            con.rollback();
            return null;
        }finally {

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
    public ArrayList<DeliveryOrder> getAllDuration(String fromDate, String toDate, Integer paymentType) {
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
"ot.sub_total_price,ot.delivery_fee,dot.status,dot.status_id,dot.is_return,ot.total_order_price,dot.remark,pt.payment_type_id,ot.is_print \n" +
"FROM pos_main_delivery_order_tb dot\n" +
"INNER JOIN pos_main_customer_tb ct ON dot.customer_id=ct.customer_id\n" +
"INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n" +
"INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id=pt.payment_type_id\n" +
"WHERE dot.status=1 AND DATE(dot.created_date) BETWEEN '"+fromDate+"' AND '"+toDate+"' AND pt.payment_type_id='"+paymentType+"'";
            }else if(paymentType==2){
                sql="SELECT dot.delivery_id,dot.order_code,ct.customer_name,ct.address,dot.cod_amount,ct.phone_one,ct.phone_two,\n" +
"ot.sub_total_price,ot.delivery_fee,dot.status,dot.status_id,dot.is_return,ot.total_order_price,dot.remark,pt.payment_type_id,ot.is_print \n" +
"FROM pos_main_delivery_order_tb dot\n" +
"INNER JOIN pos_main_customer_tb ct ON dot.customer_id=ct.customer_id\n" +
"INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n" +
"INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id=pt.payment_type_id\n" +
"WHERE dot.status=1 AND DATE(dot.created_date) BETWEEN '"+fromDate+"' AND '"+toDate+"' AND pt.payment_type_id='"+paymentType+"'";
            }else{
                sql="SELECT dot.delivery_id,dot.order_code,ct.customer_name,ct.address,dot.cod_amount,ct.phone_one,ct.phone_two,\n" +
"ot.sub_total_price,ot.delivery_fee,dot.status,dot.status_id,dot.is_return,ot.total_order_price,dot.remark,pt.payment_type_id,ot.is_print \n" +
"FROM pos_main_delivery_order_tb dot\n" +
"INNER JOIN pos_main_customer_tb ct ON dot.customer_id=ct.customer_id\n" +
"INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n" +
"INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id=pt.payment_type_id\n" +
"WHERE dot.status=1 AND DATE(dot.created_date) BETWEEN '"+fromDate+"' AND '"+toDate+"'";
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
                        deliveryOrder.setStatusType(rst.getInt("status_id"));
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

    public void update(Integer orderCode, int status_id) throws Exception {
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
        
        ArrayList<DeliveryOrderAmounts> deliveryOrdersAmounts=new ArrayList<>();
        
        try {
            
            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }
            
            String sql="";
            
//            System.out.println("Payment Type : "+paymentType);
            if(paymentType==1){
                sql="SELECT dot.delivery_id,dot.order_code,ct.customer_name,ct.address,dot.cod_amount,ct.phone_one,ct.phone_two,\n" +
"ot.sub_total_price,ot.delivery_fee,dot.status,dot.status_id,dot.is_return,ot.total_order_price,dot.remark,pt.payment_type_id,ot.is_print \n" +
"FROM pos_main_delivery_order_tb dot\n" +
"INNER JOIN pos_main_customer_tb ct ON dot.customer_id=ct.customer_id\n" +
"INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n" +
"INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id=pt.payment_type_id\n" +
"WHERE dot.status=1 AND DATE(dot.created_date) BETWEEN '"+fromDate+"' AND '"+toDate+"' AND pt.payment_type_id='"+paymentType+"'";
            }else if(paymentType==2){
                sql="SELECT dot.delivery_id,dot.order_code,ct.customer_name,ct.address,dot.cod_amount,ct.phone_one,ct.phone_two,\n" +
"ot.sub_total_price,ot.delivery_fee,dot.status,dot.status_id,dot.is_return,ot.total_order_price,dot.remark,pt.payment_type_id,ot.is_print \n" +
"FROM pos_main_delivery_order_tb dot\n" +
"INNER JOIN pos_main_customer_tb ct ON dot.customer_id=ct.customer_id\n" +
"INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n" +
"INNER JOIN pos_main_payment_types_tb pt ON ot.payment_type_id=pt.payment_type_id\n" +
"WHERE dot.status=1 AND DATE(dot.created_date) BETWEEN '"+fromDate+"' AND '"+toDate+"' AND pt.payment_type_id='"+paymentType+"'";
            }else{
                sql="SELECT SUM(dot.cod_amount) AS total_cod,SUM(ot.delivery_fee) AS total_delivery_fee,SUM(ot.total_order_price) AS total_amount FROM pos_main_delivery_order_tb dot\n" +
"INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n" +
"WHERE dot.status_id NOT IN(6) AND DATE(dot.created_date) BETWEEN '"+fromDate+"' AND '"+toDate+"'";
            }
            
//            System.out.println(sql);
            ps=con.prepareStatement(sql);
            
            rst=ps.executeQuery();
            
            DeliveryOrderAmounts deliveryOrder=new DeliveryOrderAmounts();
            while(rst.next()){
                
                        deliveryOrder.setTotalAmount(rst.getDouble("total_amount"));
                        deliveryOrder.setTotalDeliveryCharge(rst.getDouble("total_delivery_fee"));
                        
                        deliveryOrdersAmounts.add(deliveryOrder);
            }
            String sql2="SELECT SUM(ot.sub_total_price) AS total_returns FROM pos_main_delivery_order_tb dot\n" +
"INNER JOIN pos_main_order_tb ot ON dot.delivery_id=ot.delivery_order_id\n" +
"WHERE dot.status_id=5 AND DATE(dot.created_date) BETWEEN '"+fromDate+"' AND '"+toDate+"'";
            
            ps=con.prepareStatement(sql2);
            rst=ps.executeQuery();
            while(rst.next()){
//                DeliveryOrderAmounts deliveryOrder=new DeliveryOrderAmounts();
                        deliveryOrder.setTotalReturns(rst.getDouble("total_returns"));
                        System.out.println(rst.getDouble("total_returns"));
                        deliveryOrdersAmounts.add(deliveryOrder);
            }
            
            ;
            String sql3="SELECT SUM(cod_amount) AS total_cod FROM pos_main_delivery_order_tb\n" +
"WHERE status_id NOT IN (5,6) AND DATE(created_date) BETWEEN '"+fromDate+"' AND '"+toDate+"'";
            
            ps=con.prepareStatement(sql3);
            rst=ps.executeQuery();
            System.out.println(ps);
            while(rst.next()){
                        deliveryOrder.setTotalCod(rst.getDouble("total_cod"));
                        System.out.println(rst.getDouble("total_cod"));
                        deliveryOrdersAmounts.add(deliveryOrder);
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
       return deliveryOrdersAmounts;
        
    }
}
