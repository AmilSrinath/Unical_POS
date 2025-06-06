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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dto.MainOrderDto;
import net.unical.pos.dto.OrderDetailsDto;
import net.unical.pos.dto.OrderDto;
import net.unical.pos.model.OrderModel;
import net.unical.pos.model.PosMainOrder;
import net.unical.pos.repository.custom.MainOrderRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author HP
 */
public class MainOrderRepositoryImpl implements MainOrderRepositoryCustom {

    Integer mainOrderId=0;
//    @Override
//    public Integer mainOrderSave(PosMainOrder mainOrder) {
//        try {
//            return Statement.executeUpdate("INSERT into pos_main_order_tb values (?,?,?,?,?,?,?,?,?,?,)",
//                    0,
//                    mainOrder.getBillNo(),
//                    mainOrder.getNetAmount(),
//                    mainOrder.getDiscountPrice(),
//                    mainOrder.getDiscountPrasentage(),
//                    mainOrder.getTotalPrice(),
//                    mainOrder.getPaymentTypeId(),
//                    mainOrder.getStatus(),
//                    mainOrder.getUserId(),
//                    mainOrder.getVisible());
//        } catch (Exception ex) {
//            Logger.getLogger(MainOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
//            return 0;
//        }
//    }

//    @Override
//    public PosMainOrder findOne(Integer orderId) {
//        try {
//            ResultSet resultSet = Statement.executeQuery("SELECT * FROM pos_main_order_tb WHERE id = " + orderId);
//            PosMainOrder mainOrder = new PosMainOrder();
//            if (resultSet.next()) {
//                mainOrder = new PosMainOrder(resultSet.getInt("order_id"), resultSet.getString("bill_no"), resultSet.getDouble("net_amount"),
//                        resultSet.getDouble("discount_price"),
//                        resultSet.getDouble("discount_prasentage"),
//                        resultSet.getDouble("total_price"),
//                        resultSet.getInt("payment_type_id"),
//                        resultSet.getInt("status"),
//                        resultSet.getInt("user_id"),
//                        resultSet.getInt("visible")
//                );
//            }
//            return mainOrder;
//        } catch (Exception ex) {
//            Logger.getLogger(MainOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }

//    @Override
//    public Integer mainOrderUpdate(Integer paymentTypeId, Integer orderId) {
//        try {
//            return Statement.executeUpdate("UPDATE pos_main_order_tb  SET payment_type_id = " + orderId + " WHERE id = " + paymentTypeId);
//        } catch (Exception ex) {
//            Logger.getLogger(MainOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
//            return 0;
//        }
//
//    }

    @Override
    public Integer saveMainOrder(MainOrderDto mainOrderDto) throws SQLException, ClassNotFoundException{
        PreparedStatement ps = null;
        ResultSet rst = null;
        boolean isLocalConnection = false;
        Connection con = null;
        
        
        try{
        if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }
//            con.setAutoCommit(false);
            
            ps=con.prepareStatement("INSERT into pos_main_order_tb values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, mainOrderDto.getOrderDto().getOrderId());
            ps.setString(2, mainOrderDto.getOrderDto().getBillNo());
            ps.setInt(3, mainOrderDto.getOrderDto().getDiscountId());
            ps.setDouble(4, mainOrderDto.getOrderDto().getSubTotalPrice());
            ps.setDouble(5, mainOrderDto.getOrderDto().getTotalDiscountPrice());
            ps.setDouble(6, mainOrderDto.getOrderDto().getTotalOrderPrice());
            ps.setInt(7, mainOrderDto.getOrderDto().getPaymentType());
            ps.setInt(8, mainOrderDto.getOrderDto().getTableNo());
            ps.setDate(9, mainOrderDto.getOrderDto().getCreatedDate());
            ps.setDate(10, mainOrderDto.getOrderDto().getEditedDate());
            ps.setInt(11, mainOrderDto.getOrderDto().getAdult());
            ps.setInt(12, mainOrderDto.getOrderDto().getChild());
            ps.setString(13, mainOrderDto.getOrderDto().getRemark());
            ps.setInt(14, mainOrderDto.getOrderDto().getUserId());
            ps.setInt(15, mainOrderDto.getOrderDto().getEditedBy());
            ps.setInt(16, mainOrderDto.getOrderDto().getStatus());
            ps.setInt(17, LogInForm.userID);
            
            ps.executeUpdate();
            rst=ps.getGeneratedKeys();
            if (rst.next()) {
                // returen value from previous action
                mainOrderId = rst.getInt(1);
            }
            
            ArrayList<OrderDetailsDto> orderDetailsDtos=mainOrderDto.getOrderDetailsDtos();
            
            for(OrderDetailsDto dto:orderDetailsDtos){
                ps=con.prepareStatement("INSERT into pos_main_order_details_tb values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, dto.getOrderDetailsId());
                ps.setInt(2, mainOrderId);
                ps.setInt(3, dto.getItemId());
                ps.setInt(4, dto.getItemBarCode());
                ps.setInt(5, dto.getUnitTypeId());
                ps.setInt(6, dto.getPrinterTypeId());
                ps.setDouble(7, dto.getQty());
                ps.setDouble(8, dto.getPerItemPrice());
                ps.setDouble(9, dto.getTotalDiscountPrice());
                ps.setDouble(10, dto.getTotalItemPrice());
                ps.setString(11, dto.getRemark());
                ps.setInt(12, dto.getStatus());
                ps.setInt(13, LogInForm.userID);
                ps.executeUpdate();
                
                
                ps=con.prepareStatement("UPDATE pos_inv_stock_tb SET quantity = quantity-'"+dto.getQty()+"'WHERE item_id='"+dto.getItemId()+"'");
                ps.executeUpdate();
            }
            
            
         } catch (SQLException ex) {
            Log.info(MainOrderRepositoryImpl.class, "Error in Order " + ex);
            Log.error(MainOrderRepositoryImpl.class, "Error in Order " + ex);
            con.rollback();
            throw ex;
        } 
//        finally {
//
//            if (isLocalConnection && con != null) {
//                try {
//                    con.close();
//                } catch (Exception e) {
//                    return 0;
//                }
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (Exception e) {
//                    return 0;
//                }
//            }
//            if (rst != null) {
//                try {
//                    rst.close();
//                } catch (Exception e) {
//                    return 0;
//                }
//            }
//
//        }
        return mainOrderId;
    }

    @Override
    public ArrayList<OrderModel> getAllOrders() throws SQLException, ClassNotFoundException {
        Connection con = DBCon.getDatabaseConnection();
        String query = "SELECT * FROM pos_main_order_tb";

        ArrayList<OrderModel> orderDtos = new ArrayList<>();

        try (PreparedStatement pstmt = con.prepareStatement(query); 
             ResultSet rst = pstmt.executeQuery()) {
            while (rst.next()) {
                orderDtos.add(new OrderModel(
                        rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getDouble(6),
                    rst.getDouble(7),
                    rst.getDouble(8),
                    rst.getDouble(9),
                    rst.getInt(10),
                    rst.getInt(11),
                    rst.getDate(12),
                    rst.getDate(13),
                    rst.getInt(14),
                    rst.getInt(15),
                    rst.getString(16),
                    rst.getInt(17),
                    rst.getInt(18),
                    rst.getInt(19),
                    rst.getInt(20),
                    rst.getInt(21)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return orderDtos;
    }

    
}

