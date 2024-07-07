/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.dto.MainOrderDto;
import net.unical.pos.dto.OrderDto;
import net.unical.pos.repository.custom.MainOrderRepositoryCustom;
import net.unical.pos.repository.custom.OrderRepositoryCustom;

/**
 *
 * @author Sanjuka
 */
public class OrderRepo implements OrderRepositoryCustom{

    @Override
    public ArrayList<OrderDto> getAllOrders(String query) throws Exception{
        ResultSet rst=Statement.executeQuery("SELECT order_id,bill_no,discount_id,"
                + "sub_total_price,total_discount_price,payment_type_id,"
                + "table_id,created_Date,edited_Date,adult,child,remark,user_id,"
                + "edited_by,status,visible FROM pos_main_order_tb "+query);
        System.out.println(query);
        ArrayList<OrderDto> mainOrderDtos=new ArrayList<>();
        while(rst.next()){
            mainOrderDtos.add(new OrderDto(
                    rst.getInt("order_id"),
                    rst.getString("bill_no"),
                    rst.getInt("discount_id"), 
                    rst.getDouble("sub_total_price"), 
                    rst.getDouble("total_discount_price"),
                    rst.getDouble("total_discount_price"),
                    rst.getInt("payment_type_id"), 
                    rst.getInt("table_id"), 
                    rst.getDate("created_Date"), 
                    rst.getDate("edited_Date"), 
                    rst.getInt("adult"),
                    rst.getInt("child"), 
                    rst.getString("remark"),
                    rst.getInt("user_id"),
                    rst.getInt("edited_by"),
                    rst.getInt("status"), 
                    rst.getInt("visible")));
        }
        return mainOrderDtos;
    }
    
}
