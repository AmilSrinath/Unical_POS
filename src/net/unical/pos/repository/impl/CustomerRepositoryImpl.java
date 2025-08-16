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
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.configurations.Log;
import net.unical.pos.dbConnection.DBCon;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.dto.CustomerDto;
import net.unical.pos.model.CustomerModel;
import net.unical.pos.model.DeliveryOrder;
import net.unical.pos.model.TestModel;
import net.unical.pos.repository.custom.CustomerRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Sanjuka
 */
public class CustomerRepositoryImpl implements CustomerRepositoryCustom{

    @Override
    public boolean save(CustomerModel customerModel) throws Exception {
        return Statement.executeUpdate("Insert into pos_main_customer_tb (customer_id, customer_name, nic, address, phone_one, created_date, is_loyalty, loyalty_amount, status, user_id, visible) values(?,?,?,?,?,?,?,?,?,?,?)",
                customerModel.getCustomerId(),
                customerModel.getCustomerName(),
                customerModel.getNic(),
                customerModel.getAddress(),
                customerModel.getPhoneOne(),
                customerModel.getCreatedDate(),
                customerModel.getIsLoyalty(),
                customerModel.getLoyaltyPoints(),
                customerModel.getStatus(),
                LogInForm.userID,
                customerModel.getVisible()) > 0;
    }

    @Override
    public List<CustomerModel> getCustomer(String quary) {
        PreparedStatement ps = null;
        ResultSet rst = null;
        boolean isLocalConnection = false;
        Connection con = null;
        
        List<CustomerModel> customerModels = new Vector<>();
        
        try {
            
            if (con == null) {
                con = DBCon.getDatabaseConnection();
                isLocalConnection = true;
            }
            
            ps=con.prepareStatement("SELECT * FROM pos_main_customer_tb "+quary);
            
            rst=ps.executeQuery();
            
            while(rst.next()){
                CustomerModel customerModel=new CustomerModel();
                    customerModel.setCustomerId(rst.getInt("customer_id"));
                    customerModel.setCustomerName(rst.getString("customer_name"));
                    customerModel.setNic(rst.getString("nic"));
                    customerModel.setAddress(rst.getString("address"));
                    customerModel.setPhoneOne(rst.getInt("phone_one"));
                    customerModel.setPhoneTwo(rst.getInt("phone_two"));
                    customerModel.setCreatedDate(rst.getDate("created_Date"));
                    customerModel.setIsLoyalty(rst.getInt("is_loyalty"));
                    customerModel.setLoyaltyPoints(rst.getDouble("loyalty_amount"));
                    customerModel.setStatus(rst.getInt("status"));
                    customerModel.setUserId(rst.getInt("user_id"));
                    customerModel.setVisible(rst.getInt("visible"));
                    customerModel.setCustomerNumber(rst.getString("customer_number"));
                    customerModels.add(customerModel);
            }
            
        } catch (Exception e) {
            Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            Log.error(e,"get Customer error");
        }finally {

            if (isLocalConnection && con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                    Log.error(e,"get Customer error");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    Log.error(e,"get Customer error");
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception e) {
                    Logger.getLogger(DeliveryOrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
                    Log.error(e,"get Customer error");
                }
            }
        }
        return customerModels;
    }

    @Override
    public CustomerDto getCustomerByPhone(String quary) {
        String sql = "SELECT * FROM pos_main_customer_tb WHERE phone_one = ?";
        try {
            PreparedStatement pstm = DBCon.getDatabaseConnection().prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(quary));
            ResultSet rst = pstm.executeQuery();
            if(rst.next())  {
                return new CustomerDto(
                        rst.getInt("customer_id"),
                        rst.getString("customer_name"),
                        rst.getString("customer_number")
                );
            }
        } catch (Exception e) {
            Logger.getLogger(CustomerRepositoryImpl.class.getName()).log(Level.SEVERE,null, e);
            Log.error(e, "Customer fetching error");
        }
        return null;
    }
    
    
}
